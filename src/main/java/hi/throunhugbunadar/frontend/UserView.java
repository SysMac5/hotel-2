package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.PaymentInfo;
import hi.throunhugbunadar.backend.User;
import hi.throunhugbunadar.backend.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserView {
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelUsername;
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
    private Label labelAlert;
    private UserController userController;
    private User user;

    // ATH, setja inn röksegðir?

    // ATH, sleppa?
    public void setTenging(LoginView lv) {
        userController = lv.getUserController();
        user = userController.getUser();
    }

    /**
     * Setur upp notendaviðmót m.t.t. notanda.
     */
    public void frumstilla() {
        textFieldName.setText(user.getName());
        labelUsername.setText(user.getUsername());
        textFieldPhoneNumber.setText(user.getPhoneNumber());
        textFieldEmail.setText(user.getEmail());

        textFieldCardNumber.setText(user.getPaymentInfo().getCardNumber());
        textFieldMonthValid.setText(user.getPaymentInfo().getMonthValid());
        textFieldYearValid.setText(user.getPaymentInfo().getYearValid());
        textFieldCvv.setText(user.getPaymentInfo().getCvv());
    }

    /**
     * Vistar upplýsingar um notanda.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     * @throws Exception
     */
    public void vistaMouseClicked(MouseEvent mouseEvent) throws Exception {
        user.setName(textFieldName.getText());
        user.setPhoneNumber(textFieldPhoneNumber.getText());
        user.setEmail(textFieldEmail.getText());

        String newCardNumber = textFieldCardNumber.getText();
        String newMonthValid = textFieldMonthValid.getText();
        String newYearValid = textFieldYearValid.getText();
        String newCvv = textFieldCvv.getText();
        PaymentInfo newPaymentInfo = new PaymentInfo(newCardNumber, newMonthValid, newYearValid, newCvv);
        user.setPaymentInfo(newPaymentInfo);

        labelAlert.setText("Breytingar vistaðar");
    }
}
