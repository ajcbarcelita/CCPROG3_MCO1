package src.hrs.model;

public class ExecutiveRoom extends Room {
    private final String roomType = "Executive";

    public ExecutiveRoom(String roomName, double roomPrice) {
        super(roomName, ((roomPrice + (roomPrice * 0.35)))); //increase by 35%
    }

    public String getRoomType() {
        return this.roomType;
    }
}
