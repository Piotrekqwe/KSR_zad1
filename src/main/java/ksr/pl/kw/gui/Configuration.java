package ksr.pl.kw.gui;

import ksr.pl.kw.classification.KnnCalculator;
import ksr.pl.kw.extraction.ArticleCharacteristic;

public class Configuration {
    private static KnnCalculator calculator;

    public static KnnCalculator getCalculator() {
        return calculator;
    }

    public static void setCalculator(ArticleCharacteristic[] learningCollection) {
        Configuration.calculator = new KnnCalculator(learningCollection);
    }
}
