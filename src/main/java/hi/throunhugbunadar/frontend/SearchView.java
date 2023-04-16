package hi.throunhugbunadar.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchView implements Initializable {
    @FXML
    private TextField textFieldHotel;
    @FXML
    private ChoiceBox choiceBoxLocation;
    @FXML
    private DatePicker datePickerArrival;
    @FXML
    private DatePicker datePickerDeparture;
    @FXML
    private TextField textFieldGuestCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public TextField getTextFieldHotel() {
        return textFieldHotel;
    }

    public void userInformationMouseClicked(ActionEvent actionEvent) {
    }

    public void searchHotelMouseClicked(ActionEvent actionEvent) {
    }

    public void searchHotelroomsMouseClicked(ActionEvent actionEvent) {
    }
}
