package fr.kede.server.game;

public class GamerSquare {
	
	private int x, y;

    
    public GamerSquare(int x, int y) {
        this.x = x;
        this.y=y;
    }

    /**
     * Accesseur pour la position de la raquette sur l'axe des x
     * 
     * @return Position de la raquette sur l'axe des x
     */
    public int getX() {
        return x;
    }

    /**
     * Mutateur pour la position de la raquette sur l'axe des x
     * 
     * @param x Nouvelle position de la raquette sur l'axe des x
     */
    public void setX(int x) {
        this.x = x;
    }
    
    
    
    
    
    public int getY() {
        return y;
    }

    /**
     * Mutateur pour la position de la raquette sur l'axe des x
     * 
     * @param x Nouvelle position de la raquette sur l'axe des x
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
	
}
