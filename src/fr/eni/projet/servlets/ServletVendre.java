package fr.eni.projet.servlets;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Vente;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.dal.VenteDAO;

/**
 * Servlet implementation class ServletVendre
 */
@WebServlet("/Secure/ServletVendre")
public class ServletVendre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVendre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		Utilisateur utilisateur = UtilisateurDAO.selectByPseudo(pseudo);
		request.setAttribute("rue", utilisateur.getRue());
		request.setAttribute("codePostal", utilisateur.getCodePostal());
		request.setAttribute("ville", utilisateur.getVille());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		Categorie categorie = new Categorie();
		HttpSession session = request.getSession();
		String messageErreurArticle = null;
		String messageErreurCategorie = null;
		String messageErreurDescription = null;
		String messageErreurFinEnchere = null;
		String messageErreurPrixPositif = null;
		String messageErreurPrix = null;

		utilisateur = UtilisateurDAO.selectByPseudo((String) session.getAttribute("pseudo"));
		String nomArticle = null;
		String libelle = null;
		String description = null;
		LocalDate finEcnhere = null;
		int prixInitial = 0;
		RequestDispatcher rd;
		String destination;
		if (!request.getParameter("article").trim().equals("")) {
			nomArticle = request.getParameter("article");
		} else {
			messageErreurArticle = "Veuillez entrer un article";
		}

		if (!request.getParameter("libelle").trim().equals("")) {
			libelle = request.getParameter("libelle");

			categorie = CategorieDAO.selectByLibelle(libelle);
		} else {
			messageErreurCategorie = "Veuillez sélectionner une catégorie";
		}

		if (!request.getParameter("descritpion").trim().equals("")) {
			description = request.getParameter("descritpion");
		} else {
			messageErreurDescription = "Veuillez entrer une description";
		}

		if (!request.getParameter("finEnchere").trim().equals("")) {
			finEcnhere = LocalDate.parse(request.getParameter("finEnchere"));
		} else {
			messageErreurFinEnchere = "Veuillez entrer une date de fin d'enchère";
		}

		if (!request.getParameter("prixInitial").trim().equals("")) {
			if (Integer.parseInt(request.getParameter("prixInitial")) > 0) {
				prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
			} else {
				messageErreurPrixPositif = "Veuillez entrer une valeure positive";
			}
		} else {
			messageErreurPrix = "Veuillez entrer un chiffre";
		}

		int noUtililisateur = utilisateur.getNoUtilisateur();
		int noCategorie = categorie.getNoCategorie();
		String photo = request.getParameter("lienPhoto");
		if (messageErreurArticle==null && messageErreurCategorie==null
				&& messageErreurDescription==null && messageErreurFinEnchere==null
				&& messageErreurPrixPositif==null && messageErreurPrix==null) {
			Vente vente = new Vente(nomArticle, description, finEcnhere, prixInitial, noUtililisateur, noCategorie,
					photo);
			if (request.getParameter("bouton").equals("publier")) {
				VenteDAO.insertVente(vente);
				if (session.getAttribute("libelle") != null) {
					session.removeAttribute("libelle");
					session.removeAttribute("article");
					session.removeAttribute("descritpion");
					session.removeAttribute("prixInitial");
					session.removeAttribute("finEchere");
					session.removeAttribute("lienPhoto");
				}
			}
			if (request.getParameter("bouton").equals("enregistrer")) {
				session.setAttribute("libelle", libelle);
				session.setAttribute("article", nomArticle);
				session.setAttribute("descritpion", description);
				session.setAttribute("prixInitial", prixInitial);
				session.setAttribute("finEchere", finEcnhere);
				session.setAttribute("lienPhoto", photo);
			}
			destination = "/WEB-INF/PageListeEncheres.jsp";
		} else {
			request.setAttribute("messageErreurArticle", messageErreurArticle);
			request.setAttribute("messageErreurCategorie", messageErreurCategorie);
			request.setAttribute("messageErreurDescription", messageErreurDescription);
			request.setAttribute("messageErreurFinEnchere", messageErreurFinEnchere);
			request.setAttribute("messageErreurPrixPositif", messageErreurPrixPositif);
			request.setAttribute("messageErreurPrix", messageErreurPrix);
			session.setAttribute("libelle", libelle);
			session.setAttribute("article", nomArticle);
			session.setAttribute("descritpion", description);
			session.setAttribute("prixInitial", prixInitial);
			session.setAttribute("finEchere", finEcnhere);
			session.setAttribute("lienPhoto", photo);
			destination = "/WEB-INF/NouvelleVente.jsp";

		}
		rd = request.getRequestDispatcher(destination);
		rd.forward(request, response);
	}

}
