package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projet.bo.Categorie;

public class CategorieDAO {
	private static final String INSERT_CATEGORIE = "insert into CATEGORIES(libelle) values(?)";
	private static final String SELECT_BY_LIBELLE = "SELECT * FROM ARTICLES WHERE libelle = ?";

	public static void insertCategorie(Categorie categorie) {
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_CATEGORIE);
			pstmt.setString(1, categorie.getLibelle());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Categorie selectByLibelle(String pseudo) {
		Categorie categorie = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("pseudo"));
			}
			rs.close();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

}
