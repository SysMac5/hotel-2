package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class SearchView implements Initializable {

    @FXML
    private TextField textFieldHotel;
    @FXML
    private Button buttonSearchHotel;
    @FXML
    private ChoiceBox<String> choiceBoxLocation;
    @FXML
    private DatePicker datePickerArrival;
    @FXML
    private DatePicker datePickerDeparture;
    @FXML
    private Spinner<Integer> spinnerGuestCount;
    @FXML
    private Button buttonSearchHotelrooms;
    private LoginView lv;
    private static final String[] region = {"Höfuðborgarsvæðið", "Suðurland", "Norðurland", "Austurland", "Vesturland"};
    private HotelController hotelController;

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

        //Spinner<Integer> spinnerGuestCount = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,1,1);
        spinnerGuestCount.setValueFactory(valueFactory);
    }

    /**
     * Regla búin til um hvenær hnappurinn til að leita af hótelherbergjum á að vera óvirkur/virkur.
     */
    private void searchHotelroomsRule() {
        buttonSearchHotelrooms.disableProperty().bind(
                choiceBoxLocation.valueProperty().isNull()
                        .or(datePickerArrival.valueProperty().isNull())
                                .or(datePickerDeparture.valueProperty().isNull())
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

    /**
     * Sýnir notandaviðmót með lista af hótelherbergjum m.t.t. leitarskilyrða notanda.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     * @throws Exception
     */
    public void searchHotelroomsMouseClicked(MouseEvent mouseEvent) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelroom-search-results.fxml"));
        Parent root = loader.load();
        HotelRoomsView hv = loader.getController();

        stage.setTitle("Leitarniðurstöður");
        Scene s = new Scene(root, 650, 590);
        stage.setScene(s);

        HotelList hotelList = searchByCriteria();

        //uv.setTenging(lv);
        hv.frumstilla(hotelList);

        stage.show();
    }

    /**
     * Skilar lista af hótelherbergjum m.t.t. leitarskilyrða notanda.
     * @return listi af hótelherbergjum m.t.t. leitarskilyrða
     * @throws Exception
     */
    private HotelList searchByCriteria() throws Exception {
        Criteria criteria = new Criteria();

        String selectedLocation = (String) choiceBoxLocation.getValue(); // Get the selected value from the choice box
        switch (selectedLocation) {
            case "Höfuðborgarsvæðið":
                criteria.location = Region.HOFUDBORGARSVAEDID.getLocation();
                break;
            case "Suðurland":
                criteria.location = Region.SUDURLAND.getLocation();
                break;
            case "Norðurland":
                criteria.location = Region.NORDURLAND.getLocation();
                break;
            case "Austurland":
                criteria.location = Region.AUSTURLAND.getLocation();
                break;
            case "Vesturland":
                criteria.location = Region.VESTURLAND.getLocation();
                break;
        }

        LocalDate localDateArrival = datePickerArrival.getValue();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instantArrival = localDateArrival.atStartOfDay(zoneId).toInstant();
        java.sql.Date sqlDateArrival = new java.sql.Date(instantArrival.toEpochMilli());
        criteria.arrival = sqlDateArrival;

        LocalDate localDateDeparture = datePickerArrival.getValue();
        Instant instantDeparture = localDateDeparture.atStartOfDay(zoneId).toInstant();
        java.sql.Date sqlDateDeparture = new java.sql.Date(instantDeparture.toEpochMilli());
        criteria.departure = sqlDateDeparture;

        criteria.guestCount = (int) spinnerGuestCount.getValue();

        return hotelController.searchByCriteria(criteria);
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

    public void searchHotelMouseClicked(MouseEvent mouseEvent) throws Exception {
        //hotelController.searchForHotel(textFieldHotel.getText());
    }

    /**
     * Tengir SearchView við LoginView.
     * @param lv LoginView
     */
    public void setTenging(LoginView lv) {
        this.lv = lv;
    }

    public void setHotelController(Connection connection) {
        HotelRepository hotelRepository = new HotelRepository(connection);
        this.hotelController = new HotelController(hotelRepository);
    }
}
