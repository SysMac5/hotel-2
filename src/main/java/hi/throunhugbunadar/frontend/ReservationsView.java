package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class ReservationsView {
    @FXML
    private ListView<Reservation> listViewReservations;
    private BookingController bookingController;

    public void frumstilla(Owner owner) {
        ArrayList<Reservation> reservations = bookingController.getReservations(owner.getHotel());

        showList(reservations);
    }

    /**
     * Sýnir lista af bókunum.
     * @param reservations listi af bókunum
     */
    private void showList(ArrayList<Reservation> reservations) {
        ObservableList<Reservation> reservationsObservableList = FXCollections.observableArrayList(reservations);

        listViewReservations.setItems(reservationsObservableList);
        listViewReservations.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Reservation reservation, boolean empty) {
                super.updateItem(reservation, empty);

                if (empty || reservation == null) {
                    setText(null);
                } else {
                    try {
                        setText(reservation.getReservee().getName() + " | " + reservation.getReservee().getPhoneNumber()
                                + " | " + reservation.getReservee().getEmail() + " | " + reservation.getGuestPerRoom()
                                + ". manna herbergi | " + reservation.getNumberOfRooms() + " herbergi | "
                                + reservation.getArrival() + " til " + reservation.getDeparture());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
