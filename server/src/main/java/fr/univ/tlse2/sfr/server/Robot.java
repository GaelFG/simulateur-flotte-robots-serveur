package fr.univ.tlse2.sfr.server;

import java.util.Random;

import fr.univ.tlse2.sfr.communication.Position;

public class Robot {
	private int id_robot;
	private Position pos_robot;
	/** orientation en degres*/
	private int orientation_robot;
	private double vitesse;
	private Random random_orientation;
	private int coef_orientation;
	
	//constructeur par défaut
	public Robot(){
		this.id_robot = 0;
		this.pos_robot = new Position(0.0,0.0);
		this.orientation_robot = 0;
		this.vitesse = 1;
	}
	
	//constructeur avec paramètre
	public Robot(int id, Position pos, int orientation, double vitesse){
		this.id_robot = id;
		this.pos_robot = pos;
		this.orientation_robot = orientation;
		this.vitesse = vitesse;
	}

	//getteur id_robot
	public int getId_robot() {
		return id_robot;
	}

	//getteur pos_robot
	public Position getPos_robot() {
		return pos_robot;
	}

	//getteur orientation_robot 
	public int getOrientation_robot() {
		return orientation_robot;
	}
	
	public void agir() {
		this.determiner_nouvelle_orientation();
		this.se_deplacer(vitesse);
	}

	private void se_deplacer(double vitesse) {
		double futur_x;
		double futur_y; 
		
		futur_x = this.pos_robot.get_x() + Math.cos(Math.toRadians(orientation_robot)) * vitesse;
		futur_y = this.pos_robot.get_y() + Math.sin(Math.toRadians(orientation_robot)) * vitesse;
		this.pos_robot.set_x(futur_x);
		this.pos_robot.set_y(futur_y);
		
	}

	private void determiner_nouvelle_orientation() {
		// on determine la nouvelle orientation
		this.random_orientation = new Random();
		this.coef_orientation = random_orientation.nextInt(101);
		//boolean coef_orientationisbetween = 
		if (0 <= this.coef_orientation && this.coef_orientation <= 5)
			this.orientation_robot = (orientation_robot - 60) % 360;
		else if (6 <= this.coef_orientation && this.coef_orientation <= 15)
			this.orientation_robot = (orientation_robot - 30) % 360;
		else if (86 <= this.coef_orientation && this.coef_orientation <= 95)
			this.orientation_robot = (orientation_robot + 30) % 360;
		else if (96 <= this.coef_orientation && this.coef_orientation <= 100)
			this.orientation_robot = (orientation_robot + 60) % 360;
	}
	
	
}
