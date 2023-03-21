package hi.throunhugbunadar.backend;

import java.awt.*;
import java.util.List;

/**
 * Listi af hótelherbergjum.
 */

public class HotelroomList {
    private java.util.List<HotelRooms> hotelroomList;
    private int minPrice = 0;
    private int maxPrice = Integer.MAX_VALUE;
    private int minStars = 1;
    private int maxStars = 5;

    public HotelroomList(java.util.List<HotelRooms> hotelroomList) {
        this.hotelroomList = hotelroomList;
    }

    public List<HotelRooms> getList() {
        /*
            FILTER
        */
        return hotelroomList;
    }

    public void filterByPrice(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public void filterByStars(int minStars, int maxStars) {
        this.minStars = minStars;
        this.maxStars = maxStars;
    }

    public void restartFilter() {
        minPrice = 0;
        maxPrice = Integer.MAX_VALUE;
        minStars = 1;
        maxStars = 5;
    }
}
