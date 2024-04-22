package com.example.battaillenavalfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.battaillenavalfx.*;
import java.io.IOException;
/**/
public class HelloApplication extends Application {

    private Stage stage;

    private Grille grille;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        grille = new Grille();

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("scene-bienvenue.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 800);

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 600, 800);

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("scene-jeu.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 600, 800);

        BienvenueControlleur controller = fxmlLoader1.getController();
        controller.setStage(primaryStage);
        controller.setScene2(scene2);
        InitialisationController controller1 = fxmlLoader2.getController();
        controller1.setStage(primaryStage);
        controller1.setScene2(scene3);
        controller1.setGrille(grille);
        controller1.setHelloApplication(this);

        JeuControlleur jeuControlleur = fxmlLoader3.getController();

        jeuControlleur.setHelloApplication(this);




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

    public Grille getGrille() {
        return grille;
    }
    public void setGrille(Grille grille){this.grille = grille;}
}
