package src.hrs.controller;

import src.hrs.model.Hotel;
import src.hrs.view.HotelView;
import src.hrs.model.Room;

public class HotelController {
    private Hotel hotel;
    private HotelView view;

    public HotelController(Hotel hotel) {
        this.hotel = hotel;
        this.view = new HotelView();
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

    public int findRoom(String roomName) {
        return hotel.findRoom(roomName);
    }

    public Room getRoom(int index) {
        return hotel.getRoom(index);
    }

    public double getRoomPrice() {
        return hotel.getRoomPrice();
    }

    public boolean isHotelEmpty() {
        return hotel.isHotelEmpty();
    }

    public int findRes(String resID) {
        return hotel.findRes(resID);
    }

    public int getRoomAmount() {
        return hotel.getRoomAmount();
    }

    public int countEmptyRooms() {
        return hotel.countEmptyRooms();
    }

    public void setRoomPricePerNight(double price) {
        hotel.setRoomPricePerNight(price);
    }

    public void removeReservation(String resID) {
        hotel.removeReservation(resID);
    }

    public int AvailableRoomIndex(int date1, int date2) {
        return hotel.AvailableRoomIndex(date1, date2);
    }

    // Add more methods to handle other user actions as needed
}
