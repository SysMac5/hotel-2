package hi.throunhugbunadar.backend;

import java.util.ArrayList;

public class HotelRepository implements iHotelRepository {
    @Override
    public ArrayList<Hotel> searchForHotel(String hotelName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<HotelRooms> searchByCriteria(Criteria criteria) {
        throw new UnsupportedOperationException();
    }
}
