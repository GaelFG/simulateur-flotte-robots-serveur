package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.EtatRobot;
import fr.univ.tlse2.sfr.communication.EtatSimulation;

public class Simulateur {
	private List<Robot> liste_robots;
	private Boolean simulation_pas_finie = true;
	private Carte carte;
	
	public Simulateur(){}
	
	public List<Robot> getListe_robots() {
		return liste_robots;
	}
	
	public void demarrer_simulation(List<Robot> liste, Carte map) {
		initialiser_simulation(liste, map);
		while ( simulation_pas_finie ) {
		 faire_evoluer();
		}
	}

	public void initialiser_simulation(List<Robot> liste, Carte map) {
		liste_robots = liste;
		carte = map;
	}
	
	public void faire_evoluer() {
		//pour tout robot, robot.agir
		for (Robot r : liste_robots)
			r.agir();
		afficher_etat_simu();
		//simulation_pas_finie = false;
	}
	
	public EtatSimulation calculer_etat_simulation() {
		LinkedList<EtatRobot> liste_etats_robots = new LinkedList<EtatRobot>();
		for (Robot r : liste_robots){
			liste_etats_robots.add(r.calculer_etat_robot());
		}
		
		return new EtatSimulation(carte.calculer_etat_carte(), liste_etats_robots);
	}
	
	public void afficher_etat_simu() {
		//pour tout robot afficher etat robot
		for (Robot r : liste_robots){
			System.out.println("Information robot " + r.getId_robot());
			System.out.println("x=" + r.getPos_robot().get_x() + " y=" + r.getPos_robot().get_y());
			System.out.println(r.getOrientation_robot());
		}
	}
}
