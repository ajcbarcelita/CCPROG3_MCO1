package src.hrs.controller;

import src.hrs.model.Room;
import src.hrs.view.RoomView;

public class RoomController {
    private RoomView roomView;

    public RoomController() {
        this.roomView = new RoomView();
    }

    public void displayReservations(Room room) {
        roomView.displayReservations(room);
    }

    public void displayRoomStatus(Room room) {
        roomView.displayRoomStatus(room);
    }
}
