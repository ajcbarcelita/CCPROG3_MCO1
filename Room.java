import java.util.*;
public class Room {
    Random rd = new Random();
    private String roomName;
    private double roomPrice;
    private ArrayList<Reservation> reservationList; 
    private String[] status = new String[31];

    public Room(String roomName, double roomPrice) {
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
        Boolean returns = true;
        for (int i = checkInDate; i < checkOutDate - 1; i++) {
            if (status[checkInDate-1] != "X") {
                if (status[checkInDate] == "X") {
                    returns = true;
                }
            }
            if (status[i] != "X") {
                returns = false;
            }
        }
        return returns;
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

    public String createReservation(String guestName, int checkInDate, int checkOutDate, Room roomBooked) {
        int idNum = rd.nextInt(9000) + 1000;
        String reservationID = (roomBooked.getRoomName() + idNum);
        Reservation tempRes = new Reservation(guestName, checkInDate, checkOutDate, roomBooked, reservationID);
        tempRes.setRoomStatus();
        reservationList.add(tempRes);
        return reservationID;
    }

    public double getEstimatedEarnings() {
        double total = 0;
        for (Reservation reservation : reservationList) {
            total += reservation.getTotalPrice();
        }
        return total;
    }

    public void displayReservations() {
        for (int i = 0; i < reservationList.size(); i++) {
            System.out.println((i+1)+ " Reservation ID: " + reservationList.get(i).getReservationID());
        }
    }

    public void displayRoomStatus() {
        for (int i = 0; i < 31; i++) {
            System.out.printf("%02d ", (i + 1));
            if (status[i].equals("X")) {
                System.out.print("X ");
            } else {
                System.out.print("B ");
            }
            if ((i + 1) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
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
    }
}
