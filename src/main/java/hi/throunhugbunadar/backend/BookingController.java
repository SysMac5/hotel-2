package hi.throunhugbunadar.backend;

import java.sql.Date;
import java.util.List;

public class BookingController {
    private final iBookingRepository hotelRepository;

    /**
     * Smiður fyrir klasann.
     */
    public BookingController(iBookingRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Skilar fjölda herbergja sem eru laus yfir tímabil frá {@code arrival} til {@code departure}.
     *
     * @param arrival fyrsti dagur tímabils
     * @param departure síðasti dagur tímabils
     * @return fjöldi herbergja laus yfir tímabilið
     */
    public int howManyAvailable(Date arrival, Date departure) {
        int min = Integer.MAX_VALUE;
        for (Date day = arrival; day.before(departure); day = nextDay(day)) {
            if (howManyAvailable(day) < min) {
                min = howManyAvailable(day);
            }
        }
        return min;
    }

    /**
     * Hjálparfall sem skilar deginum eftir {@code day}.
     *
     * @param day dagur
     * @return dagurinn eftir {@code day}
     */
    private Date nextDay(Date day) {
        if (day == null) throw new NullPointerException();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
        java.util.Date dayAfter = calendar.getTime();
        return new java.sql.Date(dayAfter.getTime());
    }

    /**
     * Skilar fjölda herbergja sem eru laus nóttina eftir {@code dayBefore}.
     *
     * @param dayBefore dagur fyrir nóttina sem er athuguð
     * @return fjöldi herbergja sem eru laus
     */
    public int howManyAvailable(Date dayBefore) {
        return hotelRepository.howManyAvailable(dayBefore);
    }

    public boolean reserveRooms(Reservation reservation) { // óklárað ! !
        throw new UnsupportedOperationException();
    }

    public List<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        throw new UnsupportedOperationException();
    }
}
