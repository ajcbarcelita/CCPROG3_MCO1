package src.hrs.model;

public class Reservation {
    private Hotel hotel;
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

    public Reservation(Hotel hotel, String guestName, int checkInDate, int checkOutDate, Room RoomBooked, String reservationID, String discountCode) {
        this.hotel = hotel;
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

    public String getReservationID() {
        return reservationID;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getCheckInDate() {
        return checkInDate;
    }

    public int getCheckOutDate() {
        return checkOutDate;
    }

    public double getPricePerNight() {
        return PricePerNight;
    }

    public Room getRoomBooked() {
        return roomBooked;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean getIsValidDiscount() {
        return isValidDiscount;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (int day = checkInDate; day < checkOutDate; day++) {
            double modifier = hotel.getPriceModifier(day);
            totalPrice += (PricePerNight * modifier);
        }

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
