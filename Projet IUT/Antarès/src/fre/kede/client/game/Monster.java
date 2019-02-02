package fre.kede.client.game;


/**
 * Classe d�finissant une balle.
 * Une balle est ici un disque dessin� dans un canevas (un panneau).
 * � noter que le jeu ne doit comporter qu'une seule balle, d'o� l'utilisation d'un Singleton.
 * 
 */




public class Monster {
	

/** Constante d�terminant le diam�tre du monstre */
public static byte DIAMETER = 20;

/** Instance statique de la classe elle-m�me */

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
     * M�thode statique d'acc�s � l'unique instance de la classe.
     * On simule ici un constructeur par d�faut.
     */
    public static Monster getMonster() {
        return getMonster(0,0);
    }
    
    /**
     * M�thode statique d'acc�s � l'unique instance de la classe.

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
