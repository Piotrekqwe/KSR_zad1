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
        stage.setScene(new Scene(root, 1000, 600));
        stage.setOnCloseRequest(event -> {
            FxUserInterfaceController.es.shutdown();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
