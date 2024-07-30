package src.hrs.view;

import java.util.ArrayList;

import src.hrs.controller.RoomController;
import src.hrs.model.Hotel;

public class HRSView {
    public void displayHotels(ArrayList<Hotel> hotelList) {
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println("\t"+(i+1)+ " Hotel Name: " + hotelList.get(i).getHotelName());
        }
    }

    /**
     * Displays high-level information about a Hotel, including its name, Room amount, and Estimated Earnings for the Month.
     * 
     * @param hotelIndex the index of the hotel to display information for
     */
    public void highLevelInformation(Hotel hotel)
    {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t Hotel Name: " + hotel.getHotelName());
        System.out.println("\t Total Number of Rooms: " + hotel.getRoomAmount());
        System.out.println("\t Total Estimated Earnings : " + hotel.getEstimatedEarnings());
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void lowLevelRoomAvailability(Hotel hotel, int date) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t Total Number of Rooms: " + hotel.getRoomAmount());
        System.out.println("\t Number of Rooms Empty on " + date + ": " + hotel.checkEmptyRooms(date));
        System.out.println("\t Number of Rooms Booked on " + date + ": "+ (hotel.getRoomAmount() - hotel.checkEmptyRooms(date)));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Press Enter to continue");
    }

    public void lowLevelRoomInformation(RoomController roomController) {
        System.out.println("--------------------------------------------------------------------------------");   
        System.out.println("Room: " + roomController.getRoomName() + "\tPrice per night: " + roomController.getRoomPrice());
        System.out.println("Room Availability: (B = Booked) (X = Available)");
        roomController.displayRoomStatus();
        System.out.println("--------------------------------------------------------------------------------");
    }

}
