package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projet.bo.Enchere;

public class EnchereDAO {
	private static final String SELECT_BY_NO_VENTE = "select * from ENCHERES where no_vente = ?";
	private static final String INSERT_ENCHERE = "insert into ENCHERES (date_enchere,no_utilisateur_no_vente,montant)values(?,?,?,?)";
	
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
		Enchere enchere=null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NO_VENTE);
			pstmt.setInt(1, noVente);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				enchere = new Enchere(rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("no_utilisateur"), rs.getInt("no_vente"));
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
	
}
