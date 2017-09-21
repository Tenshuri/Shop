/*
    Servlet d'inscription
 */
package shop.servlets;

import shop.beans.Client;
import shop.dao.DAOClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.Map;

public class Signup extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la vue d'inscription
        RequestDispatcher rd = request.getRequestDispatcher("jsp/signup.jsp");
        rd.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = (String) request.getParameter("nom");
        if (nom == null) nom = "";
        String prenom = (String) request.getParameter("prenom");
        if (prenom == null) prenom = "";
        String genreP = (String) request.getParameter("gender");
        if (genreP == null) genreP = "";
        int genre = Integer.parseInt(genreP);
        String email = (String) request.getParameter("email");
        if (email == null) email = "";
        String adresse = (String) request.getParameter("address");
        if (adresse == null) adresse = "";
        String telephone = (String) request.getParameter("phone");
        if (telephone == null) telephone = "";
        String dateNaissance = (String) request.getParameter("datenaiss");
        if (dateNaissance == null) dateNaissance = "";
        String pwd = (String) request.getParameter("password");
        if (pwd == null) pwd = "";
        if (email.equals("") || pwd.equals("")) {
            processRequest(request, response);
        } else { // Données OK, créer le client
            Client client = new Client(0, nom, prenom, adresse, email, telephone, dateNaissance, genre, pwd);
            String msg = new DAOClient().ajouterClient(client);
            //PrintWriter p = response.getWriter();
            //p.println(msg);
            // Rediriger vers le succès
            request.getRequestDispatcher("jsp/signup_success.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet Signup";
    }

}
