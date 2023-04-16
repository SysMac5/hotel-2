package hi.throunhugbunadar.backend;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;

public class HotelRepositoryTest {
    private HotelRepository hotelRepository;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        String url = "jdbc:sqlite:GG_9.db";
        try {
            this.connection = DriverManager.getConnection(url);
            hotelRepository = new HotelRepository(this.connection);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            this.connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSearchForHotelFullt() {
        String[] hotelNames = {"Hótel Íslands", "Sigló Hótel", "Eldey Airport Hotel"};

        for (String hotelName : hotelNames) {
            ArrayList<Hotel> theHotel = hotelRepository.searchForHotel(hotelName);
            Assertions.assertEquals(1, theHotel.size());
            Assertions.assertEquals(hotelName, theHotel.get(0).getName());
        }
    }

    @Test
    public void testSearchForHotelHalft() {
        String[] hotelHalfNames = {"Hótel", "sK", "Ey"};

        for (String hotelHalfName : hotelHalfNames) {
            ArrayList<Hotel> hotels = hotelRepository.searchForHotel(hotelHalfName);
            Assertions.assertTrue(hotels.size() > 1);
            for (Hotel hotel : hotels) {
                Assertions.assertTrue(hotel.getName().contains(hotelHalfName));
            }
        }
    }

    @Test
    public void testSearchForHotelNonexistent() {
        String[] hotelHalfNames = {"okw03j2c3 ons", "kakkalakki", "riss"};

        for (String hotelHalfName : hotelHalfNames) {
            ArrayList<Hotel> hotels = hotelRepository.searchForHotel(hotelHalfName);
            Assertions.assertTrue(hotels.isEmpty());
        }
    }

    @Test
    public void testSearchByCriteria1() {
        Criteria criteria = new Criteria();
        criteria.location = "A";
        criteria.guestCount = 3;
        criteria.arrival = new Date(1679226210);
        criteria.departure = new Date(1679312610);

        ArrayList<HotelRooms> hotelRooms = hotelRepository.searchByCriteria(criteria);
        for (HotelRooms hotelRoom : hotelRooms) {
            Assertions.assertEquals(criteria.guestCount, hotelRoom.getNumberOfGuests());
            Assertions.assertEquals(criteria.location, hotelRoom.getHotel().getLocation());
        }
    }

    @Test
    public void testSearchByCriteria2() {
        Criteria criteria = new Criteria();
        criteria.location = "V";
        criteria.guestCount = 2;
        criteria.arrival = new Date(1679226210);
        criteria.departure = new Date(1679312610);

        ArrayList<HotelRooms> hotelRooms = hotelRepository.searchByCriteria(criteria);
        for (HotelRooms hotelRoom : hotelRooms) {
            Assertions.assertEquals(criteria.guestCount, hotelRoom.getNumberOfGuests());
            Assertions.assertEquals(criteria.location, hotelRoom.getHotel().getLocation());
        }
    }

    @Test
    public void testSearchByCriteria3() {
        Criteria criteria = new Criteria();
        criteria.location = "H";
        criteria.guestCount = 4;
        criteria.arrival = new Date(1679226210);
        criteria.departure = new Date(1679312610);

        ArrayList<HotelRooms> hotelRooms = hotelRepository.searchByCriteria(criteria);
        for (HotelRooms hotelRoom : hotelRooms) {
            Assertions.assertEquals(criteria.guestCount, hotelRoom.getNumberOfGuests());
            Assertions.assertEquals(criteria.location, hotelRoom.getHotel().getLocation());
        }
    }

    @Test
    public void testSearchByCriteriaArrivalAfterDeparture() {
        Criteria criteria = new Criteria();
        criteria.location = "H";
        criteria.guestCount = 4;
        criteria.arrival = new Date(1679312610);
        criteria.departure = new Date(1679226210);

        ArrayList<HotelRooms> hotelRooms = hotelRepository.searchByCriteria(criteria);
        for (HotelRooms hotelRoom : hotelRooms) {
            Assertions.assertEquals(criteria.guestCount, hotelRoom.getNumberOfGuests());
            Assertions.assertEquals(criteria.location, hotelRoom.getHotel().getLocation());
        }
    }
}
