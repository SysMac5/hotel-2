package hi.throunhugbunadar.backend;

import java.sql.*;

public class UserRepository implements iUserRepository {
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     */
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sækir notanda í gagnagrunn eftir notandanafni.
     *
     * @param username notandanafn þess sem á að sækja
     * @return notandinn sem var sóttur
     * @throws Exception ef notandi með natandanafn {@code username} er ekki til
     */
    public User getUser(String username) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
        statement.setString(1,username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            PaymentInfo paymentInfo = getPaymentInfo(resultSet.getInt("payment_info_id"));
            return new User(resultSet.getString("name"),
                    username,
                    resultSet.getString("password"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("email"),
                    paymentInfo);
        } else {
            throw new Exception("User not found in database");
        }
    }

    private PaymentInfo getPaymentInfo(int id) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment_info WHERE id = ?");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new PaymentInfo(resultSet.getString("card_number"),
                    resultSet.getString("month_valid"),
                    resultSet.getString("year_valid"),
                    resultSet.getString("cvv"));
        } else {
            throw new Exception("Payment information not found in database");
        }
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
