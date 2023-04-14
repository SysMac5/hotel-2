package hi.throunhugbunadar.backend;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

// ATH, hafa documention hér?

public class HotelController {
    private final iHotelRepository hotelRepository;
    private HotelroomList hotelroomList;

    /**
     * Smiður fyrir HotelController.
     *
     * @param hotelRepository Sér um samskipti við gagnagrunn.
     * @param hotelroomList Listi með hótelherbergjum.
     */
    public HotelController(iHotelRepository hotelRepository, HotelroomList hotelroomList) {
        this.hotelRepository = hotelRepository;
        // ATH, sleppa að frumstilla hotelroomList?
        this.hotelroomList = hotelroomList;
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
    public HotelroomList searchByCriteria(Criteria criteria) throws Exception {
        if (criteria == null) throw new NullPointerException();

        try {
            return new HotelroomList(hotelRepository.searchByCriteria(criteria));
        } catch (Exception e) {
            // ATH, skila hverju?
            throw new Exception();
        }
    }
}
