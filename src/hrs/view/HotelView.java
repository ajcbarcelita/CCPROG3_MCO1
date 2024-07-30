package src.hrs.view;

import src.hrs.model.Hotel;
import src.hrs.model.Room;

public class HotelView{
    public void displayReservations(Hotel hotel) {
        RoomView roomView = new RoomView();
        for (Room room : hotel.getRoomList()) {
            roomView.displayReservations(room);
        }
    }

    public void displayRooms(Hotel hotel) {
        for (Room room : hotel.getRoomList()) {
            System.out.println("\tRoom Number: " + room.getRoomName());
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
