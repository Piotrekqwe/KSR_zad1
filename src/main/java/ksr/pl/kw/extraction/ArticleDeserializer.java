package ksr.pl.kw.extraction;

import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleDeserializer {

    private String filePath = "src/main/resources/article";
    private List<ArticleDTO> allArticles=new ArrayList<ArticleDTO>();
    private List<ArticleDTO> trainArticles;
    private List<ArticleDTO> testArticles;
    private File[] filesInDirectory;

    public ArticleDeserializer(){

        File direcory = new File(getFilePath());
        this.filesInDirectory = direcory.listFiles();

        readDataFromFiles();
        readArticlesDTO();
    }

    public List<ArticleDTO> getAllArticles(){
        return allArticles;
    }

    public List<ArticleDTO> getTrainArticles() {
        return trainArticles;
    }

    public List<ArticleDTO> getTestArticles() {
        return testArticles;
    }

    public String getFilePath(){
        return filePath;
    }

    private void readDataFromFiles(){

        for(File file : filesInDirectory) {

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {//to the end of file

                    String article = "";

                    if (line.contains("<REUTERS")) {

                        do {
                            article += line;
                            line = bufferedReader.readLine();

                        } while (!line.equals("</REUTERS>"));

                        //System.out.println(article);

                        String[] temp = article.split("<PLACES>");
                        temp = temp[1].split("</PLACES>");
                        //System.out.println(temp);
                        String[] places = temp[0].split("</D>");
                        // System.out.println(places[0]);


                        // places = temp.get(1).replaceAll("[<>/]", "").split("DD");
                        //System.out.println(places[0]);
                        String cos=places[0].replaceAll("<D>","");
                        if(!selectCountries(cos))
                            continue;
                        if (places.length > 1) {
                            continue;
                        }
                        if(!temp[1].contains("<BODY>"))
                            continue;
                        temp=temp[1].split("<BODY>");
                        temp=temp[1].split("</BODY>");

                        ArticleDTO articleDTO=new ArticleDTO(places[0].replaceAll("<D>",""),temp[0]);
                        this.allArticles.add(articleDTO);
                       // String tempBody = temp.get(2).;


                    }
                }

            }catch (IOException e){
                System.out.println(e);
            }
        }
    }

    private void readArticlesDTO(){

        for(ArticleDTO articleDTO : allArticles){
            System.out.println(articleDTO);
        }
    }

    private boolean selectCountries(String countryFromArticle){
        String [] countries={"west-germany", "usa", "france", "uk", "canada japan"};
        List<String> countryList=new ArrayList<String>(Arrays.asList(countries));
        for(String country : countryList)
            if(countryFromArticle.equals(country))
                return true;
            return false;
    }

    public void selectTrainAndTestSet(int percentageOfTrainSet){

        int amountOfTrainArticles = (int) Math.ceil((allArticles.size()*percentageOfTrainSet)/100);

        for(int i=0; i<amountOfTrainArticles; i++){
            trainArticles.add(allArticles.get(i));
        }

        for(int i=(amountOfTrainArticles); i<allArticles.size(); i++){
            testArticles.add(allArticles.get(i));
        }
    }

}
