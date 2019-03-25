package fr.eni.projet.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere;
	
	public Enchere() {}
	
	public Enchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + "]";
	}
	
	
	
}
