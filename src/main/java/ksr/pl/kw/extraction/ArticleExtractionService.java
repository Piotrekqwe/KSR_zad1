package ksr.pl.kw.extraction;

import ksr.pl.kw.classification.Country;
import ksr.pl.kw.gui.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void split(int trainSetSize) {
        if (trainSetSize > 100) {
            trainSetSize = 100;
        }
        int splitPoint = allArticles.size() * trainSetSize / 100;
        trainCharacteristics = allArticles.subList(0, splitPoint);
        testCharacteristics = allArticles.subList(splitPoint, allArticles.size());
        Configuration.getCalculator().setLearningCollection(trainCharacteristics);
        Configuration.getClassificationService().setTestCollection(testCharacteristics);
    }

    public void evenSplit(int trainSetSize) {
        int[] sum = new int[Country.NUMBER_OF_COUNTRIES];
        for (ArticleCharacteristic article : allArticles) {
            sum[article.getCountry().id]++;
        }
        int min = allArticles.size();

        for (int x : sum) {
            if (x < min) {
                min = x;
            }
        }

        if (min == 0) {
            System.out.println("za malo artykulow");
            split(trainSetSize);
        } else {
            int num = min * trainSetSize / 100;
            if (num == 0) {
                num = 1;
            }
            Arrays.fill(sum, num);
            ArrayList<ArticleCharacteristic> train = new ArrayList<>();
            ArrayList<ArticleCharacteristic> test = new ArrayList<>();

            for (ArticleCharacteristic article : allArticles) {
                if (sum[article.getCountry().id] > 0) {
                    sum[article.getCountry().id]--;
                    train.add(article);
                } else {
                    test.add(article);
                }
            }
            trainCharacteristics = train;
            testCharacteristics = test;
        }
    }
}











