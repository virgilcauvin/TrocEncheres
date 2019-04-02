package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Vente;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.dal.VenteDAO;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/Secure/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
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
		System.out.println(session.getAttribute("pseudo" + "servlet accueil"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageListeEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8087/TrocEncheres/Secure/ServletAccueil?mesVentes=on&mesEncheresEnCours=on&mesAcquisitions=on&autresEncheres=on&categorie=1&recherche=ordinateur
		if (request.getParameter("mesVentes") != null) {
			List<Vente> listeVentesUtilisateur = new ArrayList<>();
			int noUtilisateur = 0;
			int noCategorie = 0;
			String motsCles = null;
			HttpSession session = request.getSession();
			noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo")).getNoUtilisateur();
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			listeVentesUtilisateur = VenteDAO.selectAllVentesUtilisateur(noUtilisateur, noCategorie, motsCles);
			for (Vente vente : listeVentesUtilisateur) {
				System.out.println(vente.toString());
			}
			request.setAttribute("listeVentesUtilisateur", listeVentesUtilisateur);
		}
		
		if (request.getParameter("mesEncheresEnCours") != null) {
			List<Vente> listeEncheresUtilisateurEnCours = new ArrayList<>();
			int noUtilisateur = 0;
			int noCategorie = 0;
			String motsCles = null;
			HttpSession session = request.getSession();
			noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo")).getNoUtilisateur();
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			listeEncheresUtilisateurEnCours = VenteDAO.selectAllEncheresUtilisateurEnCours(noUtilisateur, noCategorie, motsCles);
			for (Vente vente : listeEncheresUtilisateurEnCours) {
				System.out.println(vente.toString());
			}
			request.setAttribute("listeEncheresUtilisateurEnCours", listeEncheresUtilisateurEnCours);
		}
		
		if (request.getParameter("mesAcquisitions") != null) {
			//A FAIRE
		}
		
		if (request.getParameter("autresEncheres") != null) {
			//A FAIRE
		}
		
		if (request.getParameter("mesVentes") == null && request.getParameter("mesEncheresEnCours") == null && request.getParameter("mesAcquisitions") == null && request.getParameter("autresEncheres") == null) {
			List<Vente> listeVentesEnCours = new ArrayList<>();
			int noCategorie = 0;
			String motsCles = null;
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			listeVentesEnCours = VenteDAO.selectAllVentesEnCours(noCategorie, motsCles);
			for (Vente vente : listeVentesEnCours) {
				System.out.println(vente.toString());
			}
			request.setAttribute("listeVentesEnCours", listeVentesEnCours);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageListeEncheres.jsp");
		rd.forward(request, response);
		
		
	}

}
