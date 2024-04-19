package com.example.battaillenavalfx.modules_bataille;
import java.util.Scanner;

/**
 * Classe Affichage
 * <p>Cette classe est chargée d'afficher l'interface utilisateur et de récupérer les entrées utilisateurs.</p>
 *
 * @author Maxence SABATIER
 * @version 1.0
 */

public class Affichage
{
    //Constantes permettant d'afficher des couleurs dans la console
    private static final String ANSI_RESET = "\u001B[0m";  //Permet de repasser à la couleur standard : du blanc.
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";


    //Initialisation du scanner qui permet à l'utilisateur de faire des entrées au clavier.
    private static final Scanner entreeUtilisateur = new Scanner(System.in);

    //Compteur de tour
    private int nbTours = 0;



    /**
     * Méthode afficherGrille
     * <p>Cette méthode affiche la grille passée en paramètre</p>
     * @param grille (Grille joueur ou grille ordinateur)
     */
    public static void afficherGrille(int[][] grille)
    {
        String lettres = "ABCDEFGHIJ";

        System.out.println();   //Laisse une ligne vide entre la dernière ligne de la console et l'affichage de la grille.

        if (grille == Grille.grilleJeu)
        {
            System.out.print("     == Grille Joueur ==");
        }
        else
        {
            System.out.print("      == Grille Ordi ==");
        }

        System.out.println();


        //Affiche les lettres
        System.out.print("     ");
        for (int indice = 0; indice < lettres.length(); indice++)
        {
            System.out.print(String.format("%-2s",lettres.charAt(indice)));
        }

        System.out.println();


        //Affiche un séparateur entre les lettres et la grille
        System.out.print("     ");
        for (int indice = 0; indice < lettres.length(); indice++)
        {
            System.out.print(String.format("%-2s","-"));
        }

        System.out.println();


        //Affiche la grille
        for (int ligne = 0; ligne < grille.length; ligne++)
        {
            System.out.print(String.format("%-3d",ligne+1) + "| ");    //Affiche le numéro de ligne

            //Affiche le contenu d'une ligne
            for (int colonne = 0; colonne < grille[0].length; colonne++)
            {
                if (grille[ligne][colonne] == 0)
                    System.out.print(String.format("%-2d",grille[ligne][colonne])); //Case vide, l'affiche en blanc.

                else if (grille[ligne][colonne] == 6)
                    System.out.print( ANSI_RED + String.format("%-2d",grille[ligne][colonne]) + ANSI_RESET); //Case navire touché, l'affiche en rouge

                else
                    System.out.print( ANSI_GREEN + String.format("%-2d",grille[ligne][colonne]) + ANSI_RESET); //Case navire non touché, l'affiche en vert
            }

            System.out.println();
        }

    }


    /**
     * Méthode demanderColonne
     * <p>Demande à l'utilisateur une lettre (colonne) sur laquelle doit être placé le bateau.</p>
     * @param nomBateau (Nom du bateau pour lequel on demande la colonne)
     * @return Numéro de colonne sur laquelle doit être placé le bateau
     */
    public static int demanderColonne(String nomBateau)
    {
        char lettreColonne;
        int entierColonne;

        if (!nomBateau.isEmpty())   //Si le nom est vide, c'est que l'on appelle la méthode pour un tir, et non un placement.
        {
            System.out.println("Veuillez entrer la lettre (colonne) pour le " + nomBateau + " : ");
        }

        //Récupère la lettre de l'utilisateur
        lettreColonne = entreeUtilisateur.next().charAt(0);

        //Conversion en majuscule pour permettre à l'utilisateur de rentrer une lettre minuscule.
        lettreColonne = Character.toUpperCase(lettreColonne);

        //Convertit la lettre donnée en l'indice correspondant. Si la saisie est erronée, on recommence.
        do {
            switch (lettreColonne)
            {
                case 'A':
                    entierColonne = 0;
                    break;
                case 'B':
                    entierColonne = 1;
                    break;
                case 'C':
                    entierColonne = 2;
                    break;
                case 'D':
                    entierColonne = 3;
                    break;
                case 'E':
                    entierColonne = 4;
                    break;
                case 'F':
                    entierColonne = 5;
                    break;
                case 'G':
                    entierColonne = 6;
                    break;
                case 'H':
                    entierColonne = 7;
                    break;
                case 'I':
                    entierColonne = 8;
                    break;
                case 'J':
                    entierColonne = 9;
                    break;
                default:
                    entierColonne = -1;
                    System.out.println("Lettre invalide. Veuillez entrer une lettre entre A et J : ");
                    lettreColonne = entreeUtilisateur.next().charAt(0);

            }
        }
        while (entierColonne == -1);

        return entierColonne;
    }


    /**
     * Méthode demanderLigne
     * <p>Demande à l'utilisateur la ligne sur laquelle doit être placé le bateau.</p>
     * @param nomBateau (Nom du bateau pour lequel on demande la ligne)
     * @return Numéro de ligne sur laquelle doit être placé le bateau.
     */
    public static int demanderLigne(String nomBateau)
    {
        int entierLigne = -999;
        boolean entierCorrect;

        if (!nomBateau.isEmpty())   //Si le nom est vide, c'est que l'on appelle la méthode pour un tir, et non un placement.
        {
            System.out.println("Veuillez entrer le nombre (ligne) pour le " + nomBateau + " : ");
        }


        //Récupère la saisie de l'utilisateur et vérifie si elle est valide. Si elle ne l'est pas, on recommence.
        do
        {
            try {
                String entree = entreeUtilisateur.next();

                entierLigne = Integer.parseInt(entree); //Récupère l'entier à partir du String

                if (entierLigne < 1 || entierLigne > 10)
                {
                    System.out.println("Saisie invalide. Veuillez entrer un entier entre 1 et 10 : ");
                    entierCorrect = false;
                }
                else
                {
                    entierCorrect = true;
                }
            }
            catch (NumberFormatException e) //La saisie n'est pas un entier.
            {
                System.out.println("Saisie invalide. Veuillez entrer un entier entre 1 et 10 : ");
                entierCorrect = false;
            }

        }
        while (!entierCorrect);

        return entierLigne - 1; //Renvoi l'indice du tableau de la ligne choisie
    }


    /**
     * Méthode demanderDirection
     * <p>Demande à l'utilisateur dans quelle direction doit être placé le bateau.</p>
     * @param nomBateau (Nom du bateau pour lequel on demande la colonne)
     * @return 1 (direction horizontale) ou 2 (direction verticale)
     */
    public static int demanderDirection(String nomBateau)
    {
        int entierDirection = -999;
        boolean entierCorrect;

        System.out.println("Voulez vous que le " + nomBateau + " soit horizontal (1) ou vertical (2) : ");

        //Récupère la saisie de l'utilisateur et vérifie si elle est valide. Si elle ne l'est pas, on recommence.
        do
        {
            try {
                String entree = entreeUtilisateur.next();

                entierDirection = Integer.parseInt(entree); //Récupère l'entier à partir du String

                if (entierDirection != 1 && entierDirection != 2)
                {
                    System.out.println("Saisie invalide. Veuillez entrer 1 (horizontal) ou 2 (vertical) : ");
                    entierCorrect = false;
                }
                else
                {
                    entierCorrect = true;
                }
            }
            catch (NumberFormatException e) //La saisie n'est pas un entier.
            {
                System.out.println("Saisie invalide. Veuillez entrer un entier entre 1 et 10 : ");
                entierCorrect = false;
            }

        }
        while (!entierCorrect);

        return entierDirection;

    }


    /**
     * Méthode conversionEntierLettre
     * <p>Cette méthode convertit un numéro de ligne en la lettre correspondante.</p>
     * @param ligne (Numéro de ligne : 0 - 9)
     * @return Lettre correspondant au numéro de ligne donné.
     */
    public static char conversionEntierLettre(int ligne)
    {
        return switch (ligne) {
            case 0 -> 'A';
            case 1 -> 'B';
            case 2 -> 'C';
            case 3 -> 'D';
            case 4 -> 'E';
            case 5 -> 'F';
            case 6 -> 'G';
            case 7 -> 'H';
            case 8 -> 'I';
            case 9 -> 'J';
            default -> 'Z';
        };
    }


    /**
     * Méthode demanderPositionTir
     * <p>Demande à l'utilisateur où il veut tirer.</p>
     * @return Tableau de deux entiers contenant la ligne (indice 0 du tableau) et la colonne (indice 1 du tableau) choisies
     * <br>
     * Méthodes utilisées :
     * @see Affichage#demanderLigne(String) 
     * @see Affichage#demanderColonne(String)
     * @see Affichage#conversionEntierLettre(int) 
     */
    public static int[] demanderPositionTir()
    {
        int colonne;
        int ligne;

        System.out.println("C'est à vous de jouer ! Où voulez vous tirer ?\nVeuillez choisir une lettre (colonne) :");

        colonne = demanderColonne("");  //Récupère la colonne

        System.out.println("Veuillez choisir une ligne :");

        ligne = demanderLigne("");  //Récupère la ligne

        System.out.println("Tir en position : " + conversionEntierLettre(colonne) + (ligne+1) + " ...");    //Affiche la position choisie

        return new int[]{ligne,colonne};

    }


    /**
     * Méthode afficherResultatTir
     * <p>Affiche le résultat du tir. Si un navire a été coulé, affiche son nom et son ID.</p>
     * @param resultat (A l'eau - Touché - Coulé - Navire déjà touché)
     * @param navire   (Navire coulé, doit être null si aucun navire coulé pour ce tir.)
     */
    public static void afficherResultatTir(String resultat, Navire navire)
    {
        System.out.println("Resultat du tir : " + resultat);

        if (navire != null) {

            System.out.println(
                    "Navire : "
                    + navire.obtenirNom()
                    + " (No : " + navire.obtenirID() + ")"
            );
        }
    }


    /**
     * Méthode afficherVainqueur
     * <p>Affiche le vainqueur de la bataille.</p>
     * @param grille (Grille du vainqueur : grilleOrdi ou grilleJeu)
     */
    public static void afficherVainqueur(int[][] grille)
    {
        //Affiche le résultat en jaune, selon la grille passée en paramètre
        if (grille == Grille.grilleJeu)
        {
            System.out.println(ANSI_YELLOW + "VICTOIRE du joueur !" + ANSI_RESET);
        }
        else
        {
            System.out.println(ANSI_YELLOW + "VICTOIRE du joueur !" + ANSI_RESET);
        }

    }


    /**
     * Méthode afficherTour
     * <p>Affiche le numéro du tour actuel.</p>
     */
    public void afficherTour() { System.out.println("=== TOUR No " + ++nbTours + " ====");}

}
