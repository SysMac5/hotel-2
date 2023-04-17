package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.Criteria;
import hi.throunhugbunadar.backend.Hotel;
import hi.throunhugbunadar.backend.User;
import hi.throunhugbunadar.backend.UserController;
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

    public void frumstilla(Hotel hotel) {
        user = hotelRoomsView.getUser();
        System.out.println("user");
        criteria = hotelRoomsView.getCriteria();
        System.out.println("criteria");
        labelHotelName.setText(hotel.getName());
        labelLocation.setText(hotel.getLocation()); //breyta í nöfnin
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

        // ATH hafa min og max m.t.t. hvers hótels?
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,1,1);
        spinnerGuestCount.setValueFactory(valueFactory);
        spinnerGuestCount.getValueFactory().setValue(criteria.guestCount);
        System.out.println("spinner");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate localDateArrival = LocalDate.parse(formatter.format(criteria.arrival));
        datePickerArrival.setValue(localDateArrival);
        System.out.println("date");

        LocalDate localDateDeparture = LocalDate.parse(formatter.format(criteria.departure));
        datePickerDeparture.setValue(localDateDeparture);
        //ATH, date ekki rétt? sama í arrival og departure

        System.out.println("date2");
        frumstillaNotandaUpplysingar();
        System.out.println("user");
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
}
