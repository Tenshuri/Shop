/*
    Servlet de visualisation de commande
 */
package shop.servlets;

import shop.beans.Produit;
import shop.dao.DAOCommande;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numP = request.getParameter("num");
        if (numP != null && !numP.equals("")) {
            int numCommande = Integer.parseInt(numP);
            List<Produit> produits;
            produits = new DAOCommande().lireUneCommande(numCommande);
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("jsp/vieworder.jsp").forward(request, response);
        } else {
            // Rediriger vers les commandes
            response.sendRedirect("orders");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Servlet ViewOrder";
    }

}
