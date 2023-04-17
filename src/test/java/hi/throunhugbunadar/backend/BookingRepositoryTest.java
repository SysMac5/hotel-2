package hi.throunhugbunadar.backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;

public class BookingRepositoryTest {
    private BookingRepository bookingRepository;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        String url = "jdbc:sqlite:src/main/resources/GG_9.db";
        try {
            this.connection = DriverManager.getConnection(url);
            bookingRepository = new BookingRepository(this.connection);
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
    public void testGetReservations() {
        HotelRepository hotelRepository = new HotelRepository(this.connection);
        Hotel hotel = hotelRepository.searchForHotel("Hótel Dyrhólaey").get(0);
        ArrayList<Reservation> reservations = bookingRepository.getReservations(hotel);

        for (Reservation reservation : reservations) {
            Assertions.assertEquals(hotel.getName(), reservation.getHotel().getName());
            Assertions.assertEquals(hotel, reservation.getHotel());
        }
    }

    @Test
    public void testReserve() {
        UserRepository userRepository = new UserRepository(this.connection);
        HotelRepository hotelRepository = new HotelRepository(this.connection);
        Hotel hotel = hotelRepository.searchForHotel("Hótel Íslands").get(0);
        try {
            User user = userRepository.getUser("maggi");
            HotelRooms hotelRooms = new HotelRooms(50,4,25000,143);
            Reservation reservation = new Reservation(user,hotel,new Date(1679226210),new Date(1679312610),hotelRooms.getNumberOfGuests(), 1);
            Assertions.assertTrue(bookingRepository.reserveRooms(reservation));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void testReserveArrivalAfterDeparture() {
        UserRepository userRepository = new UserRepository(this.connection);
        HotelRepository hotelRepository = new HotelRepository(this.connection);
        Hotel hotel = hotelRepository.searchForHotel("Hótel Íslands").get(0);
        try {
            User user = userRepository.getUser("maggi");
            HotelRooms hotelRooms = new HotelRooms(50,4,25000,143);
            Assertions.assertThrows(Exception.class, () -> new Reservation(user, hotel, new Date(1679312610), new Date(1679226210), hotelRooms.getNumberOfGuests(),1));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
