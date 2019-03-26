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

	private static final String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from utilisateurs";
	private static final String delete = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String selectById = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";

	public Utilisateur selectById(int NoUtilisateur){
		Utilisateur utilisateur = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(selectById);
			ResultSet rs = pstmt.executeQuery();
			pstmt.setInt(1, NoUtilisateur);
			
			if (rs.next()) {
					utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), 
							rs.getString("pseudo"), 
							rs.getString("nom"), 
							rs.getString("prenom"), 
							rs.getString("email"), 
							rs.getString("telephone"), 
							rs.getString("rue"),
							rs.getString("code_postal"),
							rs.getString("ville"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur"));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}
	
	public void insertUtilisateur(Utilisateur utilisateur) {

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
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setBoolean(11, utilisateur.isAdministrateur());
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


	public List<Utilisateur> selectAll() {

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

	private Utilisateur UtilisateurBuilder(ResultSet rs) throws Exception {

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
	
	public void deleteUtilisateur(int NoUtilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(delete);

			pstmt.setInt(1, NoUtilisateur);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
