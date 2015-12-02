package fr.univ.tlse2.sfr.server;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Simulateur simu = new Simulateur();
        GenerateurParDefaut parametre_par_defaut = new GenerateurParDefaut();
        simu.demarrer_simulation(parametre_par_defaut.get_liste_robot_par_defaut(), parametre_par_defaut.get_carte_par_defaut());
    }
}
