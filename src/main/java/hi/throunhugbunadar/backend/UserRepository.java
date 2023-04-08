package hi.throunhugbunadar.backend;

import java.sql.*;

public class UserRepository implements iUserRepository {
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     *
     * @throws SQLException þegar tenging við gagnagrunn klikkar
     */
    public UserRepository() throws SQLException {
        String url = "jdbc:sqlite:GG_7.db";
        connection = DriverManager.getConnection(url);
    }

    /**
     * Sækir notanda í gagnagrunn eftir notandanafni.
     *
     * @param username notandanafn þess sem á að sækja
     * @return notandinn sem var sóttur
     * @throws Exception ef notandi með natandanafn {@code username} er ekki til
     */
    public User getUser(String username) throws Exception { // óklárað ! !
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
        statement.setString(1,username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            PaymentInfo paymentInfo = getPaymentInfo(resultSet.getInt("paymentInfo"));
            return new User(resultSet.getString("name"),
                    username,
                    resultSet.getString("password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email"),
                    paymentInfo);
        } else {
            throw new Exception("User not found in database");
        }
    }

    // mögulega ehv rusl, setja frekar í skipun fyrir ofan
    private PaymentInfo getPaymentInfo(int id) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    /**
     * Uppfærir notandann í gagnagrunni sem er með sama notandanafn og gefinn {@code user}.
     *
     * @param user uppfærður notandi
     * @return hvort breyting hafi heppanst
     */
    public boolean updateUser(User user) { // óklárað ! !
        throw new UnsupportedOperationException();
    }
}
