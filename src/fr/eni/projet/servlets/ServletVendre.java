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
		System.out.println("doGet servletVendre");
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
		System.out.println("doPost servletVendre");
		Utilisateur utilisateur = new Utilisateur();
		Categorie categorie = new Categorie();
		HttpSession session = request.getSession();

		utilisateur = UtilisateurDAO.selectByPseudo((String) session.getAttribute("pseudo"));
		System.out.println(utilisateur);

		String nomArticle = request.getParameter("article");
		System.out.println(nomArticle);
		String libelle = request.getParameter("libelle");
		System.out.println(libelle);
		
		categorie = CategorieDAO.selectByLibelle(libelle);
		categorie.toString();

		String description = request.getParameter("descritpion");
		LocalDate finEcnhere = LocalDate.parse(request.getParameter("finEnchere"));
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		int noUtililisateur = utilisateur.getNoUtilisateur();
		int noCategorie = categorie.getNoCategorie();
		String photo = request.getParameter("lienPhoto");

		Vente vente = new Vente(nomArticle, description, finEcnhere, prixInitial, noUtililisateur, noCategorie, photo);
		if (request.getParameter("bouton").equals("publier")) {
			VenteDAO.insertVente(vente);
		}
		if (request.getParameter("bouton").equals("enregistrer")) {
			session.setAttribute("libelle", libelle);
			session.setAttribute("article", nomArticle);
			session.setAttribute("descritpion", description);
			session.setAttribute("prixInitial", prixInitial);
			session.setAttribute("finEchere", finEcnhere);
			session.setAttribute("lienPhoto", photo);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageListeEncheres.jsp");
		rd.forward(request, response);
	}

}
