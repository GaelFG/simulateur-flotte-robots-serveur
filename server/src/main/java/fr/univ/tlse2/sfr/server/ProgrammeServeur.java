package fr.univ.tlse2.sfr.server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Server;
import fr.univ.tlse2.sfr.communication.EnregistreurKryo;

public class ProgrammeServeur {
    
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ProgrammeServeur programme = new ProgrammeServeur();
        //programme.run();
    }
	
	private Server serveur_kryo;
	private Simulateur simulateur;
	/** Le framerate du serveur, baiss� pour les tests*/
	private static int FRAMERATE = 30;
	private static int TEMPS_ENTRE_DEUX_FRAMES = 1000/FRAMERATE;
	private boolean etat_simulation;
	
	public ProgrammeServeur() {
		initialiser_serveur_kryo(8073);
		definir_ecouteur_serveur_kryo();
		simulateur = new Simulateur();
	}

	public void run() {
		System.out.println("On démarre une simulation sur le serveur !");
		GenerateurParDefaut parametre_par_defaut = new GenerateurParDefaut(simulateur);
        simulateur.initialiser_simulation(parametre_par_defaut.get_robots(), parametre_par_defaut.get_carte(), parametre_par_defaut.get_obstacles());
        while (etat_simulation) {
        	simulateur.faire_evoluer();
        	serveur_kryo.sendToAllTCP(simulateur.calculer_etat_simulation());
			try {
				Thread.sleep(TEMPS_ENTRE_DEUX_FRAMES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void set_etat_simulation(boolean etat) {
		System.out.println("modification de l'etat simulation");
		this.etat_simulation = etat;
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
	
	/** L'ecouteur d�finit la facon dont le serveur r�agit aux messages clients recus. */
	private void definir_ecouteur_serveur_kryo() {
		serveur_kryo.addListener(new EcouteurReseau(this));
	}
}