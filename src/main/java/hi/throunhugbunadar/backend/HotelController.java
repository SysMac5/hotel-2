package hi.throunhugbunadar.backend;

import java.util.ArrayList;

/**
 * Stýringar fyrir hótelin.
 */

public class HotelController {
    private final iHotelRepository hotelRepository;

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
     * @throws Exception ef {@code hotelName} er {@code null}
     */
    public ArrayList<Hotel> searchForHotel(String hotelName) throws Exception {
        if(hotelName.equals("")) throw new Exception("Searched for an empty String.");
        return hotelRepository.searchForHotel(hotelName);
    }

    /**
     * Finnur og skilar HotelroomList hlut sem inniheldur hótelherbergi sem uppfylla gefin leitarskilyrði.
     *
     * @param criteria Leitarskilyrði sem eru notuð til að leita að hótelherbergjum.
     * @return HotelroomList með hótelherbergjum sem uppfylla gefin leitarskilyrði.
     * @throws NullPointerException ef {@code criteria} er {@code null}
     */
    public HotelList searchByCriteria(Criteria criteria) throws NullPointerException {
        if (criteria == null) throw new NullPointerException("Criteria is null.");
        return new HotelList(hotelRepository.searchByCriteria(criteria), criteria);
    }
}
