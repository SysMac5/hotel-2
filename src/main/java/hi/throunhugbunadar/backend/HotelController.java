package hi.throunhugbunadar.backend;

import java.util.ArrayList;

public class HotelController {
    private final iHotelRepository hotelRepository;
    private HotelroomList hotelroomList;

    public HotelController(iHotelRepository hotelRepository, HotelroomList hotelroomList) {
        this.hotelRepository = hotelRepository;
        this.hotelroomList = hotelroomList;
    }

    public ArrayList<Hotel> searchForHotel(String hotelName) {
        throw new UnsupportedOperationException();
    }

    public HotelroomList searchByCriteria(Criteria criteria) {
        throw new UnsupportedOperationException();
    }
}
