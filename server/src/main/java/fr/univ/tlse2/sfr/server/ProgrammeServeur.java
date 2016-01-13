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
	
	public ProgrammeServeur() {
		initialiser_serveur_kryo(8073);
		definir_ecouteur_serveur_kryo();
		while (true) {
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
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