package com.example.battaillenavalfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.example.battaillenavalfx.*;
import java.net.URL;
import java.util.ResourceBundle;

public class JeuControlleur {


    private Grille grilleJoueur;
    private HelloApplication helloApplication;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        grilleJoueur = helloApplication.getGrille();




    }
    @FXML
    private GridPane VGrille;

    @FXML
    private GridPane VGrilleOrdi;

    @FXML
    private Label visionPositionTir;

    @FXML
    void ContinuerPartie(ActionEvent event) {
        System.out.println(grilleJoueur.grille[0][0]);
    }




    public void setGrille(Grille grille) {
        this.grilleJoueur = grille;
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}
