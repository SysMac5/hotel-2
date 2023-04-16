package hi.throunhugbunadar.backend;

import java.awt.*;
import java.util.List;

/**
 * Hótel.
 */

public class Hotel {
    private final int id;
    private String name;
    private String info;
    private java.util.List<Image> imageList;
    private List<HotelRooms> hotelRoomsList;
    private int stars;
    private String location;

    public Hotel(int id, String name, String info, java.util.List<Image> imageList, List<HotelRooms> hotelRoomsList, int stars, String location) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.imageList = imageList;
        this.hotelRoomsList = hotelRoomsList;
        this.stars = stars;
        this.location = location;
    }

    public int getId() {
        return id;
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
