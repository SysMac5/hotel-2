package hi.throunhugbunadar.backend;

public class UserRepository {
    private final Database dbInstance;
    User dummyUser;

    public UserRepository() {
        dbInstance = new Database();
        PaymentInfo dummyPaymentInfo = new PaymentInfo("8383949472728383", "09", "09", "999");
        dummyUser = new User("Helena", "helena5", "helenaRules101", "+354 58 12345", "helena@byko.is", dummyPaymentInfo);
    }

    public User getUser(String username) throws Exception {
        if(dummyUser.getUsername().equals(username)) {
            return dummyUser;
        } else {
            throw new Exception("No user found.");
        }
    }

    public boolean updateUser(User user) {
        if(dummyUser.getUsername().equals(user.getUsername())){
            dummyUser = user;
            return true;
        }
        return false;
    }
}
