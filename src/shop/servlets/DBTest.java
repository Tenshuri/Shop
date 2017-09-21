/*
    Servlet de test de connexion à la base de données.
    Essaie de se connecter et de renvoyer des données à la suite d'une requête.
 */
package shop.servlets;

import shop.beans.SourceMariaDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String msg = "";
        try {
            SourceMariaDB s = new SourceMariaDB();
            Connection cnx = s.getCnx();
            msg = s.getMessage();
            Statement lien = cnx.createStatement();
            String requete = "SELECT * FROM Produit";
            ResultSet rset;
            rset = lien.executeQuery(requete);
            while (rset.next()) {
                int num = rset.getInt(1);
                String nom = rset.getString(2);
                int prix = rset.getInt(3);

                out.println(num + " " + nom + " " + prix);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
            out.println("Message etablirConnexion() : " + msg + "<br>");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                out.println(e.getStackTrace()[i].toString()+"<br>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
