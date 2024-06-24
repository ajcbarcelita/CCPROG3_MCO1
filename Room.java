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

    public void creationStatusOpen() {
        for (int i = 0; i < 31; i++) {
        status[i] = "X";
        }
    }

    public Boolean checkRoomAvailability(Date checkInDate, Date checkOutDate) {
        for (int i = checkInDate.getDay() - 1; i < checkOutDate.getDay() - 1; i++) {
            if (status[checkInDate.getDay()-1] != "X") {
                if (status[checkInDate.getDay()] != "X") {
                    return false;
                }
            }
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

    public String createReservation(String guestName, Date checkInDate, Date checkOutDate, Room roomBooked) {
        String reservationID = (roomBooked.getRoomName() + rd.nextInt(10000));
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
}
