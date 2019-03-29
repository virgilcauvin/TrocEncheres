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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// /!\NEW : gestion de la deconnexion du profil avec suppression du paramètre de session "connecte"
		if(request.getParameter("deconnexion") !=null) {
			HttpSession session = request.getSession();
			session.removeAttribute("connecte");
			session.removeAttribute("compteCree");
			System.out.println("Vous êtes déconnecté : " + session.getAttribute("connecte"));
		} // FIN NEW
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageConnexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// /!\NEW : gestion de la redirection sur la page de connexion si l'identifiant ou le mot de passe n'a pas été saisi
		System.out.println("Je suis rentré dans : " + request.getContextPath() + request.getServletPath());
		if(request.getParameter("identifiant") == "" || request.getParameter("password") == ""){
			request.setAttribute("messageErreurConnexion", "Veuillez saisir votre identifiant (pseudo ou mail) et votre mot de passe puis appuyez sur \"Connexion\" pour vous connecter");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageConnexion.jsp");
			rd.forward(request, response);
		} else {// FIN NEW
			String identifiant = request.getParameter("identifiant");
			String password = request.getParameter("password");
			HttpSession session = request.getSession();
			Utilisateur utilisateurPseudo = new Utilisateur();
			Utilisateur utilisateurEmail = new Utilisateur();
			utilisateurPseudo = UtilisateurDAO.selectByPseudo(identifiant);
			utilisateurEmail = UtilisateurDAO.selectByEmail(identifiant);
			if (utilisateurPseudo != null) {
				if (utilisateurPseudo.getMotDePasse().equals(password)) {
					session.setAttribute("connecte", true);
					session.setAttribute("pseudo", utilisateurPseudo.getPseudo());
					request.setAttribute("utilisateur", utilisateurPseudo);
					System.out.println("L'utilisateur existant dans la BDD est : " + utilisateurPseudo);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageListeEncheres.jsp");
					rd.forward(request, response);
				} else {
					System.out.println("L'utilisateur n'a pas été retrouvé dans la BDD");
					request.setAttribute("messageErreurConnexion", "Ce compte n'existe pas : veuillez réessayez");
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageConnexion.jsp");
					rd.forward(request, response);	
				}
			}
			if (utilisateurEmail != null) {
				if ( utilisateurEmail.getMotDePasse().equals(password)) {
					session.setAttribute("connecte", true);
					session.setAttribute("pseudo", utilisateurEmail.getPseudo());
					request.setAttribute("utilisateur", utilisateurEmail);
					System.out.println("L'utilisateur existant dans la BDD est : " + utilisateurEmail);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageListeEncheres.jsp");
					rd.forward(request, response);
				} else {
					System.out.println("L'utilisateur n'a pas été retrouvé dans la BDD");
					request.setAttribute("messageErreurConnexion", "Ce compte n'existe pas : veuillez réessayez");
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageConnexion.jsp");
					rd.forward(request, response);	
				}
			}
		}
	
	}

}
