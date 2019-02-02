package fre.kede.client.game;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * Classe d�finissant une zone d'affichage pour les scores.
 * Il s'agit ici d'un conteneur avec ascenseur vertical.
 * Utile si le jeu accueille un grand nombre de joueurs (car les scores sortent alors de l'�cran)
 *  
 * @author Baptiste Vannesson
 */
public class HealthZone extends JScrollPane {
    
    /** Constante d�terminant la marge interne sup�rieure */
    public static final byte PADDING_TOP = 0;
    
    /** Constante d�terminant la marge interne gauche */
    public static final byte PADDING_LEFT = 0;
    
    /** Constante d�terminant la marge interne inf�rieure */
    public static final byte PADDING_BOTTOM = 0;
    
    /** Constante d�terminant la marge interne droite */
    public static final byte PADDING_RIGHT = 3;
    
    /**
     * Constructeur de la zone d'affichage des scores.
     * Ce dernier prend en param�tre un mod�le de liste.
     * Lors de la mise � jour de ce mod�le, notre vue sera repeinte automatiquement.
     * 
     * @param gamers La liste des joueurs connect�s au jeu
     */
    public HealthZone(JList<Gamer> gamers) {
        super(gamers);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setBorder(new EmptyBorder(PADDING_TOP, PADDING_LEFT, PADDING_BOTTOM, PADDING_RIGHT));
        setBackground(Color.LIGHT_GRAY);
    }
    
}