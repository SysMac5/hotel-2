package hi.throunhugbunadar.backend;

import java.sql.*;
import java.util.ArrayList;

import static hi.throunhugbunadar.backend.HelperFunctions.*;

public class BookingRepository implements iBookingRepository {
    Connection connection;

    /**
     * Býr til nýja tengingu við gagnagrunninn.
     */
    public BookingRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean reserveRooms(Reservation reservation) {
        PreparedStatement statement1 = null;
        ResultSet result1;
        int maxId = 0;
        try {
            statement1 = connection.prepareStatement("Select Max(id) as max from reservations;");
            result1 = statement1.executeQuery();

            maxId = result1.getInt("max");
        } catch (SQLException e) {
            return false;
        }

        maxId = maxId + 1;


        PreparedStatement statement2 = null;
        try {
            statement2 = connection.prepareStatement("Insert into reservations (id, hotel_rooms_id, user_id, number_of_rooms, arrival_date, departure_date) values (?,?,?,?,?,?)");

        } catch (SQLException e) {
            return false;
        }

        try {
            statement2.setInt(1, maxId);
            statement2.setInt(2, reservation.getHotelRooms().getId());
            statement2.setString(3, reservation.getUser().getUsername());
            statement2.setInt(4, reservation.getNumberOfRooms());
            statement2.setDate(5, reservation.getArrival());
            statement2.setDate(6, reservation.getDeparture());

            ResultSet result2;
            result2 = statement2.executeQuery(); // Keyra skipunina að setja inn í gagnagrunn

            PreparedStatement statement3 = null;
            ResultSet result3;

            statement3 = connection.prepareStatement("Select Max(id) as max from reservations;"); // Keyrum aftur til að tékka hvort að bókunin hafi verið sett inn
            result3 = statement3.executeQuery();

            int maxIdCheck = 0;
            maxIdCheck = result3.getInt("max");


            if (maxId == maxIdCheck) return true; // You should use the equals() method to compare the values of two Integer objects. => maxId.equals(maxIdCheck)
            else return false;


        } catch (SQLException e) {
            return false;
        }
    }

    public int howManyAvailable(Reservation reservation) {
        try {
            return HelperFunctions.howManyAvailable(reservation.getArrival(), reservation.getDeparture(), reservation.getHotelRooms().getId(), connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<PaymentInfo> getPaymentInfo(ResultSet resultSet) throws SQLException {
        ArrayList<PaymentInfo> addedPaymentInfo = new ArrayList<>();

        while(true){
            try {
                addedPaymentInfo.add(new PaymentInfo(
                        resultSet.getString("card_number"), // Kannski ekki String?
                        resultSet.getString("month_valid"),
                        resultSet.getString("year_valid"),
                        resultSet.getString("cvv")));
                if (!resultSet.next()) break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } return addedPaymentInfo;
    };

    private ArrayList<User> getUsers(ResultSet resultSet) throws SQLException {
        ArrayList<User> addedUser = new ArrayList<>();

        while(true){
            try {
                // PaymentInfo
                int count2 = 0;
                PaymentInfo paymentInfoFromList;
                ArrayList<PaymentInfo> listOfPaymentInfo = getPaymentInfo(resultSet); // In the getUsers method, there is a call to getPaymentInfo(resultSet) inside a loop. This could potentially cause an infinite loop, as getPaymentInfo() does not move the cursor of the resultSet to the next row. You may need to modify the code to advance the cursor of the resultSet after calling getPaymentInfo().
                paymentInfoFromList = listOfPaymentInfo.get(count2); // Fyrsta stakið og koll af kolli

                addedUser.add(new User(
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("email"),
                        paymentInfoFromList
                        ));

                count2 = count2 + 1;
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } return addedUser;
    };

    // resultSet.getList<Image>("file_name")

    private ArrayList<Hotel> getHotels(ResultSet resultSet) throws SQLException {
        ArrayList<Hotel> addedHotels = new ArrayList<>();

        while(true){
            try {
                addedHotels.add(new Hotel(
                        resultSet.getInt("id"), // ATH virkar kannski ekki af því það eru nokkrir dálkar sem heita bara id??
                        resultSet.getString("hotel_name"),
                        resultSet.getString("information"),
                        getImageList(resultSet.getInt("id"), connection),
                        getHotelRoomsList(resultSet.getInt("id"), connection),
                        resultSet.getInt("stars"),
                        resultSet.getString("location")

                ));
                if (!resultSet.next()) break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } return addedHotels;
    };


    private ArrayList<HotelRooms> getHotelRooms(ResultSet resultSet) throws SQLException {
        ArrayList<HotelRooms> addedHotelRooms = new ArrayList<>();

        while(true){
            try {
                addedHotelRooms.add(new HotelRooms(
                        resultSet.getInt("capacity"),
                        resultSet.getInt("number_of_guests"),
                        resultSet.getInt("price_per_night"),
                        resultSet.getInt("id")));

                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } return addedHotelRooms;
    };

    public ArrayList<Reservation> getReservations(Hotel hotel) { // óklárað ! !
        PreparedStatement statement1;
        try {
            ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();
            statement1 = connection.prepareStatement("Select * from reservations t1 left join hotel_rooms t2 on t1.hotel_rooms_id = t2.id join hotels t3 on t2.hotel_id = t3.id join users t4 on t4.username = t1.user_id join payment_info t5 on t4.payment_info_id = t5.id where t3.id = ?");
            // Það þarf að bæta við fleiri töflum, ss setja allar töflurnar saman miðað við allt sem er beðið um í reservations, við vitum að það er ekki nauðsynlegt fyrir þær upplýsingar sem við þurfum að sýna úr þessu falli, get ég komist hjá þessari svaka vinnu?
            statement1.setInt(1, hotel.getId());
            ResultSet result = statement1.executeQuery();
            // t1.hotel_rooms_id, t1.user_id, t1.number_of_rooms, t1.arrival_date, t1.departure_date, t2.number_of_guests, t4.phone_number, t4.name, t4.email
            // int first = list.get(0);

            while(result.next()){
                // User
                int count1 = 0;
                User userFromList;
                ArrayList<User> listOfUsers = getUsers(result);
                userFromList = listOfUsers.get(count1);

                // HotelRooms
                HotelRooms hotelRoomsFromList;
                ArrayList<HotelRooms> listOfHotelRooms = getHotelRooms(result);
                hotelRoomsFromList = listOfHotelRooms.get(count1);

                // Hotel
                Hotel hotelFromList;
                ArrayList<Hotel> listOfHotels = getHotels(result); // Kalla á fallið
                hotelFromList = listOfHotels.get(count1); // Fyrsta stakið og koll af kolli


                // Data objt = new Data(name, address, contact); // Creating a new object
                // Contacts.add(objt); // Adding it to the list

                Reservation newReservation = new Reservation(userFromList,
                        hotelRoomsFromList,
                        hotelFromList,
                        result.getDate("arrival_date"),
                        result.getDate("departure_date"),
                        result.getInt("number_of_rooms"));


                reservationsList.add(newReservation);

                count1 = count1 + 1;

            }return reservationsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
