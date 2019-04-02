package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.dal.ConnectionProvider;
import fr.eni.projet.bo.Utilisateur;

public class UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, visionNom, visionPrenom, visionEmail, visionTelephone) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from utilisateurs";
	private static final String delete = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String selectById = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email = ?";
	private static final String SELECT_BY_TELEPHONE = "SELECT * FROM UTILISATEURS WHERE telephone = ?";
	private static final String UPDATE_PROFIL = "UPDATE Utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur = ?";
	private static final String UPDATE_CREDIT_BY_ID = "UPDATE Utilisateurs SET credit=? WHERE no_utilisateur = ?";
	private static final String UPDATE_VISION = "UPDATE Utilisateurs SET visionNom=? visionPrenom=? visionEmail=? visionTelephone = ? WHERE no_utilisateur = ?";

	public static void updateVision(int noUtilisateur, boolean visionNom, boolean visionPrenom, boolean visionEmail,
			boolean visionTelephone) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_VISION);
			pstmt.setBoolean(1, visionNom);
			pstmt.setBoolean(2, visionPrenom);
			pstmt.setBoolean(3, visionEmail);
			pstmt.setBoolean(4, visionTelephone);
			pstmt.setInt(5, noUtilisateur);
			pstmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateCredit(int noUtilisateur, int nouveauCredit) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT_BY_ID);
			pstmt.setInt(1, nouveauCredit);
			pstmt.setInt(2, noUtilisateur);
			pstmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProfil(Utilisateur utilisateur) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PROFIL);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Utilisateur selectById(int NoUtilisateur) {
		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(selectById);
			pstmt.setInt(1, NoUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}

	public static Utilisateur selectByPseudo(String pseudo) {

		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}

	public static Utilisateur selectByEmail(String email) {

		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}

	/*
	 * /!\ NEW : pour tester unicite telephone pour creation et modification compte
	 * utilisateur
	 */
	public static Utilisateur selectByTelephone(String telephone) {

		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_TELEPHONE);
			pstmt.setString(1, telephone);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}/* /!\ FIN NEW */

	public static void insertUtilisateur(Utilisateur utilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, 100);
				pstmt.setBoolean(11, utilisateur.isAdministrateur());
				pstmt.setBoolean(12, false);
				pstmt.setBoolean(13, false);
				pstmt.setBoolean(14, false);
				pstmt.setBoolean(15, false);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Utilisateur> selectAll() {

		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			Utilisateur UtilisateurCourant = new Utilisateur();
			while (rs.next()) {
				if (rs.getInt("no_utilisateur") != UtilisateurCourant.getNoUtilisateur()) {
					UtilisateurCourant = UtilisateurBuilder(rs);
					listeUtilisateur.add(UtilisateurCourant);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeUtilisateur;
	}

	private static Utilisateur UtilisateurBuilder(ResultSet rs) throws Exception {

		Utilisateur UtilisateurCourant;
		UtilisateurCourant = new Utilisateur();
		UtilisateurCourant.setNoUtilisateur(rs.getInt("no_utilisateur"));
		UtilisateurCourant.setPseudo(rs.getString("pseudo"));
		UtilisateurCourant.setNom(rs.getString("nom"));
		UtilisateurCourant.setPrenom(rs.getString("prenom"));
		UtilisateurCourant.setEmail(rs.getString("email"));
		UtilisateurCourant.setTelephone(rs.getString("telephone"));
		UtilisateurCourant.setRue(rs.getString("rue"));
		UtilisateurCourant.setCodePostal(rs.getString("code_postal"));
		UtilisateurCourant.setVille(rs.getString("ville"));
		UtilisateurCourant.setMotDePasse(rs.getString("mot_de_passe"));
		UtilisateurCourant.setCredit(rs.getInt("credit"));
		UtilisateurCourant.setAdministrateur(rs.getBoolean("administrateur"));

		return UtilisateurCourant;

	}

	public static void deleteUtilisateur(int NoUtilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(delete);

			pstmt.setInt(1, NoUtilisateur);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
