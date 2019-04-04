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
 * Servlet implementation class ServletEncherePerdue
 */
@WebServlet("/Secure/ServletEncherePerdue")
public class ServletEncherePerdue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherePerdue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noVente = Integer.parseInt(request.getParameter("noVente"));
		int classement = Integer.parseInt(request.getParameter("classement"));
		Vente vente = VenteDAO.selectVenteByNoVente(noVente);
		Utilisateur vendeur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
		request.setAttribute("nomArticle", vente.getNomArticle());
		request.setAttribute("dateFinEncheres", vente.getDateFinEncheres());
		request.setAttribute("photo", vente.getPhoto());
		request.setAttribute("classement", classement);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageEncherePerdue.jsp");
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
