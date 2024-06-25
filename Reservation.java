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

    /**
     * Creates a new Reservation given guest name, check-in and check-out dates, roomBooked, and a reservationID.
     * 
     * @param guestName
     * @param checkInDate
     * @param checkOutDate
     * @param RoomBooked
     * @param reservationID
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room RoomBooked, String reservationID) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomBooked = RoomBooked;
        this.reservationID = reservationID;
        this.PricePerNight = roomBooked.getRoomPrice();
        this.totalPrice = calculateTotalPrice();
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

    /**
     * Calculates the total price of the reservation based on the number of nights stayed.
     * 
     * @return the total price
     */
    public double calculateTotalPrice() {
        int days = (checkOutDate - checkInDate);
        return days * PricePerNight;
    } 

    /**
     * Sets the room status to booked for the duration of the reservation.
     */
    public void setRoomStatus() {
        for (int i = checkInDate - 1; i <= checkOutDate - 1;i++ ) {
            roomBooked.setRoomAvailability(i, reservationID);
        }
    }

    /**
     * Displays the details of the reservation.
     */
    public void displayReservationDetails() {
        System.out.println("\tReservation ID: " + reservationID);
        System.out.println("\tGuest Name: " + guestName);
        System.out.println("\tRoom Name: " + roomBooked.getRoomName());
        System.out.println("\tCheck-in: "+ checkInDate +" Check-out: "+ checkOutDate);
        System.out.println("\tTotal Price of Booking: "+ totalPrice);
        System.out.println("\tPrice per Night: "+ roomBooked.getRoomPrice());
        System.out.println("\tNights Stayed: "+ (checkOutDate-checkInDate));
    }
}