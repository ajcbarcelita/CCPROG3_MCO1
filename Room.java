import java.util.*;
import java.lang.*;

public class Room {
    private String roomName;
    private double roomPrice;
    private Hotel hotel;

    private Room() {
        //private constructor to prevent instantiation w/o initialization
    }

    public Room(String roomName, double roomPrice, Hotel hotel) {
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.hotel = hotel;
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

    public Hotel getHotel() {
        return this.hotel;
    }
}
