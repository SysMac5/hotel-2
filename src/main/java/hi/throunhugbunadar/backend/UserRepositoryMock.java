package hi.throunhugbunadar.backend;

/**
 * Mock object fyrir UserRepository sem inniheldur einn notanda.
 */

public class UserRepositoryMock implements iUserRepository {
    User dummyUser;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     *
     * @throws Exception ef {@code PaymentInfo} í smið kastar villu
     */
    public UserRepositoryMock() throws Exception {
        PaymentInfo dummyPaymentInfo;
        dummyPaymentInfo = new PaymentInfo("8383949472728383", "09", "09", "999");
        dummyUser = new User("Helena", "maggi2370", "egErHeima123", "+354 58 12345", "helena@byko.is", dummyPaymentInfo);
    }

    /**
     * Sækir notanda í gagnagrunn eftir notandanafni.
     *
     * @param username notandanafn þess sem á að sækja
     * @return notandinn sem var sóttur
     * @throws Exception ef notandi með natandanafn {@code username} er ekki til
     */
    public User getUser(String username) throws Exception {
        if(dummyUser.getUsername().equals(username)) {
            return dummyUser;
        } else {
            throw new Exception("No user found.");
        }
    }

    /**
     * Uppfærir notandann í gagnagrunni sem er með sama notandanafn og gefinn {@code user}.
     *
     * @param user uppfærður notandi
     * @return hvort breyting hafi heppanst
     */
    public boolean updateUser(User user) {
        if(dummyUser.getUsername().equals(user.getUsername())){
            dummyUser = user;
            return true;
        }
        return false;
    }
}
