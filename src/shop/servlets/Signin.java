/*
    Servlet de connexion au site
 */
package shop.servlets;

import shop.dao.DAOClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Signin")
public class Signin extends HttpServlet {

    // Savoir si utilisateur a entré de bons identifiants
    private boolean checkInfos(String email, String password) {
        if (email == null) email = "";
        if (password == null) password = "";
        String dbPassword;
        try {
            dbPassword = new DAOClient().motDePasse(email);
        } catch (SQLException e) {
            return false;
        }
        return dbPassword.equals(password);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la vue de connexion
        RequestDispatcher rd = request.getRequestDispatcher("jsp/signin.jsp");
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
        HttpSession s = request.getSession(true);
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        if (email == null) email = "";
        if (password == null) password = "";
        //response.getWriter().println(email + " - " + password);
        if (checkInfos(email, password)) {
            // Identifiants OK -> Session de connexion
            //response.getWriter().println("ok");
            s.setAttribute("connected", "true");
            s.setAttribute("email", email);
            // Rediriger vers l'accueil
            request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
        }
        // Sinon renvoyer le formulaire
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet Signin";
    }
}
