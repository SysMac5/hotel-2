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

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchView implements Initializable {
    @FXML
    private Button buttonReservations;
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
    private LoginView loginView;
    private static final String[] region = {"Höfuðborgarsvæðið", "Suðurland", "Norðurland", "Austurland", "Vesturland"};
    private HotelController hotelController;
    private BookingController bookingController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> regionNode = FXCollections.observableArrayList(region);
        choiceBoxLocation.setItems(regionNode);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,1,1);
        spinnerGuestCount.setValueFactory(valueFactory);

        searchHotelRule();
        searchHotelroomsRule();
    }

    /**
     * Setja upp notendaviðmót.
     */
    public void frumstilla() {
        if(!(getUser() instanceof Owner)){
            buttonReservations.setVisible(false);
        }
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
     * Sýnir notendaviðmót með lista af hótelum m.t.t. innsláttar.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     * @throws Exception
     */
    public void searchHotelMouseClicked(MouseEvent mouseEvent) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotel-search-results.fxml"));
        Parent root = loader.load();
        HotelView hv = loader.getController();

        stage.setTitle("Leitarniðurstöður");
        Scene s = new Scene(root, 300, 364);
        stage.setScene(s);

        ArrayList<Hotel> hotelList = hotelController.searchForHotel(textFieldHotel.getText());

        hv.setTenging(this);
        hv.frumstilla(hotelList);
        hv.setBookingController(bookingController);

        stage.show();
    }

    /**
     * Sýnir notendaviðmót með lista af hótelherbergjum m.t.t. leitarskilyrða notanda.
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

        hv.setTenging(this);
        hv.frumstilla(hotelList);
        hv.setBookingController(bookingController);

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

        LocalDate localDateDeparture = datePickerDeparture.getValue();
        Instant instantDeparture = localDateDeparture.atStartOfDay(zoneId).toInstant();
        java.sql.Date sqlDateDeparture = new java.sql.Date(instantDeparture.toEpochMilli());
        criteria.departure = sqlDateDeparture;

        System.out.println("localDateArrival: " + localDateArrival);
        System.out.println("localDateDeparture: " + localDateDeparture);

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

        uv.setTenging(loginView);
        uv.frumstilla();

        stage.show();
    }

    /**
     * Tengir SearchView við LoginView.
     * @param loginView LoginView
     */
    public void setTenging(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setControllers(Connection connection) {
        HotelRepository hotelRepository = new HotelRepository(connection);
        this.hotelController = new HotelController(hotelRepository);

        BookingRepository bookingRepository = new BookingRepository(connection);
        this.bookingController = new BookingController(bookingRepository);
    }

    public User getUser() {
        return loginView.getUserController().getUser();
    }

    /**
     * Opnar nýjan glugga með lista yfir bókanir fyrir hótel sem eigandi á.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     */
    public void reservationsMouseClicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservations.fxml"));
        Parent root = loader.load();
        ReservationsView rv = loader.getController();

        stage.setTitle("Listi af bókunum");
        Scene s = new Scene(root, 650, 365);
        stage.setScene(s);

        rv.setBookingController(bookingController);
        rv.frumstilla((Owner) getUser());

        stage.show();
    }
}
