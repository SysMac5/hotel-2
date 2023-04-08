package hi.throunhugbunadar.backend;

import java.util.ArrayList;

/**
 * Listi af hótelherbergjum sem hægt er að sía eftir verði og stjörnum hótela.
 */

public class HotelroomList {
    private ArrayList<HotelRooms> hotelroomList;
    private int minPrice = 0;
    private int maxPrice = Integer.MAX_VALUE;
    private int minStars = 1;
    private int maxStars = 5;

    /**
     * Smiður sem tekur inn lista af hótelherbergjum.
     *
     * @param hotelroomList Listi af hótelherbergjum.
     */
    public HotelroomList(ArrayList<HotelRooms> hotelroomList) {
        this.hotelroomList = hotelroomList;
    }

    /**
     * Skilar ArrayList með hótelherbergjum sem uppfylla núverandi skilyrði.
     *
     * @return ArrayList með hótelherbergjum sem uppfylla núverandi skilyrði.
     */
    public ArrayList<HotelRooms> getList() {
        ArrayList<HotelRooms> filteredList = new ArrayList<>();

        for (HotelRooms room : hotelroomList) {
            if (room.getPricePerNight() >= minPrice && room.getPricePerNight() <= maxPrice
                    && room.getHotel().getStars() >= minStars && room.getHotel().getStars() <= maxStars) {
                filteredList.add(room);
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
