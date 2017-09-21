/*
    Accès aux données relatives aux clients
 */
package shop.dao;

import shop.beans.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOClient extends DAO {

    public DAOClient() {
    }

    public int getNumClient(String email) throws SQLException {
        String request = "SELECT numClient FROM Client WHERE adresseMailClient LIKE ?;";
        PreparedStatement ps = getConnection().prepareStatement(request);
        ps.setString(1, email);
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        int numClient = resultSet.getInt(1);
        return numClient;
    }

    public String ajouterClient(Client cli) {
        String msg = "";
        String requete = "INSERT INTO Client (nomClient, prenomClient, adresseClient, adresseMailClient, telephoneClient, dateNaissanceClient, genre, password)VALUES (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = getConnection().prepareStatement(requete);
            ps.setString(1, cli.getNom());
            ps.setString(2, cli.getPrenom());
            ps.setString(3, cli.getAdresse());
            ps.setString(4, cli.getAdresseMail());
            ps.setString(5, cli.getTelephone());
            ps.setString(6, cli.getDateN());
            ps.setInt(7, cli.getGenre());
            ps.setString(8, cli.getPwd());
            int rs = ps.executeUpdate();
            return msg;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public Client lireUnClient(int numero) throws SQLException {
        String requete = "SELECT * FROM Client WHERE numClient = ? ;";
        PreparedStatement pstmt = getConnection().prepareStatement(requete);
        pstmt.setInt(1, numero);
        ResultSet rset = pstmt.executeQuery();
        rset.next();

        int num = rset.getInt(1);
        String nom = rset.getString(2);
        String prenom = rset.getString(3);
        String adresse = rset.getString(4);
        String adresseMail = rset.getString(5);
        String telephone = rset.getString(6);
        String dateN = rset.getString(7);
        int genre = rset.getInt(8);
        String pwd = rset.getString(9);

        Client client = new Client(num, nom, prenom, adresse, adresseMail, telephone, dateN, genre, pwd);

        rset.close();
        pstmt.close();
        return client;
    }

    public String motDePasse(String email) throws SQLException {
        String requete = "SELECT password FROM Client WHERE adresseMailClient = ? ;";
        PreparedStatement pstmt = getConnection().prepareStatement(requete);
        pstmt.setString(1, email);
        ResultSet rset = pstmt.executeQuery();
        rset.next();
        String mdp = rset.getString(1);
        if (mdp == null) return "";
        return mdp;
    }
}
