package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.BookingController;
import hi.throunhugbunadar.backend.Hotel;
import hi.throunhugbunadar.backend.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HotelView {
    @FXML
    private ListView<Hotel> listViewHotelList;
    private SearchView searchView;
    private BookingController bookingController;

    /**
     * Tengir HotelView við SearchView.
     * @param searchView SearchView
     */
    public void setTenging(SearchView searchView) {
        this.searchView = searchView;
    }

    /**
     * Setja upp notendaviðmót.
     * @param hotelList listi af hótelum sem á að birta
     */
    public void frumstilla(ArrayList<Hotel> hotelList) {
        showList(hotelList);

        listViewHotelList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    nyrGluggi((Hotel)newValue);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Sýnir lista af hótelum.
     * @param hotelList listi af hótelum
     */
    private void showList(ArrayList<Hotel> hotelList) {
        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList);

        listViewHotelList.setItems(hotelObservableList);
        listViewHotelList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Hotel hotel, boolean empty) {
                super.updateItem(hotel, empty);

                if (empty || hotel == null) {
                    setText(null);
                } else {
                    try {
                        setText(hotel.getName() + " - " + hotel.getStars() + " stjörnur");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Sýnir bókunarglugga.
     * @param selectedHotel Hótel sem er valið úr lista
     * @throws IOException
     */
    private void nyrGluggi(Hotel selectedHotel) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        BookingView bv = loader.getController();

        stage.setTitle(selectedHotel.getName());
        Scene s = new Scene(root, 600, 730);
        stage.setScene(s);

        bv.setTenging(this);
        bv.frumstilla(selectedHotel, false);
        bv.setBookingController(bookingController);

        stage.show();
    }

    public User getUser() {
        return searchView.getUser();
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
