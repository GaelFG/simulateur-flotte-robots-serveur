package fr.univ.tlse2.sfr.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;

import fr.univ.tlse2.sfr.communication.DemarrerSimulation;
import fr.univ.tlse2.sfr.communication.MessageTexte;

/**
 * L'écouteur réseau du serveur. Définit la réaction du serveur aux messages envoyés.
 */
public class EcouteurReseau extends Listener{
	private ProgrammeServeur programme_serveur;
	public EcouteurReseau (ProgrammeServeur programme_serveur) {
		this.programme_serveur = programme_serveur;
	}
	/** Réagit à la reception d'un objet sérialisé */
	 public void received (Connection connection, Object object) {
         System.out.println("le serveur recoit qqch");
		 if (object instanceof DemarrerSimulation) {
       	  DemarrerSimulation demande_demarrage_simulation = (DemarrerSimulation)object;
            System.out.println("Demande de démarrage de simuation appellée " + demande_demarrage_simulation.nom_simulation);
            MessageTexte reponse = new MessageTexte("Demande de démarrage de simulation recu !");
            connection.sendTCP(reponse);
            programme_serveur.set_etat_simulation(true);
            programme_serveur.run();
         }
		 else if (object instanceof MessageTexte) {
			 System.out.println(((MessageTexte) object).get_contenu());
			 String message = ((MessageTexte)object).get_contenu();
			 if (message.equals("STOP"))
			 {
				 programme_serveur.set_etat_simulation(false);
			 }
		 }
		 else if (object instanceof FrameworkMessage)
		 {
			 //on fait rien
		 }
		 else
		 {
			 System.out.println(object.getClass().getName());
			 System.err.println("commande inconnue");
		 }
      }
}
