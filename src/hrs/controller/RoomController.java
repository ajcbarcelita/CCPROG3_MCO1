package src.hrs.controller;

import src.hrs.model.Room;
import src.hrs.view.RoomView;

public class RoomController {
    private Room room;
    private RoomView roomView;

    public RoomController(Room room) {
        this.room = room;
        this.roomView = new RoomView();
    }

    public void displayReservations() {
        roomView.displayReservations(room);
    }

    public void displayRoomStatus() {
        roomView.displayRoomStatus(room);
    }

    public String getRoomName() {
        return room.getRoomName();
    }

    public double getRoomPrice() {
        return room.getRoomPrice();
    }
}
