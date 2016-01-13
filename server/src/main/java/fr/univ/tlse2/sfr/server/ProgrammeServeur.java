package fr.univ.tlse2.sfr.server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import fr.univ.tlse2.sfr.communication.DemarrerSimulation;
import fr.univ.tlse2.sfr.communication.EnregistreurKryo;
import fr.univ.tlse2.sfr.communication.MessageTexte;

public class ProgrammeServeur {
	
	private Server serveur_kryo;
	private Simulateur simulateur;
	
	public ProgrammeServeur() {
		initialiser_serveur_kryo(8073);
		definir_ecouteur_serveur_kryo();
		simulateur = new Simulateur();
	}

	public void run() {
		GenerateurParDefaut parametre_par_defaut = new GenerateurParDefaut(simulateur);
        simulateur.initialiser_simulation(parametre_par_defaut.get_liste_robot_par_defaut(), parametre_par_defaut.get_carte_par_defaut());
        while (true) {
        	simulateur.faire_evoluer();
        	serveur_kryo.sendToAllTCP(simulateur.calculer_etat_simulation());
			try { 
				wait(100); 
				} catch (Exception e) {
				
			}
		}
	}
	
	private void initialiser_serveur_kryo(int port_tcp) {
		serveur_kryo = new Server();
		serveur_kryo.start();
		EnregistreurKryo.enregistrerLesClassesDeCommunication(serveur_kryo.getKryo());
		try {
			serveur_kryo.bind(port_tcp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** L'ecouteur définit la facon dont le serveur éagit aux messages clients recus. */
	private void definir_ecouteur_serveur_kryo() {
		serveur_kryo.addListener(new Listener() {
		       public void received (Connection connection, Object object) {
		          if (object instanceof DemarrerSimulation) {
		        	  DemarrerSimulation demande_demarrage_simulation = (DemarrerSimulation)object;
		             System.out.println("Demande de démarrage de simuation appellée " + demande_demarrage_simulation.nom_simulation);

		             MessageTexte reponse = new MessageTexte("Demande de démarrage de simulation recu !");
		             connection.sendTCP(reponse);
		          }
		       }
		    });
	}
}