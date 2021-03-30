package ksr.pl.kw.extraction;

import ksr.pl.kw.classification.Country;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ArticleDTO {

//    private String date;
//    private String topics;
    private String places;
//    private String people;
//    private String orgs;
//    private String exchanges;
//    private String companies;
//    private String title;
//    private String dateline;
    private String body;
    private List<String> stopWordsEnglish;

    public ArticleDTO(String places, String body){
        this.places = places;
        this.body = body;
    }

    public String getPlaces(){
        return places;
    }

    public Country getCountry(){
        Country country;
        switch(places) {
            case "west-germany":
                country = Country.WEST_GERMANY;
                break;
            case "usa":
                country = Country.USA;
                break;
            case "france":
                country = Country.FRANCE;
                break;
            case "uk":
                country = Country.UK;
                break;
            case "canada":
                country = Country.CANADA;
                break;
            case "japan":
                country = Country.JAPAN;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + places);
        }
        return country;
    }

    public String getBody(){
        return body;
    }

    public List<String> splitText(String text){

        List<String> wordsList = new ArrayList<String>(Arrays.asList(text.split(" ")));

        return wordsList;
    }

    @Override
    public String toString(){

        return "ArticleDTO{places:'" + getPlaces() + "'" + ", body:'" + getBody() +"'}";
    }

    private void readStopWordsFromFile(){

        this.stopWordsEnglish = new ArrayList<String>();
        File file = new File("src/main/resources/data/stop_words_english.txt");

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                this.stopWordsEnglish.add(line);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<String> stopWordsRemover(){

        String text = getBody().replaceAll("[^A-Za-z\\s]", "");
        List<String> splittedText = splitText(text);
        List<String> textWithNoStopWord = new ArrayList<String>();
        readStopWordsFromFile();

        for(String word : splittedText){
            if(!stopWordsEnglish.contains(word.toLowerCase()) && !word.isEmpty()){
                textWithNoStopWord.add(word);
            }
        }
        return textWithNoStopWord;
    }

}
