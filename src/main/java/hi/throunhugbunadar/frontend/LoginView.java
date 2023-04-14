package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.UserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginView implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label alertLabel;
    private UserController userController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            this.userController = new UserController();
        } catch (SQLException e) {
            // ATH, handle the exception here
        }
        loginRule();
    }

    /**
     * Regla búin til um hvenær hnappurinn til að skrá sig inn á að vera óvirkur/virkur.
     */
    private void loginRule() {
        loginButton.disableProperty().bind(
                usernameTextField.textProperty().isEmpty()
                        .or(passwordField.textProperty().isEmpty())
        );
    }

    /**
     * Skrá notanda inn.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður.
     */
    public void buttonMouseClicked(javafx.scene.input.MouseEvent mouseEvent) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if(userController.login(username, password)) {
            login();
        }
        else {
            alertLabel.setText("Rangt notandanafn eða lykilorð");
        }
    }

    /**
     * Sýnir nýja senu með leitarviðmóti.
     */
    private void login() {
        //Stage s = (Stage)
    }
}