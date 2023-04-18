package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ResourceBundle;

public class BookingView {
    @FXML
    private ListView<ImageView> listViewImages;
    @FXML
    private Spinner<Integer> spinnerNumberOfRooms;
    @FXML
    private Label labelHotelName;
    @FXML
    private Label labelLocation;
    @FXML
    private ImageView imageViewHotel;
    @FXML
    private Label labelHotelInformation;
    @FXML
    private DatePicker datePickerDeparture;
    @FXML
    private Spinner<Integer> spinnerGuestCount;
    @FXML
    private DatePicker datePickerArrival;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPhoneNumber;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldCardNumber;
    @FXML
    private TextField textFieldMonthValid;
    @FXML
    private TextField textFieldYearValid;
    @FXML
    private TextField textFieldCvv;
    @FXML
    private Button buttonReserve;
    @FXML
    private Label labelAlert;
    private HotelRoomsView hotelRoomsView;
    private Criteria criteria;
    private User user;//frekar en usercontroller?
    private HotelView hotelView;
    private Hotel hotel;
    private BookingController bookingController;

    /**
     * Setja upp bókunarviðmót.
     * @param hotel hótel sem á að sýna
     * @param hotelrooms satt ef notandi velur ákveðið herbergi á hótelinu
     */
    public void frumstilla(Hotel hotel, boolean hotelrooms) {
        rules();
        this.hotel = hotel;

        // ATH hafa min og max m.t.t. hvers hótels?
        SpinnerValueFactory<Integer> valueFactoryGuestCount = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,1,1);
        spinnerGuestCount.setValueFactory(valueFactoryGuestCount);

        if(hotelrooms) {
            user = hotelRoomsView.getUser();

            criteria = hotelRoomsView.getCriteria();

            spinnerGuestCount.getValueFactory().setValue(criteria.guestCount);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            LocalDate localDateArrival = LocalDate.parse(formatter.format(criteria.arrival));
            datePickerArrival.setValue(localDateArrival);

            LocalDate localDateDeparture = LocalDate.parse(formatter.format(criteria.departure));
            datePickerDeparture.setValue(localDateDeparture);
            //ATH, date ekki rétt? sama í arrival og departure
        }
        else {
            user = hotelView.getUser();
        }

        SpinnerValueFactory<Integer> valueFactoryNumberOfRooms = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100,1,1);
        spinnerNumberOfRooms.setValueFactory(valueFactoryNumberOfRooms);

        labelHotelName.setText(hotel.getName());

        labelLocation.setText(hotel.getLocation()); //breyta í nöfnin

        //Location!

        for(File file: hotel.getImageList()){
            Image image = new Image(file.toURI().toString());
            // Virkar ekki: Image image = new Image("../../resources/images/dyrholaey1.jpg");
            // Virkar: Image image = new Image("C:/Users/Gyða Perla/IdeaProjects/hotel-2/src/main/resources/images/siglo" + i + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            //imageViewHotel.setImage(image);
            //imageViewHotel.setVisible(true);
            listViewImages.getItems().add(imageView);
        }

        if(hotel.getImageList().isEmpty()){
            listViewImages.setVisible(false);
            listViewImages.setManaged(false);
        }

        labelHotelInformation.setText(hotel.getInfo());

        frumstillaNotandaUpplysingar();
    }

    /**
     * Regla búin til um hvenær hnappurinn til að bóka herbergi á að vera óvirkur/virkur.
     */
    private void rules() {
        buttonReserve.disableProperty().bind(
                datePickerArrival.valueProperty().isNull()
                        .or(datePickerDeparture.valueProperty().isNull())
                        .or(textFieldName.textProperty().isEmpty())
                        .or(textFieldPhoneNumber.textProperty().isEmpty())
                        .or(textFieldEmail.textProperty().isEmpty())
                        .or(textFieldCardNumber.textProperty().isEmpty())
                        .or(textFieldMonthValid.textProperty().isEmpty())
                        .or(textFieldYearValid.textProperty().isEmpty())
                        .or(textFieldCvv.textProperty().isEmpty())
        );
    }

    /**
     * Sýna upplýsingar um notanda í viðmóti.
     */
    private void frumstillaNotandaUpplysingar() {
        textFieldName.setText(user.getName());
        textFieldPhoneNumber.setText(user.getPhoneNumber());
        textFieldEmail.setText(user.getEmail());

        textFieldCardNumber.setText(user.getPaymentInfo().getCardNumber());
        textFieldMonthValid.setText(user.getPaymentInfo().getMonthValid());
        textFieldYearValid.setText(user.getPaymentInfo().getYearValid());
        textFieldCvv.setText(user.getPaymentInfo().getCvv());
    }

    /**
     * Bóka herbergi.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     */
    public void reserveMouseClicked(MouseEvent mouseEvent) {
        int guestPerRoom = spinnerGuestCount.getValue();
        int numberOfRooms = spinnerNumberOfRooms.getValue();

        LocalDate localDateArrival = datePickerArrival.getValue();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instantArrival = localDateArrival.atStartOfDay(zoneId).toInstant();
        java.sql.Date DateArrival = new java.sql.Date(instantArrival.toEpochMilli());

        LocalDate localDateDeparture = datePickerArrival.getValue();
        Instant instantDeparture = localDateDeparture.atStartOfDay(zoneId).toInstant();
        java.sql.Date DateDeparture = new java.sql.Date(instantDeparture.toEpochMilli());

        Reservation reservation;
        try {
            reservation = new Reservation(user, hotel, DateArrival, DateDeparture, guestPerRoom, numberOfRooms);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(bookingController.reserveRooms(reservation)){
            labelAlert.setTextFill(Color.web("#00FFFF"));
            labelAlert.setText("Herbergi bókað");
        }
        else{
            labelAlert.setTextFill(Color.web("#880808"));
            labelAlert.setText("Herbergi ekki laust");
        }
    }

    /**
     * Tengir BookingView við HotelRoomsView.
     * @param hotelRoomsView HotelRoomsView
     */
    public void setTenging(HotelRoomsView hotelRoomsView) {
        this.hotelRoomsView = hotelRoomsView;
    }

    /**
     * Tengir BookingView við HotelView.
     * @param hotelView HotelView
     */
    public void setTenging(HotelView hotelView) {
        this.hotelView = hotelView;
    }

    // ATH, alltaf að connecta aftur og aftur?
    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
