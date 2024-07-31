package src.hrs.model;

/**
 * Represents a reservation in the hotel system.
 */ 
public class Reservation {
    private String reservationID;
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room roomBooked;
    private double totalPrice;
    private double PricePerNight;
    private String discountCode;
    private double discountPercentage;
    private boolean isValidDiscount;


    /**
     * Creates a new Reservation given guest name, check-in and check-out dates, roomBooked, and a reservationID.
     * 
     * @param guestName
     * @param checkInDate
     * @param checkOutDate
     * @param RoomBooked
     * @param reservationID
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room RoomBooked, String reservationID, String discountCode) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomBooked = RoomBooked;
        this.reservationID = reservationID;
        this.PricePerNight = roomBooked.getRoomPrice();
        this.totalPrice = calculateTotalPrice();
        this.discountCode = discountCode;
        this.discountPercentage = isDiscountCodeValid(discountCode);
        this.isValidDiscount = isConditionMet(discountCode);
    }

    /**
     * Returns the unique ID of the reservation.
     * 
     * @return the reservation ID
     */
    public String getReservationID() {
        return reservationID;
    }

    /**
     * Returns the name of the guest making the reservation.
     * 
     * @return the guest name
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Returns the check-in date of the reservation.
     * 
     * @return the check-in date
     */
    public int getCheckInDate() {
        return checkInDate;
    }

    /**
     * Returns the check-out date of the reservation.
     * 
     * @return the check-out date
     */
    public int getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Returns the price per night of the room.
     * 
     * @return the price per night
     */
    public double getPricePerNight() {
        return PricePerNight;
    }

    /**
     * Returns the room booked for the reservation.
     * 
     * @return the room booked
     */
    public Room getRoomBooked() {
        return roomBooked;
    }

    /**
     * Returns the total price of the reservation.
     * 
     * @return the total price */
    public double getTotalPrice() {
        return totalPrice;
    }
    public boolean getIsValidDiscount() {
        return isValidDiscount;
    }

    /**
     * Calculates the total price of the reservation based on the number of nights stayed.
     * 
     * @return the total price
     */
    public double calculateTotalPrice() {
        int days = (checkOutDate - checkInDate);
        double totalPrice = days * PricePerNight;

        if (this.discountCode.equals("STAY4_GET1") && isConditionMet(this.discountCode)) {
            totalPrice -= PricePerNight;
        }

        double discountAmount = totalPrice * discountPercentage;
        return totalPrice - discountAmount;
    } 

    public void setRoomStatus() {
        for (int i = checkInDate - 1; i <= checkOutDate - 1;i++ ) {
            roomBooked.setRoomAvailability(i, reservationID);
        }
    }

    private double isDiscountCodeValid(String discountCode) {
        switch (discountCode) {
            case "I_WORK_HERE":
                return 0.10;
            case "STAY4_GET1":
                return 0.00; //special condition to be checked again in isConditionMet
            case "PAYDAY":
                return 0.07;
        }

        return 0.0;
    }

    public boolean isConditionMet(String discountCode) {
        switch (discountCode) {
            case "I_WORK_HERE":
                return true;
            case "STAY4_GET1":
                int dayRange = checkOutDate - checkInDate;
                return dayRange >= 5;
            case "PAYDAY":
                return (checkInDate <= 15 && checkOutDate > 15) || (checkInDate <= 30 && checkOutDate > 30);
        }
        return false;
    }
}
