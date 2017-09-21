/*
    Servlet de consultation du compte
 */
package shop.servlets;

import shop.beans.Client;
import shop.dao.DAOClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Account")
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lire le client
        String emailS = (String) request.getSession().getAttribute("email");
        Client client = null;
        int numClient = -1;
        if (emailS != null) {
            try {
                DAOClient daoClient = new DAOClient();
                numClient = daoClient.getNumClient(emailS);
                client = daoClient.lireUnClient(numClient);
            } catch (Exception e) {
                //
            }
        }
        if (client != null) {
            request.setAttribute("client", client);
        }
        request.getRequestDispatcher("jsp/account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Servlet Account";
    }
}
