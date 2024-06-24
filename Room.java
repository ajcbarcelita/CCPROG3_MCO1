import java.util.*;
import java.lang.*;

public class Room {
    private String roomName;
    private double roomPrice;
    private ArrayList<Reservation> reservationList; 
    private Boolean[] status = new Boolean[31]; // true if available -- false if not

    private Room() {
        //private constructor to prevent instantiation w/o initialization
    }

    public Room(String roomName, double roomPrice) {
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.reservationList = new ArrayList<Reservation>();
        creationStatusOpen();

    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getRoomPrice() {
        return this.roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void creationStatusOpen() {
        for (int i = 0; i < 31; i++) {
        status[i] = true;
        }
    }

    public Boolean checkRoomAvailability(Date checkInDate, Date checkOutDate) {
        for (int i = checkInDate.getDay() - 1; i < checkOutDate.getDay() - 2; i++) {
            if (status[i] == false) {
                return false;
            }
        }
        return true;
    }

    public Boolean isRoomEmpty() {
        if (reservationList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
