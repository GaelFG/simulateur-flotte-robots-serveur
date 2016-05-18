package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.ParametresSimulation;
import fr.univ.tlse2.sfr.communication.Position;

public class GenerateurParDefaut {
	private List<Robot> robots;
	private List<Obstacle> obstacles;
	private Carte carte;
	private Simulateur simulation;
	private final int TAILLE_OBSTACLE = 16; //TODO avec l'algo de colision actuel il est umperatif que la taille des bstacle soit toujour de la meme taille que les robots
	
	public GenerateurParDefaut(Simulateur simu, ParametresSimulation parametres){
		this.simulation = simu;
		carte = new Carte();
		
		
		robots = new LinkedList<Robot>();
		double x_base = 10.0;
		double y_base = 10.0;
		for (int i = 1 ; i <= parametres.nombre_robots ; i++) {
			robots.add(new Robot(i, new Position(x_base,y_base),45, 0.5, simulation));
			x_base += 48;
			y_base += 48;
		}
		
		obstacles = new LinkedList<Obstacle>();
		x_base = 55.0;
		y_base = 25.0;
		for (int i = 1 ; i <= parametres.nombre_obstacles ; i++) {
			obstacles.add(new Obstacle(TAILLE_OBSTACLE, new Position(x_base,y_base)));
			x_base += 48;
			y_base += 48;
		}
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
