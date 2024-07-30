package src.hrs.model;

import java.util.ArrayList;

public class HRS {
    private ArrayList<Hotel> hotelList;

    public HRS() {
        hotelList = new ArrayList<Hotel>();
    }

    public void addHotel(Hotel hotel) {
        hotelList.add(hotel);
    }

    public void removeHotel(Hotel hotel) {
        hotelList.remove(hotel);
    }

    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

    public Hotel getHotel(int index) {
        return hotelList.get(index);
    }

    public int getHotelAmount() {
        return hotelList.size();
    }

    public void createHotel(String hotelName, int roomAmount) {
        Hotel tempHotel = new Hotel(hotelName, roomAmount);
        addHotel(tempHotel);
    }

    public void createHotel(String hotelName, int roomAmount, double roomPrice) {
        Hotel tempHotel = new Hotel(hotelName, roomAmount, roomPrice);
        addHotel(tempHotel);
    }

    
}