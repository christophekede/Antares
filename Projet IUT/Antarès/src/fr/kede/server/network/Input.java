package fr.kede.server.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.kede.server.game.GamerManagement;

public class Input extends Thread {
	
	/**
	 * Flux de lecture du client
	 */
	private BufferedReader in;
	
	/**
	 * Message � renvoyer aux joueurs
	 */
	private String primitive;
	
	/**
	 * Le client courant 
	 */
	private GamerManagement gamerManagement;
	
	/**
	 * Le pseudo du client courant
	 */
	private String pseudo;
	
	/**
	 * Constructeur de la classe
	 * @param in gestionnaire de client
	 */
	public Input(GamerManagement gm) {
		this.gamerManagement = gm;
		in = gamerManagement.getIn();
		this.start();
	}
	@Override
	/**
	 * M�thode g�rant la r�ception des donn�es dans l'entr�e standard des clients
	 */
	public void run() {
		read();
	}
	/**
	 * M�thode effectuant une lecture permanente des donn�es 
	 * depuis l'entr�e standard des clients
	 */
	private void read() {
		try {
			boolean canRead = true;
			ArrayList<String> listPrimitve = new ArrayList<>();
			listPrimitve.add(Primitives.SEND_PSEUDO.toString());
			listPrimitve.add(Primitives.SEND_SQUARE_POSITION.toString());
			primitive = in.readLine();
			if (primitive.equals(Primitives.SEND_PSEUDO.toString())) {
				pseudo  = in.readLine();
				gamerManagement.getGamer().setPseudo(pseudo); //associe le pseudo au joueur courant
			}
			while(canRead) {
				primitive = in.readLine();
				if (primitive!=null) {
					if (primitive.equals(Primitives.SEND_SQUARE_POSITION.toString())) {
						int paddleX = Integer.parseInt(in.readLine());
						gamerManagement.getGamer().getGamerSquare().setX(paddleX); //Modifie en temps r�el la raquette du joueur courant
					}
				} else {
					canRead = false;
				}
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
