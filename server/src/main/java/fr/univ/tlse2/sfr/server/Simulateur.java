package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.EtatCarte;
import fr.univ.tlse2.sfr.communication.Position;

public class Simulateur {
	private EtatCarte carte;
	private LinkedList<Robot> liste_robots;
	private Boolean simulation_pas_finie;
	
	//méthode retournant la liste des robots
	private static List<Robot> get_liste_robot_par_defaut(){
		List<Robot> liste_robots = new LinkedList<Robot>();
		liste_robots.add(new Robot(1, new Position(0.0,0.0),0));
		liste_robots.add(new Robot(2, new Position(1.0,1.0),1));
		return liste_robots;
	}
	
	public void demarrer_simulation() {
		//crer objets
		
		while ( simulation_pas_finie ) {
		 //pour tout robot, robot.agir
			for (Robot r : liste_robots)
				r.agir();
			afficher_etat_simu();
		}
	}

	public void afficher_etat_simu() {
		//pour tout robot afficher etat robot
		for (Robot r : liste_robots){
			System.out.println(r.getId_robot());
			System.out.println(r.getPos_robot());
			System.out.println(r.getOrientation_robot());
		}
	}
}
