package hi.throunhugbunadar.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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


    public Criteria getCriteria() {
        return criteria;
    }


    public int getPrice(Hotel hotel) throws Exception {
        //ATH, geri ekki ráð fyrir að það gætu verið tvær týpur með sama gildi í guestCount
        for (HotelRooms room: hotel.getHotelRoomsList()) {
            if (room.getNumberOfGuests() == criteria.guestCount) {
                return room.getPricePerNight();
            }
        }
        throw new Exception();
    }

    /**
     * Raða lista eftir verði.
     * @return röðuðum lista
     */
    public ArrayList<Hotel> sortByPrice() {
        ArrayList<Hotel> sortedList = new ArrayList<Hotel>(getList());
        sortedList.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                double price1 = h1.getHotelRoom(criteria.guestCount).getPricePerNight();
                double price2 = h2.getHotelRoom(criteria.guestCount).getPricePerNight();
                return Double.compare(price1, price2);
            }
        });
        return sortedList;
    }

    /**
     * Raða lista eftir stjörnum.
     * @return röðuðum lista
     */
    public ArrayList<Hotel> sortByStars() {
        ArrayList<Hotel> sortedList = new ArrayList<Hotel>(getList());
        sortedList.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                double stars1 = h1.getStars();
                double stars2 = h2.getStars();
                return Double.compare(stars2, stars1);
            }
        });
        return sortedList;
    }
}
