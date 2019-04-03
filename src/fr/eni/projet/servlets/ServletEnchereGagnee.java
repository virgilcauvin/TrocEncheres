package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Vente;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.dal.VenteDAO;

/**
 * Servlet implementation class ServletEnchereGagnee
 */
@WebServlet("/Secure/ServletEnchereGagnee")
public class ServletEnchereGagnee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEnchereGagnee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int noVente = Integer.parseInt(request.getParameter("noVente"));
		Vente vente = VenteDAO.selectVenteByNoVente(noVente);
		Utilisateur vendeur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
		request.setAttribute("nomArticle", vente.getNomArticle());
		request.setAttribute("photo", vente.getPhoto());
		request.setAttribute("meilleureOffre", vente.getPrixVente());
		request.setAttribute("miseAPrix", vente.getMiseAPrix());
		request.setAttribute("rue", vendeur.getRue());
		request.setAttribute("codePostal", vendeur.getCodePostal());
		request.setAttribute("ville", vendeur.getVille());
		request.setAttribute("pseudo", vendeur.getPseudo());
		request.setAttribute("telephone", vendeur.getTelephone());

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageEnchereGagnee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
