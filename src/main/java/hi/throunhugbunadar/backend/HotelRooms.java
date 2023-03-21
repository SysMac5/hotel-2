package hi.throunhugbunadar.backend;

/**
 * Herbergi af sömu tegund á ákveðnu hóteli.
 */

public class HotelRooms {
    private Hotel hotel;
    private int capacity;
    private int numberOfGuests;
    private int pricePerNight;

    public HotelRooms(Hotel hotel, int capacity, int numberOfGuests, int pricePerNight) {
        this.hotel = hotel;
        this.capacity = capacity;
        this.numberOfGuests = numberOfGuests;
        this.pricePerNight = pricePerNight;
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
}
