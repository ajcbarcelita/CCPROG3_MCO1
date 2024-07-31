package src.hrs.view;

import src.hrs.model.Reservation;

public class ReservationView {
    public void displayReservationDetails(Reservation reservation) {
        System.out.println("\tReservation ID: " + reservation.getReservationID());
        System.out.println("\tGuest Name: " + reservation.getGuestName());
        System.out.println("\tRoom Name: " + reservation.getRoomBooked().getRoomName());
        System.out.println("\tCheck-in: "+ reservation.getCheckInDate() +" Check-out: "+ reservation.getCheckOutDate());
        System.out.println("\tTotal Price of Booking: "+ reservation.getTotalPrice());
        System.out.println("\tPrice per Night: "+ reservation.getRoomBooked().getRoomPrice());
        System.out.println("\tNights Stayed: "+ (reservation.getCheckOutDate() - reservation.getCheckInDate()));
    }
}
