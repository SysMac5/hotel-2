package hi.throunhugbunadar.backend;

/**
 * Herbergi af sömu tegund á ákveðnu hóteli.
 */

public class HotelRooms {
    private final Hotel hotel;
    private final int capacity;
    private final int numberOfGuests;
    private final int pricePerNight;
    private final int id;

    public HotelRooms(Hotel hotel, int capacity, int numberOfGuests, int pricePerNight, int id) {
        this.hotel = hotel;
        this.capacity = capacity;
        this.numberOfGuests = numberOfGuests;
        this.pricePerNight = pricePerNight;
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getId() {
        return id;
    }

    /*@Override
    public boolean equals(HotelRooms hotelRooms) {
        return this.id == hotel.getId();
    }*/
}
