package ksr.pl.kw.extraction;

import ksr.pl.kw.classification.Country;

public class ArticleCharacteristic {
    public static final int NUMBER_OF_TRAITS = 10;
    private int ID;
    private Country country;

    private int sentencesAmount;
    private int digitsAmount;
    private int shortWordsAmount; //words < 4 characters
    private int longWordsAmount; //words > 10 characters
    private int textLength;
    private String largestAmountCitiesCountry;
    private String currency;//USD,EUR etc.
    private String dateFormat;
    private String lengthUnit;
    private String temperatureUnit;

    public ArticleCharacteristic(int ID, Country country, int sentencesAmount, int digitsAmount, int shortWordsAmount,
                                 int longWordsAmount, int textLength, String largestAmountCitiesCountry, String currency, String dateFormat,
                                 String lengthUnit, String temperatureUnit) {
        this.ID = ID;
        this.country = country;
        this.sentencesAmount = sentencesAmount;
        this.digitsAmount = digitsAmount;
        this.shortWordsAmount = shortWordsAmount;
        this.longWordsAmount = longWordsAmount;
        this.textLength = textLength;
        this.largestAmountCitiesCountry = largestAmountCitiesCountry;
        this.currency = currency;
        this.dateFormat = dateFormat;
        this.lengthUnit = lengthUnit;
        this.temperatureUnit = temperatureUnit;
    }

    public int getID() {
        return ID;
    }

    public Country getCountry() {
        return country;
    }

    public int getSentencesAmount() {
        return sentencesAmount;
    }

    public int getDigitsAmount() {
        return digitsAmount;
    }

    public int getShortWordsAmount() {
        return shortWordsAmount;
    }

    public int getLongWordsAmount() {
        return longWordsAmount;
    }

    public int getTextLength() {
        return textLength;
    }

    public String getLargestAmountCitiesCountry() {
        return largestAmountCitiesCountry;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getLengthUnit() {
        return lengthUnit;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }
}
