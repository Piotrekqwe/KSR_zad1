package ksr.pl.kw.extraction;

import ksr.pl.kw.gui.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleExtractionService {

    private static final CharacteristicsRecognitionService recognitionService = Configuration.getRecognitionService();

    public void extract() {
        Configuration.getDeserializer().selectTrainAndTestSet(50);
        List<ArticleDTO> trainArticles = Configuration.getDeserializer().getTrainArticles();
        List<ArticleDTO> testArticles = Configuration.getDeserializer().getTestArticles();
        List<ArticleCharacteristic> trainCharacteristics = trainArticles.stream().map(recognitionService::recognize).collect(Collectors.toList());
        List<ArticleCharacteristic> testCharacteristics = testArticles.stream().map(recognitionService::recognize).collect(Collectors.toList());
        Configuration.getCalculator().setLearningCollection(trainCharacteristics);
        Configuration.getClassificationService().setTestCollection(testCharacteristics);
    }

}
