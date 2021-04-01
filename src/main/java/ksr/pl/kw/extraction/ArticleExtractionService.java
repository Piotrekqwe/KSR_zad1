package ksr.pl.kw.extraction;

import ksr.pl.kw.gui.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleExtractionService {
    private List<ArticleCharacteristic> allArticles;
    private List<ArticleCharacteristic> trainCharacteristics;
    private List<ArticleCharacteristic> testCharacteristics;


    public void extract() {
        CharacteristicsRecognitionService recognitionService = Configuration.getRecognitionService();
        List<ArticleDTO> DTOs = Configuration.getDeserializer().getAllArticles();
        allArticles = DTOs.stream().map(recognitionService::recognize).collect(Collectors.toList());
        //Configuration.getDeserializer().selectTrainAndTestSet(trainSetSize);
        //List<ArticleDTO> trainArticles = Configuration.getDeserializer().getTrainArticles();
        //List<ArticleDTO> testArticles = Configuration.getDeserializer().getTestArticles();
        //List<ArticleCharacteristic> trainCharacteristics = trainArticles.stream().map(recognitionService::recognize).collect(Collectors.toList());
        //List<ArticleCharacteristic> testCharacteristics = testArticles.stream().map(recognitionService::recognize).collect(Collectors.toList());
    }

    public void split(int trainSetSize){
        int splitPoint = allArticles.size() * trainSetSize / 100;
        trainCharacteristics = allArticles.subList(0, splitPoint);
        testCharacteristics = allArticles.subList(splitPoint, allArticles.size());
        Configuration.getCalculator().setLearningCollection(trainCharacteristics);
        Configuration.getClassificationService().setTestCollection(testCharacteristics);
    }

}
