package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.EtatCarte;
import fr.univ.tlse2.sfr.communication.Position;

public class Simulateur {
	private List<Robot> liste_robots;
	private Boolean simulation_pas_finie = true;
	private Carte carte;
	
	public Simulateur(){}
	
	
	
	public void demarrer_simulation(List<Robot> liste, Carte map) {
		//creer objets
		liste_robots = liste;
		carte = map;
		
		while ( simulation_pas_finie ) {
		 //pour tout robot, robot.agir
			for (Robot r : liste_robots)
				r.agir();
			afficher_etat_simu();
			simulation_pas_finie = false;
		}
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
