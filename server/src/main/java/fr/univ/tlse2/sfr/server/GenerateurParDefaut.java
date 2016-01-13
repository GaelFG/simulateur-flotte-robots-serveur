package fr.univ.tlse2.sfr.server;

import java.util.LinkedList;
import java.util.List;

import fr.univ.tlse2.sfr.communication.Position;

public class GenerateurParDefaut {
	private List<Robot> liste_robots;
	private Carte map;
	private Simulateur simulation;
	
	public GenerateurParDefaut(Simulateur simu){
		//liste_robots = get_liste_robot_par_defaut();
		//map = get_carte_par_defaut();
		this.simulation = simu;
	}
	
	//methode retournant la liste des robots
	public List<Robot> get_liste_robot_par_defaut(){
		List<Robot> liste_robots = new LinkedList<Robot>();
		liste_robots.add(new Robot(1, new Position(0.0,0.0),45, 1.0, simulation));
		liste_robots.add(new Robot(2, new Position(1.0,1.0),45, 1.0, simulation));
		return liste_robots;
	}
	
	//méthode retournant une carte par défaut
	public static Carte get_carte_par_defaut(){
		Carte carte = new Carte();
		return carte;
	}
}
