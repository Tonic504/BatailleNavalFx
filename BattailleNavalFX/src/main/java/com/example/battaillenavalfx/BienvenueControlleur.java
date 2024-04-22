package com.example.battaillenavalfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controlleur du menu de jeu
 */
public class BienvenueControlleur{

    private Stage stage;
    private Scene scene2;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
    }

    @FXML
    void jouer(ActionEvent event) {
        if (stage != null && scene2 != null) {
            stage.setScene(scene2);
        }
    }

    @FXML
    void quitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void tricher(ActionEvent event) {
        if (stage != null && scene2 != null) {
            stage.setScene(scene2);
        }
    }
}
