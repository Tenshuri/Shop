/*
    Accès aux données relatives aux produits
 */
package shop.dao;

import shop.beans.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOProduit extends DAO {


    public DAOProduit() {}

    public void lireLesProduits(List<Produit> lesProduits) {
        try {
            String requete = "SELECT * FROM Produit";
            PreparedStatement pstmt = getConnection().prepareStatement(requete);
            ResultSet rset = pstmt.executeQuery(requete);
            while (rset.next()) {
                int num = rset.getInt(1);
                String nom = rset.getString(2);
                int prix = rset.getInt(3);
                String photo = rset.getString(4);

                lesProduits.add(new Produit(num, nom, prix, photo));
            }
            rset.close();
            pstmt.close();
        } catch (Exception e) {
            lesProduits.clear();
        }
    }

    public Produit lireUnProduit(int numero) throws SQLException {
        String requete = "SELECT * FROM Produit WHERE numProduit = ? ;";
        PreparedStatement pstmt = getConnection().prepareStatement(requete);
        pstmt.setInt(1, numero);
        ResultSet rset = pstmt.executeQuery();
        rset.next();

        int num = rset.getInt(1);
        String nom = rset.getString(2);
        int prix = rset.getInt(3);
        String photo = rset.getString(4);

        Produit produit = new Produit(num, nom, prix, photo);

        rset.close();
        pstmt.close();
        return produit;
    }

}