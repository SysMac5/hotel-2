package hi.throunhugbunadar.backend;

import java.awt.*;
import java.util.ArrayList;

/**
 * Listi af hótelherbergjum.
 */

public class HotelroomList {
    private ArrayList<HotelRooms> hotelroomList;
    private int minPrice = 0;
    private int maxPrice = Integer.MAX_VALUE;
    private int minStars = 1;
    private int maxStars = 5;

    public HotelroomList(ArrayList<HotelRooms> hotelroomList) {
        this.hotelroomList = hotelroomList;
    }

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
