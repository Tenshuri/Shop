/*
    Accès aux données relatives aux commandes
 */
package shop.dao;

import shop.beans.Commande;
import shop.beans.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCommande extends DAO {

    public DAOCommande() {
    }

    public String lireLesCommandes(List<Commande> lesCommandes, int numeroClient) throws Exception {
        try {
            String requete;
            requete = "SELECT C.numCommande, C.etat,"
                    + "                    ((SELECT SUM(prixUnitaireProduit)"
                    + "                    FROM ligneCommande L, Produit P"
                    + "                    WHERE L.numProduit = P.numProduit"
                    + "                    AND L.numCommande = C.numCommande))"
                    + "                    FROM Commande C"
                    + "                    WHERE C.numClient = ?;";
            PreparedStatement pstmt = getConnection().prepareStatement(requete);
            pstmt.setInt(1, numeroClient);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                int num = rset.getInt(1);
                int etat = rset.getInt(2);
                int mtTotal = rset.getInt(3);

                // Attention, on met le montant total dans le numéro client (désolé :( )
                lesCommandes.add(new Commande(num, etat, mtTotal, numeroClient));
            }
            rset.close();
            pstmt.close();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Renvoie tous les produits d'une commande
    public List<Produit> lireUneCommande(int numero) {
        List<Produit> produits = new ArrayList<>();
        try {
            String requete = "SELECT nomProduit, prixUnitaireProduit"
                    + "       FROM Produit P"
                    + "       INNER JOIN ligneCommande L ON P.numProduit = L.numProduit"
                    + "       WHERE numCommande = ?;";
            PreparedStatement pstmt = getConnection().prepareStatement(requete);
            pstmt.setInt(1, numero);

            ResultSet rset = pstmt.executeQuery();

            int num = 1;
            while(rset.next()) {
                Produit p = new Produit(num, rset.getString(1), rset.getInt(2), "");
                produits.add(p);

                num += 1;
            }

            rset.close();
            pstmt.close();
            return produits;
        } catch (SQLException e) {
            return produits;
        }
    }
    
    public void ajouterCommande(int numClient, List<Produit> produits) {
        String reqCom = "INSERT INTO Commande(etat, numClient) VALUES(0,?);";
        String reqLigne = "INSERT INTO ligneCommande VALUES(?,?);";
        try {
            // Commencer par insérer dans "Commande"
            PreparedStatement ps = getConnection().prepareStatement(reqCom);
            ps.setInt(1, numClient);
            ps.executeUpdate();
            ResultSet lastId = ps.getGeneratedKeys();
            lastId.next();
            int numCommande = lastId.getInt(1);
            
            // Insérer une ligne par produit dans "ligneCommande"
            for (Produit p : produits) {
                PreparedStatement ps2 = getConnection().prepareStatement(reqLigne);
                ps2.setInt(1, numCommande);
                ps2.setInt(2, p.getNum());
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            //
        }
    }
}
