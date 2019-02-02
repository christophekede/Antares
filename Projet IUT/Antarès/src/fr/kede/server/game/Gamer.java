package fr.kede.server.game;



public class Gamer {

	
	private String pseudo;
	
	/**
	 * Le score du joueur
	 */
	private int health;
	
	/**
	 * La raquette du joueur
	 */
	private GamerSquare square;
	
	/**
	 * Nombre de points gagnés par le joueur
	 */
	private int earnedPoints;
	
	/**
	 * Nombre de points idéal que devrait ganger le joueur
	 */
	private int idealPoints;
	/**
	 * Constructeur de la classe
	 * @param pseudo pseudo du joueur
	 * @param score score du joueur
	 * @param paddle raquette du joueur
	 */
	public Gamer(String pseudo, int health, GamerSquare gamerSquare) {
		this.pseudo = pseudo;
		this.health = health;
		this.square = gamerSquare;
		this.earnedPoints = 0;
		this.idealPoints = 0;
	}

	/**
	 * Renvoie le pseudo du joueur
	 * @return String
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Permet de modifier le pseudo du joueur
	 * @param pseudo le nouveau pseudo à assigner
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Renvoie le score du joueur 
	 * @return int
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Modifie le score du joueur
	 * @param score nouveau score à assigner
	 */
	public void setHealth(int heatlth) {
		this.health = health;
	}

	/**
	 * Renvoie la raquette du joueur
	 * @return Paddle
	 */
	public GamerSquare getGamerSquare() {
		return square;
	}

	/**
	 * Modifie la raquette du joueur
	 * @param paddle nouvelle raquette à assigner
	 */
	public void setGamerSqaure(GamerSquare square) {
		this.square = square;
	}
	
	/**
	 * Nombre points gagnés par le joueur
	 * @return int
	 */
	public int getEarnedPoints() {
		return earnedPoints;
	}

	/**
	 * Modifie le nombre de points du joueur 
	 * @param earnedPoints nouveaux points à assigner
	 */
	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints += earnedPoints;
	}

	/**
	 * Renvoie le nombre de points idéal pour le joueur
	 * @return int
	 */
	public int getIdealPoints() {
		return idealPoints;
	}

	/**
	 * Modifie le nombre de points idéal pour le joueur
	 * @param idealPoints nouveaux points à assigner
	 */
	public void setIdealPoints(int idealPoints) {
		this.idealPoints += idealPoints;
	}

	public String toString() {
		String str = pseudo +"_"+health+"_"+square.getX()+" " +square.getY();
		return str;
	}
	
}
