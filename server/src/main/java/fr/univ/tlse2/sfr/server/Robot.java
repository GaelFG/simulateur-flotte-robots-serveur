package fr.univ.tlse2.sfr.server;

import java.util.Random;

import fr.univ.tlse2.sfr.communication.EtatRobot;
import fr.univ.tlse2.sfr.communication.Position;

public class Robot {
	private static int RADIUS_ROBOT_EN_PX = 16;
	private int id;
	private Position position;
	/** orientation en degres*/
	private int orientation;
	private double vitesse;
	private Random random_orientation;
	private int coef_orientation;
	private Simulateur simulation;
	
	//constructeur par defaut
	public Robot(){
		this.id = 0;
		this.position = new Position(0.0,0.0);
		this.orientation = 0;
		this.vitesse = 1;
	}
	
	//constructeur avec parametre
	public Robot(int id, Position pos, int orientation, double vitesse, Simulateur simulation){
		this.id = id;
		this.position = pos;
		this.orientation = orientation;
		this.vitesse = vitesse;
		this.simulation = simulation;
	}

	//getteur id_robot
	public int getId_robot() {
		return id;
	}

	//getteur pos_robot
	public Position getPos_robot() {
		return position;
	}

	//getteur orientation_robot 
	public int getOrientation_robot() {
		return orientation;
	}
	
	public void agir() {
		this.determiner_nouvelle_orientation();
		this.se_deplacer(vitesse);
	}

	private void se_deplacer(double vitesse) {
		double futur_x;
		double futur_y; 
		
		futur_x = this.position.x + Math.cos(Math.toRadians(orientation)) * vitesse;
		futur_y = this.position.y + Math.sin(Math.toRadians(orientation)) * vitesse;
		
		// On vérifie qu'il ne percute pas un autre robot
		for (Robot r : this.simulation.getListe_robots()){
			if (this.id != r.id){
				if ((futur_x > r.position.x - RADIUS_ROBOT_EN_PX && futur_x < r.position.x + RADIUS_ROBOT_EN_PX) && (futur_y > r.position.y - RADIUS_ROBOT_EN_PX && futur_y < r.position.y + RADIUS_ROBOT_EN_PX)){
					futur_x = this.position.x;
					futur_y = this.position.y;
				}		
			}
		}	
		
		// On vérifie qu'il ne percute pas un obstacle
		for (Obstacle o : this.simulation.get_liste_obstacles()){
			if ((futur_x > o.getPosition().x - o.getTaille() && futur_x < o.getPosition().x + o.getTaille()) && (futur_y > o.getPosition().y - o.getTaille() && futur_y < o.getPosition().y + o.getTaille())){
				futur_x = this.position.x;
				futur_y = this.position.y;
			}		
		}
		
		// Gestion des débordements de la carte
		// Si un robot dépasse les bords de la carte, il est ramené de 
		// l'autre coté, comme si la carte est une sphère
		while (futur_x > this.simulation.get_carte().get_largeur()){
			futur_x = futur_x - this.simulation.get_carte().get_largeur();
		}
		while (futur_x < 0){
			futur_x = futur_x + this.simulation.get_carte().get_largeur();
		}
		while (futur_y > this.simulation.get_carte().get_hauteur()){
			futur_y = futur_y - this.simulation.get_carte().get_hauteur();
		}
		while (futur_y < 0){
			futur_y = futur_y + this.simulation.get_carte().get_hauteur();
		}
			
		this.position = new Position(futur_x, futur_y);
	}

	private void determiner_nouvelle_orientation() {
		// on determine la nouvelle orientation
		this.random_orientation = new Random();
		this.coef_orientation = random_orientation.nextInt(101);
		//boolean coef_orientationisbetween = 
		if (0 <= this.coef_orientation && this.coef_orientation <= 5)
			this.orientation = (orientation - 60) % 360;
		else if (6 <= this.coef_orientation && this.coef_orientation <= 15)
			this.orientation = (orientation - 30) % 360;
		else if (86 <= this.coef_orientation && this.coef_orientation <= 95)
			this.orientation = (orientation + 30) % 360;
		else if (96 <= this.coef_orientation && this.coef_orientation <= 100)
			this.orientation = (orientation + 60) % 360;
	}

	public EtatRobot calculer_etat_robot() {
		// TODO Auto-generated method stub
		return new EtatRobot(id, position, orientation);
	}	
}
