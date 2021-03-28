package ksr.pl.kw.gui;

import ksr.pl.kw.classification.ArticleClassificationService;
import ksr.pl.kw.classification.KnnCalculator;

public class Configuration {
    private static final KnnCalculator calculator = new KnnCalculator();
    private static final ArticleClassificationService service = new ArticleClassificationService();

    public static KnnCalculator getCalculator() {
        return calculator;
    }

    public static ArticleClassificationService getService() {
        return service;
    }
}
