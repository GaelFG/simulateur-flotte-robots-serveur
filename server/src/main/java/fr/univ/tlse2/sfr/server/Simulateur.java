package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.EtatRobot;
import fr.univ.tlse2.sfr.communication.EtatSimulation;

public class Simulateur {
	private List<Robot> robots;
	private List<Obstacle> obstacles;
	private Boolean simulation_pas_finie = true;
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
	
	public void demarrer_simulation(List<Robot> liste, Carte map) {
		initialiser_simulation(liste, map);
		while ( simulation_pas_finie ) {
		 faire_evoluer();
		}
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
		for (Robot robot : robots)
			robot.agir();
		afficher_etat_simulation();
		//simulation_pas_finie = false;
	}
	
	public EtatSimulation calculer_etat_simulation() {
		LinkedList<EtatRobot> liste_etats_robots = new LinkedList<EtatRobot>();
		for (Robot robot : robots){
			liste_etats_robots.add(robot.calculer_etat_robot());
		}
		return new EtatSimulation(carte.calculer_etat_carte(), liste_etats_robots);
	}
	
	public void afficher_etat_simulation() {
		for (Robot robot : robots){
			System.out.println("Information robot " + robot.getId_robot());
			System.out.println("x=" + robot.getPos_robot().x + " y=" + robot.getPos_robot().y);
			System.out.println(robot.getOrientation_robot());
		}
		for (Obstacle obstacle : obstacles){
			System.out.println("Information obstacle \ntaille : " + obstacle.getTaille());
			System.out.println("x=" + obstacle.getPosition().x + " y=" + obstacle.getPosition().y);
		}
	}
}
