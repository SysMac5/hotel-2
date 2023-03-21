package hi.throunhugbunadar.backend;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertTrue(userController.login(testUsername, testPassword));
        Assertions.assertEquals(testUsername, userController.getUser().getUsername());
    }

    @Test
    public void testLoginWithWrongPassword() {
        String testUsername = "maggi2370";
        String testPassword = "eErHima23";
        Assertions.assertFalse(userController.login(testUsername, testPassword));
    }

    @Test
    public void testLoginToNonexistent() {
        String testUsername = "magi23";
        String testPassword = "egErHeima123";
        Assertions.assertFalse(userController.login(testUsername, testPassword));
    }

    @Test
    public void testChangeName() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        String newName = "S. Maggi Snorrason";
        userController.getUser().setName(newName);

        userController.updateUser();

        Assertions.assertEquals(newName, userController.getUser().getName());
    }

    @Test
    public void testChangePhoneNumber() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        String newPhoneNumber = "+354 693 7742";
        userController.getUser().setPhoneNumber(newPhoneNumber);

        userController.updateUser();

        Assertions.assertEquals(newPhoneNumber, userController.getUser().getPhoneNumber());
    }

    @Test
    public void testChangeEmail() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        String newEmail = "maggisnorra@hi.is";
        userController.getUser().setEmail(newEmail);

        userController.updateUser();

        Assertions.assertEquals(newEmail, userController.getUser().getEmail());
    }

    @Test
    public void testChangePaymentInfo() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        // Gyða
        // Þarf að vera PaymentInfoMock
        // PaymentInfoMock er klasi og PaymentInfo er interface
        // skoða nýjasta fyrirlestur
        PaymentInfo newPaymentInfo = new PaymentInfo("1234876534569876", "03", "02", "123");
        userController.getUser().setPaymentInfo(newPaymentInfo);

        userController.updateUser();

        Assertions.assertEquals(newPaymentInfo.getCardNumber(), userController.getUser().getPaymentInfo().getCardNumber());
        Assertions.assertEquals(newPaymentInfo.getCvv(), userController.getUser().getPaymentInfo().getCvv());
        Assertions.assertEquals(newPaymentInfo.getMonthValid(), userController.getUser().getPaymentInfo().getMonthValid());
        Assertions.assertEquals(newPaymentInfo.getYearValid(), userController.getUser().getPaymentInfo().getYearValid());
    }

    @Test
    public void testChangeToInvalidPaymentInfo() {
        try {
            // Gyða
            // Hér aftur: PaymentInfoMock
            new PaymentInfo("sCat", "777", "9", "0öö");
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void testChangePassword() {
        // Gyða
        // Þarf ekki að setja upp userinn fyrst?
        // Ath að það þarf þá að vera UserMock
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        String newPassword = "egErEkkiHeima321";
        userController.getUser().changePassword(newPassword);

        userController.updateUser();

        Assertions.assertTrue(userController.login(testUsername, newPassword));
    }

    @Test
    public void testChangePasswordToEmpty() {
        String testUsername = "maggi2370";
        String testPassword = "egErHeima123";
        userController.login(testUsername, testPassword);

        String newPassword = "";

        try {
            userController.getUser().changePassword(newPassword);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
