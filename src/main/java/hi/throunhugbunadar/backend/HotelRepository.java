package hi.throunhugbunadar.backend;

import java.util.ArrayList;

public class HotelRepository implements iHotelRepository {
    /**
     * Finnur og skilar lista af hótelum sem uppfylla leit m.t.t. nafn hótels.
     *
     * @param hotelName Nafn á hóteli sem leitað er eftir.
     * @return ArrayList með hótelum sem uppfylla leit.
     */
    @Override
    public ArrayList<Hotel> searchForHotel(String hotelName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Finnur og skilar ArrayList sem inniheldur hótelherbergi sem uppfylla gefin leitarskilyrði.
     *
     * @param criteria Leitarskilyrði sem eru notuð til að leita að hótelherbergjum.
     * @return ArrayList með hótelherbergjum sem uppfylla gefin leitarskilyrði.
     */
    @Override
    public ArrayList<HotelRooms> searchByCriteria(Criteria criteria) {
        throw new UnsupportedOperationException();
    }
}
