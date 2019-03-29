package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/ServletCreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCreationCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasse;
		String confirmationMDP;
		
		//Reste à faire : contrôle des types champs et de luer non nullité
		
		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue");
		codePostal = request.getParameter("codePostal");
		ville = request.getParameter("ville");
		motDePasse = request.getParameter("motDePasse");
		confirmationMDP = request.getParameter("confirmation");
		Utilisateur utilisateur = null;
		
		
		//S'assurer que la confirmation du mot de passe correspond au mot de passe
		if (!confirmationMDP.equals(motDePasse)) {
			request.setAttribute("messageErreurConfirmationMDP", "Veuillez confirmer votre mot de passe");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
			rd.forward(request, response);
		} else {
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		}
		
		//Verifier la non nullite de tous les champs en regroupant toutes les erreurs dans une MapList
		Map<String, StringBuffer> listeErreurChamps = verifierUtilisateur(utilisateur);
		//Construction d'une liste en parallele contenant le type de messages d'erreurs potentiels pour pouvoir parcourir la MapList par la suite
		List<String> listeChamps = new ArrayList<>();
		listeChamps.add("messageErreurPseudo");
		listeChamps.add("messageErreurNom");
		listeChamps.add("messageErreurPrenom");
		listeChamps.add("messageErreurEmail");
		listeChamps.add("messageErreurTelephone");
		listeChamps.add("messageErreurRue");
		listeChamps.add("messageErreurCodePostal");
		listeChamps.add("messageErreurVille");
		listeChamps.add("messageErreurMotDePasse");
		
		//Verifier la validite de email
		String messageErreurEmail = null;
		StringBuffer messageErreurGlobalEmail = new StringBuffer();
		
		try {
			messageErreurEmail = validationEmail(email);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (messageErreurEmail != null) {
			if (listeErreurChamps.containsKey("messageErreurEmail")) {
				listeErreurChamps.get("messageErreurEmail").append(" ").append(messageErreurEmail);
			} else {
				messageErreurGlobalEmail.append(messageErreurEmail);
				listeErreurChamps.put("messageErreurEmail", messageErreurGlobalEmail);
			}
		}
		//Verifier l'unicite du pseudo
		String messageErreurPseudo = null;
		StringBuffer messageErreurGlobalPseudo = new StringBuffer();
		
		messageErreurPseudo = validationPseudo(pseudo);
		if (messageErreurPseudo!= null) {
			if (listeErreurChamps.containsKey("messageErreurPseudo")) {
				listeErreurChamps.get("messageErreurPseudo").append(" ").append(messageErreurPseudo);
			} else {
				System.out.println("Le message d'erreur est : " + messageErreurPseudo);
				System.out.println("Le message global d'erreur est : " + messageErreurGlobalPseudo);
				listeErreurChamps.put("messageErreurPseudo", messageErreurGlobalPseudo.append(messageErreurPseudo));
			}
		}
		
		
		//Parcours de la MapList d'erreur
		if (listeErreurChamps.size()>0) {
			for (String champ : listeChamps) {
				if (listeErreurChamps.containsKey(champ)) {
					System.out.println("L'erreur " + champ + "est : " + listeErreurChamps.get(champ));
					request.setAttribute(champ, listeErreurChamps.get(champ));
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
			rd.forward(request, response);
		} else {
			UtilisateurDAO.insertUtilisateur(utilisateur);
		}
		
		System.out.println("utilisateur : " + utilisateur.toString() + "créé.");
		
		HttpSession session = request.getSession();	
		session.setAttribute("compteCree", true);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/PageConnexion.jsp");
		rd.forward(request, response);
		
	}

	

	private Map<String, StringBuffer> verifierUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		Map<String, StringBuffer> listeErreur = new TreeMap<>();
		
		if (utilisateur.getPseudo() == null || utilisateur.getPseudo().equals("") || utilisateur.getPseudo().trim().equals("")) {
			StringBuffer sb1 = new StringBuffer();
			listeErreur.put("messageErreurPseudo", sb1.append("Veuillez renseigner un pseudo."));
		}
		if (utilisateur.getNom() == null || utilisateur.getNom().equals("") || utilisateur.getNom().trim().equals("")) {
			StringBuffer sb2 = new StringBuffer();
			listeErreur.put("messageErreurNom", sb2.append("Veuillez renseigner un nom."));
		}
		if (utilisateur.getPrenom() == null || utilisateur.getPrenom().equals("") || utilisateur.getPrenom().trim().equals("")) {
			StringBuffer sb3 = new StringBuffer();
			listeErreur.put("messageErreurPrenom", sb3.append("Veuillez renseigner un prénom."));
		}
		if (utilisateur.getEmail() == null || utilisateur.getEmail().equals("") || utilisateur.getEmail().trim().equals("")) {
			StringBuffer sb4 = new StringBuffer();
			listeErreur.put("messageErreurEmail", sb4.append("Veuillez renseigner un email."));
		}
		if (utilisateur.getTelephone() == null || utilisateur.getTelephone().equals("") || utilisateur.getTelephone().trim().equals("")) {
			StringBuffer sb5 = new StringBuffer();
			listeErreur.put("messageErreurTelephone", sb5.append("Veuillez renseigner un téléphone."));
		}
		if (utilisateur.getRue() == null || utilisateur.getRue().equals("") || utilisateur.getRue().trim().equals("")) {
			StringBuffer sb6 = new StringBuffer();
			listeErreur.put("messageErreurRue", sb6.append("Veuillez renseigner une rue."));
		}
		if (utilisateur.getCodePostal() == null || utilisateur.getCodePostal().equals("") || utilisateur.getCodePostal().trim().equals("")) {
			StringBuffer sb7 = new StringBuffer();
			listeErreur.put("messageErreurCodePostal", sb7.append("Veuillez renseigner un code postal."));
		}
		if (utilisateur.getVille() == null || utilisateur.getVille().equals("") || utilisateur.getVille().trim().equals("")) {
			StringBuffer sb8 = new StringBuffer();
			listeErreur.put("messageErreurVille", sb8.append("Veuillez renseigner une ville."));
		}
		if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().equals("") || utilisateur.getMotDePasse().trim().equals("")) {
			StringBuffer sb9 = new StringBuffer();
			listeErreur.put("messageErreurMotDePasse", sb9.append("Veuillez renseigner un mot de passe."));
		}
		return listeErreur;
	}
	
	private String validationEmail(String email) throws Exception {
        String messageErreurMail = null;
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            messageErreurMail="Merci de saisir une adresse mail valide.";
        }
		return messageErreurMail;
		
    }
	
	
	private String validationPseudo(String pseudo) {
		String messageErreurPseudo = null;
		Utilisateur utilisateur = UtilisateurDAO.selectByPseudo(pseudo);
		if (utilisateur != null) {
			System.out.println("Le pseudo existe déjà sur l'utilisateur : " + utilisateur.toString());
			messageErreurPseudo = "Ce pseudo existe déjà : veuillez en choisir un autre.";
			System.out.println(messageErreurPseudo);
        } else {
        	System.out.println("Le pseudo n'existe pas et est valide.");
		}
		
		return messageErreurPseudo;
	}

}
