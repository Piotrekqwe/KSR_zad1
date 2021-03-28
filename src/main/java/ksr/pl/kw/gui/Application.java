package ksr.pl.kw.gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ksr.pl.kw.extraction.ArticleDTO;
import ksr.pl.kw.extraction.ArticleDeserializer;
import ksr.pl.kw.extraction.CharacteristicsRecognitionService;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainStage.fxml")));

        stage.setTitle("Klasyfikacja dokumentów tekstowych");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    public static void main(String[] args){
        launch();
        ArticleDeserializer articleDeserializer = new ArticleDeserializer();
        CharacteristicsRecognitionService cRS = new CharacteristicsRecognitionService();
        for(ArticleDTO articleDTO : articleDeserializer.getAllArticles()){

            //System.out.println(articleDTO.stopWordsRemover());

            //System.out.println(cRS.amountOfSentences(articleDTO));
            //System.out.println(cRS.digitsAmount(articleDTO));
            //System.out.println(cRS.shortWordsAmount(articleDTO));
            //System.out.println(cRS.longWordsAmount(articleDTO));
            //System.out.println(cRS.wordsAmountInArticle(articleDTO));
            //System.out.println(cRS.largestAmountCitiesCountry(articleDTO));
            //System.out.println(cRS.largestAmountCurrency(articleDTO));
            //System.out.println(cRS.largestAmountDateFormat(articleDTO));
            //System.out.println(cRS.largestAmountLengthUnit(articleDTO));
            //System.out.println(cRS.largestAmountTemperatureUnit(articleDTO));

        }

    }

}
