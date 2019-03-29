package fr.eni.projet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.projet.bo.Vente;

public class VenteDAO {
	private static final String INSERT_VENTE = "insert into VENTES(nomarticle, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie,photo ) values(?,?,?,?,?,?,?,?)";
	
	public static void insertVente(Vente vente) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_VENTE,
						PreparedStatement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, vente.getNomArticle());
				pstmt.setString(2, vente.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(vente.getDateFinEncheres()));
				pstmt.setInt(4, vente.getMiseAPrix());
				pstmt.setInt(5, vente.getPrixVente());
				pstmt.setInt(6, vente.getNoUtilisateur());
				pstmt.setInt(7, vente.getNoCategorie());
				pstmt.setString(8, vente.getPhoto());
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
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
