public class Reservation {
    private String reservationID;
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room roomBooked;
    private double totalPrice;
    private double PricePerNight;

    public Reservation(String guestName, int checkInDate, int checkOutDate, Room RoomBooked, String reservationID) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomBooked = RoomBooked;
        this.reservationID = reservationID;
        this.PricePerNight = roomBooked.getRoomPrice();
        this.totalPrice = calculateTotalPrice();
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

    public double calculateTotalPrice() {
        int days = (checkOutDate - checkInDate);
        return days * PricePerNight;
    } 

    public void setRoomStatus() {
        for (int i = checkInDate - 1; i <= checkOutDate - 1;i++ ) {
            roomBooked.setRoomAvailability(i, reservationID);
        }
    }

    public void displayReservationDetails() {
        System.out.println("Reservation ID: " + reservationID);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Name: " + roomBooked.getRoomName());
        System.out.println("Check-in: "+ checkInDate +" Check-out: "+ checkOutDate);
        System.out.println("Total Price of Booking: "+ totalPrice);
        System.out.println("Price per Night: "+ roomBooked.getRoomPrice());
        System.out.println("Nights Stayed: "+ (checkOutDate-checkInDate));
    }
}
