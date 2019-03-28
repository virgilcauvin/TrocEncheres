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
		
		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue");
		codePostal = request.getParameter("codePostal");
		ville = request.getParameter("ville");
		motDePasse = request.getParameter("motDePasse");
		
		HttpSession session = request.getSession();
		
		Utilisateur noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo"));
		
		Utilisateur utilisateur = new Utilisateur(noUtilisateur.getNoUtilisateur() ,pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, noUtilisateur.getCredit(), noUtilisateur.isAdministrateur());
		
		UtilisateurDAO.updateProfil(utilisateur);
		
		session.setAttribute("pseudo",request.getParameter("pseudo"));
		request.setAttribute("utilisateur", utilisateur);
		
		System.out.println("utilisateur : " + utilisateur.toString() + "modifié.");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageListeEncheres.jsp");
		rd.forward(request, response);
	}

}
