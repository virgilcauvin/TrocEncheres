package fr.eni.projet.dal;

import java.util.Map;
import java.util.TreeMap;

import fr.eni.projet.bo.Utilisateur;

public class GestionErreursCompteUtilisateur {

	public static Map<String, StringBuffer> genererMapErreurs(String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, String motDePasse, String confirmationMDP,
			String pseudoInitial, String emailInitial, String telephoneInitial) {
		
		Map<String, StringBuffer> listeErreur = new TreeMap<>();
		
		//Verifier la validite du pseudo
		StringBuffer messageErreurGlobalPseudo = new StringBuffer();
		boolean pseudoNonNull;
		boolean pseudoUnique;
		boolean pseudoLongueurCorrecte;
		//1 Verifier la non nullite du pseudo
		pseudoNonNull = verifierNonNullite(pseudo);
		if (pseudoNonNull == false) {
			messageErreurGlobalPseudo.append("Veuillez renseigner un pseudo. ");
		}
		//2 Verifier l'unicite du pseudo sauf dans le cas du update s'il n'a pas ete modifie
		if (pseudoInitial != null && pseudo.equals(pseudoInitial)) {
			pseudoUnique = true;
		} else {
			pseudoUnique = verifierUnicitePseudo(pseudo);
		}
		if (pseudoUnique == false) {
			messageErreurGlobalPseudo.append("Ce pseudo existe déjà : veuillez en choisir un autre. ");
		}
		//3 Verifier la longueur du pseudo
		pseudoLongueurCorrecte = verifierLongueur30(pseudo);
		if (pseudoLongueurCorrecte == false) {
			messageErreurGlobalPseudo.append("La taille du pseudo n'est pas correcte : elle doit être comprise entre 2 et 30 caractères.");
		}
		//Si pseudo non valide : ajout du message erreur pseudo dans la MapList
		if (messageErreurGlobalPseudo.length() != 0) {
			listeErreur.put("pseudoMessageErreur", messageErreurGlobalPseudo);
		}
		
		
		//Verifier la validite du nom
		StringBuffer messageErreurGlobalNom = new StringBuffer();
		boolean nomNonNull;
		boolean nomLongueurCorrecte;
		//1 Verifier la non nullite du nom
		nomNonNull = verifierNonNullite(nom);
		if (nomNonNull == false) {
			messageErreurGlobalNom.append("Veuillez renseigner un nom. ");
		}
		//2 Verifier la longueur du nom
		nomLongueurCorrecte = verifierLongueur30(nom);
		if (nomLongueurCorrecte == false) {
			messageErreurGlobalPseudo.append("La taille du nom n'est pas correcte : elle doit être comprise entre 2 et 30 caractères. ");
		}
		//Si nom non valide : ajout du message erreur nom dans la MapList
		if (messageErreurGlobalNom.length() != 0) {
			listeErreur.put("nomMessageErreur", messageErreurGlobalNom);
		}
		
		//Verifier la validite du prenom
		StringBuffer messageErreurGlobalPrenom = new StringBuffer();
		boolean prenomNonNull;
		boolean prenomLongueurCorrecte;
		//1 Verifier la non nullite du nom
		prenomNonNull = verifierNonNullite(prenom);
		if (prenomNonNull == false) {
			messageErreurGlobalPrenom.append("Veuillez renseigner un prenom. ");
		}
		//2 Verifier la longueur du prénom
		prenomLongueurCorrecte = verifierLongueur30(prenom);
		if (prenomLongueurCorrecte == false) {
			messageErreurGlobalPseudo.append("La taille du prénom n'est pas correcte : elle doit être comprise entre 2 et 30 caractères. ");
		}
		//Si prénom non valide : ajout du message erreur prenom dans la MapList
		if (messageErreurGlobalPrenom.length() != 0) {
			listeErreur.put("prenomMessageErreur", messageErreurGlobalPrenom);
		}
				
				
		//Verifier la validite de email
		StringBuffer messageErreurGlobalEmail = new StringBuffer();
		boolean emailNonNull;
		boolean emailValide;
		boolean emailUnique;
		boolean emailLongueurCorrecte;
		
		//1 Verifier la non nullite de email
		emailNonNull = verifierNonNullite(email);
		if (emailNonNull == false) {
			messageErreurGlobalEmail.append("Veuillez renseigner un email. ");
		}
		//2 Verifier la validite de email
		emailValide = validationEmail(email);
		if (emailValide == false) {
			messageErreurGlobalEmail.append("Merci de saisir une adresse mail valide.");
		}
		//3 Verifier l'unicite de email sauf dans le cas du update s'il n'a pas ete modifie
		if (emailInitial != null && email.equals(emailInitial)) {
			emailUnique = true;
		} else {
			emailUnique = verifierUniciteEmail(email);
		}
		if (emailUnique == false) {
			messageErreurGlobalEmail.append("Cet email existe déjà : veuillez en choisir un autre. ");
		}
		//4 Verifier la longueur de email
		emailLongueurCorrecte = verifierLongueur20(email);
		if (emailLongueurCorrecte == false) {
			messageErreurGlobalPseudo.append("La taille de l'adresse email n'est pas correcte : elle doit être comprise entre 2 et 20 caractères.");
		}
		//Si email non valide : ajout du message erreur email dans la MapList
		if (messageErreurGlobalEmail.length() != 0) {
			listeErreur.put("emailMessageErreur", messageErreurGlobalEmail);
		}
		
		
		//Verifier la validite de telephone
		StringBuffer messageErreurGlobalTelephone = new StringBuffer();
		boolean telephoneNonNull;
		boolean telephoneValide;
		boolean telephoneUnique;
		//1 Verifier la non nullite de telephone
		telephoneNonNull = verifierNonNullite(telephone);
		if (telephoneNonNull == false) {
			messageErreurGlobalTelephone.append("Veuillez renseigner un numero de téléphone. ");
		}
		//2 Verifier la validite de telephone
		telephoneValide = validationTelephone(telephone);
		if (telephoneValide == false) {
			messageErreurGlobalTelephone.append("Veuillez saisir un numero de telephone valide.");
		}
		//3 Verifier l'unicite de telephone sauf dans le cas du update s'il n'a pas ete modifie
		if (telephoneInitial != null && telephone.equals(telephoneInitial)) {
			telephoneUnique = true;
		} else {
			telephoneUnique = verifierUniciteTelephone(telephone);
		}
		if (telephoneUnique == false) {
			messageErreurGlobalTelephone.append("Ce numero de telephone existe déjà : veuillez en choisir un autre. ");
		}
		//Si telephone non valide : ajout du message erreur telephone dans la MapList
		if (messageErreurGlobalTelephone.length() != 0) {
			listeErreur.put("telephoneMessageErreur", messageErreurGlobalTelephone);
		}
		
		
		//Verifier la validite de rue
		StringBuffer messageErreurGlobalRue = new StringBuffer();
		boolean rueNonNull;
		boolean rueLongueurCorrecte;;
		//1 Verifier la non nullite de rue
		rueNonNull = verifierNonNullite(rue);
		if (rueNonNull == false) {
			messageErreurGlobalRue.append("Veuillez renseigner une rue. ");
		}
		//2 Verifier la longueur de rue
		rueLongueurCorrecte = verifierLongueur100(rue);
		if (rueLongueurCorrecte == false) {
			messageErreurGlobalPseudo.append("La taille de la rue n'est pas correcte : elle doit être comprise entre 2 et 100 caractères.");
		}
		//Si rue non valide : ajout du message erreur rue dans la MapList
		if (messageErreurGlobalRue.length() != 0) {
			listeErreur.put("rueMessageErreur", messageErreurGlobalRue);
		}
		
		
		//Verifier la validite de code postal
		StringBuffer messageErreurGlobalCodePostal = new StringBuffer();
		boolean codePostalNonNull;
		boolean codePostalValide;
		//1 Verifier la non nullite de rue
		codePostalNonNull = verifierNonNullite(codePostal);
		if (codePostalNonNull == false) {
			messageErreurGlobalCodePostal.append("Veuillez renseigner un code postal. ");
		}
		//2 Verifier la validite de code postal
		codePostalValide = validationCodePostal(codePostal);
		if (codePostalValide == false) {
			messageErreurGlobalTelephone.append("Veuillez saisir un code postal valide.");
		}
		//Si code postal non valide : ajout du message erreur code postal dans la MapList
		if (messageErreurGlobalCodePostal.length() != 0) {
			listeErreur.put("codePostalMessageErreur", messageErreurGlobalCodePostal);
		}

		
		//Verifier la validite de ville
		StringBuffer messageErreurGlobalCodeVille = new StringBuffer();
		boolean villeNonNull;
		boolean villeLongueurCorrecte;
		//1 Verifier la non nullite de ville
		villeNonNull = verifierNonNullite(ville);
		if (villeNonNull == false) {
			messageErreurGlobalCodeVille.append("Veuillez renseigner une ville. ");
		}
		//2 Verifier la longueur de ville
		villeLongueurCorrecte = verifierLongueur30(ville);
		if (villeLongueurCorrecte == false) {
			messageErreurGlobalPseudo.append("La taille de la ville n'est pas correcte : elle doit être comprise entre 2 et 30 caractères. ");
		}
		//Si ville non valide : ajout du message erreur ville dans la MapList
		if (messageErreurGlobalCodeVille.length() != 0) {
			listeErreur.put("villeMessageErreur", messageErreurGlobalCodeVille);
		}
		
		
		//Verifier la validite de mot de passe
		StringBuffer messageErreurGlobalMotDePasse = new StringBuffer();
		boolean motDePasseNonNull;
		//1 Verifier la non nullite de mot de passe
		motDePasseNonNull = verifierNonNullite(motDePasse);
		if (motDePasseNonNull == false) {
			messageErreurGlobalMotDePasse.append("Veuillez renseigner un mot de passe. ");
		}
		//Si mot de passe non valide : ajout du message erreur mot de passe dans la MapList
		if (messageErreurGlobalMotDePasse.length() != 0) {
			listeErreur.put("motDePasseMessageErreur", messageErreurGlobalMotDePasse);
		}
		
		
		//Verifier la validite de confirmation mot de passe
		StringBuffer messageErreurGlobalConfirmationMDP = new StringBuffer();
		boolean confirmationMDPNonNull;
		//1 Verifier la non nullite de mot de passe
		confirmationMDPNonNull = verifierNonNullite(confirmationMDP);
		if (confirmationMDPNonNull == false) {
			messageErreurGlobalConfirmationMDP.append("Veuillez renseigner une confirmation de mot de passe. ");
		}
		//2 S'assurer que la confirmation du mot de passe correspond au mot de passe
		if (!confirmationMDP.equals(motDePasse)) {
			messageErreurGlobalConfirmationMDP.append("La confirmation du mot de passe est erronn�e : veuillez confirmer le m�me mot de passe. ");
		}
		//Si confirmation mot de passe non valide : ajout du message erreur confirmation mot de passe dans la MapList
		if (messageErreurGlobalConfirmationMDP.length() != 0) {
			listeErreur.put("confirmationMDPMessageErreur", messageErreurGlobalConfirmationMDP);
		}
		
		return listeErreur;
	}



	private static boolean verifierNonNullite(String champAVerifier) {
		boolean estNonNul;
		if (champAVerifier == null || champAVerifier.equals("") || champAVerifier.trim().equals("")) {
			estNonNul = false;
		} else {
			estNonNul = true;
		}
		return estNonNul;
	}
	
	
	private static boolean verifierLongueur20(String element) {
		boolean estValide;
		if (element != null && element.length() < 2 || element.length() > 30) {
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
	}


	private static boolean verifierLongueur30(String element) {
		boolean estValide;
		if (element != null && element.length() < 2 || element.length() > 30) {
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
	}

	
	private static boolean verifierLongueur100(String element) {
		boolean estValide;
		if (element != null && element.length() < 2 || element.length() > 100) {
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
	}


	private static boolean verifierUnicitePseudo(String pseudo) {
		boolean estValide;
		Utilisateur utilisateur = UtilisateurDAO.selectByPseudo(pseudo);
		if (utilisateur != null) {
			//penser inserer les champs utilisateurs en minuscule dans BDD
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
	}
	
	
	private static boolean validationEmail(String email) {
		boolean estValide;
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
    }
	
	
	private static boolean verifierUniciteEmail(String email) {
		boolean estValide;
		Utilisateur utilisateur = UtilisateurDAO.selectByEmail(email);
		if (utilisateur != null) {
			//penser inserer les champs utilisateurs en minuscule dans BDD
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
	}

	
	private static boolean validationTelephone(String telephone) {
		boolean estValide;
		int numero;
		if (telephone.length() > 15) {
			estValide = false;
		} else {
			try {
				numero = Integer.parseInt(telephone);
				estValide = true;
			} catch (NumberFormatException nfe) {
				String info = "Le telephone ne doit contenir que du numérique";
				estValide = false;
			}
		}
		return estValide;
	}

	
	private static boolean verifierUniciteTelephone(String telephone) {
		boolean estValide;
		Utilisateur utilisateur = UtilisateurDAO.selectByTelephone(telephone);
		if (utilisateur != null) {
			//penser inserer les champs utilisateurs en minuscule dans BDD
			estValide = false;
        } else {
        	estValide = true;
		}
		return estValide;
	}
	
	
	private static boolean validationCodePostal(String codePostal) {
		boolean estValide;
		int numero;
		if (codePostal.length() > 10) {
			estValide = false;
		} else {
			try {
				numero = Integer.parseInt(codePostal);
				estValide = true;
			} catch (NumberFormatException nfe) {
				String info = "Le code postal ne doit contenir que du numérique";
				estValide = false;
			}
		}
		return estValide;
	}

	
	
}
