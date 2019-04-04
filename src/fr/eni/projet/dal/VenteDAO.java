package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Vente;

public class VenteDAO {
	private static final String INSERT_VENTE = "insert into VENTES(nomarticle, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie,photo, terminee ) values(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_VENTES_BY_ = "SELECT v.* FROM VENTES v ";
	private static final String SELECT_VENTE_BY_NO_VENTE = "select * from VENTES where no_vente = ?";
	private static final String UPDATE_PRIX_VENTE_BY_NO_VENTE = "UPDATE VENTES SET prix_vente = ? WHERE no_vente =  ? ";
	private static final String SELECT_ALL_VENTES_BY_VENDEUR = "select * from VENTES where no_utilisateur = ?";
	private static final String UPDATE_TERMINEE = "update VENTES set terminee=1 where no_vente=?";
	
	public static void updateTerminee(int noVente) {
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_TERMINEE);
			pstmt.setInt(1, noVente);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Vente> selectListeVente(int noUtilisateur) {
		Connection cnx;
		Vente vente;
		ArrayList<Vente> listeVente = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_VENTES_BY_VENDEUR);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomarticle"), rs.getString("description"),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"),
						rs.getString("photo"), rs.getBoolean("terminee"));
				listeVente.add(vente);
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeVente;
	}

	public static void updatePrixVente(int noVente, int prixVente) {
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PRIX_VENTE_BY_NO_VENTE);
			pstmt.setInt(1, prixVente);
			pstmt.setInt(2, noVente);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Vente selectVenteByNoVente(int noVente) {
		Vente vente = null;
		try {

			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VENTE_BY_NO_VENTE);
			pstmt.setInt(1, noVente);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vente = new Vente(rs.getInt("no_vente"), rs.getString("nomarticle"), rs.getString("description"),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"),
						rs.getString("photo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vente;
	}

	public static void insertVente(Vente vente) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_VENTE, PreparedStatement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, vente.getNomArticle());
				pstmt.setString(2, vente.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(vente.getDateFinEncheres()));
				pstmt.setInt(4, vente.getMiseAPrix());
				pstmt.setInt(5, vente.getPrixVente());
				pstmt.setInt(6, vente.getNoUtilisateur());
				pstmt.setInt(7, vente.getNoCategorie());
				pstmt.setString(8, vente.getPhoto());
				pstmt.setBoolean(9, false);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					vente.setNoVente(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Vente> selectAllVentesEnCours(int noCategorie, String motsCles) {
		List<Vente> listeVentes = new ArrayList<Vente>();
		String[] tableauMotsCles = null;
		if (motsCles != "") {
			tableauMotsCles = motsCles.split(" ");// prévoir séparateur "," ?
		}
		StringBuffer requete = new StringBuffer();
		requete.append(SELECT_ALL_VENTES_BY_);
		requete.append("WHERE ");
		// élaboration de la requête si des mots clé sont renseignés en fonction de leur
		// nombre
		if (motsCles != "") {
//			System.out.println("la longueur du tableau de mots clé est : " + tableauMotsCles.length);
		}
		if (motsCles != "") {
			requete.append("(");
			for (int i = 0; i < tableauMotsCles.length; i++) {
				if (i == tableauMotsCles.length - 1) {
					requete.append("nomarticle LIKE ?");
				} else {
					requete.append("nomarticle LIKE ? OR ");
				}
			}
			requete.append(") AND ");
		}
		// élaboration de la requête en fonction de la catégorie : /!\ prévoir sur la
		// servlet émettrice de configurer noCategorie à 0 si Catégories = toutes
		if (noCategorie != 0) {
			requete.append("no_categorie = ? AND ");
		}
		// élaboration de la requête en fonction de l'utilisateur
		requete.append("date_fin_encheres > GETDATE()");
//		System.out.println(requete);

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(requete.toString());

			// balayage du tableau de mots clés pour paramétrer la requête
			if (motsCles != "") {
				for (int i = 0; i < tableauMotsCles.length; i++) {
					pstmt.setString(i + 1, "%" + tableauMotsCles[i] + "%");
//					System.out.println("parametre requete " + (i + 1) + " = " + tableauMotsCles[i]);
				}
			}

			// paramétrage de la requête si catégorie renseignée
//			System.out.println("la chaine de mots clés est : " + motsCles);
//			System.out.println("la catégorie est : " + noCategorie);
			if (noCategorie != 0) {
				if (motsCles != "") {
					pstmt.setInt(tableauMotsCles.length + 1, noCategorie);
//					System.out.println("parametre requete " + (tableauMotsCles.length + 1) + " = " + noCategorie);
				} else {
					pstmt.setInt(1, noCategorie);
				}

			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeVentes.add(new Vente(rs.getInt("no_vente"), rs.getString("nomarticle"),
						rs.getString("description"), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("photo")));// /!\ NEW : ajout photo
			}

			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVentes;
	}

	// méthode de recherche de toutes les ventes de l'utilisateur avec option par
	// mots clés du nom article et/ou noCategorie
	public static List<Vente> selectAllVentesUtilisateur(int noUtilisateur, int noCategorie, String motsCles) {
		List<Vente> listeVentes = new ArrayList<Vente>();
		String[] tableauMotsCles = null;
		int nbVariablesAparametrer = 0;
		if (motsCles != "") {
			tableauMotsCles = motsCles.split(" ");// prévoir séparateur "," ?
		}
		StringBuffer requete = new StringBuffer();
		requete.append(SELECT_ALL_VENTES_BY_);
		requete.append("WHERE ");
		// élaboration de la requête si des mots clé sont renseignés en fonction de leur
		// nombre
		if (motsCles != "") {
			requete.append("(");
			for (int i = 0; i < tableauMotsCles.length; i++) {
				if (i == tableauMotsCles.length - 1) {
					requete.append("nomarticle LIKE ?");
				} else {
					requete.append("nomarticle LIKE ? OR ");
				}
				nbVariablesAparametrer++;
			}
			requete.append(") AND ");
		}

		// élaboration de la requête en fonction de la catégorie : /!\ prévoir sur la
		// servlet émettrice de configurer noCategorie à 0 si Catégories = toutes
		if (noCategorie != 0) {
			requete.append("no_categorie = ? AND ");
			nbVariablesAparametrer++;
		}
		requete.append("no_utilisateur = ?");
		nbVariablesAparametrer++;
//		System.out.println(requete);

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(requete.toString());

			// balayage du tableau de mots clés pour paramétrer la requête
			if (motsCles != "") {
				for (int i = 0; i < tableauMotsCles.length; i++) {
					pstmt.setString(i + 1, "%" + tableauMotsCles[i] + "%");
//					System.out.println("parametre requete " + (i + 1) + " = " + tableauMotsCles[i]);
				}
			}

			// paramétrage de la requête si catégorie renseignée
			if (noCategorie != 0) {
				pstmt.setInt(nbVariablesAparametrer - 1, noCategorie);
//				System.out.println("parametre requete " + (nbVariablesAparametrer - 1) + " = " + noCategorie);
			}
			// paramétrage du numero utilisateur de la requête
			pstmt.setInt(nbVariablesAparametrer, noUtilisateur);
//			System.out.println("parametre requete " + nbVariablesAparametrer + " = " + noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeVentes.add(new Vente(rs.getInt("no_vente"), rs.getString("nomarticle"),
						rs.getString("description"), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("photo")));// /!\ NEW : ajout photo
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVentes;
	}

	// méthode de recherche de toutes les encheres en cours de l'utilisateur avec
	// option par mots clés du nom article et/ou noCategorie
	public static List<Vente> selectAllEncheresUtilisateurEnCours(int noUtilisateur, int noCategorie, String motsCles) {
		List<Vente> listeVentes = new ArrayList<Vente>();
		String[] tableauMotsCles = null;
		int nbVariablesAparametrer = 0;
		if (motsCles != "") {
			tableauMotsCles = motsCles.split(" ");// prévoir séparateur "," ?
		}
		StringBuffer requete = new StringBuffer();
		requete.append(SELECT_ALL_VENTES_BY_);
		requete.append(
				"INNER JOIN ENCHERES e ON e.no_vente = v.no_vente INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur WHERE ");
		// élaboration de la requête si des mots clé sont renseignés en fonction de leur
		// nombre
		if (motsCles != "") {
			requete.append("(");
			for (int i = 0; i < tableauMotsCles.length; i++) {
				if (i == tableauMotsCles.length - 1) {
					requete.append("nomarticle LIKE ?");
				} else {
					requete.append("nomarticle LIKE ? OR ");
				}
				nbVariablesAparametrer++;
			}
			requete.append(") AND ");
		}

		// élaboration de la requête en fonction de la catégorie :
		if (noCategorie != 0) {
			requete.append("no_categorie = ? AND ");
			nbVariablesAparametrer++;
		}
		requete.append("e.no_utilisateur = ? AND ");
		nbVariablesAparametrer++;
		requete.append("date_fin_encheres > GETDATE()");
//		System.out.println(requete);

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(requete.toString());

			// balayage du tableau de mots clés pour paramétrer la requête
			if (motsCles != "") {
				for (int i = 0; i < tableauMotsCles.length; i++) {
					pstmt.setString(i + 1, "%" + tableauMotsCles[i] + "%");
//					System.out.println("parametre requete " + (i + 1) + " = " + tableauMotsCles[i]);
				}
			}

			// paramétrage de la requête si catégorie renseignée
			if (noCategorie != 0) {
				pstmt.setInt(nbVariablesAparametrer - 1, noCategorie);
//				System.out.println("parametre requete " + (nbVariablesAparametrer - 1) + " = " + noCategorie);
			}
			// paramétrage du numero utilisateur de la requête
			pstmt.setInt(nbVariablesAparametrer, noUtilisateur);
//			System.out.println("parametre requete " + nbVariablesAparametrer + " = " + noUtilisateur);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeVentes.add(new Vente(rs.getInt("no_vente"), rs.getString("nomarticle"),
						rs.getString("description"), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("photo")));// /!\ NEW : ajout photo
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVentes;
	}

	/*
	 * /!\ NEW : méthode de recherche de toutes les acquisitions de l'utilisateur
	 * avec option par mots clés du nom article et/ou noCategorie
	 */
	public static List<Vente> selectAllEncheresTermineesUtilisateur(int noUtilisateur, int noCategorie,
			String motsCles) {
		List<Vente> listeVentes = new ArrayList<Vente>();
		String[] tableauMotsCles = null;
		int nbVariablesAparametrer = 0;
		if (motsCles != "") {
			tableauMotsCles = motsCles.split(" ");// prévoir séparateur "," ?
		}
		StringBuffer requete = new StringBuffer();
		requete.append(SELECT_ALL_VENTES_BY_);
		requete.append(
				"INNER JOIN ENCHERES e ON e.no_vente = v.no_vente INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur WHERE ");
		// élaboration de la requête si des mots clé sont renseignés en fonction de leur
		// nombre
		if (motsCles != "") {
			requete.append("(");
			for (int i = 0; i < tableauMotsCles.length; i++) {
				if (i == tableauMotsCles.length - 1) {
					requete.append("nomarticle LIKE ?");
				} else {
					requete.append("nomarticle LIKE ? OR ");
				}
				nbVariablesAparametrer++;
			}
			requete.append(") AND ");
		}

		// élaboration de la requête en fonction de la catégorie :
		if (noCategorie != 0) {
			requete.append("no_categorie = ? AND ");
			nbVariablesAparametrer++;
		}
		requete.append("e.no_utilisateur = ? AND ");
		nbVariablesAparametrer++;
		requete.append("date_fin_encheres < GETDATE()");
//		System.out.println(requete);

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(requete.toString());

			// balayage du tableau de mots clés pour paramétrer la requête
			if (motsCles != "") {
				for (int i = 0; i < tableauMotsCles.length; i++) {
					pstmt.setString(i + 1, "%" + tableauMotsCles[i] + "%");
//					System.out.println("parametre requete " + (i + 1) + " = " + tableauMotsCles[i]);
				}
			}

			// paramétrage de la requête si catégorie renseignée
			if (noCategorie != 0) {
				pstmt.setInt(nbVariablesAparametrer - 1, noCategorie);
//				System.out.println("parametre requete " + (nbVariablesAparametrer - 1) + " = " + noCategorie);
			}
			// paramétrage du numero utilisateur de la requête
			pstmt.setInt(nbVariablesAparametrer, noUtilisateur);
//			System.out.println("parametre requete " + nbVariablesAparametrer + " = " + noUtilisateur);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeVentes.add(new Vente(rs.getInt("no_vente"), rs.getString("nomarticle"),
						rs.getString("description"), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("photo")));// /!\ NEW : ajout photo
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVentes;
	}

	/* /!\ FIN NEW */

}
