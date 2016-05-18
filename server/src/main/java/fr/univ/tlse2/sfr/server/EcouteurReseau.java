package fr.univ.tlse2.sfr.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import fr.univ.tlse2.sfr.communication.AjouterRobot;
import fr.univ.tlse2.sfr.communication.ArreterSimulation;
import fr.univ.tlse2.sfr.communication.DemarrerSimulation;
import fr.univ.tlse2.sfr.communication.MessageTexte;
import fr.univ.tlse2.sfr.communication.ParametresSimulation;
import fr.univ.tlse2.sfr.communication.PauseSimulation;

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
			traiter_demande_demarrage_simulation((DemarrerSimulation)object, connection);
         }
		 else if (object instanceof MessageTexte) {
			 traiter_message_texte((MessageTexte)object);
		 } else if (object instanceof ArreterSimulation) {
			 traiter_demande_arret_simulation();
			 String message = ((MessageTexte)object).get_contenu();
			 System.out.println(message);
		 } 
		 else if (object instanceof ArreterSimulation) {
			 programme_serveur.set_etat_simulation(false);
			 System.out.println("Demande de fermeture de la simulation courante");
		 }
		 else if (object instanceof PauseSimulation) {
			 System.out.println("on veut mettre en pause biatch");
		 }
		 else if (object instanceof AjouterRobot) {
			 System.out.println("on veut ajouter un robot");
		 } else {
			 System.err.println("commande inconnue");
		 }
      }

	 @Override
	 public void disconnected (Connection connection) {
		 programme_serveur.set_etat_simulation(false);
	}
	
	 private void traiter_message_texte(MessageTexte message) {
		 String contenu = message.get_contenu();
		 System.out.println(contenu);
	 }
	 
	 private void traiter_demande_demarrage_simulation(DemarrerSimulation demande, Connection connection) {
		 ParametresSimulation parametres = demande.getParametres();
		 if (parametres_simulation_valides(parametres)) {
			 demarrer_simulation(parametres, connection);
		 } else {
			 refuser_de_demarrer_une_simulation(connection);
		 }
	 }
	 
	 private void demarrer_simulation(ParametresSimulation parametres, Connection connection) {
		 System.out.println("Demande de démarrage de simuation appellée " + parametres.nom_simulation);
         MessageTexte reponse = new MessageTexte("Demande de démarrage de simulation bien recue !");
         connection.sendTCP(reponse);
         programme_serveur.creer_une_simulation(parametres);
         programme_serveur.set_etat_simulation(true);
	 }
	 
	 private void refuser_de_demarrer_une_simulation(Connection connection) {
		 System.out.println("Erreur de parametres, impossible de traiter la demande de démarrage de simulation !");
		 MessageTexte reponse = new MessageTexte("Erreur de parametres, impossible de traiter la demande de démarrage de simulation !");
         connection.sendTCP(reponse);
	 }
	 
	 private void traiter_demande_arret_simulation() {
		 programme_serveur.set_etat_simulation(false);
		 System.out.println("Demande de fermeture de la simulation courante");
	 }
	 
	/**
	 * Verifie que les paramètres de création d'une simulation soient complets et valides.
	 * @param parametres Les paramètres à analyser
	 * @return True si les paramètres sont valides.
	 */
	private boolean parametres_simulation_valides(ParametresSimulation parametres) {
		boolean nom_valide = parametres.nom_simulation != "";
		boolean nombre_robots_valide = parametres.nombre_robots >= 0;
		boolean nombre_obstacles_valide = parametres.nombre_obstacles >= 0;
		boolean tous_parametres_valides = nom_valide && nombre_robots_valide && nombre_obstacles_valide;
		return tous_parametres_valides;
	}
}
