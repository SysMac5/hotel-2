package hi.throunhugbunadar.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private LoginView lv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public TextField getTextFieldHotel() {
        return textFieldHotel;
    }

    public void searchHotelroomsMouseClicked(ActionEvent actionEvent) {
    }

    /**
     * Opnar nýjan glugga með upplýsingum um notanda.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     * @throws java.io.IOException
     */
    public void userInformationMouseClicked(MouseEvent mouseEvent) throws java.io.IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userInformation.fxml"));
        Parent root = loader.load();
        UserView uv = loader.getController();

        stage.setTitle("Mínar upplýsingar");
        Scene s = new Scene(root, 400, 475);
        stage.setScene(s);

        uv.setTenging(lv);
        uv.frumstilla();

        stage.show();
    }

    public void searchHotelMouseClicked(MouseEvent mouseEvent) {
    }

    public void setTenging(LoginView lv) {
        this.lv = lv;
    }
}
