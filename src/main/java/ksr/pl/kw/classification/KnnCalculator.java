package ksr.pl.kw.classification;

import ksr.pl.kw.extraction.ArticleCharacteristic;

import java.util.ArrayList;
import java.util.Comparator;

public class KnnCalculator {
    private ArticleCharacteristic[] learningCollection;


    public KnnCalculator(ArticleCharacteristic[] learningCollection) {
        this.learningCollection = learningCollection;
    }


    public Country classify(ArticleCharacteristic article, Method method, int K, double[] weights) {
        ArrayList<ComparedArticle> articles = new ArrayList<>(learningCollection.length);
        Country result = Country.USA;

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
            if(article.getLargestAmountCitiesCountry().equals(learningArticle.getLargestAmountCitiesCountry())){
                distance[5] = 0;
            }else{
                distance[5] = 1;
            }

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


        articles.sort(new Comparator<ComparedArticle>() {
            @Override
            public int compare(ComparedArticle o1, ComparedArticle o2) {
                return o1.distance.compareTo(o2.distance);
            }
        });

        if (K < articles.size()) {
            K = articles.size();
        }
        //zliczanie k najblizszych sąsiadów (których krajów jest najwięcej)
        int max = 0;
        int[] counts = new int[6];
        for(int i = 0; i < K; i++){
            int num;
            switch (articles.get(i).article.getCountry()){
                case WEST_GERMANY:
                    num = 0;
                    break;
                case USA:
                    num = 1;
                    break;
                case FRANCE:
                    num = 2;
                    break;
                case UK:
                    num = 3;
                    break;
                case CANADA:
                    num = 4;
                    break;
                case JAPAN:
                    num = 5;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + articles.get(i).article.getCountry());
            }
            counts[num]++;

            if(counts[num] > max){
                max = counts[num];
                switch (num){
                    case 0:
                        result = Country.WEST_GERMANY;
                        break;
                    case 1:
                        result = Country.USA;
                        break;
                    case 2:
                        result = Country.FRANCE;
                        break;
                    case 3:
                        result = Country.UK;
                        break;
                    case 4:
                        result = Country.CANADA;
                        break;
                    case 5:
                        result = Country.JAPAN;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + articles.get(i).article.getCountry());
                }
            }
        }

        return result;
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
