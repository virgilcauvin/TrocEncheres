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
		int idUtilisateur = (int) session.getAttribute("id_utilisateur");
		Utilisateur utilisateur = UtilisateurDAO.selectById(idUtilisateur);
		request.setAttribute("rue", utilisateur.getRue());
		request.setAttribute("codePostal", utilisateur.getCodePostal());
		request.setAttribute("ville", utilisateur.getVille());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageVendreUnArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate finEcnhere;
		int prixInitial;
		String photo;
		Vente vente;
		Utilisateur utilisateur = new Utilisateur();
		Categorie categorie = new Categorie();
		HttpSession session = request.getSession();

		utilisateur = UtilisateurDAO.selectByPseudo((String) session.getAttribute("pseudo"));
		categorie = CategorieDAO.selectByLibelle(request.getParameter("libelle"));

		photo = (String) request.getParameter("photo");
		prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		finEcnhere = LocalDate.parse(request.getParameter("finEnchere"));

		vente = new Vente((String) request.getParameter("article"), (String) request.getParameter("descritpion"),
				finEcnhere, prixInitial, utilisateur.getNoUtilisateur(), categorie.getNoCategorie(),photo);
		VenteDAO.insertVente(vente);

		RequestDispatcher rd = request.getRequestDispatcher("ServletAccueil.java");
		rd.forward(request, response);
	}

}
