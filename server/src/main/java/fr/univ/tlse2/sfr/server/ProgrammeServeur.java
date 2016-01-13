package fr.univ.tlse2.sfr.server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Server;
import fr.univ.tlse2.sfr.communication.EnregistreurKryo;

public class ProgrammeServeur {
    
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ProgrammeServeur programme = new ProgrammeServeur();
        programme.run();
    }
	
	private Server serveur_kryo;
	private Simulateur simulateur;
	
	public ProgrammeServeur() {
		initialiser_serveur_kryo(8073);
		definir_ecouteur_serveur_kryo();
		simulateur = new Simulateur();
	}

	public void run() {
		GenerateurParDefaut parametre_par_defaut = new GenerateurParDefaut(simulateur);
        simulateur.initialiser_simulation(parametre_par_defaut.get_robots(), parametre_par_defaut.get_carte());
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
		serveur_kryo.addListener(new EcouteurReseau());
	}
}