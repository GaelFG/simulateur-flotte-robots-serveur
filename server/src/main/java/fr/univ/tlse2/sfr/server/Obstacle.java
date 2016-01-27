package fr.univ.tlse2.sfr.server;

import fr.univ.tlse2.sfr.communication.EtatObstacle;
import fr.univ.tlse2.sfr.communication.Position;

public class Obstacle {
	private int taille;
	private Position position;
	
	public Position getPosition() {
		return position;
	}
	
	public int getTaille() {
		return taille;
	}

	public Obstacle(){}
	
	public Obstacle(int ptaille, Position pposition){
		this.taille = ptaille;
		this.position = pposition;
	}
	
	public EtatObstacle calculer_etat_obstacle() {
		return new EtatObstacle(this.taille, this.position);
	}
}
