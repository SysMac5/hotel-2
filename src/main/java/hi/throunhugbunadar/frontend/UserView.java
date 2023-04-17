package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.PaymentInfo;
import hi.throunhugbunadar.backend.User;
import hi.throunhugbunadar.backend.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserView {
    @FXML
    private Button buttonSave;
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelUsername;
    @FXML
    private TextField textFieldPassword;
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
    // setja reglu með hvenær má vista, allt þarf að vera til staðar

    // ATH, sleppa?
    public void setTenging(LoginView lv) {
        userController = lv.getUserController();
        user = userController.getUser();
    }

    /**
     * Setur upp notendaviðmót m.t.t. notanda.
     */
    public void frumstilla() {
        regla();

        textFieldName.setText(user.getName());
        labelUsername.setText(user.getUsername());
        textFieldPassword.setText(user.getPassword());
        textFieldPhoneNumber.setText(user.getPhoneNumber());
        textFieldEmail.setText(user.getEmail());

        textFieldCardNumber.setText(user.getPaymentInfo().getCardNumber());
        textFieldMonthValid.setText(user.getPaymentInfo().getMonthValid());
        textFieldYearValid.setText(user.getPaymentInfo().getYearValid());
        textFieldCvv.setText(user.getPaymentInfo().getCvv());
    }

    private void regla() {
        buttonSave.disableProperty().bind(
                textFieldName.textProperty().isEmpty()
                        .or(textFieldPassword.textProperty().isEmpty())
                        .or(textFieldPhoneNumber.textProperty().isEmpty())
                        .or(textFieldEmail.textProperty().isEmpty())
                        .or(textFieldCardNumber.textProperty().isEmpty())
                        .or(textFieldMonthValid.textProperty().isEmpty())
                        .or(textFieldYearValid.textProperty().isEmpty())
                        .or(textFieldCvv.textProperty().isEmpty())
        );
    }

    /**
     * Vistar upplýsingar um notanda.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     * @throws Exception
     */
    public void saveMouseClicked(MouseEvent mouseEvent) throws Exception {
        user.setName(textFieldName.getText());
        user.changePassword(textFieldPassword.getText());
        user.setPhoneNumber(textFieldPhoneNumber.getText());
        user.setEmail(textFieldEmail.getText());

        String newCardNumber = textFieldCardNumber.getText();
        String newMonthValid = textFieldMonthValid.getText();
        String newYearValid = textFieldYearValid.getText();
        String newCvv = textFieldCvv.getText();
        PaymentInfo newPaymentInfo = new PaymentInfo(newCardNumber, newMonthValid, newYearValid, newCvv);
        user.setPaymentInfo(newPaymentInfo);

        userController.updateUser();

        labelAlert.setText("Breytingar vistaðar");
    }
}
