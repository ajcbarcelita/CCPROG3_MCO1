package src.hrs.controller;

import src.hrs.model.Reservation;
import src.hrs.view.ReservationView;

public class ReservationController {
    private ReservationView reservationView;

    public ReservationController(Reservation reservation) {
        this.reservationView = new ReservationView();
    }

    public void displayReservationDetails(Reservation reservation) {
        reservationView.displayReservationDetails(reservation);
    }
}
