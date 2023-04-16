package hi.throunhugbunadar.backend;

import java.util.ArrayList;

/**
 * Listi af hótelherbergjum sem hægt er að sía eftir verði og stjörnum hótela.
 */

public class HotelList {
    private final ArrayList<Hotel> hotelList;
    private int minPrice = 0;
    private int maxPrice = Integer.MAX_VALUE;
    private int minStars = 1;
    private int maxStars = 5;
    private final Criteria criteria;

    /**
     * Smiður sem tekur inn lista af hótelherbergjum.
     *
     * @param hotelList Listi af hótelum.
     */
    public HotelList(ArrayList<Hotel> hotelList, Criteria criteria) {
        this.hotelList = hotelList;
        this.criteria = criteria;
    }

    /**
     * Skilar ArrayList með hótelherbergjum sem uppfylla núverandi skilyrði.
     *
     * @return ArrayList með hótelherbergjum sem uppfylla núverandi skilyrði.
     */
    public ArrayList<Hotel> getList() {
        ArrayList<Hotel> filteredList = new ArrayList<>();

        for (Hotel hotel : hotelList) {
            HotelRooms theHotelRoom = null;
            for (HotelRooms hotelRoom : hotel.getHotelRoomsList()) {
                if (hotelRoom.getNumberOfGuests() == criteria.guestCount) {
                    theHotelRoom = hotelRoom;
                    break;
                }
            }
            if (theHotelRoom == null) continue;
            if (theHotelRoom.getPricePerNight() >= minPrice && theHotelRoom.getPricePerNight() <= maxPrice
                    && hotel.getStars() >= minStars && hotel.getStars() <= maxStars) {
                filteredList.add(hotel);
            }
        }

        return filteredList;
    }

    /**
     * Stillir lágmarks- og hámarksverð á hótelherbergjum.
     *
     * @param minPrice lágmarksverð á hótelherberjum.
     * @param maxPrice hámarksverð á hótelherbergjum.
     */
    public void filterByPrice(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    /**
     * Stillir lágmarks- og hámarksstjörnufjölda á hótelum.
     * @param minStars lágmarksstjörnufjöldi á hótelum.
     * @param maxStars hámarksstjörnufjöldi á hótelum.
     */
    public void filterByStars(int minStars, int maxStars) {
        this.minStars = minStars;
        this.maxStars = maxStars;
    }

    /**
     * Endurræsir stillingar á lágmarki og hámarki bæði á verði hótelherbergja og stjörnufjölda hótela.
     */
    public void restartFilter() {
        minPrice = 0;
        maxPrice = Integer.MAX_VALUE;
        minStars = 1;
        maxStars = 5;
    }
}
