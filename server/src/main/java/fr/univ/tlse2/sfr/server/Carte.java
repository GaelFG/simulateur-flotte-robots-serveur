package fr.univ.tlse2.sfr.server;

import fr.univ.tlse2.sfr.communication.EtatCarte;

public class Carte {
	private String nom_carte;
	private double largeur;
	private double hauteur;
	
	public double get_largeur() {
		return largeur;
	}

	public double get_hauteur() {
		return hauteur;
	}

	public Carte(){
		this.nom_carte = "Carte par défaut";
		this.largeur = 50;
		this.hauteur = 50;
	}
	
	public Carte(String nom, int p_largeur, int p_hauteur){
		this.nom_carte = nom;
		this.largeur = p_largeur;
		this.hauteur = p_hauteur;
	}

	public EtatCarte calculer_etat_carte() {
		// TODO Auto-generated method stub
		return new EtatCarte(nom_carte, largeur, hauteur);
	}
}
