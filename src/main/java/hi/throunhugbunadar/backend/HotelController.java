package hi.throunhugbunadar.backend;

import java.util.ArrayList;

// ATH, hafa documention hér?

public class HotelController {
    private final iHotelRepository hotelRepository;
    private HotelList hotelList;

    /**
     * Smiður fyrir HotelController.
     *
     * @param hotelRepository Sér um samskipti við gagnagrunn.
     */
    public HotelController(iHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    /**
     * Finnur og skilar lista af hótelum sem uppfylla leit m.t.t. nafn hótels.
     *
     * @param hotelName Nafn á hóteli sem leitað er eftir.
     * @return ArrayList með hótelum sem uppfylla leit.
     */
    public ArrayList<Hotel> searchForHotel(String hotelName) throws Exception {
        if(hotelName.equals("")) throw new Exception();

        try {
            return hotelRepository.searchForHotel(hotelName);
        } catch (Exception e) {
            // ATH, skila hverju?
            throw new Exception();
        }
    }

    /**
     * Finnur og skilar HotelroomList hlut sem inniheldur hótelherbergi sem uppfylla gefin leitarskilyrði.
     *
     * @param criteria Leitarskilyrði sem eru notuð til að leita að hótelherbergjum.
     * @return HotelroomList með hótelherbergjum sem uppfylla gefin leitarskilyrði.
     */
    public HotelList searchByCriteria(Criteria criteria) throws Exception {
        if (criteria == null) throw new NullPointerException();

        try {
            return new HotelList(hotelRepository.searchByCriteria(criteria), criteria);
        } catch (Exception e) {
            // ATH, skila hverju?
            throw new Exception();
        }
    }
}
