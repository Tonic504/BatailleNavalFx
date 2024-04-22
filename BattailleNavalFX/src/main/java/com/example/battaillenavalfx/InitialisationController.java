package com.example.battaillenavalfx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InitialisationController implements Initializable {


    private Stage stage;
    private Scene scene2;
    private HelloApplication helloApplication;
    private static final int ROWS = 11;
    private static final int COLS = 11;

    int directionSelectionner = 0 ;
    int idBateauSelectionner = 1;


    Grille grilleJoueur = new Grille();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createGrille();

    }

    @FXML
    private GridPane VGrille;

    @FXML
    private Label console;


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
        idBateauSelectionner = 2;
    }

    @FXML
    void appelPorteAvion(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Porte-Avions.png"));
        idBateauSelectionner = 1;

    }

    @FXML
    void appelSousMarin(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Sous-Marin.png"));
        idBateauSelectionner = 4;
    }

    @FXML
    void appelTorpilleur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Torpilleur.png"));
        idBateauSelectionner = 5;
    }

    @FXML
    void tournerBateau(ActionEvent event) {
        if (directionSelectionner ==0){
            directionSelectionner = 1;
            orientationBateau.setText("Vertical");
        }
        else{
            directionSelectionner = 0;
            orientationBateau.setText("Horizontal");}

    }

    @FXML
    void recommencer(ActionEvent event) {

        recommencerGrille();
    }

    @FXML
    void Terminer(ActionEvent event) {

        if(!grilleValide()){
            grilleJoueur.gridPane = VGrille;

            helloApplication.setGrille(grilleJoueur);
            if (stage != null && scene2 != null) {
                stage.setScene(scene2);
            }
        }
        else{console.setText("tu n'as pas fini de remplir ta grille");}
    }



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

    private void recommencerGrille(){
        for (int i=  0;i<5;i++ ){
        deleteImageViewFromCell(grilleJoueur.placeSurLaGrille[i][0],grilleJoueur.placeSurLaGrille[i][1]);
        }
        grilleJoueur = new Grille();
    }

    private void onCellClicked(int row, int col) {
        System.out.println("Clicked on row: " + row + ", col: " + col);
        if(grilleJoueur.validerPosition(row-1,col-1,directionSelectionner,tailleParId(idBateauSelectionner))&&grilleJoueur.chercherBateau(idBateauSelectionner)){

            Image image = new Image(routeParId(idBateauSelectionner));
            ImageView imageView = new ImageView(image);

            if (directionSelectionner==1){        Rotate rotate = new Rotate(90, 0, 0); // Nouveau point de pivot en haut à gauche (0, 0)
                imageView.getTransforms().add(rotate);
                imageView.setTranslateX(tailleParId(idBateauSelectionner)*15+15);



            }
            else{
                GridPane.setValignment(imageView, VPos.BOTTOM);
                GridPane.setHalignment(imageView, HPos.LEFT);}



            VGrille.add(imageView, col, row);
            grilleJoueur.placerBateau(idBateauSelectionner,tailleParId(idBateauSelectionner),row-1,col-1,directionSelectionner);
        }
        else{System.out.println("bateau impossible a placer"+ directionSelectionner +"  "+ tailleParId(idBateauSelectionner));
        console.setText("bateau impossible a placer ici");}




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

    private boolean grilleValide(){
        boolean trustTheProcess = true;
        for (int i = 1;i<=6;i++){
            if(!grilleJoueur.chercherBateau(i)){
                trustTheProcess = false;
            }
        }return trustTheProcess;
    }

    private void deleteImageViewFromCell(int row, int col) {
        row++;
        col++;
        ObservableList<Node> children = VGrille.getChildren();
        for (Node node : children) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col && node instanceof ImageView) {
                VGrille.getChildren().remove(node);
                break; // Sortir de la boucle une fois l'image supprimée
            }
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
    }



    public void setGrille(Grille grille) {
        this.grilleJoueur = grille;
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}


