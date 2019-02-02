package fr.kede.client.network;

import fre.kede.client.game.GameZone;

/**
 * Classe abstraite d'entr�es/sorties permettant d'�viter la redondance de code.
 * Cette classe est utile pour factoriser les �l�ments communs � Input et Output.
 * 
 * @author Baptiste Vannesson
 */
public abstract class IO implements Runnable {
    
    /** Zone de jeu */
    protected GameZone gameZone;
    
    /** 
     * Constructeur prenant en param�tre la zone de jeu � manipuler
     * 
     * @param gameZone La zone de jeu
     */
    public IO(GameZone gameZone) {
        this.gameZone = gameZone;
    }
    
}