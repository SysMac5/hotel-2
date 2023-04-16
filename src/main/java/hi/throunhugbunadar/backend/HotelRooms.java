package hi.throunhugbunadar.backend;

/**
 * Herbergi af sömu tegund á ákveðnu hóteli.
 */

public class HotelRooms {
    private final int capacity;
    private final int numberOfGuests;
    private final int pricePerNight;
    private final int id;

    /**
     * Smiður fyrir hótelherbergjategund.
     *
     * @param capacity fjöldi herbergja af hóteltegund
     * @param numberOfGuests fjöldi sem kemst í hvert herbergi herbergistegundar
     * @param pricePerNight verð herbergjategundar fyrir eina nótt
     * @param id auðkenni herbergjategundar í gagnagrunni
     */
    public HotelRooms(int capacity, int numberOfGuests, int pricePerNight, int id) {
        this.capacity = capacity;
        this.numberOfGuests = numberOfGuests;
        this.pricePerNight = pricePerNight;
        this.id = id;
    }

    /**
     * Skilar fjölda herbergja af hóteltegund.
     *
     * @return fjöldi herbergja
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Skilar fjölda sem kemst í hvert herbergi af herbergistegundinni.
     *
     * @return
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Skilar verði fyrir eina nótt í hverju herbergi af herbergistegundinni.
     *
     * @return verð fyrir nóttina
     */
    public int getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Skilar auðkenni hótelherbergistegundarinnar í gagnagrunninum.
     *
     * @return auðkennið
     */
    public int getId() {
        return id;
    }
}
