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
		//HttpSession session = request.getSession();
		//Utilisateur utilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo"));
		//request.setAttribute("utilisateur", utilisateur);
		//System.out.println(session.getAttribute("pseudo"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/MonProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
