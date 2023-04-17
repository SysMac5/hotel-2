package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class BookingView {
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

    public void frumstilla(Hotel hotel, boolean hotelrooms) {
        rules();

        // ATH hafa min og max m.t.t. hvers hótels?
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,1,1);
        spinnerGuestCount.setValueFactory(valueFactory);

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

    private void frumstillaNotandaUpplysingar() {
        textFieldName.setText(user.getName());
        textFieldPhoneNumber.setText(user.getPhoneNumber());
        textFieldEmail.setText(user.getEmail());

        textFieldCardNumber.setText(user.getPaymentInfo().getCardNumber());
        textFieldMonthValid.setText(user.getPaymentInfo().getMonthValid());
        textFieldYearValid.setText(user.getPaymentInfo().getYearValid());
        textFieldCvv.setText(user.getPaymentInfo().getCvv());
    }

    public void reserveMouseClicked(MouseEvent mouseEvent) {
    }

    public void setTenging(HotelRoomsView hotelRoomsView) {
        this.hotelRoomsView = hotelRoomsView;
    }

    public void setTenging(HotelView hotelView) {
        this.hotelView = hotelView;
    }
}
