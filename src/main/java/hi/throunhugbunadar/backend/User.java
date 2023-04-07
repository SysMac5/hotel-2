package hi.throunhugbunadar.backend;

/**
 * Notandi hugbúnaðar.
 */

public class User {
    private String name;
    private final String username;
    private String password;
    private String phoneNumber;
    private String email;
    private PaymentInfo paymentInfo;

    /**
     * Býr til nýjan notanda.
     *
     * @param name fullt nafn
     * @param username notandanafn
     * @param password lykilorð (hakkatala)
     * @param phoneNumber símanúmer
     * @param email netfang
     * @param paymentInfo greiðsluupplýsingar
     */
    public User(String name, String username, String password, String phoneNumber, String email, PaymentInfo paymentInfo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.paymentInfo = paymentInfo;
    }

    /**
     * Skilar nafni notanda.
     *
     * @return nafn notanda, þ.e. {@link User#name}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Uppfærir nafn notanda, þ.e. {@link User#name}.
     *
     * @param name nýtt nafn notanda
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Skilar notandanafni notanda.
     *
     * @return skilar notandanafni notanda, þ.e. {@link User#username}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Athugar ef innslegið lykilorð stemmi við lykilorð notanda, þ.e. {@link User#password}.
     *
     * @param password innslegið lykilorð
     * @return hvort lykilorðin stemma
     */
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Breytir lykilorði notanda, þ.e. {@link User#password}.
     *
     * @param password nýtt lykilorð
     * @throws Exception ef nýtt lykilorð er tómastrengurinn
     */
    public void changePassword(String password) throws Exception {
        if(password.equals("")) throw new Exception();
        this.password = password;
    }

    /**
     * Skilar símanúmeri notanda.
     *
     * @return símanúmer notanda, þ.e. {@link User#phoneNumber}
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Uppfærir símanúmer notanda, þ.e. {@link User#phoneNumber}.
     *
     * @param phoneNumber nýtt símanúmer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Skilar netfangið notanda.
     *
     * @return netfang notanda, þ.e. {@link User#email}
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Uppfærir netfang notanda, þ.e. {@link User#email}.
     *
     * @param email netfang notanda
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Skilar greiðsluupplýsingum notanda.
     *
     * @return greiðsluupplýsingar notanda, þ.e. {@link User#paymentInfo}
     */
    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    /**
     * Uppfærir greiðsluupplýsingar notanda, þ.e. {@link User#paymentInfo}.
     *
     * @param paymentInfo nýjar greiðsluupplýsingar
     */
    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
