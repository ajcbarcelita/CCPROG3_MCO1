package src.hrs.model;

import java.util.*;


/**
 * Represents a hotel with a list of rooms.
 * 
 * A hotel has a name, a prefix for its rooms, and a list of rooms. Each room has a unique name, a price per night, and a status (available or booked).
 * 
 * The hotel provides methods to create rooms, add or remove rooms, change the price per night, and manage reservations.
 */ 
public class Hotel {
    private String hotelName;
    private ArrayList<Room> roomList;
    private char roomPrefix;
    private double roomPricePerNight = 1299.00;

    /** Creates a new hotel with the given name, room amount, and prefix.
     * 
     * @param hotelName the name of the hotel
     * @param prefix the prefix for the room names
     */
    public Hotel(String hotelName, int roomAmount) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomPrefix = hotelName.charAt(0);
        addRooms(roomAmount);
    }

    /**
     * Creates a new hotel with the given name, room amount, prefix, and price per night.
     * 
     * @param hotelName the name of the hotel
     * @param roomAmount the initial number of rooms
     * @param prefix the prefix for the room names
     * @param pricePerNight the price per night for each room
     */
    public Hotel(String hotelName, int roomAmount,double price) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomPrefix = hotelName.charAt(0);
        this.roomPricePerNight = price;
        addRooms(roomAmount);;
    }

    /**
     * Gets the name of the Hotel.
     * 
     * @return the hotel name
     */
    public String getHotelName() {
        return this.hotelName;
    }

    /**
     * Gets the list of rooms in the Hotel.
     * 
     * @return the list of rooms
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    /**
     * Gets the amount of rooms in the Hotel.
     * 
     * @return the amount of rooms
     */
    public int getRoomAmount() {
        return roomList.size();
    }

    /**
     * Gets the room in the specified index of the List.
     * 
     * @param index the index of the room
     * @return the room
     */
    public Room getRoom(int index) {
        if (index < getRoomAmount() || index >= 0){
            return roomList.get(index);
        }
        else{
            return null;
        }
    }

    /**
     * Gets the price of the room per night.
     * 
     * @return room's price per night
     */
    public double getRoomPrice() {
        return roomPricePerNight;
    }

    /**
     * Creates the amount of rooms specified when the Hotel is Instantiated. 
     */

    /**
     * Creates the rooms for this hotel.
     * 
     * This method creates a new room for each number in the range from 1 to roomAmount, using the roomPrefix and roomPricePerNight fields to initialize the room name and price.
     * The rooms are added to the roomList field. 
     */
    public void createRooms() {
        for (int i = 1; i <= getRoomAmount(); i++) {
            Room tempRoom = new Room((roomPrefix + String.valueOf(i)), roomPricePerNight);
            roomList.add(tempRoom);
        }
    }

    /**
     * Counts the number of empty rooms in this hotel.
     * 
     * @return the number of empty rooms in this hotel
     */ 
    public int countEmptyRooms() {
        int ctr = 0;
        for (int i = 0; i < getRoomAmount(); i++) {
            if (roomList.get(i).isRoomEmpty() == true){
                ctr++;
            }
        }
        return ctr;
    }

    /**
     * Adds rooms to this hotel.
     * 
     * This method prompts the user to enter the number of rooms to add, and then adds that many rooms to the hotel.
     * The rooms are given names based on the roomPrefix field and the current size of the roomList field.
     */
    public void addRooms(int amount) {
        if (getRoomAmount() == 50 || amount+getRoomAmount()>50) {
            System.out.println("Max Room Amount Reached.");
        }
        else{
            for (int i = getRoomAmount(); i < amount; i++) {
                Room tempRoom = new Room(roomPrefix + String.valueOf(i), roomPricePerNight);
                roomList.add(tempRoom);
            }
        }
    }

    /**
     * 
     * Removes rooms from this hotel.
     * 
     * This method removes up to amount empty rooms from the hotel, starting from the last room.
     * If there are not enough empty rooms to remove, the method does nothing. 
     */
    public void removeRoom(int i) {
        if(roomList.get(i).isRoomEmpty()) {
            roomList.remove(i);
        }
        else{
            System.out.println("[!][!] Room not Empty [!][!]");
        }
    }

    /**
     * Removes a reservation from a room in this hotel.
     * 
     * This method first displays all reservations in the hotel.
     * Then it prompts the user to select a reservation to remove, and removes that reservation from the corresponding room. 
     */
    public void removeReservation(String resID) {
        getRoom(findRes(resID)).removeReservation(resID);
    }

    /**
     * Finds the index of a room with the given name in this hotel.
     * 
     * @param roomName the name of the room to find
     * @return the index of the room with the given name, or -1 if no such room exists
     */
    public int findRoom(String roomName) {
        for (int i = 0; i < getRoomAmount(); i++) {
            if (getRoom(i).getRoomName().equalsIgnoreCase(roomName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets the name of this hotel.
     * 
     * @param hotelName the new name of the hotel
     */ 
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Checks whether this hotel is empty.
     * 
     * @return true if this hotel has no non-empty rooms, and false otherwise
     */
    public Boolean isHotelEmpty() {
        for (int i = 0; i < getRoomAmount(); i++) {
            if (roomList.get(i).isRoomEmpty() == false){
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the price per night for all rooms in this hotel.
     * 
     * @param amount the new price per night for all rooms in this hotel
     */
    public void setRoomPricePerNight(double amount) {
        if (amount >= 100) {
            this.roomPricePerNight = amount;
            for (int i = 0; i < getRoomAmount(); i++) {
                roomList.get(i).setRoomPrice(amount);
            }
        }
    }

    /**
     * Finds the index of the first available room for the given dates.
     * 
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return the index of the first available room, or -1 if no room is available */
    public int AvailableRoomIndex(int checkInDate, int checkOutDate) {
        if (checkInDate < checkOutDate) {
            for (int i = 0; i < getRoomAmount(); i++) {
                if(roomList.get(i).checkRoomAvailability(checkInDate, checkOutDate)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Calculates the estimated earnings of this hotel.
     * 
     * @return the estimated earnings of this hotel
     */
    public double getEstimatedEarnings() {
        double total = 0;
        for (Room room : roomList) {
            total += room.getEstimatedEarnings();
        }

        return total;
    }

    /**
     * Finds the index of the room with a reservation matching the given ID.
     * 
     * @param reservationID the ID of the reservation to find
     * @return the index of the room with the matching reservation, or -1 if no such reservation exists
     */
    public int findRes(String reservationID) {
        for (int i = 0; i < getRoomAmount(); i++) {
            for (int j = 0; j < getRoom(i).getReservationAmount(); j++) {
                if (getRoom(i).getReservationList().get(j).getReservationID().equalsIgnoreCase(reservationID)){
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Counts the number of empty rooms on a given day.
     * 
     * @param day the day to check
     * @return the number of empty rooms on the given day 
     */
    public int checkEmptyRooms(int day) {
        int ctr = 0;
        if (day > 0 || day < 32) {
            for (int i = 0; i < getRoomAmount(); i++) {
                if(roomList.get(i).getStatus(day-1).equalsIgnoreCase("X")) {
                    ctr++;
                }
            }
            return ctr;
        }
        return 0;
    }
}
