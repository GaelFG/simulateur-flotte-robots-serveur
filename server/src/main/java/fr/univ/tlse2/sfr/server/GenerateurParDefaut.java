package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.Position;

public class GenerateurParDefaut {
	private List<Robot> robots;
	private Carte carte;
	private Simulateur simulation;
	
	public GenerateurParDefaut(Simulateur simu){
		this.simulation = simu;
		carte = new Carte();
		robots = new LinkedList<Robot>();
		robots.add(new Robot(1, new Position(0.0,0.0),45, 1.0, simulation));
		robots.add(new Robot(2, new Position(1.0,1.0),45, 1.0, simulation));
	}
	
	//methode retournant la liste des robots
	public List<Robot> get_robots(){
		return robots;
	}
	
	//méthode retournant une carte par défaut
	public Carte get_carte(){
		return carte;
	}
}
