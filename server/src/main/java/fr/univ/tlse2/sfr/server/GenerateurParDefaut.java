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

		robots.add(new Robot(1, new Position(50.0,0.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(100.0,1.0),45, 0.5, simulation));
		
		robots.add(new Robot(2, new Position(300.0,100.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(200.0,501.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(400.0,61.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(500.0,401.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(600.0,341.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(700.0,431.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(800.0,121.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(900.0,211.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1000.0,221.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1100.0,441.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1000.0,555.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1200.0,70.0),45, 0.5, simulation));
		robots.add(new Robot(2, new Position(1400.0,549.0),45, 0.5, simulation));
		
		obstacles = new LinkedList<Obstacle>();
		obstacles.add(new Obstacle(10, new Position(600.0,40.0)));
		obstacles.add(new Obstacle(10, new Position(400.0,450.0)));
		obstacles.add(new Obstacle(10, new Position(300.0,120.0)));
		obstacles.add(new Obstacle(10, new Position(200.0,1000.0)));
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
