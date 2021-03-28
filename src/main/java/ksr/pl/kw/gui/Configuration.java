package ksr.pl.kw.gui;

import ksr.pl.kw.classification.ArticleClassificationService;
import ksr.pl.kw.classification.KnnCalculator;
import ksr.pl.kw.extraction.ArticleDeserializer;
import ksr.pl.kw.extraction.ArticleExtractionService;
import ksr.pl.kw.extraction.CharacteristicsRecognitionService;

public class Configuration {
    private static final KnnCalculator calculator = new KnnCalculator();
    private static final ArticleClassificationService classificationService = new ArticleClassificationService();
    private static final ArticleExtractionService extractionService = new ArticleExtractionService();
    private static final ArticleDeserializer deserializer = new ArticleDeserializer();
    private static final CharacteristicsRecognitionService recognitionService = new CharacteristicsRecognitionService();

    public static KnnCalculator getCalculator() {
        return calculator;
    }

    public static ArticleClassificationService getClassificationService() {
        return classificationService;
    }

    public static ArticleExtractionService getExtractionService() {
        return extractionService;
    }

    public static ArticleDeserializer getDeserializer() {
        return deserializer;
    }

    public static CharacteristicsRecognitionService getRecognitionService() {
        return recognitionService;
    }
}
