package com.example.battaillenavalfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.example.battaillenavalfx.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlleur gérant le déroulé du jeu
 */
public class JeuControlleur {

    private Stage stage;
    private Scene sceneRecommencer;
    private Grille grilleJoueur;
    private HelloApplication helloApplication;
    private Scene sceneMenu;

    /**
     * Fonction appelé à la création de la scène, elle permet de récupérer la grille du joueur qui a fait durant le positionement des bateaux
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grilleJoueur = helloApplication.getGrille();




    }
    @FXML
    private GridPane VGrille;

    @FXML
    private GridPane VGrilleOrdi;
    @FXML
    private Label console;
    @FXML
    private Label visionPositionTir;

    /**
     * Fonction inachevée sensée servire au joueur a tiré, il aurait aussi servi à faire tirer l'ordinateur et à vérifier si quelqu'un à gagner
     * @param event
     */
    @FXML
    void ContinuerPartie(ActionEvent event) {

    }

    /**
     * Renvoie au menu du jeu
     * @param event
     */
    @FXML
    void Quitter(ActionEvent event) {
        if (stage != null && sceneRecommencer!= null) {
            stage.setScene(sceneMenu);
        }
    }

    /**
     * Renvoie à l'initialisation des bateaux du joueur
     * @param event
     */
    @FXML
    void Recommencer(ActionEvent event) {

        if (stage != null && sceneRecommencer!= null) {
            stage.setScene(sceneRecommencer);
        }
    }

    /**
     * Censé faire apparaitre les bateaux
     * @param event
     */
    @FXML
    void Tricher(ActionEvent event) {

    }

    /**
     * Modifie le stage
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Permet de récupérer la scene de mofification de la grille du joueur
     */
    public void setSceneRecommencer(Scene scene) {
        this.sceneRecommencer = scene;
    }    public void setSceneMenu(Scene scene) {
        this.sceneMenu = scene;
    }
    /**
     * Permet de récupérer la scene des menus
     */
    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}
