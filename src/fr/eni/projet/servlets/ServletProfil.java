package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.GestionErreursCompteUtilisateur;
import fr.eni.projet.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/Secure/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo"));
		request.setAttribute("utilisateur", utilisateur);
		System.out.println(session.getAttribute("pseudo"+ "servlet profil"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/MonProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasse;
		String confirmationMDP;
		String urlCible;
		String pseudoInitial;
		String emailInitial;
		String telephoneInitial;
		boolean visionNom;
		boolean visionPrenom;
		boolean visionEmail;
		boolean visionTelephone;
		 
		HttpSession session = request.getSession();
		
		Utilisateur utilisateurCourant = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo"));
		pseudoInitial = utilisateurCourant.getPseudo();
		emailInitial = utilisateurCourant.getEmail();
		telephoneInitial = utilisateurCourant.getTelephone();
		
		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue");
		codePostal = request.getParameter("codePostal");
		ville = request.getParameter("ville");
		motDePasse = request.getParameter("motDePasse");
		confirmationMDP = request.getParameter("confirmation");
		if (request.getParameter("visionNom")==null) {
			visionNom = false;
		}else {visionNom=true;}
		if (request.getParameter("visionPrenom")==null) {
			visionPrenom = false;
		}else {visionPrenom=true;}
		if (request.getParameter("visionEmail")==null) {
			visionEmail = false;
		}else {visionEmail=true;}
		if (request.getParameter("visionTelephone")==null) {
			visionTelephone = false;
		}else {visionTelephone=true;}
		
		
		UtilisateurDAO.updateVision(utilisateurCourant.getNoUtilisateur(), visionNom, visionPrenom, visionEmail, visionTelephone);
		
		//Verifier la non nullite et la validite de tous les champs en regroupant toutes les erreurs dans une MapList
		Map<String, StringBuffer> listeErreurChamps = new TreeMap<>();
		//Construction d'une liste en parallele contenant le type de messages d'erreurs potentiels pour pouvoir parcourir la MapList par la suite
		List<String> listeChamps = new ArrayList<>();
		listeChamps.add("pseudoMessageErreur");
		listeChamps.add("nomMessageErreur");
		listeChamps.add("prenomMessageErreur");
		listeChamps.add("emailMessageErreur");
		listeChamps.add("telephoneMessageErreur");
		listeChamps.add("rueMessageErreur");
		listeChamps.add("codePostalMessageErreur");
		listeChamps.add("villeMessageErreur");
		listeChamps.add("motDePasseMessageErreur");
		listeChamps.add("confirmationMDPMessageErreur");
		
		//Construction d'une liste en parallele contenant les saisies des differents champs pour pouvoir parametrer les attributs de requetes valides par la suite
		List<String> listeSaisies = new ArrayList<>();
		listeSaisies.add(pseudo);
		listeSaisies.add(nom);
		listeSaisies.add(prenom);
		listeSaisies.add(email);
		listeSaisies.add(telephone);
		listeSaisies.add(rue);
		listeSaisies.add(codePostal);
		listeSaisies.add(ville);
		listeSaisies.add(motDePasse);
		listeSaisies.add(confirmationMDP);
		
		listeErreurChamps = GestionErreursCompteUtilisateur.genererMapErreurs(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmationMDP, pseudoInitial, emailInitial, telephoneInitial);
		
		//Parcours de la MapList d'erreur par la liste d'erreurs potentielles :
		//si contient des erreurs : attribut de requete pour indiquer les messages sous les champs du formulaire correspondant et redirection sur la page de creation
		//sinon : creation de l'utilisateur pour insertion BDD, attribut de requete pour preremplir pseudo et mdp, attribut de session pour confirmer cr�ation et redirection sur la page de creation
		if (listeErreurChamps.size()>0) {
			int indexListe = 0;
			for (String champ : listeChamps) {
				if (listeErreurChamps.containsKey(champ)) {
					request.setAttribute(champ, listeErreurChamps.get(champ));
				} else {
					request.setAttribute(champ.replace("MessageErreur", "Valide"), listeSaisies.get(indexListe));
				}
				indexListe++;
			}
			request.setAttribute("credit", utilisateurCourant.getCredit());
			urlCible = "/WEB-INF/MonProfil.jsp";
		} else {
			Utilisateur utilisateur = new Utilisateur(utilisateurCourant.getNoUtilisateur() ,pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, utilisateurCourant.getCredit(), utilisateurCourant.isAdministrateur());
			UtilisateurDAO.updateProfil(utilisateur);
			
			System.out.println("utilisateur : " + utilisateur.toString() + " a été modifié.");
			
			session.setAttribute("pseudo",request.getParameter("pseudo"));
			request.setAttribute("utilisateur", utilisateur);
			urlCible = "/WEB-INF/PageListeEncheres.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(urlCible);
		rd.forward(request, response);
		
	}

}
