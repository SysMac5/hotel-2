package hi.throunhugbunadar.backend;

/**
 * Eigandi hótels.
 */

public class Owner extends User {

    private final Hotel hotel;

    /**
     * Býr til nýjan notanda.
     *
     * @param name        fullt nafn
     * @param username    notandanafn
     * @param password    lykilorð (hakkatala)
     * @param phoneNumber símanúmer
     * @param email       netfang
     * @param paymentInfo greiðsluupplýsingar
     */
    public Owner(String name, String username, String password, String phoneNumber, String email, PaymentInfo paymentInfo, Hotel hotel) {
        super(name, username, password, phoneNumber, email, paymentInfo);
        this.hotel = hotel;
    }

    /**
     * Skilar hóteli sem notandi á.
     *
     * @return hótel
     */
    public Hotel getHotel() {
        return hotel;
    }
}
