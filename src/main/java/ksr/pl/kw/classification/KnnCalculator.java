package ksr.pl.kw.classification;

import ksr.pl.kw.extraction.ArticleCharacteristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KnnCalculator {
    private List<ArticleCharacteristic> learningCollection;
    StringComparisonMethod stringComparisonMethod;

    public void setLearningCollection(List<ArticleCharacteristic> learningCollection) {
        this.learningCollection = learningCollection;
    }

    public List<ArticleCharacteristic> getLearningCollection() {
        return learningCollection;
    }

    public Country classify(ArticleCharacteristic article, Method method, int K, double[] weights) {
        ArrayList<ComparedArticle> articles = new ArrayList<>(learningCollection.size());
        Country result = Country.USA;

        //calculating distance for each article in learning collection
        for (ArticleCharacteristic learningArticle : learningCollection) {
            double[] distance = new double[ArticleCharacteristic.NUMBER_OF_TRAITS];

            //sentencesAmount
            if(article.getSentencesAmount() >= learningArticle.getSentencesAmount()){
                distance[0] = 1 - (double) learningArticle.getSentencesAmount() / article.getSentencesAmount();
            }else{
                distance[0] = 1 - (double) article.getSentencesAmount() / learningArticle.getSentencesAmount();
            }

            //digitsAmount
            if(article.getDigitsAmount() >= learningArticle.getDigitsAmount()){
                distance[1] = 1 - (double) learningArticle.getDigitsAmount() / article.getDigitsAmount();
            }else{
                distance[1] = 1 - (double) article.getDigitsAmount() / learningArticle.getDigitsAmount();
            }

            //shortWordsAmount
            if(article.getShortWordsAmount() >= learningArticle.getShortWordsAmount()){
                distance[2] = 1 - (double) learningArticle.getShortWordsAmount() / article.getShortWordsAmount();
            }else{
                distance[2] = 1 - (double) article.getShortWordsAmount() / learningArticle.getShortWordsAmount();
            }

            //longWordsAmount
            if(article.getLongWordsAmount() >= learningArticle.getLongWordsAmount()){
                distance[3] = 1 - (double) learningArticle.getLongWordsAmount() / article.getLongWordsAmount();
            }else{
                distance[3] = 1 - (double) article.getLongWordsAmount() / learningArticle.getLongWordsAmount();
            }

            //textLength
            if(article.getTextLength() >= learningArticle.getTextLength()){
                distance[4] = 1 - (double) learningArticle.getTextLength() / article.getTextLength();
            }else{
                distance[4] = 1 - (double) article.getTextLength() / learningArticle.getTextLength();
            }

            //largestAmountCitiesCountry
            distance[5] = stringComparisonMethod.compare(article.getLargestAmountCitiesCountry(), learningArticle.getLargestAmountCitiesCountry());

            //currency
            if(article.getCurrency().equals(learningArticle.getCurrency())){
                distance[6] = 0;
            }else{
                distance[6] = 1;
            }

            //dateFormat
            if(article.getDateFormat().equals(learningArticle.getDateFormat())){
                distance[7] = 0;
            }else{
                distance[7] = 1;
            }

            //lengthUnit
            if(article.getLengthUnit().equals(learningArticle.getLengthUnit())){
                distance[8] = 0;
            }else{
                distance[8] = 1;
            }

            //temperatureUnit
            if(article.getTemperatureUnit().equals(learningArticle.getTemperatureUnit())){
                distance[9] = 0;
            }else{
                distance[9] = 1;
            }

            for(int i = 0; i < ArticleCharacteristic.NUMBER_OF_TRAITS; i++){
                distance[i] *= weights[i];
            }

            articles.add(new ComparedArticle(learningArticle, method.process(distance)));
        }

        articles.sort(Comparator.comparing(comparedArticle -> comparedArticle.distance));

        if (K > articles.size()) {
            K = articles.size();
        }

        //counting k nearest neighbours
        int max = 0;
        int[] counts = new int[Country.values().length];
        for(int i = 0; i < K; i++){
            int num;
            counts[articles.get(i).article.getCountry().id]++;

            if(counts[articles.get(i).article.getCountry().id] > max){
                max = counts[articles.get(i).article.getCountry().id];
                result = articles.get(i).article.getCountry();
            }
        }

        return result;
    }

    public void setStringComparisonMethod(StringComparisonMethod stringComparisonMethod) {
        this.stringComparisonMethod = stringComparisonMethod;
    }

    private static class ComparedArticle {
        ArticleCharacteristic article;
        Double distance;

        public ComparedArticle(ArticleCharacteristic article, double distance) {
            this.article = article;
            this.distance = distance;
        }
    }

}
