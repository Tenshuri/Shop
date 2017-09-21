/*
    Servlet des commandes
 */
package shop.servlets;

import shop.beans.Commande;
import shop.dao.DAOClient;
import shop.dao.DAOCommande;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Orders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg = "";
        List<Commande> commandes = new ArrayList<>();
        String email = (String) request.getSession().getAttribute("email");
        int numClient = -1;
        if (email != null) {
            try {
                numClient = new DAOClient().getNumClient(email);
                if (numClient != -1) {
                   msg = new DAOCommande().lireLesCommandes(commandes, numClient);
                }
            } catch (Exception e) {
                //
            }
        }
        request.setAttribute("orderList", commandes);
        request.getRequestDispatcher("jsp/orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Servlet Commandes";
    }

}
