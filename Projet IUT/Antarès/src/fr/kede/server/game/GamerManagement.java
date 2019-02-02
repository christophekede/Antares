package fr.kede.server.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.kede.server.network.Input;
import fr.kede.server.network.Output;




public class GamerManagement extends Thread {
	
	/***
	 * Socket client
	 */
	private Socket client;
	
	/**
	 * Flux d'�criture du client
	 */
	private PrintWriter out;
	
	/**
	 * Flux de lecture du client
	 */
	private BufferedReader in;
	
	/**
	 * La r�f�rence du joueur courant
	 */
	private Gamer gamer;
	
	/**
	 * Constructeur de la classe
	 * @param client le client courant en cours de connexion
	 */
	public GamerManagement(Socket client) {
		this.client = client;
		this.gamer = new Gamer(null, 0 , new GamerSquare(25,25) );
		try {
			out = new PrintWriter(this.client.getOutputStream());
			in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	/**
	 * M�thode g�rant le lancement du gestionnaire
	 */
	public void run() {
		try {
			new Input(this);
			new Output();
		} catch (Exception e) {
			System.out.println("Impossible de d�marrer la session"+" "+e.getMessage());
		}
		
	}
	/**
	 * Renvoie le joueur courant associ� au gestionnaire
	 * @return Gamer
	 */
	public Gamer getGamer() {
		return gamer;
	}
	/**
	 * Modifie le joueur courant associ� au gestionnaire
	 * @param gamer le nouveau joueur � assigner
	 */
	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
	}
	/**
	 * Renvoie la sortie standard
	 * @return la sortie standard
	 */
	public PrintWriter getOut() {
		return out;
	}
	/**
	 * Modifie la sortie standard du client courant
	 * @param out nouvelle sortie � assigner
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	/**
	 * Renvoie l'entr�e standard du client courant
	 * @return l'entr�e standard
	 */
	public BufferedReader getIn() {
		return in;
	}
	/**
	 * Modifie l'entr�e standard du client
	 * @param in nouvelle entr�e � assigner
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
}