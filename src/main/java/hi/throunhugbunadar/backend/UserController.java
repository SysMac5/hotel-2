package hi.throunhugbunadar.backend;

/**
 * Tilvik af keyrslu.
 */

public class UserController {
    private User user;
    private UserRepository userRepository;

    /**
     * Uppfæra breytingar á notanda í gagnagrunni.
     */
    public void updateUser() {

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
}
