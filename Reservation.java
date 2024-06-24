import java.util.*; //for RNG

public class Reservation {
    private String reservationID;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private Room roomBooked;
    private double totalPrice;
    private double PricePerNight;

    public Reservation(String guestName, Date checkInDate, Date checkOutDate, Room RoomBooked, String reservationID) {
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

    public Date getCheckInDate() {
        return checkInDate;
    }
    
    public Date getCheckOutDate() {
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
        int days = (checkOutDate.getDay() - checkInDate.getDay());
        return days * PricePerNight;
    } 

    public void setRoomStatus() {
        for (int i = checkInDate.getDay() - 1; i <= checkOutDate.getDay() - 1;i++ ) {
            roomBooked.setRoomAvailability(i, reservationID);
        }
    }

}
