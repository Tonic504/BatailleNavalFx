package com.example.battaillenavalfx.modules_bataille;


/***
 * Classe Navire
 * <p>Cette classe permet d'instancier un navire avec ses données associées.</p>
 *
 * @author Maxence SABATIER
 * @version 1.0

 */
public class Navire
{
    //Données d'un navire : Son nom, sa taille et son identifiant.
    private String nom_navire;
    private int taille_navire;
    private int ID_navire;


    /***
     * Constructeur Navire
     * <p>Permet de créer un navire.</p>
     * @param nom (ex : Porte-avions...)
     * @param taille (ex : 5)
     * @param ID (ex : 1)
     * @return Crée un navire.
     */
    Navire(String nom, int taille, int ID)
    {
        nom_navire = nom;
        taille_navire = taille;
        ID_navire = ID;

    }

    //Méthodes permettant d'obtenir les données du navire.

    /***
     * Méthode obtenirNom
     * <p>Renvoie le nom du navire</p>
     * @return Nom du navire (ex : Porte-avions)
     */
    public String obtenirNom() { return nom_navire;}

    /***
     * Méthode obtenirTaille
     * <p>Renvoie la taille du navire</p>
     * @return Taille du navire (ex : 5)
     */
    public int obtenirTaille() { return taille_navire;}

    /***
     * Méthode obtenirID
     * <p>Renvoie l'identifiant du navire</p>
     * @return Identifiant du navire (ex : 1)
     */
    public int obtenirID() { return ID_navire;}
}
