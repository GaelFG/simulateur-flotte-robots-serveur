package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.EtatObstacle;
import fr.univ.tlse2.sfr.communication.EtatRobot;
import fr.univ.tlse2.sfr.communication.EtatSimulation;

public class Simulateur {
	private List<Robot> robots;
	private List<Obstacle> obstacles;
	private Boolean simulation_en_pause = false;
	private Carte carte;
	
	public Simulateur(){}
	
	public List<Robot> getListe_robots() {
		return robots;
	}
	
	public List<Obstacle> get_liste_obstacles(){
		return obstacles;
	}
	
	public Carte get_carte(){
		return carte;
	}

	public void initialiser_simulation(List<Robot> liste, Carte map) {
		robots = liste;
		carte = map;
	}
	
	public void initialiser_simulation(List<Robot> liste_robots, Carte map, List<Obstacle> liste_obstacles) {
		robots = liste_robots;
		carte = map;
		obstacles = liste_obstacles;
	}
	
	public void faire_evoluer() {
		if (!simulation_en_pause) {
			for (Robot robot : robots) {
				robot.agir();
			}
		}
	}
	
	public EtatSimulation calculer_etat_simulation() {
		LinkedList<EtatRobot> liste_etats_robots = new LinkedList<EtatRobot>();
		for (Robot robot : robots){
			liste_etats_robots.add(robot.calculer_etat_robot());
		}
		LinkedList<EtatObstacle> liste_etats_obstacles = new LinkedList<EtatObstacle>();
		for (Obstacle obstacle : obstacles){
			liste_etats_obstacles.add(obstacle.calculer_etat_obstacle());
		}
		return new EtatSimulation(carte.calculer_etat_carte(), liste_etats_robots, liste_etats_obstacles);
	}
}
