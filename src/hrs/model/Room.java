package src.hrs.model;

import java.util.*;

public class Room {
    private Random rd;
    private Hotel hotel;
    private String roomName;
    private double roomPrice;
    private ArrayList<Reservation> reservationList; 
    private String[] status = new String[31];
    private String roomType;

    public Room(Hotel hotel, String roomName, double roomPrice) {
        this.rd = new Random();
        this.hotel = hotel;
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.reservationList = new ArrayList<Reservation>();
        creationStatusOpen();
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getRoomPrice() {
        return this.roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getReservationAmount() {
        return reservationList.size();
    }
     
    public String getRoomType() {
        return this.roomType;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public String getStatus(int index) {
        return status[index];
    }

    public void creationStatusOpen() {
        for (int i = 0; i < 31; i++) {
        status[i] = "X";
        }
    }

    public Boolean checkRoomAvailability(int checkInDate, int checkOutDate) {
        if (status[checkInDate-1] != "X") {
            return false;
        }
        for (int i = checkInDate; i < checkOutDate; i++) {
            if (status[i] != "X") {
                return false;
            }
        }
        return true;
    }

    public Boolean isRoomEmpty() {
        if (reservationList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setRoomAvailability(int index, String id) {
        status[index] = id;
    }

    public String createReservation(String guestName, int checkInDate, int checkOutDate, Room roomBooked, String discountCode) {
        int idNum = rd.nextInt(9000) + 1000;
        String reservationID = (roomBooked.getRoomName() + idNum);
        
        if (checkInDate < checkOutDate) {
            Reservation tempRes = new Reservation(hotel, guestName, checkInDate, checkOutDate, roomBooked, reservationID, discountCode);
            tempRes.setRoomStatus();
            reservationList.add(tempRes);
            return reservationID;
        }
        else {return null;}
    }

    public double getEstimatedEarnings() {
        double total = 0;
        for (Reservation reservation : reservationList) {
            total += reservation.getTotalPrice();
        }
        return total;
    }

    public int findRes(String reservationID) {
        for (int i = 0; i < getReservationAmount(); i++) {
            if (reservationList.get(i).getReservationID().equalsIgnoreCase(reservationID)){
                return i;
            }
        }
        return -1;
    }

    public void removeReservation(String reservationID) {
        reservationList.remove(findRes(reservationID));
        for(int i = 0; i< 31; i++) {
            if (status[i].equals(reservationID)) {
                status[i] = "X";
            }
        }
    }
}
