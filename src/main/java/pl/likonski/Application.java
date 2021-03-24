package pl.likonski;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainStage.fxml")));

        stage.setTitle("testujemy");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
