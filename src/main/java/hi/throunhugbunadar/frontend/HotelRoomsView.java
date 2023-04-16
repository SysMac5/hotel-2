package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.Hotel;
import hi.throunhugbunadar.backend.HotelList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HotelRoomsView {
    @FXML
    private Slider sliderMinPrice;
    @FXML
    private Slider sliderMaxPrice;
    @FXML
    private Slider sliderMinStars;
    @FXML
    private Slider sliderMaxStars;
    @FXML
    private Button buttonFilter;
    @FXML
    private Button buttonReset;
    @FXML
    private ListView<Hotel> listViewHotelroomList;
    private HotelList hotelList;

    /**
     *
     * Setja upp notendaviðmót.
     * @param hotelList listi af hótelum sem á að birta
     */
    public void frumstilla(HotelList hotelList) {
        this.hotelList = hotelList;

        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList.getList());

        listViewHotelroomList.setItems(hotelObservableList);
        listViewHotelroomList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Hotel hotel, boolean empty) {
                super.updateItem(hotel, empty);

                if (empty || hotel == null) {
                    setText(null);
                } else {
                    try {
                        setText(hotel.getName() + " - " + hotel.getStars() + " stjörnur - " + hotelList.getPrice(hotel) + " kr nóttin");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        listViewHotelroomList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Hotel selectedHotel = (Hotel) newValue;
                try {
                    nyrGluggi(selectedHotel);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void nyrGluggi(Hotel selectedHotel) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        BookingView bv = loader.getController();

        stage.setTitle(selectedHotel.getName());
        Scene s = new Scene(root, 650, 590);
        stage.setScene(s);

        //uv.setTenging(lv);
        bv.frumstilla(selectedHotel);

        stage.show();
    }

    public void filterMouseClicked(MouseEvent mouseEvent) {
        hotelList.filterByPrice((int)sliderMinPrice.getValue()*1000, (int)sliderMaxPrice.getValue()*1000);
        hotelList.filterByStars((int)sliderMinStars.getValue(), (int)sliderMaxStars.getValue());

        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList.getList());

        listViewHotelroomList.setItems(hotelObservableList);
    }

    public void resetMouseClicked(MouseEvent mouseEvent) {
        sliderMinPrice.setValue(0);
        sliderMaxPrice.setValue(40);
        sliderMinStars.setValue(0);
        sliderMaxStars.setValue(5);

        hotelList.restartFilter();

        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList.getList());

        listViewHotelroomList.setItems(hotelObservableList);
    }
}
