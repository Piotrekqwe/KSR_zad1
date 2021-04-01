package ksr.pl.kw.classification;

import ksr.pl.kw.extraction.ArticleCharacteristic;
import ksr.pl.kw.gui.ClassifiedArticle;
import ksr.pl.kw.gui.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleClassificationService {
    private List<ArticleCharacteristic> testCollection;

    public void setTestCollection(List<ArticleCharacteristic> testCollection) {
        this.testCollection = testCollection;
    }

    public List<ClassifiedArticle> classify(Method method, int K, double[] weights, StringComparisonMethod stringComparisonMethod) {
        if (testCollection == null || testCollection.isEmpty()) {
            return Collections.emptyList();
        }

        Configuration.getCalculator().setStringComparisonMethod(stringComparisonMethod);

        return testCollection.stream()
                .map(testArticle -> {
                    Country country = Configuration.getCalculator().classify(testArticle, method, K, weights);
                    return new ClassifiedArticle(testArticle, country);
                })
                .collect(Collectors.toList());
    }
}
