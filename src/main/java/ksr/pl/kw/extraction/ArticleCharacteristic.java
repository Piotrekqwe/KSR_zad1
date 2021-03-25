package ksr.pl.kw.extraction;

import ksr.pl.kw.classification.Country;

public class ArticleCharacteristic {
    private int ID;
    private Country country;
    private double[] characteristicVector;

    public ArticleCharacteristic(int ID, Country country, double[] characteristicVector) {
        this.ID = ID;
        this.country = country;
        this.characteristicVector = characteristicVector;
    }
}
