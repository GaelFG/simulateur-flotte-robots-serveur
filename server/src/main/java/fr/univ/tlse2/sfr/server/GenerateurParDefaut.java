package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.Position;

public class GenerateurParDefaut {
	private List<Robot> robots;
	private List<Obstacle> obstacles;
	private Carte carte;
	private Simulateur simulation;
	
	public GenerateurParDefaut(Simulateur simu){
		this.simulation = simu;
		carte = new Carte();
		robots = new LinkedList<Robot>();

		robots.add(new Robot(1, new Position(0.0,0.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1.0,1.0),45, 0.5, simulation));
		
		robots.add(new Robot(2, new Position(3.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(2.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(4.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(5.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(6.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(7.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(8.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(9.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(10.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(11.0,1.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1.0,5.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1.0,7.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1.0,9.0),45, 0.5, simulation));
		
		obstacles = new LinkedList<Obstacle>();
		obstacles.add(new Obstacle(1, new Position(3.0,3.0)));
		obstacles.add(new Obstacle(1, new Position(4.0,2.0)));
	}
	
	//methode retournant la liste des robots
	public List<Robot> get_robots(){
		return robots;
	}
	
	//méthode retournant une carte par défaut
	public Carte get_carte(){
		return carte;
	}
	
	//méthode retournant la liste des obstacles
	public List<Obstacle> get_obstacles(){
		return obstacles;
	}
}
