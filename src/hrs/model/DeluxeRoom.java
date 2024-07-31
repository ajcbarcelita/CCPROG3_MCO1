package src.hrs.model;

public class DeluxeRoom extends Room {
    public DeluxeRoom(Hotel hotel, String roomName, double roomPrice) {
        super(hotel, roomName, roomPrice + (roomPrice * 0.20)); //increase by 20%
    }
}
