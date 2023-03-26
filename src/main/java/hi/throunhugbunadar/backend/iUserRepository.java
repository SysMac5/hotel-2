package hi.throunhugbunadar.backend;

public interface iUserRepository {

    public User getUser(String username) throws Exception;

    public boolean updateUser(User user);
}
