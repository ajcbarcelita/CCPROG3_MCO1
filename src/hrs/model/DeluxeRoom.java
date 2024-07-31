package src.hrs.model;

public class DeluxeRoom extends Room {
    private final String roomType = "Deluxe";

    public DeluxeRoom(String roomName, double roomPrice) {
        super(roomName, roomPrice + (roomPrice * 0.20)); //increase by 20%
    }

    public String getRoomType() {
        return this.roomType;
    }
}
