package hi.throunhugbunadar.backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class UserRepositoryTest {
    private UserRepository userRepository;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        String url = "jdbc:sqlite:src/main/resources/GG_9.db";
        try {
            this.connection = DriverManager.getConnection(url);
            this.userRepository = new UserRepository(this.connection);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testGetUser() throws Exception {
        String testUsername = "maggi";

        User user = userRepository.getUser(testUsername);
        Assertions.assertEquals(testUsername, user.getUsername());
    }

    @Test
    public void testGetUserNonexistent() {
        String testUsername = "magi94";
        try {
            Assertions.assertThrows(Exception.class, () -> userRepository.getUser(testUsername));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateUser1() {
        String testUsername = "maggi";
        User user;
        try {
            user = userRepository.getUser(testUsername);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            return;
        }
        String newPhoneNumber = "+354 693 7742";
        String newName = "S. Maggi Snorrason";
        String newEmail = "maggi@queer.is";
        PaymentInfo newPaymentInfo = null;
        try {
            newPaymentInfo = new PaymentInfo("1234987645675432", "03", "25", "333");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            return;
        }
        user.setPhoneNumber(newPhoneNumber);
        user.setName(newName);
        user.setEmail(newEmail);
        user.setPaymentInfo(newPaymentInfo);

        Assertions.assertTrue(userRepository.updateUser(user), "Update failed.");

        try {
            user = userRepository.getUser(testUsername);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            return;
        }

        Assertions.assertEquals(newName, user.getName(),"Name not updated.");
        Assertions.assertEquals(newEmail, user.getEmail(), "E-mail not updated.");
        Assertions.assertEquals(newPhoneNumber, user.getPhoneNumber(), "Phone number not updated.");
        Assertions.assertEquals(newPaymentInfo.getCardNumber(), user.getPaymentInfo().getCardNumber(), "Card number not updated.");
        Assertions.assertEquals(newPaymentInfo.getCvv(), user.getPaymentInfo().getCvv(), "CVV not updated.");
        Assertions.assertEquals(newPaymentInfo.getMonthValid(), user.getPaymentInfo().getMonthValid(), "Year in payment information not updated.");
        Assertions.assertEquals(newPaymentInfo.getYearValid(), user.getPaymentInfo().getYearValid(), "Month in payment information not updated.");
    }

    @Test
    public void testUpdateUser2() {
        String testUsername = "maggi";
        User user;
        try {
            user = userRepository.getUser(testUsername);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            return;
        }
        String newPhoneNumber = "+354 58 12345";
        String newName = "Maggi Snorra";
        String newEmail = "sms70@hi.is";
        PaymentInfo newPaymentInfo = null;
        try {
            newPaymentInfo = new PaymentInfo("0987563498657834", "09", "29", "222");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            return;
        }
        user.setPhoneNumber(newPhoneNumber);
        user.setName(newName);
        user.setEmail(newEmail);
        user.setPaymentInfo(newPaymentInfo);

        Assertions.assertTrue(userRepository.updateUser(user), "Update failed.");

        try {
            user = userRepository.getUser(testUsername);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
            return;
        }

        Assertions.assertEquals(newName, user.getName(),"Name not updated.");
        Assertions.assertEquals(newEmail, user.getEmail(), "E-mail not updated.");
        Assertions.assertEquals(newPhoneNumber, user.getPhoneNumber(), "Phone number not updated.");
        Assertions.assertEquals(newPaymentInfo.getCardNumber(), user.getPaymentInfo().getCardNumber(), "Card number not updated.");
        Assertions.assertEquals(newPaymentInfo.getCvv(), user.getPaymentInfo().getCvv(), "CVV not updated.");
        Assertions.assertEquals(newPaymentInfo.getMonthValid(), user.getPaymentInfo().getMonthValid(), "Year in payment information not updated.");
        Assertions.assertEquals(newPaymentInfo.getYearValid(), user.getPaymentInfo().getYearValid(), "Month in payment information not updated.");
    }
}
