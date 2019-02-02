package fr.kede.client.network;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fr.kede.server.game.GamerManagement;
import fre.kede.client.game.GameZone;
import fre.kede.client.game.Gamer;
import fre.kede.client.game.GamerSquare;

/**
 * Classe permettant de g�rer les flux entrants de mani�re plus �l�gante.
 * Elle agit comme un gestionnaire de r�ponses venant du serveur.
 * 
 * @author Baptiste Vannesson
 */
public class Input extends IO {

    /** Repr�sentation du flux d'entr�e (pour la r�ception de donn�es venant du serveur) */
    private BufferedReader in;
    
    /** L'index de mise � jour de la liste des joueurs */
    private static int index = 0;
    
    /**
     * Constructeur de la classe.
     * Celui-ci prend en param�tres un objet InputStream repr�sentant un flux d'entr�e.
     * � noter que le flux d'entr�e est d�cor� d'un BufferedReader lors de l'initialisation...
     * 
     * @param in Flux d'entr�e
     * @param gameZone Zone de jeu
     */
    public Input(InputStream in, GameZone gameZone) {
        super(gameZone);
        this.in = new BufferedReader(new InputStreamReader(in)); // Pattern Decorator
    }
 

	/**
     * M�thode g�rant les interactions effectives avec le serveur, en lecture.
     */ 
    private void read() throws IOException {
    	// R�ception du message venant du serveur
        String message = in.readLine();
        // Si le client re�oit les nouvelles coordonn�es de la balle...
        if(message.equals(Primitives.SEND_MONSTER_COORDS.toString())) { 
    		int MonsterX = Integer.parseInt(in.readLine());
    		int MonsterY = Integer.parseInt(in.readLine());
    		gameZone.getMonster().setX(MonsterX);
    		gameZone.getMonster().setY(MonsterY);
    		gameZone.repaint();
    		index = 0;
        } else { // Sinon le client re�oit les informations sur les joueurs...
        	// Attention, ce tableau fini de couleurs va poser probl�me au-del� de 7 joueurs !
            Color[] colors = {Color.GREEN, Color.RED, Color.CYAN, Color.PINK, Color.ORANGE, Color.BLUE, Color.YELLOW};
            String[] gamerInfo = message.split("_");
            String pseudo = gamerInfo[0];
            int health= Integer.parseInt(gamerInfo[1]);
            int squarePosition= Integer.parseInt(gamerInfo[2]);
            // On fait la distinction entre le joueur courant et ses adversaires
            if (!pseudo.equals(gameZone.getGamer().getPseudo()) && pseudo != null) {
                // On cr�e et on ajoute l'adversaire au jeu si ce joueur n'existe pas
                if (!gameZone.getOpponents().containsKey(pseudo)) {
                	GamerSquare square = new GamerSquare(squarePosition, squarePosition);
                    Gamer opponent = new Gamer(pseudo, health, square, colors[index]);
                    gameZone.addOpponent(opponent);
                    gameZone.getHealthModel().addElement(opponent);
                } else { // On met � jour l'adversaire s'il existe d�j�
                	Gamer opponent = gameZone.getOpponents().get(pseudo);
                    opponent.getSquare().setX(squarePosition);
                    opponent.setHealth(health);;
                    gameZone.getHealthModel().set(index, opponent);
                }
            } else {
            	gameZone.getGamer().setHealth(health);
            	gameZone.getHealthModel().set(index, gameZone.getGamer());
            }
            index++;
        }
    }
    
    /**
     * M�thode du thread g�rant la lecture dans l'entr�e standard
     */
   
    public void run() {
        while(true) {
            try {
                if (in.ready()) {
                    // Le client lit en continu les informations provenant du serveur
                    read();
                }
            } catch(IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

}
