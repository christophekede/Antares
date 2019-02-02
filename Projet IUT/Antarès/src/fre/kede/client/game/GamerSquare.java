package fre.kede.client.game;

/**
 * Classe d�finissant une raquette.
 * Une raquette est ici un rectangle dessin� dans un canevas (panneau).
 * Dans ce jeu, chaque joueur poss�de �videmment sa propre raquette.
 * 
 * @author Baptiste Vannesson
 */
public class GamerSquare {

    /** Constante d�terminant la largeur de la raquette */
    public static final byte WIDTH = 100;
    
    /** Constante d�terminant la hauteur de la raquette */
    public static final byte HEIGHT = 15;
 
    /** Constante d�terminant la position de la raquette sur l'axe des y */
    private int y;
    
    /** Position de la raquette sur l'axe des x */
    private int x;
    
    /**
     * Constructeur par d�faut d'une raquette.
     * Par d�faut, une raquette se trouve � l'origine en x.
     */
 
    /**
     * Constructeur d'une raquette, prenant en param�tre sa position sur l'axe des abscisses.
     * 
     * @param x Position de la raquette sur l'axe des x
     * 
     */
    
    public GamerSquare() {
        this(0,0);
    }
    
    public GamerSquare(int x, int y) {
        this.x = x;
        this.y=y;
    }

    /**
     * Accesseur pour la position de la raquette sur l'axe des x
     * 
     * @return Position de la raquette sur l'axe des x
     */
    public int getX() {
        return x;
    }

    /**
     * Mutateur pour la position de la raquette sur l'axe des x
     * 
     * @param x Nouvelle position de la raquette sur l'axe des x
     */
    public void setX(int x) {
        this.x = x;
    }
    
    
    
    
    
    public int getY() {
        return y;
    }

    /**
     * Mutateur pour la position de la raquette sur l'axe des x
     * 
     * @param x Nouvelle position de la raquette sur l'axe des x
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}