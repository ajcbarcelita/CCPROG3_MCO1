package src.hrs.controller;

import src.hrs.model.Hotel;
import src.hrs.view.HotelView;

public class HotelController {
    private Hotel hotel;
    private HotelView view;

    public HotelController(Hotel hotel, HotelView view) {
        this.hotel = hotel;
        this.view = view;
    }

    public void setHotelName(String name) {
        hotel.setHotelName(name);
    }

    public String getHotelName() {
        return hotel.getHotelName();
    }

    public void addRooms(int amount) {
        hotel.addRooms(amount);
    }

    public void removeRoom(int index) {
        hotel.removeRoom(index);
    }

    public void displayReservations() {
        view.displayReservations(hotel);
    }

    public void displayRooms() {
        view.displayRooms(hotel);
    }

    // Add more methods to handle other user actions as needed
}
