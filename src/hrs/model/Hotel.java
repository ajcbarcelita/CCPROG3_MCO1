package src.hrs.model;

import java.util.*;

public class Hotel {
    private String hotelName;
    private ArrayList<Room> roomList;
    private char roomPrefix;
    private double roomPricePerNight = 1299.00;
    private double[] datePriceModifiers;

    public Hotel(String hotelName, int roomAmount) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomPrefix = hotelName.charAt(0);
        addRooms(roomAmount);
        datePriceModifiers = new double[31];
        for (int i = 0; i < 31; i++) {
            datePriceModifiers[i] = 1.0;
        }
    }

    public Hotel(String hotelName, int roomAmount,double price) {
        this.hotelName = hotelName;
        roomList = new ArrayList<Room>();
        this.roomPrefix = hotelName.charAt(0);
        this.roomPricePerNight = price;
        addRooms(roomAmount);;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    public int getRoomAmount() {
        return roomList.size();
    }

    public Room getRoom(int index) {
        if (index < getRoomAmount() || index >= 0){
            return roomList.get(index);
        }
        else{
            return null;
        }
    }

    public double getRoomPrice() {
        return roomPricePerNight;
    }

    public void createRooms() {
        for (int i = 1; i <= getRoomAmount(); i++) {
            Room tempRoom = new Room(this, (roomPrefix + String.valueOf(i)), roomPricePerNight);
            roomList.add(tempRoom);
        }
    }

    public int countEmptyRooms() {
        int ctr = 0;
        for (int i = 0; i < getRoomAmount(); i++) {
            if (roomList.get(i).isRoomEmpty() == true){
                ctr++;
            }
        }
        return ctr;
    }

    public void addRooms(int amount) {
        if (getRoomAmount() == 50 || amount+getRoomAmount()>50) {
            System.out.println("Max Room Amount Reached.");
        }
        else{
            for (int i = getRoomAmount(); i < amount; i++) {
                Room tempRoom = new Room(this, roomPrefix + String.valueOf(i), roomPricePerNight);
                roomList.add(tempRoom);
            }
        }
    }

    public void removeRoom(int i) {
        if(roomList.get(i).isRoomEmpty()) {
            roomList.remove(i);
        }
        else{
            System.out.println("[!][!] Room not Empty [!][!]");
        }
    }

    public void removeReservation(String resID) {
        getRoom(findRes(resID)).removeReservation(resID);
    }

    public int findRoom(String roomName) {
        for (int i = 0; i < getRoomAmount(); i++) {
            if (getRoom(i).getRoomName().equalsIgnoreCase(roomName)) {
                return i;
            }
        }
        return -1;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Boolean isHotelEmpty() {
        for (int i = 0; i < getRoomAmount(); i++) {
            if (roomList.get(i).isRoomEmpty() == false){
                return false;
            }
        }
        return true;
    }

    public void setRoomPricePerNight(double amount) {
        if (amount >= 100) {
            this.roomPricePerNight = amount;
            for (int i = 0; i < getRoomAmount(); i++) {
                roomList.get(i).setRoomPrice(amount);
            }
        }
    }

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

    public double getEstimatedEarnings() {
        double total = 0;
        for (Room room : roomList) {
            total += room.getEstimatedEarnings();
        }

        return total;
    }

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

    public void setDatePriceModifier(int day, double modifier) {
        if (day > 0 || day < 32) {
            if (modifier >= 0.5 || modifier <= 1.5) {
                datePriceModifiers[day - 1] = modifier;
            } else {
                throw new IllegalArgumentException("Invalid modifier. Modifier must be between 0.5 and 1.5, inclusive.");
            }
        } else {
            throw new IllegalArgumentException("Invalid day. Day must be between 1 and 31, inclusive.");
        }
    }

    public double getPriceModifier(int day) {
        if (day > 0 || day < 32) {
            return datePriceModifiers[day - 1];
        } else {
            throw new IllegalArgumentException("Invalid day. Day must be between 1 and 31, inclusive.");
        }
    }
}
