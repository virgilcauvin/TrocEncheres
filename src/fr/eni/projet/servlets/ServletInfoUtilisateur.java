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
 * Servlet implementation class ServletInfoUtilisateur
 */
@WebServlet("/Secure/ServletInfoUtilisateur")
public class ServletInfoUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletInfoUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = UtilisateurDAO.selectByPseudo(pseudo);
		
		request.setAttribute("pseudo", utilisateur.getPseudo());
		if (utilisateur.isVisionNom()) {
			request.setAttribute("nom", utilisateur.getNom());
		}else {request.setAttribute("nom", "Information cachée");}
		if (utilisateur.isVisionPrenom()) {
			request.setAttribute("prenom", utilisateur.getPrenom());
		}else {request.setAttribute("prenom", "Information cachée");}
		if (utilisateur.isVisionEmail()) {
			request.setAttribute("email", utilisateur.getEmail());
		}else {request.setAttribute("email", "Information cachée");}
		if (utilisateur.isVisionTelephone()) {
			request.setAttribute("telephone", utilisateur.getTelephone());
		}else {request.setAttribute("telephone", "Information cachée");}
		request.setAttribute("rue", utilisateur.getRue());
		request.setAttribute("codePostal", utilisateur.getCodePostal());
		request.setAttribute("ville", utilisateur.getVille());
		
		if (request.getParameter("venteValidee") != null) {
			request.setAttribute("telephone", utilisateur.getTelephone());
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageInfoUtilisateur.jsp");
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
