package fr.eni.projet.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate dateEnchere;
	private int noUtilisateur;
	private int noVente;
	private int montant;
	private int classement;// /!\ NEW

	public Enchere() {
	}

	public Enchere(LocalDate dateEnchere, int noUtilisateur, int noVente, int montant) {
		this.noUtilisateur = noUtilisateur;
		this.noVente = noVente;
		this.dateEnchere = dateEnchere;
		this.montant = montant;
	}
	public Enchere(LocalDate dateEnchere, int noUtilisateur, int noVente) {
		this.noUtilisateur = noUtilisateur;
		this.noVente = noVente;
		this.dateEnchere = dateEnchere;
	}

	public Enchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/*/!\ NEW : Constructeur avec classement*/
	public Enchere(LocalDate dateEnchere, int noUtilisateur, int noVente, int montant, int classement) {
		this.noUtilisateur = noUtilisateur;
		this.noVente = noVente;
		this.dateEnchere = dateEnchere;
		this.montant = montant;
		this.classement = classement;
	}
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/*/!\ NEW : MAJ méthode toString avec tous les paramètres*/
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", noUtilisateur=" + noUtilisateur + ", noVente=" + noVente
				+ ", montant=" + montant + ", classement=" + classement + "]";
	}


	public int getNoVente() {
		return noVente;
	}

		public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getClassement() {
		return classement;
	}

	public void setClassement(int classement) {
		this.classement = classement;
	}

	
}
