import java.util.*; //for arraylist
import java.lang.*; //for string
import java.lang.reflect.Array;

public class Hotel {
    private String hotelName;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList; //honestly not sure if we need this

    private Hotel() {
        //private constructor to prevent instantiation w/o initialization
    }

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        reservationList = new ArrayList<Reservation>();
    }

    //getter
    public String getHotelName() {
        return this.hotelName;
    }

    //room management methods - note to add a confirmation of some sorts to confirm action
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    // public boolean addRoom(Room room);
    // public boolean removeRoom (Room room);

    public Room findRoom(String roomName) {
        for (Room room : roomList) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    //reservation management methods - note to add a confirmation of some sorts to confirm action
    public ArrayList<Reservation> getReservationList() {
        return this.reservationList;
    }

    // public boolean addReservation(Reservation reservation); //might change to boolean
    // public boolean removeReservation(Reservation reservation);

    //other methods
    private void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void changeHotelName(String hotelName) {
        //
    }
}