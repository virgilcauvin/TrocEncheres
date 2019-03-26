package fr.eni.projet.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
