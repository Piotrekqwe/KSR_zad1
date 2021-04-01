package ksr.pl.kw.classification;

import java.util.Arrays;

public enum Country {
    WEST_GERMANY(0),
    USA(1),
    FRANCE(2),
    UK(3),
    CANADA(4),
    JAPAN(5);

    public final int id;

    Country(int id) {
        this.id = id;
    }

    public static final int NUMBER_OF_COUNTRIES = 6;

    public static Country findById(int id) {
        return Arrays.stream(Country.values()).filter(country1 -> country1.id == id).findAny().orElseThrow(IllegalStateException::new);
    }
}
