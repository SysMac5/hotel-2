package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookingView {
    @FXML
    private Label textFieldHotelName;

    public void frumstilla(Hotel selectedHotel) {
        textFieldHotelName.setText(selectedHotel.getName());
    }
}
