/*
* Auteur : Thomas Chaussende
* Date dernière mise a jour : 21/04/2024
* Nom du programme : BatailleNavalFX
* le programme a pour objectif de simulé une partie de bataille naval.
* La bataille navale est un jeu dont l'objectif est d'éliminer les bateaux adverses en tirant sur des cases jusqu'à toucher l'adversaire
* */

package com.example.battaillenavalfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.battaillenavalfx.*;
import java.io.IOException;
/**
 * Classe principale de la bataille navale, elle relie les controlleurs et les FXML pour son bon fonctionnement
 * @author Thomas Chaussende
 * @version 1.0.2
 * @since 21/04/2024
 */
public class HelloApplication extends Application {

    private Stage stage;

    private Grille grille;

    /**
     * Fonction appeler quand on démarre le programme, elle sert à instancier et à appeler tous les FXML et les controleurs
     * @param primaryStage
     * @throws IOException
     */
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

    /**
     * Fonction qui lance le programme
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Fonction qui retourne la grille instanciée pour que la grille du joueur puisse passer du controller de l'initiation au controller de jeu
     * @return
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Fonction qui permet au controller de modifier la grille
     * @param grille
     */
    public void setGrille(Grille grille){this.grille = grille;}
}
