package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Enchere;

public class EnchereDAO {
	private static final String SELECT_BY_NO_UTILISATEUR = "select * from ENCHERES where no_utilisateur = ?";
	private static final String SELECT_BY_NO_VENTE = "select * from ENCHERES where no_vente = ? ORDER BY montant DESC";
	private static final String INSERT_ENCHERE = "insert into ENCHERES (date_enchere,no_utilisateur,no_vente,montant)values(?,?,?,?)";
	private static final String SELECT_BY_PK = "SELECT * FROM ENCHERES WHERE no_utilisateur = ? and no_vente = ?";
	private static final String UPDADE_ENCHERE = "UPDATE ENCHERES SET montant=? WHERE no_utilisateur = ? and no_vente=?";

	public static void updateEnchere(int noUtilisateur, int noVente, int nouveauCredit) {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDADE_ENCHERE);
			pstmt.setInt(1, nouveauCredit);
			pstmt.setInt(2, noUtilisateur);
			pstmt.setInt(3, noVente);
			pstmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Enchere selectByPK(int noUtilisateur, int noVente) {
		Enchere enchere = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PK);
			pstmt.setInt(1, noUtilisateur);
			pstmt.setInt(2, noVente);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				enchere = new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("no_utilisateur"),
						rs.getInt("no_vente"),rs.getInt("montant"));
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enchere;
	}

	public static void insertEnchere(Enchere enchere) {

		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
			pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getNoUtilisateur());
			pstmt.setInt(3, enchere.getNoVente());
			pstmt.setInt(4, enchere.getMontant());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Enchere selectByNoVente(int noVente) {
		Enchere enchere = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NO_VENTE);
			pstmt.setInt(1, noVente);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				enchere = new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("no_utilisateur"), rs.getInt("no_vente"));
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enchere;
	}

	/* /!\ NEW : méthode de recherche de toutes les encheres d'un utilisateur donné*/
	public static List<Enchere> selectEncheresByNoVente(int noVente) {
		List<Enchere> listeEncheresUtilisateur = new ArrayList<Enchere>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NO_VENTE);
			pstmt.setInt(1, noVente);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeEncheresUtilisateur.add(new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("no_utilisateur"), rs.getInt("no_vente"), rs.getInt("montant")));
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeEncheresUtilisateur;
	}
	
	/* /!\ NEW : méthode de recherche de toutes les encheres d'une vente donnée*/
	public static List<Enchere> selectByNoUtilisateur(int noUtilisateur) {
		List<Enchere> listeEncheresUtilisateur = new ArrayList<Enchere>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NO_UTILISATEUR);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeEncheresUtilisateur.add(new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("no_utilisateur"), rs.getInt("no_vente"), rs.getInt("montant")));
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeEncheresUtilisateur;
	}
	

}
