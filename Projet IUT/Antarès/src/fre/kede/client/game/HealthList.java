package fre.kede.client.game;


import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

/**
 * Classe d�finissant une JList personnalis�e pour l'affichage des scores.
 * 
 * @author Baptiste Vannesson
 */
public class HealthList<Gamer> extends JList {

    /** Constante d�terminant la marge interne sup�rieure */
    public static final byte PADDING_TOP = 10;
    
    /** Constante d�terminant la marge interne gauche */
    public static final byte PADDING_LEFT = 10;
    
    /** Constante d�terminant la marge interne inf�rieure */
    public static final byte PADDING_BOTTOM = 10;
    
    /** Constante d�terminant la marge interne droite */
    public static final byte PADDING_RIGHT = 10;
    
    /**
     * Constructeur de la zone d'affichage des scores.
     * Ce dernier prend en param�tre un mod�le de liste.
     * Lors de la mise � jour de ce mod�le, notre vue sera repeinte automatiquement.
     * 
     * @param gamers La liste des joueurs connect�s au jeu
     */
    public HealthList(DefaultListModel<Gamer> model) {
        super(model);
        setBackground(Color.DARK_GRAY);
        setAlignmentX(CENTER_ALIGNMENT);
        setBorder(new EmptyBorder(PADDING_TOP, PADDING_LEFT, PADDING_BOTTOM, PADDING_RIGHT));
        setCellRenderer(new HealthRendering());
    }
    
}