	package fre.kede.client.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public class GameZone extends JPanel implements MouseMotionListener, MouseListener, KeyListener{


    /** Constante déterminant la largeur de la zone de jeu */
    public static final short WIDTH = 600;
    
    /** Constante déterminant la hauteur de la zone de jeu */
    public static final short HEIGHT = 600;
	
    private Monster monster;
    /** Le joueur courant */
    private Gamer gamer;
    
    
    /** Map des adversaires connectés au jeu */
    private Map<String,Gamer> opponents;
    
    /** Modèle de liste pour affichage des scores */
    private DefaultListModel<Gamer> healthModel;
    
    
    public GameZone(Monster monster, Gamer gamer, DefaultListModel<Gamer> healthModel) {
    			this.monster=monster;
    			this.gamer=gamer;
    			
    			 this.healthModel = healthModel;
    		        this.opponents = new HashMap<String,Gamer>();
    		        
        // Taille de la zone de jeu
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        // On écoute les événements de MouseMotionListener (drag & move)
        this.addMouseMotionListener(this);
       this.addMouseListener(this);
        this.addKeyListener(this);
   }
    
    
    
    /**
     * Méthode fondamentale pour dessiner tous les éléments du jeu.
     * La structure de cette méthode s'inspire du pattern Template.
     * 
     * @param g Un objet de type Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // On dessine la zone de jeu
        drawGameZone(g2d);
        
        drawMonster(g2d);
       
        drawGamerSquare(g2d, gamer);
    }
    
    /**
     * Méthode helper utilisée par « paintComponent » pour dessiner la zone de jeu.
     * 
     * @param g2d Objet Graphics2D
     */
    private void drawGameZone(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }
    
    
    private void drawMonster(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(monster.getX(), monster.getY(), Monster.DIAMETER, Monster.DIAMETER);
    }
    
    
    private void drawGamerSquare(Graphics g2d, Gamer gamer) {
    	GamerSquare square= gamer.getSquare();
        g2d.setColor(gamer.getColor());
        g2d.fillRect(square.getX(), square.getY(), 25, 25);
    }
    
    
    private void drawGamerSquares(Graphics2D g2d) {
        // S'ils existent, on dessine les raquettes des adversaires (avec transparence)
        if (opponents.size() > 0) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));
            for (Gamer opponent : opponents.values()) {
                drawGamerSquare(g2d, opponent);
            }
        }
        // On dessine la raquette du joueur courant (opaque)
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    	drawGamerSquare(g2d, gamer);
    }
    


    private void paddleAction(MouseEvent e) {
        GamerSquare square = gamer.getSquare();
      if (e.getX() < (GameZone.WIDTH - GamerSquare.WIDTH)) {
        		square.setX(e.getX());
        		square.setY(e.getY());
        		
        		System.out.println(e.getX()/100 +" "+e.getY()/100);
       
              repaint();
        }
      
    }
    
    
    private void paddleAction(KeyEvent e) {
        GamerSquare square = gamer.getSquare();
       if (e.getKeyCode()==39) {
        		square.setX(square.getX()+5);
       }
        repaint();
    }
    

	public void mouseDragged(MouseEvent e) {
		// paddleAction(e);
		//System.out.println(e.getX());
	}



	
	public void mouseMoved(MouseEvent e) {
		  
		
		
	}



	
	public void mouseClicked(MouseEvent e) {
		paddleAction(e);
		
		System.out.println("click");
		
	}




	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	public void keyPressed(KeyEvent e) {
		paddleAction(e);
		System.out.println("rihjt");
	}




	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stu
		
	}



	public void keyTyped(KeyEvent e) {
		System.out.println("rihjt");
		// TODO Auto-generated method stub
		
	}
    
    
    
    
	
	
	
	
	 public void addOpponent(Gamer gamer) {
	        opponents.put(gamer.getPseudo(), gamer);
	    }
	    
	    /**
	     * Méthode permettant de supprimer un adversaire de la liste des adversaires.
	     * Utile lorsqu'un client quitte le jeu.
	     * 
	     * @param La raquette à supprimer de la zone de jeu
	     */
	    public void removeOpponent(Gamer gamer) {
	        opponents.remove(gamer);
	    }

	    /**
	     * Accesseur pour la balle
	     * 
	     * @return Un objet de type Ball
	     */
	    public Monster getMonster() {
	        return monster;
	    }

	    /**
	     * Accesseur pour le joueur courant
	     * 
	     * @return Le joueur courant
	     */
	    public Gamer getGamer() {
	        return gamer;
	    }
	    
	    /**
	     * Accesseur pour les adversaires du joueur courant
	     * 
	     * @return Une liste d'objets de type Paddle
	     */
	    public Map<String,Gamer> getOpponents() {
	        return opponents;
	    }

	    /**
	     * Accesseur pour le modèle des scores
	     * 
	     * @return Un objet DefaultListModel
	     */
	    public DefaultListModel<Gamer> getHealthModel() {
	        return healthModel;
	
	    }
	
	
	
	
	
	
	
	
    
}



