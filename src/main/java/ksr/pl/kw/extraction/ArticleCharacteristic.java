package ksr.pl.kw.extraction;

import ksr.pl.kw.classification.Country;

public class ArticleCharacteristic {
    private int ID;
    private Country country;
    //characteristicVector prawdopodobnie bedzie trzeba zamienic na pola z cechami
    private double[] characteristicVector;

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

    public ArticleCharacteristic(int ID, Country country, double[] characteristicVector) {
        this.ID = ID;
        this.country = country;
        this.characteristicVector = characteristicVector;
    }

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
}
