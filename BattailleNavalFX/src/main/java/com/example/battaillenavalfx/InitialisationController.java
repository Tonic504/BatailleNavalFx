package com.example.battaillenavalfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class InitialisationController implements Initializable {



    private static final int ROWS = 11;
    private static final int COLS = 11;

    int idBateauSelectionner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createGrille();
    }

    @FXML
    private GridPane VGrille;

    private void createGrille() {
        for (int row = 1; row < ROWS; row++) {
            for (int col = 1; col < COLS; col++) {
                Pane cell = new Pane();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-border-color: black;");
                final int finalRow = row;
                final int finalCol = col;
                cell.setOnMouseClicked(event -> onCellClicked(finalRow, finalCol));
                VGrille.add(cell, col, row);
            }
        }
    }

    @FXML
    private Label orientationBateau;

    @FXML
    private ImageView visionBateau;

    @FXML
    void appelContreTropilleur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Contre-Torpilleur.png"));
        idBateauSelectionner = 3;
    }

    @FXML
    void appelCroiseur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Croiseur.png"));
        idBateauSelectionner = 4;
    }

    @FXML
    void appelPorteAvion(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Porte-Avions.png"));
        idBateauSelectionner = 5;

    }

    @FXML
    void appelSousMarin(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Sous-Marin.png"));
        idBateauSelectionner = 2;
    }

    @FXML
    void appelTorpilleur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Torpilleur.png"));
        idBateauSelectionner = 1;
    }

    @FXML
    void tournerBateau(ActionEvent event) {

    }

    private void onCellClicked(int row, int col) {
        System.out.println("Clicked on row: " + row + ", col: " + col);



    }

    private int tailleParId(int id){
        switch(id){
            case(1):
                return 5;
        }
        switch(id){
            case(2):
                return 4;
        }
        switch(id){
            case(3):
                return 3;
        }
        switch(id){
            case(4):
                return 3;
        }
        switch(id){
            case(5):
                return 2;
        }
        return 0;
    }

    private String routeParId(int id){
        switch(id){
            case(1):
                return "Image/Bateau/Porte-Avions.png";
        }
        switch(id){
            case(2):
                return "Image/Bateau/Croiseur.png";
        }
        switch(id){
            case(3):
                return "Image/Bateau/Contre-Torpilleur.png";
        }
        switch(id){
            case(4):
                return "Image/Bateau/Sous-Marin.png";
        }
        switch(id){
            case(5):
                return "Image/Bateau/Torpilleur.png";
        }
        return "0";
    }
    }


