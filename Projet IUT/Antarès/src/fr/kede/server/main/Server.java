package fr.kede.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.kede.server.game.GamerManagement;

public class Server {

	
	
	
	
	/**
	 * Port d'�coute du serveur
	 */
	public static final int PORT = 1337;
	
	/**
	 * Le socket serveur 
	 */
	public static ServerSocket ss = null;
	
	/**
	 * Liste des gestionnaires de joueurs
	 */
	public static List<GamerManagement> listGamerManagement = new ArrayList<>();
	
	/**
	 * M�thode principale de la classe permettant de recevoir en continu
	 * les connexions clients
	 * @param args les arguments de la m�thode
	 */
	public static void main(String[] args) {
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Le serveur est � l'�coute du port "+ss.getLocalPort());
			while(true) {
				Socket client = ss.accept();
				System.out.print("un client connect�");
				GamerManagement gm = new GamerManagement(client);
				listGamerManagement.add(gm);
				gm.start();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
}
