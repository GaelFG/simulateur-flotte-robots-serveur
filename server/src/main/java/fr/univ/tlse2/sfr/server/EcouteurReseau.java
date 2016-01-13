package fr.univ.tlse2.sfr.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import fr.univ.tlse2.sfr.communication.DemarrerSimulation;
import fr.univ.tlse2.sfr.communication.MessageTexte;

/**
 * L'écouteur réseau du serveur. Définit la réaction du serveur aux messages envoyés.
 */
public class EcouteurReseau extends Listener{
	
	/** Réagit à la reception d'un objet sérialisé. */
	 public void received (Connection connection, Object object) {
         
		 if (object instanceof DemarrerSimulation) {
       	  DemarrerSimulation demande_demarrage_simulation = (DemarrerSimulation)object;
            System.out.println("Demande de démarrage de simuation appellée " + demande_demarrage_simulation.nom_simulation);
            MessageTexte reponse = new MessageTexte("Demande de démarrage de simulation recu !");
            connection.sendTCP(reponse);
         }
		 
		 
      }
}
