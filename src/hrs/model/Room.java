package src.hrs.model;

import java.util.*;

public class Room {
    private Random rd;
    private String roomName;
    private double roomPrice;
    private ArrayList<Reservation> reservationList; 
    private String[] status = new String[31];

    /**
     * Constructor for a new room.
     * 
     * @param roomName the name of the room
     * @param roomPrice the price of the room per night
     */
    public Room(String roomName, double roomPrice) {
        this.rd = new Random();
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.reservationList = new ArrayList<Reservation>();
        creationStatusOpen();
    }

    /**
     * Returns the name of the room.
     * 
     * @return the room name
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * Sets the name of the room.
     * 
     * @param roomName the new room name
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Returns the price of the room per night.
     * 
     * @return the room price
     */
    public double getRoomPrice() {
        return this.roomPrice;
    }

    /**
     * Sets the price of the room per night.
     * 
     * @param roomPrice the new room price
     */
    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    /**
     * Returns the number of reservations for the room.
     * 
     * @return the number of reservations
     */
    public int getReservationAmount() {
        return reservationList.size();
    }

    /**
     * Returns the list of reservations for the room.
     * 
     * @return the reservation list
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * Returns the status of the room for a given day.
     * 
     * @param index the day of the month (1-31)
     * @return the status of the room (X for available, B for booked)
     */
    public String getStatus(int index) {
        return status[index];
    }

    /**
     * Initializes the status of the room to available for all days of the month.
     */
    public void creationStatusOpen() {
        for (int i = 0; i < 31; i++) {
        status[i] = "X";
        }
    }

    /**
     * Checks if the room is available for a given date range.
     * 
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return true if the room is available, false otherwise
     */
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

    /**
     * Checks if the room has any reservations.
     * 
     * @return true if the room is empty, false otherwise
     */
    public Boolean isRoomEmpty() {
        if (reservationList.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the status of the room for a given day.
     * 
     * @param index the day of the month (1-31)
     * @param id the reservation ID
     */
    public void setRoomAvailability(int index, String id) {
        status[index] = id;
    }

    /**
     * Creates a new reservation for the room.
     * 
     * @param guestName the name of the guest
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @param roomBooked the room being booked
     * @return the reservation ID
     */
    public String createReservation(String guestName, int checkInDate, int checkOutDate, Room roomBooked, String discountCode) {
        int idNum = rd.nextInt(9000) + 1000;
        String reservationID = (roomBooked.getRoomName() + idNum);
        
        if (checkInDate < checkOutDate) {
            Reservation tempRes = new Reservation(guestName, checkInDate, checkOutDate, roomBooked, reservationID, discountCode);
            tempRes.setRoomStatus();
            reservationList.add(tempRes);
            return reservationID;
        }
        else {return null;}
    }

    /**
     * Returns the estimated earnings for the room.
     * 
     * @return the estimated earnings */
    public double getEstimatedEarnings() {
        double total = 0;
        for (Reservation reservation : reservationList) {
            total += reservation.getTotalPrice();
        }
        return total;
    }

    /**
     * Finds a reservation by ID.
     * 
     * @param reservationID the reservation ID
     * @return the index of the reservation, or -1 if not found
     */
    public int findRes(String reservationID) {
        for (int i = 0; i < getReservationAmount(); i++) {
            if (reservationList.get(i).getReservationID().equalsIgnoreCase(reservationID)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes a reservation from the room.
     * 
     * @param reservationID the reservation ID
     */
    public void removeReservation(String reservationID) {
        reservationList.remove(findRes(reservationID));
        for(int i = 0; i< 31; i++) {
            if (status[i].equals(reservationID)) {
                status[i] = "X";
            }
        }
    }
}
