package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.text.SimpleDateFormat;
import java.time.*;

public class BookingView {
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

            System.out.println("date2");
        }
        else {
            user = hotelView.getUser();
        }

        SpinnerValueFactory<Integer> valueFactoryNumberOfRooms = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100,1,1);
        spinnerNumberOfRooms.setValueFactory(valueFactoryNumberOfRooms);

        labelHotelName.setText(hotel.getName());

        System.out.println(hotel.getLocation());

        labelLocation.setText(hotel.getLocation()); //breyta í nöfnin
/*
        if (Region.valueOf(hotel.getLocation()) == Region.HOFUDBORGARSVAEDID){
            labelLocation.setText("Höfuðborgarsvæðið"); //breyta í nöfnin
        }

 */

        /*
        Region region = Region.valueOf(hotel.getLocation().trim());
        String nameLocation;
        switch (region) {
            case HOFUDBORGARSVAEDID:
                nameLocation = "Höfuðborgarsvæðið";
                break;
            case SUDURLAND:
                nameLocation = "Suðurland";
                break;
            case NORDURLAND:
                nameLocation = "Norðurland";
                break;
            case AUSTURLAND:
                nameLocation = "Austurland";
                break;
            case VESTURLAND:
                nameLocation = "Vesturland";
                break;
            default:
                nameLocation = "Ekki vitað";
                break;
        }

         */
/*
        if (hotel.getImageList().get(0) != null) {
            File imageFile = (File) hotel.getImageList().get(0);
            Image image = new Image(imageFile.toURI().toString());
            imageViewHotel.setImage(image);
        } else {
            System.out.println("imageView virkar ekki");
        }

 */
        labelHotelInformation.setText(hotel.getInfo());

        frumstillaNotandaUpplysingar();
        System.out.println("user");
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
        int guestCount = spinnerGuestCount.getValue();
        int numberOfRooms = spinnerNumberOfRooms.getValue();

        LocalDate localDateArrival = datePickerArrival.getValue();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instantArrival = localDateArrival.atStartOfDay(zoneId).toInstant();
        java.sql.Date DateArrival = new java.sql.Date(instantArrival.toEpochMilli());

        LocalDate localDateDeparture = datePickerArrival.getValue();
        Instant instantDeparture = localDateDeparture.atStartOfDay(zoneId).toInstant();
        java.sql.Date DateDeparture = new java.sql.Date(instantDeparture.toEpochMilli());

        //Reservation reservation = new Reservation(user, hotel, guestCount, numberOfRooms, DateArrival, DateDeparture);
        //bookingController.reserveRooms(reservation);

        //labelAlert.setText("Herbergi bókað");

        System.out.println("Bóka herbergi. Klára að útfæra virkni!");
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
