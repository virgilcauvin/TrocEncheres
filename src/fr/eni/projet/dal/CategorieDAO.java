package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.projet.bo.Categorie;

public class CategorieDAO {
	private static final String INSERT_CATEGORIE = "insert into CATEGORIES(libelle) values(?)";
	private static final String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle = ?";
	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES";

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

	public static Categorie selectByLibelle(String libelle) {
		Categorie categorie = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			pstmt.setString(1, libelle);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	/*/!\ NEW : modif retour de recherche du selectAll : liste de Categories au lieu de libelles de categorie*/
	public static ArrayList<Categorie> selectAll() {
		ArrayList<Categorie> listeCategories = new ArrayList<>();
		Categorie categorie;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				listeCategories.add(categorie);
			}
			rs.close();
			pstmt.close();
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeCategories;
	}

}
