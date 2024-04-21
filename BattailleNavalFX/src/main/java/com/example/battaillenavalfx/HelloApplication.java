package com.example.battaillenavalfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("scene-bienvenue.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 800);

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 600, 800);

        BienvenueControlleur controller = fxmlLoader1.getController();
        controller.setStage(primaryStage);
        controller.setScene2(scene2);


        stage.setTitle("Bataille Navale");
        stage.setMinWidth(600);
        stage.setMinHeight(800);
        stage.setMaxWidth(700);
        stage.setMaxHeight(1200);
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return stage;
    }
}
