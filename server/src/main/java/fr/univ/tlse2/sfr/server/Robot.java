package fr.univ.tlse2.sfr.server;

import fr.univ.tlse2.sfr.communication.Position;

public class Robot {
	private int id_robot;
	private Position pos_robot;
	private int orientation_robot;
	private double vitesse;
	
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
		
		futur_x = this.pos_robot.get_x();
		futur_y = this.pos_robot.get_y(); 		
		
	}

	private void determiner_nouvelle_orientation() {
		// TODO Auto-generated method stub
		
	}
	
	
}
