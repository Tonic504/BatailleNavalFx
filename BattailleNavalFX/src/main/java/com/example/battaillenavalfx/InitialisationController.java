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

/**
 * Ce controller est utilisé pour l'initialisation de la partie, c'est-à-dire le placement des bateaux par le joueur
 */
public class InitialisationController implements Initializable {


    private Stage stage;
    private Scene scene2;
    private HelloApplication helloApplication;
    private static final int ROWS = 11;
    private static final int COLS = 11;

    int directionSelectionner = 0 ;
    int idBateauSelectionner = 1;


    Grille grilleJoueur = new Grille();

    /**
     * Fonction appeler à l'invocation de la scène et de son controller, elle appelle la fonction {@link #créationGrille()}
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        créationGrille();

    }

    @FXML
    private GridPane VGrille;

    @FXML
    private Label console;


    @FXML
    private Label orientationBateau;

    @FXML
    private ImageView visionBateau;

    /**
     * Méthode du menu déroulant qui appelle le Contre Torpilleur
     * @param event
     */
    @FXML
    void appelContreTropilleur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Contre-Torpilleur.png"));
        idBateauSelectionner = 3;
    }
    /**
     * Méthode du menu déroulant qui appelle le Croiseur
     * @param event
     */
    @FXML
    void appelCroiseur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Croiseur.png"));
        idBateauSelectionner = 2;
    }
    /**
     * Méthode du menu déroulant qui appelle le Porte-Avions
     * @param event
     */
    @FXML
    void appelPorteAvion(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Porte-Avions.png"));
        idBateauSelectionner = 1;

    }
    /**
     * Méthode du menu déroulant qui appelle le Sous-Marin
     * @param event
     */
    @FXML
    void appelSousMarin(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Sous-Marin.png"));
        idBateauSelectionner = 4;
    }
    /**
     * Méthode du menu déroulant qui appelle le Torpilleur
     * @param event
     */
    @FXML
    void appelTorpilleur(ActionEvent event) {
        visionBateau.setImage(new Image("Image/Bateau/Torpilleur.png"));
        idBateauSelectionner = 5;
    }

    /**
     * Méthode pour retourner le bateau, il sert seulement à modifier le {@link #orientationBateau} utilisé dans la classe de la Grille
     * @param event
     */
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

    /**
     * Appel la fonction {@link #recommencerGrille()}
     * @param event
     */
    @FXML
    void recommencer(ActionEvent event) {

        recommencerGrille();
    }

    /**
     * Est chargé de changer de scenes en conservant la grille
     * @param event
     */
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


    /**
     * Permet de mettre des panes dans le gridpane car c'est beaucoup trop long de le faire a la main sur Scene Builder
     */
    private void créationGrille() {
        for (int lig = 1; lig < ROWS; lig++) {
            for (int col = 1; col < COLS; col++) {
                Pane cell = new Pane();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-border-color: black;");
                final int finalRow = lig;
                final int finalCol = col;
                cell.setOnMouseClicked(event -> surCliqueDeCellule(finalRow, finalCol));
                VGrille.add(cell, col, lig);
            }
        }
    }

    /**
     * Réinnitilalise la grille et le gridpane
     */
    private void recommencerGrille(){
        for (int i=  0;i<5;i++ ){
        supprimerImageParCellule(grilleJoueur.placeSurLaGrille[i][0],grilleJoueur.placeSurLaGrille[i][1]);
        }
        grilleJoueur = new Grille();
    }

    /**
     * Fonction améliorer du "OnClick" qui prend sur lui la ligne et la colonne, il sert principalement a placé les bateaux et a redimensionné les images pour qu'elles soient droites sur la grille
     * @param lig ligne cliqué sur la grille
     * @param col ligne cliqué sur la collone
     */
    private void surCliqueDeCellule(int lig, int col) {
        
        if(grilleJoueur.validerPosition(lig-1,col-1,directionSelectionner,tailleParId(idBateauSelectionner))&&grilleJoueur.chercherBateau(idBateauSelectionner)){

            Image image = new Image(routeParId(idBateauSelectionner));
            ImageView imageView = new ImageView(image);

            if (directionSelectionner==1){        Rotate rotate = new Rotate(90, 0, 0); // Nouveau point de pivot en haut à gauche (0, 0)
                imageView.getTransforms().add(rotate);
                imageView.setTranslateX(tailleParId(idBateauSelectionner)*15+15);



            }
            else{
                GridPane.setValignment(imageView, VPos.BOTTOM);
                GridPane.setHalignment(imageView, HPos.LEFT);}



            VGrille.add(imageView, col, lig);
            grilleJoueur.placerBateau(idBateauSelectionner,tailleParId(idBateauSelectionner),lig-1,col-1,directionSelectionner);
        }
        else{System.out.println("bateau impossible a placer"+ directionSelectionner +"  "+ tailleParId(idBateauSelectionner));
        console.setText("bateau impossible a placer ici");}




    }

    /**
     * Fonction utile qui donne la taille d'un bateau en donnant son ID
     * @param id l'identifiant du bateau
     * @return la taille du bateau en int
     */
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

    /**
     * Permet d'avoir l'adresse où est stockée l'image liée au bateau avec son ID
     * @param id
     * @return
     */
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

    /**
     * Regarde si tous les bateaux sont bien placé sur la grille
     * @return
     */
    private boolean grilleValide(){
        boolean validation = true;
        for (int i = 1;i<=6;i++){
            if(!grilleJoueur.chercherBateau(i)){
                validation = false;
            }
        }return validation;
    }

    /**
     * Suprimme un imageView en renseignant la ligne et la colone sur laquel il est instancié
     * @param lig
     * @param col
     */
    private void supprimerImageParCellule(int lig, int col) {
        lig++;
        col++;
        ObservableList<Node> children = VGrille.getChildren();
        for (Node node : children) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            if (rowIndex != null && colIndex != null && rowIndex == lig && colIndex == col && node instanceof ImageView) {
                VGrille.getChildren().remove(node);
                break; // Sortir de la boucle une fois l'image supprimée
            }
        }
    }

    /**
     * permet de récupérer le stage du controller
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * permet de récupérer al scene du controller
     * @param scene2
     */
    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
    }


    /**
     * permet de modifier la grille du controller
     * @param grille
     */
    public void setGrille(Grille grille) {
        this.grilleJoueur = grille;
    }
    

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}


