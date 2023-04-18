package hi.throunhugbunadar.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hótel.
 */

public class Hotel{
    private final int id;
    private final String name;
    private final String info;
    private final ArrayList<File> imageList;
    private final List<HotelRooms> hotelRoomsList;
    private final int stars;
    private final String location;

    public Hotel(int id, String name, String info, ArrayList<File> imageList, List<HotelRooms> hotelRoomsList, int stars, String location) {
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

    public List<File> getImageList() {
        return imageList;
    }

    public List<HotelRooms> getHotelRoomsList() {
        return hotelRoomsList;
    }

    public HotelRooms getHotelRoom(int guestCount) {
        for (HotelRooms hotelRooms : hotelRoomsList) {
            if (hotelRooms.getNumberOfGuests() == guestCount) {
                return hotelRooms;
            }
        }
        throw new RuntimeException("HotelRooms with guest count '" + guestCount + "' not found in hotel '" + name + "'");
    }

    public int getStars() {
        return stars;
    }

    public String getLocation() {
        return location;
    }
}
