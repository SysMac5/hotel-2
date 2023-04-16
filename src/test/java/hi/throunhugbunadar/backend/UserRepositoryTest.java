package hi.throunhugbunadar.backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserRepositoryTest {
    private UserRepository userRepository;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        String url = "jdbc:sqlite:GG_9.db";
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
    public void testGetUser() {
        String testUsername = "maggi";
        try {
            User user = userRepository.getUser(testUsername);
            Assertions.assertEquals(testUsername, user.getUsername());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
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
}
