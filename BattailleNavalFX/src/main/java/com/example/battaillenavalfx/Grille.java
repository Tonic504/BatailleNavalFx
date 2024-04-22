package com.example.battaillenavalfx;

import javafx.scene.layout.GridPane;

/**
 * Classe permettant de modifier la grille de l'arrière-plan
 */
public class Grille {

    public GridPane gridPane;
    int[][] grille = new int[10][10];
    int[][] placeSurLaGrille = new int[5][2];

    /**
     * Constructeur de la classe, il permet de mettre des 0 sur toute la grille pour représenter le vide
     */
    public Grille(){

        grille = new int[10][10];
            for (int i = 0;i<10;i++){
                for(int j = 0;j<10;j++){
                    grille[i][j] = 0;
                }
            }

    }

    /**
     * Permet de vérifier si la position d'un bateau est cohérent, elle est utilisé dans l'initialisation
     * @param ligne la ligne où est placé le point de départ du bateau
     * @param colonne la colonne où est placé le point de départ du bateau
     * @param direction la direction dans la quel le bateau est orienté
     * @param tailleBateau la taille du bateau
     * @return un boolean qui approuve ou non le placement du bateau
     */
    public boolean validerPosition(int ligne, int colonne, int direction, int tailleBateau) {
        if (direction == 1) {
            if (ligne + tailleBateau > grille.length) {
                return false;
            }
            for (int iterateur = 0; iterateur < tailleBateau; iterateur++) {
                if (grille[ligne + iterateur][colonne] != 0) {
                    return false;
                }
            }
        } else {
            if (colonne + tailleBateau > grille[0].length) {
                return false;
            }
            for (int iterateur = 0; iterateur < tailleBateau; iterateur++) {
                if (grille[ligne][colonne + iterateur] != 0) {
                    return false;
                }
            }
        }

        if (ligne < 0 || ligne >= grille.length || colonne < 0 || colonne >= grille[0].length) {
            return false;
        }

        return true;
    }


    /**
     * Fonction qui place le bateau dans la grille choisie, cette fonction ne vérifie pas le placement du bateau donc il faut utiliser {@link #validerPosition(int, int, int, int)}
     * @param idBateau le numéro du bateau utilisé sur la grille
     * @param taille la taille du bateau
     * @param ligne la ligne ou le bateau sera placé
     * @param colonne la colonne ou le bateau sera placé
     * @param direction l'orientation du bateau sur la grille, sois verticale ou horizontale
     */

    public void placerBateau(
            int idBateau,
            int taille,
            int ligne,
            int colonne,
            int direction){
        for (int i = 0; i < taille; i++) {
            if (direction == 0) {
                grille[ligne][colonne + i] = 1; // Utiliser 1 pour représenter un bateau
            } else {
                grille[ligne + i][colonne] = 1; // Utiliser 1 pour représenter un bateau
            }
        }

        placeSurLaGrille[idBateau-1][0] = ligne;
        placeSurLaGrille[idBateau-1][1] = colonne;



    }

    /**
     * Fonction qui cherche si un bateau spécifique est encore sur la grille en parcourent la grille à la recherche de son ID
     * @param id
     * @return
     */
    public boolean chercherBateau(int id){
        boolean validation = true;
        for (int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                if (grille[i][j] == id){
                    validation = false;
                }
            }
        }
    return validation;}
}
