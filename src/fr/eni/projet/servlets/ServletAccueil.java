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

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bo.Vente;
import fr.eni.projet.dal.EnchereDAO;
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
//		System.out.println(session.getAttribute("pseudo" + "servlet accueil"));
		/*/!\ NEW : pour maintenir les critères de recherches de vente sur la pages d'accueil en retour de la page DetailMaVente*/
		if (request.getParameter("mesVentes") != null) {
			List<Vente> listeVentesUtilisateur = new ArrayList<>();
			int noUtilisateur = 0;
			noUtilisateur = utilisateur.getNoUtilisateur();
			listeVentesUtilisateur = VenteDAO.selectAllVentesUtilisateur(noUtilisateur, 0, "");
			for (Vente vente : listeVentesUtilisateur) {
				Utilisateur utilisateurVendeur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
				vente.setPseudo(utilisateurVendeur.getPseudo());
				vente.setRue(utilisateurVendeur.getRue());
				vente.setCodePostal(utilisateurVendeur.getCodePostal());
				vente.setVille(utilisateurVendeur.getVille());
//				System.out.println(vente.toString());
			}
			request.setAttribute("listeVentesUtilisateur", listeVentesUtilisateur);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageListeEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				Utilisateur utilisateur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
				vente.setPseudo(utilisateur.getPseudo());
				vente.setRue(utilisateur.getRue());
				vente.setCodePostal(utilisateur.getCodePostal());
				vente.setVille(utilisateur.getVille());
//				System.out.println(vente.toString());
			}
			request.setAttribute("listeVentesUtilisateur", listeVentesUtilisateur);
		}
		
		if (request.getParameter("mesEncheresEnCours") != null) {
			List<Vente> listeEncheresUtilisateurEnCours = new ArrayList<>();
			List<Enchere> listeEncheresUtilisateur = new ArrayList<>();
			int noUtilisateur = 0;
			int noCategorie = 0;
			String motsCles = null;
			HttpSession session = request.getSession();
			noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo")).getNoUtilisateur();
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			//sélection de toutes les ventes en cours sur lesquelles l'utilisateur a enchéri
			listeEncheresUtilisateurEnCours = VenteDAO.selectAllEncheresUtilisateurEnCours(noUtilisateur, noCategorie, motsCles);
			for (Vente vente : listeEncheresUtilisateurEnCours) {
				Utilisateur utilisateur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
				vente.setPseudo(utilisateur.getPseudo());
				vente.setRue(utilisateur.getRue());
				vente.setCodePostal(utilisateur.getCodePostal());
				vente.setVille(utilisateur.getVille());
//				System.out.println(vente.toString());
			}
			
			listeEncheresUtilisateur = genererClassementEncheres(noUtilisateur);
			
			request.setAttribute("listeEncheresUtilisateur", listeEncheresUtilisateur);
			
			request.setAttribute("listeEncheresUtilisateurEnCours", listeEncheresUtilisateurEnCours);
		}
		
		/*/!\ NEW : gestion des paramètres pour l'affichage de la liste des ventes remportées par l'utilisateur*/
		if (request.getParameter("mesAcquisitions") != null) {
			List<Vente> listeEncheresUtilisateurTerminees = new ArrayList<>();
			List<Enchere> listeEncheresUtilisateur = new ArrayList<>();
			List<Vente> listeAcquisitionsUtilisateur = new ArrayList<>();
			int noUtilisateur = 0;
			int noCategorie = 0;
			String motsCles = null;
			HttpSession session = request.getSession();
			noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo")).getNoUtilisateur();
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			//sélection de toutes les ventes terminées sur lesquelles l'utilisateur a enchéri
			listeEncheresUtilisateurTerminees = VenteDAO.selectAllEncheresTermineesUtilisateur(noUtilisateur, noCategorie, motsCles);
			for (Vente vente : listeEncheresUtilisateurTerminees) {
				Utilisateur utilisateur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
				vente.setPseudo(utilisateur.getPseudo());
				vente.setRue(utilisateur.getRue());
				vente.setCodePostal(utilisateur.getCodePostal());
				vente.setVille(utilisateur.getVille());
//				System.out.println(vente.toString());
			}
			listeEncheresUtilisateur = genererClassementEncheres(noUtilisateur);
			
			//on parcourt la liste des ventes terminées sur lesquelles l'utilisateur a enchéri
			for (Vente vente : listeEncheresUtilisateurTerminees) {
				//pour chacune de ces ventes, on parcourt la liste de toutes les enchères de l'utilisateur pour retrouver son classement
				for (Enchere enchere : listeEncheresUtilisateur) {
					//si le classement de l'utilisateur sur cette vente est égal à 1, on ajoute cette vente à la liste d'acquisition
					if (enchere.getNoVente() == vente.getNoVente() && enchere.getClassement()==1) {
						listeAcquisitionsUtilisateur.add(vente);
					}
				}
				
			}
			
/*			for (Vente vente : listeAcquisitionsUtilisateur) {
				System.out.println("Acquisition de l'utilisateur : " + vente.toString());
			}
*/				
			request.setAttribute("listeAcquisitionsUtilisateur", listeAcquisitionsUtilisateur);
			
		}
		
		// /!\ NEW : gestion des paramètres pour l'affichage de la liste des ventes perdues par l'utilisateur (autres enchères)*/
		if (request.getParameter("autresEncheres") != null) {
			List<Vente> listeEncheresUtilisateurTerminees = new ArrayList<>();
			List<Enchere> listeEncheresUtilisateur = new ArrayList<>();
			List<Vente> listeVentesPerduesUtilisateur = new ArrayList<>();
			int noUtilisateur = 0;
			int noCategorie = 0;
			String motsCles = null;
			HttpSession session = request.getSession();
			noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo")).getNoUtilisateur();
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			//sélection de toutes les ventes terminées sur lesquelles l'utilisateur a enchéri
			listeEncheresUtilisateurTerminees = VenteDAO.selectAllEncheresTermineesUtilisateur(noUtilisateur, noCategorie, motsCles);
			for (Vente vente : listeEncheresUtilisateurTerminees) {
				Utilisateur utilisateur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
				vente.setPseudo(utilisateur.getPseudo());
				vente.setRue(utilisateur.getRue());
				vente.setCodePostal(utilisateur.getCodePostal());
				vente.setVille(utilisateur.getVille());
//				System.out.println(vente.toString());
			}
			listeEncheresUtilisateur = genererClassementEncheres(noUtilisateur);
			
			//on parcourt la liste des ventes terminées sur lesquelles l'utilisateur a enchéri
			for (Vente vente : listeEncheresUtilisateurTerminees) {
				//pour chacune de ces ventes, on parcourt la liste de toutes les enchères de l'utilisateur pour retrouver son classement
				for (Enchere enchere : listeEncheresUtilisateur) {
					//si le classement de l'utilisateur sur cette vente est différent de 1, on ajoute cette vente à la liste de ventes perdues
					if (enchere.getNoVente() == vente.getNoVente() && enchere.getClassement()!=1) {
						listeVentesPerduesUtilisateur.add(vente);
					}
				}
				
			}
			
/*			for (Vente vente : listeVentesPerduesUtilisateur) {
				System.out.println("Ventes perdues de l'utilisateur : " + vente.toString());
			}
*/			
			request.setAttribute("listeEncheresUtilisateur", listeEncheresUtilisateur);
			request.setAttribute("listeVentesPerduesUtilisateur", listeVentesPerduesUtilisateur);
			
		}
		
		
		if (request.getParameter("mesVentes") == null && request.getParameter("mesEncheresEnCours") == null && request.getParameter("mesAcquisitions") == null && request.getParameter("autresEncheres") == null) {
			List<Vente> listeVentesEnCours = new ArrayList<>();
			List<Enchere> listeEncheresUtilisateur = new ArrayList<>();
			int noUtilisateur = 0;
			int noCategorie = 0;
			String motsCles = null;
			
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			motsCles = request.getParameter("recherche");
			listeVentesEnCours = VenteDAO.selectAllVentesEnCours(noCategorie, motsCles);
			for (Vente vente : listeVentesEnCours) {
				Utilisateur utilisateur = UtilisateurDAO.selectById(vente.getNoUtilisateur());
				vente.setPseudo(utilisateur.getPseudo());
				vente.setRue(utilisateur.getRue());
				vente.setCodePostal(utilisateur.getCodePostal());
				vente.setVille(utilisateur.getVille());
//				System.out.println(vente.toString());
			}
			
			HttpSession session = request.getSession();
			noUtilisateur = UtilisateurDAO.selectByPseudo((String)session.getAttribute("pseudo")).getNoUtilisateur();
			
			listeEncheresUtilisateur = genererClassementEncheres(noUtilisateur);
			
			request.setAttribute("listeEncheresUtilisateur", listeEncheresUtilisateur);
			
			request.setAttribute("listeVentesEnCours", listeVentesEnCours);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageListeEncheres.jsp");
		rd.forward(request, response);
		
		
	}

	private List<Enchere> genererClassementEncheres(int noUtilisateur) {
		List<Enchere> listeEncheresUtilisateur = new ArrayList<>();
		List<Enchere> listeEncheresVente = new ArrayList<>();
		//sélection de toutes les enchères de l'utilisateur (en cours et passées)
		listeEncheresUtilisateur = EnchereDAO.selectByNoUtilisateur(noUtilisateur);
		
		//on parcourt la liste des encheres de l'utilisateur
		for (Enchere enchereUtilisateur : listeEncheresUtilisateur) {
			//on récupère le n° de vente de chacune de ces enchères
			int noVente = enchereUtilisateur.getNoVente();
			//pour chacune des ventes sur lesquelles il a encheri, on recupère la liste d'enchères faites sur cette vente
			listeEncheresVente = EnchereDAO.selectEncheresByNoVente(noVente);
			int classement = 1;
			//on parcourt cette liste pour retrouver le no de l'utilisateur et établir son classement
			for (Enchere enchere : listeEncheresVente) {
				if (enchere.getNoUtilisateur() != enchereUtilisateur.getNoUtilisateur()) {
					classement++;
				} else {
					//on met à jour le classement de l'utilisateur sur cette enchère
					enchereUtilisateur.setClassement(classement);
					break;
				}
			}
//			System.out.println("Le classement de l'utilisateur " + noUtilisateur + " dans la vente n°" + noVente + " est : " + classement);
		}
		
/*		for (Enchere enchere : listeEncheresUtilisateur) {
			System.out.println("enchère de l'utilisateur :" + enchere.toString());
		}
*/		
		return listeEncheresUtilisateur;
	}
	
	
	
	
	
	

}
