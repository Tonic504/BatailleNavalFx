package com.example.battaillenavalfx;

/*
        ---------------------------------------- ENTÊTE DE PROJET ----------------------------------------
        Application : Bataille Navale

        Développeur : Maxence SABATIER

        Version : 1.0
        Dernière modification : 24/02


        Description générale du projet :
        Cette application permet de jouer à la bataille navale via une interface en ligne de commande.
        L'utilisateur va affronter l'ordinateur qui jouera de manière aléatoire.
        Les grilles sont de taille 10x10.
        Il y a 5 bateaux (Porte-avions, Croiseur, Sous-marin, Contre-torpilleur, Torpilleur).


        Organisation du projet :
        Ce projet dispose d'une classe principale "Bataille" et d'un package "modules_batailles".
        Le package dispose de 3 classes qui contiennent toutes les méthodes nécessaires au jeu.

        - La classe "Bataille" lance la partie et gère son déroulement (initialisations, tours...).

        - La classe "Navire" permet d'instancier des navires avec leurs informations associées.

        - La classe "Grille" gère les grilles, leurs évolutions et détermine le vainqueur.

        - La classe "Affichage" s'occupe de l'interface utilisateur.
          Elle a pour but d'afficher les grilles, les consignes et de récupérer les entrées du joueur.
          Cette classe gère la quasi-totalité de l'affichage, à une ou deux exceptions près.


         Un dossier "JavaDoc" se trouve également à la racine du projet.
         Il contient la version web de tous les commentaires JavaDoc présents dans ce projet.

         - Ouvrez index.html pour arriver sur la page principale.
         - Ouvrez overview-tree.html pour arriver sur le détail de la hiérarchie du projet (recommandé)

         À noter que chaque classe et chaque méthode dispose de son commentaire JavaDoc.
        --------------------------------------------------------------------------------------------------
 */

import com.example.battaillenavalfx.modules_bataille.*;

/**
 * Classe Bataille
 * <p>Il s'agit de la principale classe du projet.<br>
 * Elle lance la partie et contrôle le bon déroulement du jeu.</p>
 *
 * @author Maxence SABATIER
 * @version 1.0
 */
public class Bataille
{
    /**
     * Méthode main
     * <p>Fonction principale de l'application qui permet d'exécuter celle-ci.<br>
     * Appelle le lancement d'une partie de bataille navale.</p>
     * @param args (arguments éventuels - aucun argument prévu dans ce projet)
     */
    public static void main(String[] args)
    {
        engagement();   //Lance la partie.
    }


    /**
     * Méthode engagement
     * <p>Lance une bataille navale et contrôle la partie.</p>
     *
     * Méthodes utilisées :
     * @see Grille#initGrilleOrdi()
     * @see Grille#initGrilleJeu()
     * @see Affichage#afficherGrille(int[][])
     * @see Affichage#afficherTour()
     * @see Grille#tirOrdinateur()
     * @see Affichage#demanderPositionTir()
     * @see Grille#mouvement(int[][], int, int)
     * @see Grille#vainqueur(int[][])
     * @see Affichage#afficherVainqueur(int[][])
     */
    public static void engagement()
    {
        //Initialisation des instances de classes
        Grille grilles = new Grille();
        Affichage interfaceUtiisateur = new Affichage();

        //Initialisation des booléens de victoire
        boolean victoireJoueur = false;
        boolean victoireOrdi = false;

        /*
        ---------------- Début de la partie ----------------
        * Commence par initialiser la grille de l'ordinateur puis l'afficher.
        * Initialise ensuite la grille du joueur

        * Ensuite, pour chaque tour, fait jouer l'ordinateur puis le joueur à tour de rôle.
        * Une fois qu'un vainqueur est nommé, affiche le vainqueur dans la console

         */

        grilles.initGrilleOrdi();   //Initialisation ordinateur

        interfaceUtiisateur.afficherGrille(grilles.grilleOrdi); //Affichage grille ordinateur

        System.out.println();
        System.out.println();

        grilles.initGrilleJeu();    //Initialisation joueur

        System.out.println();
        System.out.println();

        while (!victoireJoueur && !victoireOrdi)    //S'exécute tant qu'il n'y a pas de vainqueur
        {
            interfaceUtiisateur.afficherTour();

            //Tour de l'ordinateur : Récupère des coordonnées et effectue un tir
            System.out.println("Tir de l'ordinateur...");
            int[] positionTir = grilles.tirOrdinateur();
            grilles.mouvement(grilles.grilleJeu, positionTir[0], positionTir[1]);


            victoireOrdi = grilles.vainqueur(grilles.grilleJeu);    //Vérifie si ce tir suffit à l'ordinateur pour gagner
            if (victoireOrdi)
                break;


            System.out.println();


            //Tour du joueur : Demande les coordonnées et effectue un tir
            positionTir = interfaceUtiisateur.demanderPositionTir();
            grilles.mouvement(grilles.grilleOrdi, positionTir[0], positionTir[1]);


            //Vérifie si ce tir permet au joueur de gagner
            victoireJoueur = grilles.vainqueur(grilles.grilleOrdi);


            System.out.println();
            System.out.println();

        }

        //Affiche le vainqueur
        if (victoireJoueur)
        {
            interfaceUtiisateur.afficherVainqueur(grilles.grilleJeu);
        }
        else
        {
            System.out.println();
            interfaceUtiisateur.afficherVainqueur(grilles.grilleOrdi);
        }

    }

}

