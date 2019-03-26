package fr.eni.projet.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletConnexionCompte
 */
@WebServlet("/ServletConnexionCompte")
public class ServletConnexionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConnexionCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("password");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		Utilisateur pseudo = new Utilisateur();
		Utilisateur email = new Utilisateur();
		pseudo = UtilisateurDAO.selectByPseudo(identifiant);
		email = UtilisateurDAO.selectByEmail(identifiant);
		if (pseudo != null || email != null) {
			if (pseudo.getMotDePasse().equals(password) || email.getMotDePasse().equals(password)) {
				session.setAttribute("utilisateurIdentifie", "oui");
			} else {
				session.setAttribute("utilisateurIdentifie", "non");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("ServletAccueil.java");
		rd.forward(httpRequest, httpResponse);
	}

}
