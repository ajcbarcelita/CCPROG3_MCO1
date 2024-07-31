package src.hrs.model;

public class StandardRoom extends Room {
    private final String roomType = "Standard";

    public StandardRoom(String roomName, double roomPrice) {
        super(roomName, roomPrice);
    }

    public String getRoomType() {
        return this.roomType;
    }
}
