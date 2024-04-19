package com.example.battaillenavalfx.modules_bataille;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Classe Grille
 * <p>Cette classe est chargée de créer et gérer les grilles du joueur et de l'ordinateur durant la bataille.</p>
 *
 * @author Maxence SABATIER
 * @version 1.0
 */
public class Grille {

    //Grilles ordinateur et utilisateur. C'est sur ces grilles que se déroulera le jeu.

    /**
     * Grille ordinateur 10x10 unique à cette classe.
     */
    public static int [][] grilleOrdi = new int [10][10];

    /**
     * Grille joueur 10x10 unique à cette classe.
     */
    public static int [][] grilleJeu = new int [10][10];


    //Liste des navires présents dans le jeu.
    private static final List<Navire> navires = Arrays.asList(
            new Navire("Porte-avions",5,1),
            new Navire("Croiseur",4,2),
            new Navire("Contre-torpilleur",3,3),
            new Navire("Sous-marin",3,4),
            new Navire("Torpilleur",2,5)
    );


    //Variable et méthode permettant la génération aléatoire d'un nombre.
    private static Random rand = new Random();


    /**
     * Méthode positionValide
     * <p>Vérifie si la position donnée est libre pour placer un navire.</p>
     * @param grille (Grille joueur ou ordinateur)
     * @param ligne (Numéro de ligne : 0 - 9)
     * @param colonne (Numéro de colonne : 0 - 9)
     * @param direction (1 = horizontal, 2 = vertical)
     * @param taille (taille du navire, 2 à 5 cases)
     * @return boolean (Position valide ou non)
     */
    public static boolean positionValide(int[][] grille, int ligne, int colonne, int direction, int taille)
    {
        if (direction == 1) //Direction horizontale
        {
            //Vérifie que la ligne donnée est valide
            if (ligne > 9 || ligne < 0)
                return false;

            else
            {
                if (colonne < 0 || colonne + taille - 1 > 9) //Vérifie que le bateau rentre dans la direction donnée
                    return false;

                for (int indice = 0; indice < taille; indice++)
                {
                    if (grille[ligne][colonne + indice] != 0)   //Vérifie si la position est libre
                        return false;
                }
            }


        }

        else    //Direction verticale
        {
            //Vérifie que la colonne donnée est valide
            if (colonne > 9 || colonne < 0)
                return false;

            else
            {
                if (ligne < 0 || ligne + taille - 1 > 9) //Vérifie que le bateau rentre dans la direction donnée
                    return false;

                for (int indice = 0; indice < taille; indice++)
                {
                    if (grille[ligne + indice][colonne] != 0)   //Vérifie si la position est libre
                        return false;
                }
            }

        }

        return true; //Si toutes les vérifications ont été passées, la position est valide.
    }


    /**
     * Méthode initGrilleOrdi
     * <p>Initialise aléatoirement la grille de l'ordinateur.</p>
     *
     * Méthodes utilisées :
     * @see Grille#positionValide(int[][], int, int, int, int)
     * @see Navire#obtenirTaille()
     * @see Navire#obtenirID()
     */
    public static void initGrilleOrdi()
    {
        //Variables à déterminer aléatoirement
        int ligne;
        int colonne;
        int direction;

        /*
        Boucle qui s'effectue tant qu'il y a un bateau à positionner.
        Teste d'abord la position avec les variables aléatoires
        Si la position est valide, place le navire.
         */
        for (int indice = 0; indice < navires.size(); indice++)
        {
            do
            {
                ligne = randomRange(0,10);
                colonne = randomRange(0,10);
                direction = randomRange(1,3);
            }
            while (!positionValide(grilleOrdi,ligne,colonne,direction,
                    (navires.get(indice)).obtenirTaille()));


            //Placement du navire en affichant son identifiant sur la grille
            for (int j = 0; j < navires.get(indice).obtenirTaille(); j++) {

                if (direction == 1)
                {
                    grilleOrdi[ligne][colonne + j] = navires.get(indice).obtenirID();
                }
                else
                {
                    grilleOrdi[ligne + j][colonne] = navires.get(indice).obtenirID();
                }
            }

        }

    }


    /**
     * Méthode initGrilleJeu
     * <p>Initialise la grille du joueur selon les entrées utilisateurs.</p>
     *
     * Méthodes utilisées :
     * @see Grille#positionValide(int[][], int, int, int, int)
     * @see Affichage#afficherGrille(int[][])
     * @see Affichage#demanderColonne(String)
     * @see Affichage#demanderLigne(String)
     * @see Affichage#demanderDirection(String)
     * @see Navire#obtenirNom() 
     * @see Navire#obtenirTaille()
     * @see Navire#obtenirID()
     */
    public static void initGrilleJeu()
    {
        //Données nécessaires au positionnement des bateaux
        int ligne;
        int colonne;
        int direction;

        boolean positionInvalide;

        System.out.println("Jouons à la bataille navale ! \nInitialisation de la grille du joueur...");

        //Boucle d'initialisation. Pour chaque navire, va demander les coordonnées et le placer.
        for (int indiceNavire = 0; indiceNavire < navires.size(); indiceNavire++)
        {
            do {

                //Demande les données à l'utilisateur.
                colonne = Affichage.demanderColonne(navires.get(indiceNavire).obtenirNom());
                ligne = Affichage.demanderLigne(navires.get(indiceNavire).obtenirNom());
                direction = Affichage.demanderDirection(navires.get(indiceNavire).obtenirNom());

                //Vérifie si la position est dans la grille et s'il n'y a rien dessus.
                if (!positionValide(grilleJeu,ligne,colonne,direction, navires.get(indiceNavire).obtenirTaille()))
                {
                    positionInvalide = true;
                    System.out.println("Le navire ne rentre pas sur la grille. Veuillez recommencer");
                }
                else
                {
                    positionInvalide = false;
                }

            }
            while (positionInvalide);


            //Place le navire.
            for (int j = 0; j < navires.get(indiceNavire).obtenirTaille(); j++) {

                if (direction == 1)
                {
                    grilleJeu[ligne][colonne + j] = navires.get(indiceNavire).obtenirID();
                }
                else
                {
                    grilleJeu[ligne + j][colonne] = navires.get(indiceNavire).obtenirID();
                }
            }

            Affichage.afficherGrille(grilleJeu);

        }
    }


    /**
     * Méthode couler
     * <p>Vérifie si un navire donné a été coulé dans une grille.</p>
     * @return booléen : true (navire coulé) - false (navire non coulé)
     */
    private static boolean couler(int[][] grille, int numeroBateau) {

        for (int ligne = 0; ligne < grille.length; ligne++) {

            for (int colonne = 0; colonne < grille[0].length; colonne++) {

                if (grille[ligne][colonne] == numeroBateau)
                    return false;
            }
        }

        return true;
    }


    /**
     * Méthode mouvement
     * <p>Effectue un tir dans une grille.</p>
     * @param grille (grille visée : grilleOrdi - grilleJeu)
     * @param ligne (Numéro de ligne : 0 - 9)
     * @param colonne (Numéro de colonne : 0 - 9)
     * <br>
     * Méthodes utilisées :
     * @see Affichage#afficherResultatTir(String, Navire) 
     * @see Grille#couler(int[][], int)
     */
    public static void mouvement(int[][] grille, int ligne, int colonne)
    {
        int numeroBateauTouche = grille[ligne][colonne]; //Récupère l'identifiant de la baseS

        if (numeroBateauTouche != 0 && numeroBateauTouche != 6) //Vérifie s'il s'agit de l'identifiant d'un navire
        {
            grille[ligne][colonne] = 6; //Met la case en "touché"

            /*
            Donne le résultat, si le navire a été touché ou coulé
            Puis affiche la grille pour que le joueur constate visuellement le résultat
            */
            if (couler(grille,numeroBateauTouche))
            {
                //Lancer affichage "Coulé"
                Affichage.afficherResultatTir("Coulé !", navires.get(numeroBateauTouche - 1));

                if (grille == grilleJeu)
                {
                    Affichage.afficherGrille(grilleJeu);
                }
                else
                {
                    Affichage.afficherGrille(grilleOrdi);
                }

            }
            else
            {
                //Lancer affichage "Touché"
                Affichage.afficherResultatTir("Touché !",null);

                if (grille == grilleJeu)
                {
                    Affichage.afficherGrille(grilleJeu);
                }
                else
                {
                    Affichage.afficherGrille(grilleOrdi);
                }
            }

        }
        else    //Si la position n'est pas un navire intact
        {
            if (grille[ligne][colonne] == 6) //Informe que cette case est un navire déjà touché
            {
                //Lancer affichage "Bateau déjà touché"
                Affichage.afficherResultatTir("Navire déjà touché.",null);
            }
            else //Informe que le tir est raté
            {
                //Lancer affichage "A l'eau"
                Affichage.afficherResultatTir("A l'eau.",null);
            }

        }
    }

    /**
     * Méthode tirOrdinateur
     * <p>Récupère les coordonnées de tir de l'ordinateur. La position est déterminée aléatoirement.</p>
     * @return Tableau de deux entiers contenant la ligne (indice 0 du tableau) et la colonne (indice 1 du tableau) choisies
     * <br>
     * Méthode utilisée :
     * @see Grille#randomRange(int, int)
     */
    public static int[] tirOrdinateur()
    {
        int ligne = randomRange(0,10);
        int colonne = randomRange(0,10);

        return new int[]{ligne,colonne};
    }


    /**
     * Méthode vainqueur
     * @param grille (grille à comparer : grilleOrdi - grilleJeu)
     * @return booléen : true (adversaire vaincu) - false (adversaire encore en combat)
     * <br>
     * Méthode utilisée :
     * @see Navire#obtenirID()
     */
    public static boolean vainqueur(int[][] grille)
    {
        for (int indice = 0; indice < navires.size(); indice++)
        {
            if (!couler(grille, navires.get(indice).obtenirID()))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Méthode randomRange
     * <p>Donne un entier aléatoire entre deux bornes.</p>
     * @param a (borne inclusive)
     * @param b (borne exclusive)
     * @return entier aléatoire entre a et b exclu.
     */
    private static int randomRange(int a, int b)
    {
        return rand.nextInt(b- a) + a;
    }

}


