package hi.throunhugbunadar.backend;

import java.awt.*;
import java.util.List;

/**
 * Hótel.
 */

public class Hotel {
    private String name;
    private String info;
    private java.util.List<Image> imageList;
    private List<HotelRooms> hotelRoomsList;
    private int stars;
    private String location;

    public Hotel(String name, String info, java.util.List<Image> imageList, List<HotelRooms> hotelRoomsList, int stars, String location) {
        this.name = name;
        this.info = info;
        this.imageList = imageList;
        this.hotelRoomsList = hotelRoomsList;
        this.stars = stars;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public List<HotelRooms> getHotelRoomsList() {
        return hotelRoomsList;
    }

    public int getStars() {
        return stars;
    }

    public String getLocation() {
        return location;
    }
}
