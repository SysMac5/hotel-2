package hi.throunhugbunadar.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class UserControllerTest {
    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController();
    }

    @After
    public void tearDown() {
        userController = null;
    }

    @Test
    public void testLogin() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);
        Assertions.assertEquals(testUsername, userController.getUser().getUsername());
    }

    @Test
    public void testChangeUser() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        String newName = "S. Maggi Snorrason";
        userController.getUser().setName(newName);

        String newPassword = "egErEkkiHeima321";
        userController.getUser().changePassword(newPassword);

        String newPhoneNumber = "+354 693 7742";
        userController.getUser().setPhoneNumber(newPhoneNumber);

        String newEmail = "maggisnorra@hi.is";
        userController.getUser().setEmail(newEmail);

        PaymentInfo newPaymentInfo = new PaymentInfo("1234876534569876", "03", "02", "123");
        userController.getUser().setPaymentInfo(newPaymentInfo);

        userController.updateUser();

        Assertions.assertFalse(userController.login(testUsername, testPassword));
        Assertions.assertFalse(userController.login(testUsername, newPassword));

        Assertions.assertEquals(newName, userController.getUser().getName());
        Assertions.assertEquals(newPhoneNumber, userController.getUser().getPhoneNumber());
        Assertions.assertEquals(newEmail, userController.getUser().getEmail());
        Assertions.assertEquals(newPaymentInfo.getCardNumber(), userController.getUser().getPaymentInfo().getCardNumber());
        Assertions.assertEquals(newPaymentInfo.getCvv(), userController.getUser().getPaymentInfo().getCvv());
        Assertions.assertEquals(newPaymentInfo.getMonthValid(), userController.getUser().getPaymentInfo().getMonthValid());
        Assertions.assertEquals(newPaymentInfo.getYearValid(), userController.getUser().getPaymentInfo().getYearValid());
    }
}
