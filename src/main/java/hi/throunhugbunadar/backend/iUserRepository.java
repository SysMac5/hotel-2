package hi.throunhugbunadar.backend;

public interface iUserRepository {

    /**
     * Sækir notanda í gagnagrunn eftir notandanafni.
     *
     * @param username notandanafn þess sem á að sækja
     * @return notandinn sem var sóttur
     * @throws Exception ef notandi með natandanafn {@code username} er ekki til
     */
    User getUser(String username) throws Exception;

    /**
     * Uppfærir notandann í gagnagrunni sem er með sama notandanafn og gefinn {@code user}.
     *
     * @param user uppfærður notandi
     * @return hvort breyting hafi heppanst
     */
    boolean updateUser(User user) ;

    Owner getOwner(String username) throws Exception;
}
