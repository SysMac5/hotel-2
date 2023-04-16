package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.HotelRooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.controlsfx.control.RangeSlider;

import java.util.ArrayList;

public class HotelRoomsView {
    @FXML
    private ListView listViewHotelroomList;

    public void frumstilla(ArrayList<HotelRooms> hotelrooms) {
        ListView<HotelRooms> listView = new ListView<>();

        listView.setCellFactory(param -> new ListCell<HotelRooms>() {
            @Override
            protected void updateItem(HotelRooms item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getHotel().getName() + " | " + item.getHotel().getStars() + " stars | " + item.getNumberOfGuests() + " | " + item.getPricePerNight());
                }
            }
        });

        ObservableList<HotelRooms> items = FXCollections.observableArrayList(hotelrooms);
        listView.setItems(items);
    }

    /*RangeSlider slider = new RangeSlider(0, 100, 10, 90);
    //Setting the slider properties
      slider.setShowTickLabels(true);
      slider.setShowTickMarks(true);
      slider.setMajorTickUnit(25);
      slider.setBlockIncrement(10);

     */
}
