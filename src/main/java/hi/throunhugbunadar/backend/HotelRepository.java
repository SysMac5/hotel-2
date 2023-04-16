package hi.throunhugbunadar.backend;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class HotelRepository implements iHotelRepository {
    Connection connection;

    public HotelRepository(Connection connection) {
        this.connection = connection;
    }

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

    /**
     * Hjálparfall sem skilar hversu mörg herbergi af
     * hotelherbergjategund eru laus á gefnu tímabili.
     *
     * @param arrival komudagur
     * @param departure brottfarardagur
     * @param hotelRoomId auðkenni hotelherbergjategundar
     * @return fjöldi herbergja laus
     */
    private int howManyAvailable(Date arrival, Date departure, int hotelRoomId) {
        throw new UnsupportedOperationException();
    }

    /**
     * Hjálparfall skilar fjölda herbergja af ákveðinni hotelherbergjategund
     * sem eru laus nóttina eftir {@code dayBefore}.
     *
     * @param dayBefore dagur fyrir nóttina sem er athuguð
     * @param hotelRoomId auðkenni hotelherbergjategundar
     * @return fjöldi herbergja laus
     */
    private int howManyAvailable(Date dayBefore, int hotelRoomId) {
        throw new UnsupportedOperationException();
    }
}
