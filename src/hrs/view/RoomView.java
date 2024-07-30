package src.hrs.view;

import java.util.*;
import src.hrs.model.Room;
import src.hrs.model.Reservation;

public class RoomView {
    
    
    /**
     * Displays the reservations for the room
     */
    public void displayReservations(Room room) {
        ArrayList<Reservation> reservationList = room.getReservationList();
        for (int i = 0; i < reservationList.size(); i++) {
            System.out.println("Reservation ID: " + reservationList.get(i).getReservationID() +" Guest: " + reservationList.get(i).getGuestName());
        }
    }

    /**
     * Displays the status of the room for each day of the month.
     */
    public void displayRoomStatus(Room room) {
        for (int i = 0; i < 31; i++) {
            System.out.printf("%02d ", (i + 1));
            String status = room.getStatus(i);
            if (status.equals("X")) {
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
}
