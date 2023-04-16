package hi.throunhugbunadar.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button buttonSearchHotel;
    @FXML
    private ChoiceBox choiceBoxLocation;
    @FXML
    private DatePicker datePickerArrival;
    @FXML
    private DatePicker datePickerDeparture;
    @FXML
    private TextField textFieldGuestCount;
    @FXML
    private Button buttonSearchHotelrooms;
    private LoginView lv;
    private static final String[] region = {"Höfuðborgarsvæðið", "Suðurland", "Norðurland", "Austurland", "Vesturland"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        frumstillaGogn();

        searchHotelRule();
        searchHotelroomsRule();
    }

    /**
     * Setja upp notendaviðmót.
     */
    private void frumstillaGogn() {
        ObservableList<String> regionNode = FXCollections.observableArrayList(region);
        choiceBoxLocation.setItems(regionNode);
    }

    /**
     * Regla búin til um hvenær hnappurinn til að leita af hótelherbergjum á að vera óvirkur/virkur.
     */
    private void searchHotelroomsRule() {
        buttonSearchHotelrooms.disableProperty().bind(
                choiceBoxLocation.valueProperty().isNull()
                        .or(datePickerArrival.valueProperty().isNull())
                                .or(datePickerDeparture.valueProperty().isNull())
                                        .or(textFieldGuestCount.textProperty().isEmpty()) //ATH, verður að vera tala?
        );
    }

    /**
     * Regla búin til um hvenær hnappurinn til að leita af hóteli á að vera óvirkur/virkur.
     */
    private void searchHotelRule() {
        buttonSearchHotel.disableProperty().bind(
                textFieldHotel.textProperty().isEmpty());
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
        Scene s = new Scene(root, 400, 505);
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
