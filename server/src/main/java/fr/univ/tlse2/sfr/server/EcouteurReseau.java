package fr.univ.tlse2.sfr.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import fr.univ.tlse2.sfr.communication.DemarrerSimulation;
import fr.univ.tlse2.sfr.communication.MessageTexte;

/**
 * L'�couteur r�seau du serveur. D�finit la r�action du serveur aux messages envoy�s.
 */
public class EcouteurReseau extends Listener{
	private ProgrammeServeur programme_serveur;
	public EcouteurReseau (ProgrammeServeur programme_serveur) {
		this.programme_serveur = programme_serveur;
	}
	/** R�agit � la reception d'un objet s�rialis�. */
	 public void received (Connection connection, Object object) {
         System.out.println("le serveur recoit qqch");
		 if (object instanceof DemarrerSimulation) {
       	  DemarrerSimulation demande_demarrage_simulation = (DemarrerSimulation)object;
            System.out.println("Demande de d�marrage de simuation appell�e " + demande_demarrage_simulation.nom_simulation);
            MessageTexte reponse = new MessageTexte("Demande de d�marrage de simulation recu !");
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
		 else
		 {
			 System.err.println("commande inconnue");
		 }
      }
}
