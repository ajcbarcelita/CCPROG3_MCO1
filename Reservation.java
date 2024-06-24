import java.util.*; //for RNG

public class Reservation {
    Random rd = new Random();
    private String reservationID;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private Room roomBooked;

    public Reservation(String guestName, Date checkInDate, Date CheckOutDate, Room RoomBooked) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomBooked = roomBooked;
        reservationID = (roomBooked.getRoomName() + rd.nextInt(10000));
    }
}
