package ksr.pl.kw.extraction;

import ksr.pl.kw.extraction.ArticleDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CharacteristicsRecognitionService {

    public CharacteristicsRecognitionService() {
    }

    //amount of sentences in article
    public int amountOfSentences(ArticleDTO articleDTO){

        int amount = 0;
        List<String> splittedText = new ArrayList<String>(Arrays.asList(articleDTO.getBody().split("[\\.!?]")));

        System.out.println(splittedText);

        amount = splittedText.size();

//        for(String item : splittedText){
//            int charactersAmount = item.length();
//            if(Character.isWhitespace(item.charAt(0)) || Character.isUpperCase(item.charAt(0))){
//                if(item.substring(charactersAmount-1).equals("!") || item.substring(charactersAmount-1).equals("?")){
//                    amount++;
//                }
//            }
//        }
        return amount;
    }

    //amount of digits in article
    public int digitsAmount(ArticleDTO articleDTO){

        String allNumbersInText = articleDTO.getBody().replaceAll("[^0-9]", "");

        return allNumbersInText.length();
    }

    //amount of words < 4
    public int shortWordsAmount(ArticleDTO articleDTO){

        int amount = 0;
        String text = articleDTO.getBody().replaceAll("[^A-Za-z\\s]", "");
        List<String> words = articleDTO.splitText(text);
        System.out.println(words);

        for(String word : words){
            if(word.length() < 4 && !word.isEmpty()){
                amount++;
            }
        }
        return amount;
    }

    //amount of words > 10
    public int longWordsAmount(ArticleDTO articleDTO){

        int amount = 0;
        String text = articleDTO.getBody().replaceAll("[^A-Za-z\\s]", "");
        List<String> words = articleDTO.splitText(text);

        for(String word : words){
            if(word.length() > 10 && !word.isEmpty()){
                amount++;
            }
        }
        return amount;
    }

    //length of the article
    public int wordsAmountInArticle(ArticleDTO articleDTO){

        List<String> words = articleDTO.splitText(articleDTO.getBody());

        return words.size();
    }

    //country (cities)
    public String largestAmountCitiesCountry(ArticleDTO articleDTO){
        String theMostFrequentCountry = "";

        //System.out.println(readDatafromCSV("K:\\PL_INFORMATYKA\\SEMESTR_6\\Komputerowe_systemy_rozpoznawania\\Projekt_1\\worldcities.csv"));

        Map <String, String> cityCountry = readDatafromCSV("K:\\PL_INFORMATYKA\\SEMESTR_6\\Komputerowe_systemy_rozpoznawania\\Projekt_1\\worldcities.csv");
        List<String> textWithNoStopWords = articleDTO.stopWordsRemover();
        List<String> countriesOfCitiesFromArticle = new ArrayList<String>();
        List<String> citiesFromMap = new ArrayList<String>(cityCountry.keySet());

        //System.out.println(citiesFromMap);

        for(String word : textWithNoStopWords){
            if(citiesFromMap.contains(word)){
                countriesOfCitiesFromArticle.add(cityCountry.get(word));
            }
        }

        System.out.println(countriesOfCitiesFromArticle);

        theMostFrequentCountry = theMostFrequentWord(countriesOfCitiesFromArticle);

        return theMostFrequentCountry;
    }


    //country (currency)
    public String largestAmountCurrency(ArticleDTO articleDTO){

        String theMostFrequentCurrency = "";
        List<String> textWithNoStopWords = articleDTO.stopWordsRemover();
        List<String> textWithCurrencyCodes = new ArrayList<String>();
        List<String> currencyList = new ArrayList<String>();

        for(Locale locale : Locale.getAvailableLocales()){
            Currency currency = null;
            try{
                currency = Currency.getInstance(locale);
                currencyList.add(currency.getCurrencyCode());
            }catch (IllegalArgumentException e){
                continue;
            }
        }

        for(String word : textWithNoStopWords){
            if(currencyList.contains(word)){
                textWithCurrencyCodes.add(word);
            }
        }
        System.out.println(textWithCurrencyCodes);

        theMostFrequentCurrency = theMostFrequentWord(textWithCurrencyCodes);

        return theMostFrequentCurrency;
    }


    //date format
    public String largestAmountDateFormat(ArticleDTO articleDTO){
        String dateFormat = "";

        

        return dateFormat;
    }


    //length units
    public String largestAmountLengthUnit(ArticleDTO articleDTO){
        String unit = "";



        return unit;
    }


    //temperature unit
    public String largestAmountTemperatureUnit(ArticleDTO articleDTO){
        String unit = "";



        return unit;
    }

    private Map<String, String> readDatafromCSV(String path){

        Map<String, String> cityCountry = new HashMap<String, String>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            String[] record;

            while((line = bufferedReader.readLine()) != null){
                String[] cityCountryValues = new String[2];
                record = line.split(",");
                cityCountryValues[0] = record[0];
                cityCountryValues[1] = record[4];
                cityCountry.put(cityCountryValues[0].replaceAll("[^A-Za-z\\s]" ,""), cityCountryValues[1].replaceAll("[^A-Za-z\\s]" ,""));
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return cityCountry;
    }

    private String theMostFrequentWord(List<String> list){

        String word = "";
        Map<String,Integer> map = new HashMap<String, Integer>();
        Set<String> occurencyOfWords = new HashSet<>(list);

        for(String string : occurencyOfWords){
            map.put(string, Collections.frequency(list, string));
        }

        System.out.println(map);

        if(!map.isEmpty()) {
            word = Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        }
        return word;
    }

}
