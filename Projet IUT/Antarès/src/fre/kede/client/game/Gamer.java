package fre.kede.client.game;

import java.awt.Color;

/**
 * JavaBean d�finissant l'entit� joueur.
 * Dans le cadre du jeu, un joueur est repr�sent� par son pseudo, son score, sa raquette, et sa couleur.
 * 
 * @author Baptiste Vannesson
 */
public class Gamer {

    /** Constante d�terminant le score par d�faut d'un joueur */
    public static final int DEFAULT_SCORE = 0;
    
    /** Cha�ne de caract�res repr�sentant le pseudo du joueur */
    private String pseudo;
    
    /** Entier entre 0 et 100 repr�sentant le score (en pourcentage) du joueur */
    private int health;
    
    /** Objet repr�sentant la raquette du joueur */
    private GamerSquare square;
    
    /** Couleur attribu�e au joueur */
    private Color color;
    
    /**
     * Constructeur par d�faut d'un joueur
     */
    public Gamer() {
        this("", DEFAULT_SCORE, null, Color.WHITE);
    }
    
    /**
     * Constructeur principal permettant d'initialiser un objet Gamer
     * 
     * @param pseudo Pseudo du joueur
     * @param score Score du joueur
     * @param paddle Raquette du joueur
     * @param color Couleur du joueur
     */
    public Gamer(String pseudo, int health, GamerSquare square, Color color) {
        this.pseudo = pseudo;
        this.health= health;
        this.square = square;
        this.color = color;
    }

    /**
     * Accesseur pour le pseudo du joueur
     * 
     * @return Pseudo du joueur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Mutateur pour le pseudo du joueur
     * 
     * @param pseudo Nouveau pseudo du joueur
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Accesseur pour le score du joueur
     * 
     * @return Score du joueur
     */
    public int getHealth() {
        return health;
    }

    /**
     * Mutateur pour le score du joueur
     * 
     * @param score Nouveau score du joueur
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Accesseur pour la raquette du joueur
     * 
     * @return Raquette du joueur
     */
    public GamerSquare getSquare() {
        return square;
    }

    /**
     * Mutateur pour la raquette du joueur
     * 
     * @param paddle Nouvelle raquette du joueur
     */
    public void setSquare(GamerSquare square) {
        this.square = square;
    }
    
    /**
     * Accesseur pour la couleur du joueur
     * 
     * @return color Couleur du joueur
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Mutateur pour la couleur du joueur
     * 
     * @param color Nouvelle couleur du joueur
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
}