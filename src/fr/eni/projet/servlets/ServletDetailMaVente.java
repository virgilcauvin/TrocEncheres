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

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Vente;
import fr.eni.projet.dal.EnchereDAO;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.dal.VenteDAO;

/**
 * Servlet implementation class ServletDetailMaVente
 */
@WebServlet("/Secure/ServletDetailMaVente")
public class ServletDetailMaVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailMaVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noVente = 0; 
		Vente vente;
		Enchere meilleureEnchere = new Enchere();
		Utilisateur meilleurEncherisseur = new Utilisateur();
		Utilisateur utilisateurCourant;
		HttpSession session = request.getSession();
		utilisateurCourant = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo"));
		
		noVente = Integer.parseInt(request.getParameter("noVente"));
		vente = VenteDAO.selectVenteByNoVente(noVente);
		meilleureEnchere = EnchereDAO.selectByNoVente(noVente);
		System.out.println("la meilleure encheree est : " + meilleureEnchere.toString());
		if (meilleureEnchere != null) {
			meilleurEncherisseur = UtilisateurDAO.selectById(meilleureEnchere.getNoUtilisateur());
		}
		request.setAttribute("nomArticle", vente.getNomArticle());
		request.setAttribute("photo", vente.getPhoto());
		if (meilleureEnchere != null) {
			request.setAttribute("meilleureOffre", meilleureEnchere.getMontant());
			request.setAttribute("meilleurEncherisseur", meilleurEncherisseur.getPseudo());
		}
		request.setAttribute("miseAPrix", vente.getMiseAPrix());
		request.setAttribute("dateFinEchere", vente.getDateFinEncheres());
		request.setAttribute("rue", utilisateurCourant.getRue());
		request.setAttribute("codePostal", utilisateurCourant.getCodePostal());
		request.setAttribute("ville", utilisateurCourant.getVille());
		request.setAttribute("vendeur", utilisateurCourant.getPseudo());
		
		LocalDate dateDuJour = LocalDate.now();
		
		
		if (vente.getDateFinEncheres().isBefore(dateDuJour)) {
			System.out.println("la vente est terminée");
			request.setAttribute("etatVente", 0);
		} else {
			System.out.println("la vente est en cours");
			request.setAttribute("etatVente", 1);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageDetailMaVente.jsp");
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
