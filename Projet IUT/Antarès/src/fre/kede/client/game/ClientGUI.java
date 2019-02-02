package fre.kede.client.game;

import java.awt.Container;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.kede.client.network.IO;
import fr.kede.client.network.Input;
import fr.kede.client.network.Output;

import java.io.OutputStream;

public class ClientGUI extends JFrame implements Runnable {


/**
 * Classe gï¿½rant l'interface graphique du jeu.
 * Le top-level container est bien sr une fenï¿½tre.
 * ï¿½ noter que cette classe implï¿½mente l'interface Runnable pour plus de flexibilitï¿½.
 * Il sera ainsi possible de lancer un thread en passant une instance de cette classe au constructeur de Thread.
 */
	
	
	
	
	/** Constante dï¿½terminant la largeur de la fenï¿½tre */
    public static final short GUI_WIDTH = 800;
    
    /** Constante dï¿½terminant la hauteur de la fenï¿½tre */
    public static final short GUI_HEIGHT = 600;
    
    /** Constante dï¿½terminant le port de connexion au serveur */
    public static final short GAME_PORT = 1337;
    
   
    
    /** Zone affichant les scores (de type JList) */
    private HealthZone healthZone;
    
    private GameZone gamezone;
  
    
    
    /** Socket de communication avec le serveur */
    private Socket socket;
    
    /** Objet encapsulant les requêtes entrantes (in) */
    private IO input;
    
    /** Objet encapsulant les requêtes sortantes (out) */
    private IO output;

    
    
    
    
    
    
    public ClientGUI() {
    	super("antares");
    	int center=(GameZone.WIDTH/2);
    	GamerSquare square=new GamerSquare(250,250);
    	Gamer gamer=new Gamer();
    	gamer.setSquare(square);
    	Monster monster = Monster.getMonster();
    	
    	  // Affichage de la pop-up initiale pour demander le pseudo de l'utilisateur
        String response = JOptionPane.showInputDialog(this, "Entrez votre pseudo :", "Bienvenue sur Paddle Game !", JOptionPane.PLAIN_MESSAGE);
        if (response == null) { // Si l'utilisateur clique sur « Annuler »...
            System.exit(0);
        } else { // Sinon...
            if (response.length() >= 3) { // Si le pseudo contient au moins 3 caractères, on accepte le joueur
                System.out.println("Joueur OK : " + response);
                gamer.setPseudo(response);
            } else { // On dit à l'utilisateur que son pseudo n'est pas bon et on met fin au jeu
                JOptionPane.showMessageDialog(this, "Votre pseudo doit contenir au moins 3 caractères", "Mauvais pseudo !", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    	
        
        DefaultListModel<Gamer> healthModel = new DefaultListModel<>();
        healthModel.addElement(gamer);
        HealthList<Gamer> healthList = new HealthList<>(healthModel);
        healthZone = new HealthZone(healthList);
        
    	
    	Container screen=this.getContentPane();
        
    	gamezone=new GameZone(monster, gamer, healthModel);
    	
    	
    	//configuration de la fenetre
    	  screen.setLayout(new BoxLayout(screen, BoxLayout.X_AXIS));
    	  screen.add(gamezone);
    	  screen.add(healthZone);    	
    
    	setSize(GUI_WIDTH,GUI_HEIGHT);
    	setResizable(false);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	pack();
    	setVisible(true);
    	
    	//Lancement du Thread de connexion au serveur 
    	new Thread().start();
    	
    	run();
    }
    
    
    
    
 // Crï¿½ation de l'interface graphique en Swing
    
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	public void run() {
		
			 try {
				socket = new Socket(InetAddress.getLocalHost(), GAME_PORT);
				this.input=new Input(socket.getInputStream(),gamezone);
				output=new Output(socket.getOutputStream(), gamezone);
				new Thread(input).start();
	            new Thread(output).start();
	            
	            System.out.println("connectéé ");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
		}
           
	
	
	

}
	
	
	
	
	
	
	
	
	
	
	
	
	