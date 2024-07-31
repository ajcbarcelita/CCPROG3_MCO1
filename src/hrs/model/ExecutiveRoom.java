package src.hrs.model;

public class ExecutiveRoom extends Room {
    public ExecutiveRoom(Hotel hotel, String roomName, double roomPrice) {
        super(hotel, roomName, ((roomPrice + (roomPrice * 0.35)))); //increase by 35%
    }
}
