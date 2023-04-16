package hi.throunhugbunadar.backend;

import java.util.ArrayList;

public interface iHotelRepository {
    ArrayList<Hotel> searchForHotel(String hotelName);
    ArrayList<Hotel> searchByCriteria(Criteria criteria);
}
