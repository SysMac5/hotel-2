package hi.throunhugbunadar.backend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Tilvik af keyrslu.
 */

public class UserController {
    private User user;
    private final iUserRepository userRepository;

    /**
     * Smiður fyrir klasann.
     *
     * @param userRepository tenging við gagnagrunn
     */
    public UserController(iUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Uppfæra breytingar á notanda í gagnagrunni.
     */
    public void updateUser() {
        userRepository.updateUser(user);
    }

    /**
     * Skrá inn sem notandi.
     *
     * @param username notandanafn notanda sem skráir sig inn
     * @param password lykilorð notanda sem skráir sig inn
     *
     * @return hvort það heppnaðist að skrá notanda inn
     */
    public boolean login(String username, String password) {
        User userLoggingIn;

        try {
            userLoggingIn = userRepository.getUser(username);
        } catch (Exception e) {
            return false;
        }

        if(userLoggingIn.validatePassword(password)) {
            this.user = userLoggingIn;
            return true;
        }

        return false;
    }

    /**
     * Sækir notandann sem er skráður inn.
     *
     * @return notandi sem er skráður inn
     */
    public User getUser() {
        return this.user;
    }


    public void vistaMouseClicked(MouseEvent mouseEvent) {
    }
}
