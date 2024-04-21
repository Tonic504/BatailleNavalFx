package com.example.battaillenavalfx;

public class Grille {

    int[][] grille;

    public Grille(){

            for (int i = 0;i<10;i++){
                for(int j = 0;j<10;j++){
                    grille[i][j] = 0;
                }
            }

    }

    public boolean validerPosition (
            int ligne ,
            int colonne,
            int direction,
            int tailleBateau)
    {
        if (direction == 1){
            if(ligne + tailleBateau >= grille.length) {
                return false;
            }
            for (int iterateur = 0; iterateur<tailleBateau; iterateur++){
                if (grille[ligne+iterateur][colonne]!=0){
                    return false;
                }
            }
        }
        else {
            if(colonne + tailleBateau >= grille.length) {
                return false;
            }
            for (int iterateur = 0; iterateur<tailleBateau; iterateur++){
                if (grille[ligne][colonne+iterateur]!=0){
                    return false;
                }
            }

            if (ligne <0 || ligne > 9 || colonne <0 || colonne >9){
                return false;
            }}

        return true;
    }}
