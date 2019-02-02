package fre.kede.client.game;


/**
 * Classe définissant une balle.
 * Une balle est ici un disque dessiné dans un canevas (un panneau).
 * À noter que le jeu ne doit comporter qu'une seule balle, d'où l'utilisation d'un Singleton.
 * 
 */




public class Monster {
	

/** Constante déterminant le diamètre du monstre */
public static byte DIAMETER = 20;

/** Instance statique de la classe elle-même */

private static Monster monster;

/** Position du monstre sur l'axe des x */
private int x;

/** Position du monstrz l'axe des y */
private int y;

	private Monster(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	
	 /**
     * Méthode statique d'accès à l'unique instance de la classe.
     * On simule ici un constructeur par défaut.
     */
    public static Monster getMonster() {
        return getMonster(0,0);
    }
    
    /**
     * Méthode statique d'accès à l'unique instance de la classe.

     */
    public static Monster getMonster(int x, int y) {
        if (monster == null) {
            monster = new Monster(x, y);
        }
        return monster;
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}








}
