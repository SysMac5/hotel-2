package hi.throunhugbunadar.backend;

public enum Region {
    SUDURLAND ("S"),
    NORDURLAND ("N"),
    AUSTURLAND("A"),
    VESTURLAND("V"),
    HOFUDBORGARSVAEDID("H");

    private final String location;

    Region(String location) {
        this.location = location;
    }

    public String getLocation () {
        return location;
    }
}
