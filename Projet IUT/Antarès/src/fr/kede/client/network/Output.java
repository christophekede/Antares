package fr.kede.client.network;


import java.io.OutputStream;
import java.io.PrintWriter;

import fre.kede.client.game.GameZone;

/**
 * Classe permettant de g�rer les flux sortants de mani�re plus �l�gante.
 * Elle agit comme un gestionnaire de requ�tes vers le serveur.
 * 
 * @author Baptiste Vannesson
 */
public class Output extends IO {

    /** Repr�sentation du flux de sortie (pour l'envoi de donn�es au serveur) */
    private PrintWriter out;
    
    /**
     * Constructeur de la classe.
     * Celui-ci prend en param�tres un objet OutputStream repr�sentant un flux de sortie, ainsi qu'un objet GameZone g�rant toute la partie.
     * Cela est suffisant : le joueur n'envoie au serveur que des informations le concernant (notamment son pseudo et la position de sa raquette).
     * � noter que le flux de sortie est d�cor� d'un PrintWriter lors de l'initialisation...
     * 
     * @param out Flux de sortie
     * @param gameZone Zone de jeu
     */
    public Output(OutputStream out, GameZone gameZone) {
        super(gameZone);
        this.out = new PrintWriter(out); // Pattern Decorator
    }
    
    /**
     * M�thode g�rant les interactions effectives avec le serveur, en �criture.
     * 
     * @param primitive Constante de l'�num�ration d�finissant les primitives du protocole de communication
     */
    private void write(Primitives primitive) {
        // Le client envoie son pseudo au serveur
        if (primitive == Primitives.SEND_PSEUDO) {
            String pseudo = gameZone.getGamer().getPseudo();
            out.println(Primitives.SEND_PSEUDO.toString());
            out.println(pseudo);
        }
        // Le client envoie la position de sa raquette au serveur
        if (primitive == Primitives.SEND_SQUARE_POSITION) {
            String squarePosition = Integer.toString(gameZone.getGamer().getSquare().getX());
            out.println(Primitives.SEND_SQUARE_POSITION.toString());
            out.println(squarePosition);
        }
        out.flush();
    }
    
    /**
     * M�thode du thread g�rant l'�criture dans la sortie standard
     */
    @Override
    public void run() {
        // Si cette m�thode est ex�cut�e, c'est que le thread est lanc�, et donc que l'utilisateur a renseign� son pseudo
        write(Primitives.SEND_PSEUDO);
        while(true) {
            try {
                // La position de la raquette est envoy�e en continu au serveur
                write(Primitives.SEND_SQUARE_POSITION);
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

}