package fr.eni.projet.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Vente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int noVente;
	private String nomArticle;
	private String description;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	private String photo;
	private String pseudo;
	private String rue;
	private String codePostal;
	private String ville;
	
	
	public Vente() {}
	
	public Vente(int noVente, String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int prixVente) {
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}
	
	public Vente(int noVente, String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, int noCategorie) {
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}
	
	public Vente(String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int noUtilisateur, int noCategorie, String photo) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.photo = photo;
	}
		
	public Vente(int noVente, String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, int noCategorie, String photo) {
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.photo = photo;
	}

	/*/!\ NEW : Constructeur avec pseudo et adresse du vendeur*/
	public Vente(int noVente, String nomArticle, String description, LocalDate dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, int noCategorie, String photo, String pseudo, String rue, String codePostal, String ville) {
		this.noVente = noVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.photo = photo;
		this.pseudo = pseudo;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	
	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	
	/*/!\ NEW : MAJ méthode toString avec tous les paramètres*/
	@Override
	public String toString() {
		return "Vente [noVente=" + noVente + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente
				+ ", noUtilisateur=" + noUtilisateur + ", noCategorie=" + noCategorie + ", photo=" + photo + ", pseudo="
				+ pseudo + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	
}
