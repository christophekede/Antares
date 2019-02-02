package fr.kede.server.network;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.kede.server.game.GameDimensions;
import fr.kede.server.game.Gamer;
import fr.kede.server.game.GamerManagement;
import fr.kede.server.game.Monster;
import fr.kede.server.main.Server;

public class Output extends Thread {
	
	/**
	 * Référence de la balle
	 */
	private static Monster monster;

	/**
     * La liste des gestionnaires de client
     */
    private List<GamerManagement> listGamerManagement;
    
    /**
     * La liste des joueurs
     */
    private List<Gamer> listGamers;
    
    /**
     * Permet de faire réculer ou anvancer la balle sur l'axe horizontal
     */
    private boolean reverseX;
    
    /**
     * Permet de faire réculer ou anvancer la balle sur l'axe vertical
     */
    private boolean reverseY;
    
    /**
     * Représente la coordonnée X de la balle
     */
    private int MonsterX;
    
    /**
     * Représente la coordonnée Y de la balle
     */
    private int MonsterY;
    
    /**
     * Permet de savoir si le Thread peux continuer à envoyer les informations
     */
    private boolean canSend;
    
    /**
     * Constructeur de la classe
     */
	public Output () {
		this.listGamerManagement = Server.listGamerManagement;
		this.listGamers = new ArrayList<>();
        this.canSend = true;
		this.start();
	}
	
	/**
	 *	Méthode génrant l'écriture dans la sortie standard des clients
	 */
	public void run () {
		try {
			createNewMonster();
			send();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * Méthode gérant l'envoi des informations aux clients
	 */
	private void send() {
		while (canSend){
			for (GamerManagement gm : listGamerManagement) {
				if (!listGamers.contains(gm.getGamer())) {
					 listGamers.add(gm.getGamer());
				 }
				String msg = Primitives.SEND_MONSTER_COORDS.toString();
		 		this.write(msg,gm.getOut()); //On informe que les infos qui vont suivres concernent la balle
		 		this.moveBall();
		 		msg = Integer.toString(monster.getX()); //Le x de la balle
		 		this.write(msg,gm.getOut());
		     	msg = Integer.toString(monster.getY()); //Le y de la balle
		     	this.write(msg,gm.getOut());
		     	for (Gamer gamer : listGamers) { //Envoi les infos de tout le monde à tous clients
		     		msg = gamer.toString(); 
			     	this.write(msg,gm.getOut());
				}
			}
		}
	}
	/**
	 * Méthode permettant de générer des coordonnées aléatoires pour la balle
	 * @return coodonnées aléatoires
	 */
	private int[] getRandomCoords() {
 		Random r = new Random();
		int low = GameDimensions.MIN_MONSTER_COORD;
		int heigh = GameDimensions.GAME_ZONE_WIDTH;
		MonsterX = r.nextInt(heigh - low) + low;
		MonsterY = r.nextInt(heigh - low) + low;
		int[] coords = {MonsterX,MonsterY};
		return coords;
		
 	}
	/**
	 * Méthode qui crée une nouvelle balle
	 */
 	private void createNewMonster() {
		int[] coords = this.getRandomCoords();
		monster = Monster.getMonster(coords[0], coords[1]);
 	}
 	/**
 	 * Méthode gérant le déplacement de la balle
 	 */
 	private void moveBall() {
 		
 		if (MonsterX < 1) reverseX = false;
	    //if (MonsterX > GameDimensions.GAME_ZONE_WIDTH - GameDimensions.Monster.DIAMETER) reverseX = true;          
	    if (MonsterY < 1) reverseY = false;
	    //if (MonsterY > GameDimensions.GAME_ZONE_WIDTH - GameDimensions.BALL_DIAMETER) reverseY = true;
	    
	    // Déplacement en x
	    if (!reverseX) {
	    	monster.setX(++MonsterX);
	    } else {
	    	monster.setX(--MonsterX);
	    }
	    
	    // Déplacement en y
	    if (!reverseY) {
	    	monster.setY(++MonsterY);
	    } else {
	    	monster.setY(--MonsterY);
	    }
	      
	      // Gestion des collisions
	      /*int min = Math.abs(ballX - (listGamers.get(0).getPaddle().getX() + (GameDimensions.PADDLE_WIDTH / 2)));
    	  Gamer winner = null;
    	  boolean collisionDetected = false;
	      for (Gamer gamer : listGamers) {
	    	  if (ballY + GameDimensions.BALL_DIAMETER == GameDimensions.PADDLE_Y
	                  && ballX >= gamer.getPaddle().getX()
	                  && ballX <= gamer.getPaddle().getX() + GameDimensions.PADDLE_WIDTH) {
	    		  collisionDetected = true;
	              reverseY = true;
	              int paddleCenter = gamer.getPaddle().getX() + (GameDimensions.PADDLE_WIDTH / 2);
	    		  int diff = Math.abs(ballX - paddleCenter);
	    		  if (diff <= min) {
	    			  min = diff;
	    			  winner = gamer;
	    		  }
	          }
	      }
	      if (collisionDetected) {
	    	  this.calculateScore(true,winner);
	      }
          // Perte de la balle
          if (ballY > GameDimensions.PADDLE_Y + GameDimensions.BALL_DIAMETER + 1) {
        	  int[] coords = this.getRandomCoords();
              ball.setX(coords[0]);
              ball.setY(coords[1]);
              this.calculateScore(false,null);
          } */
	      try {
	        Thread.sleep(5);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
 	} 
 	/**
     * Permet d'écrire les messages dans le flux d'écriture des clients
     * @param message le message à écrire
     */
    private void write(String message,PrintWriter out) {
	   	 out.println(message);
	   	 out.flush();
    }
    /**
     * Recalcule automatiquement le score des joueurs
     * @param winnerExist permet de savoir si un vainqueur existe
     * @param winner le vainqueur à qui attribuer le point
     */
    private void calculateScore(boolean winnerExist, Gamer winner) {
    	int points = listGamers.size();
    	for (Gamer gamer : listGamers) {
    		gamer.setIdealPoints(points);
    		if (winnerExist && gamer.equals(winner)) {
    			gamer.setEarnedPoints(points);
    		}
      	  double rawScore = ((double) gamer.getEarnedPoints() / gamer.getIdealPoints());
  		  int percentScore = (int)(rawScore * 100);
  		 gamer.setHealth(percentScore);
        }
    }
}