package com.example.battaillenavalfx;

import javafx.scene.layout.GridPane;

public class Grille {

    public GridPane gridPane;
    int[][] grille = new int[10][10];
    int[][] placeSurLaGrille = new int[5][2];

    public Grille(){

        grille = new int[10][10];
            for (int i = 0;i<10;i++){
                for(int j = 0;j<10;j++){
                    grille[i][j] = 0;
                }
            }

    }

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

    public boolean chercherBateau(int id){
        boolean trustTheProcess = true;
        for (int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                if (grille[i][j] == id){
                    trustTheProcess = false;
                }
            }
        }
    return trustTheProcess;}
}
