package com.example.battaillenavalfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.example.battaillenavalfx.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlleur gérant le déroulé du jeu
 */
public class JeuControlleur {


    private Grille grilleJoueur;
    private HelloApplication helloApplication;

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

    }

    /**
     * Renvoie à l'initialisation des bateaux du joueur
     * @param event
     */
    @FXML
    void Recommencer(ActionEvent event) {

    }

    /**
     * Censé faire apparaitre les bateaux
     * @param event
     */
    @FXML
    void Tricher(ActionEvent event) {

    }



    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}
