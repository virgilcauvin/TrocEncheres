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
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/Secure/ServletEnchere")
public class ServletEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletEnchere() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	int enchereMin = 0;
	int enchereMax = 0;
	int noVente = 0;
	Utilisateur acheteur = new Utilisateur();
	Utilisateur vendeur = new Utilisateur();
	Utilisateur meilleurEncherisseur = new Utilisateur();
	Vente vente = new Vente();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		noVente = Integer.parseInt(request.getParameter("noVente"));

		HttpSession session = request.getSession();

		vente = VenteDAO.selectVenteByNoVente(noVente);

		if (vente.getPrixVente() >= vente.getMiseAPrix()) {
			enchereMin = vente.getPrixVente() + 1;
		} else {
			enchereMin = vente.getMiseAPrix();
		}
		acheteur = UtilisateurDAO.selectByPseudo((String) session.getAttribute("pseudo"));

		Enchere meilleureEnchere = new Enchere();
		meilleureEnchere = EnchereDAO.selectByNoVente(noVente);
		if (meilleureEnchere != null) {
			meilleurEncherisseur = UtilisateurDAO.selectById(meilleureEnchere.getNoUtilisateur());
		}

		vendeur = UtilisateurDAO.selectById(vente.getNoUtilisateur());

		enchereMax = acheteur.getCredit();

		request.setAttribute("nomArticle", vente.getNomArticle());
		request.setAttribute("photo", vente.getPhoto());
		request.setAttribute("meilleureOffre", vente.getPrixVente());
		if (meilleureEnchere != null) {
			request.setAttribute("meilleurEncherisseur", meilleurEncherisseur.getPseudo());
		}
		request.setAttribute("miseAPrix", vente.getMiseAPrix());
		request.setAttribute("dateFinEchere", vente.getDateFinEncheres());
		request.setAttribute("rue", vendeur.getRue());
		request.setAttribute("codePostal", vendeur.getCodePostal());
		request.setAttribute("ville", vendeur.getVille());
		request.setAttribute("vendeur", vendeur.getPseudo());
		request.setAttribute("enchereMin", enchereMin);
		request.setAttribute("enchereMax", enchereMax);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String destination = null;
		String erreurObjetPropre = null;
		String erreurPasAssez = null;
		String erreurTrop = null;
		Enchere ancienneEnchere = null;
		int enchere = Integer.parseInt(request.getParameter("enchere"));
		Enchere enchereSaisie = new Enchere(LocalDate.now(), acheteur.getNoUtilisateur(), noVente, enchere);
		if (acheteur.getNoUtilisateur() == vendeur.getNoUtilisateur()) {
			destination = "/WEB-INF/DetailVente.jsp";
			erreurObjetPropre = "Vous ne pouvez pas enchérir sur un de vos objets";
		} else {
			if (enchere > enchereMax) {
				destination = "/WEB-INF/DetailVente.jsp";
				erreurPasAssez = "Pas assez de points pour effectuer cette enchère.";
			} else {
				if (enchere < enchereMin) {
					destination = "/WEB-INF/DetailVente.jsp";
					erreurTrop = "Ecnhère pas assez élevée.";
				} else {
					VenteDAO.updatePrixVente(noVente, enchere);
					ancienneEnchere = EnchereDAO.selectByPK(acheteur.getNoUtilisateur(), noVente);
					if (ancienneEnchere == null) {
						UtilisateurDAO.updateCredit(acheteur.getNoUtilisateur(), acheteur.getCredit() - enchere);
						EnchereDAO.insertEnchere(enchereSaisie);
					} else {
						UtilisateurDAO.updateCredit(acheteur.getNoUtilisateur(),
								acheteur.getCredit() + ancienneEnchere.getMontant() - enchere);
						EnchereDAO.updateEnchere(acheteur.getNoUtilisateur(), noVente, enchere);
					}

					destination = "/WEB-INF/PageListeEncheres.jsp";
				}
			}
		}
		vente = VenteDAO.selectVenteByNoVente(noVente);

		if (vente.getPrixVente() >= vente.getMiseAPrix()) {
			enchereMin = vente.getPrixVente() + 1;
		} else {
			enchereMin = vente.getMiseAPrix();
		}
		request.setAttribute("nomArticle", vente.getNomArticle());
		request.setAttribute("photo", vente.getPhoto());
		request.setAttribute("meilleureOffre", vente.getPrixVente());
		request.setAttribute("meilleurEncherisseur", meilleurEncherisseur.getPseudo());
		request.setAttribute("miseAPrix", vente.getMiseAPrix());
		request.setAttribute("dateFinEchere", vente.getDateFinEncheres());
		request.setAttribute("rue", vendeur.getRue());
		request.setAttribute("codePostal", vendeur.getCodePostal());
		request.setAttribute("ville", vendeur.getVille());
		request.setAttribute("vendeur", vendeur.getPseudo());
		request.setAttribute("enchereMin", enchereMin);
		request.setAttribute("enchereMax", enchereMax);
		request.setAttribute("erreurObjetPropre", erreurObjetPropre);
		request.setAttribute("erreurPasAssez", erreurPasAssez);
		request.setAttribute("erreurTrop", erreurTrop);
		rd = request.getRequestDispatcher(destination);
		rd.forward(request, response);
	}

}
