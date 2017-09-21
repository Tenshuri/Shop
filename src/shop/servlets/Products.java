/*
    Servlet de la page produits
 */
package shop.servlets;

import shop.beans.Produit;
import shop.beans.Produits;
import shop.dao.DAOProduit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Products")
public class Products extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Rediriger vers la vue des produits
        RequestDispatcher rd = request.getRequestDispatcher("jsp/products.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            PrintWriter p = response.getWriter();
            p.println(e.getStackTrace()[4]);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg = "";
        String userMsg = "";
        // Ajout d'un produit au panier si demandé
        String addParam = request.getParameter("add");
        int numProduit = -1;
        if (addParam != null) {
            msg += "addparam pas null";
            numProduit = Integer.parseInt(addParam);
            msg += "\nparse ok";
            // Déjà des produits dans le panier ?
            Cookie[] cookies = request.getCookies();
            Produits produits;
            boolean create = true;
            for (Cookie c : cookies) {
                if (c.getName().equals("panier")) try {
                    msg += "\n deja un panier";
                    create = false;
                    produits = Produits.fromString(c.getValue());
                    msg +="\npanier: " + c.getValue();
                    // Ajouter seulement si pas déjà dans le panier
                    List<Produit> lesProds = produits.getProduits();
                    boolean present = false;
                    for (Produit p : lesProds) {
                        if (p.getNum() == numProduit) {
                            present = true;
                            userMsg = "Ce produit est déjà dans votre panier.";
                        }
                    }
                    if (!present) {
                        // Mettre à jour le cookie
                        userMsg = "Produit ajouté à votre panier.";
                        lesProds.add(new DAOProduit().lireUnProduit(numProduit));
                        produits.setProduits(lesProds);
                        c.setValue(Produits.toString(produits));
                        c.setMaxAge(60*24*3600);
                        c.setPath("/");
                        response.addCookie(c);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    // Produit pas trouvé dans la BD
                }
            }
            // Si pas encore de panier :
            if (create) {
                try {
                    List<Produit> lesProds = new ArrayList<>();
                    lesProds.add(new DAOProduit().lireUnProduit(numProduit));
                    produits = new Produits();
                    produits.setProduits(lesProds);
                    Cookie cookie = new Cookie("panier", Produits.toString(produits));
                    cookie.setMaxAge(60*24*3600);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    userMsg = "Produit ajouté à votre panier.";
                } catch (Exception e) {
                    // Pas dispo DB
                }
            }
        }
        // Récupération et envoi des produits
        List<Produit> lesProduits = new ArrayList<>();
        Produits produits;
        new DAOProduit().lireLesProduits(lesProduits);
        produits = new Produits(lesProduits);
        request.setAttribute("produits", produits);
        request.setAttribute("userMsg", userMsg);
        // DEBUG
        //PrintWriter p = response.getWriter();
        //p.println(msg);
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet Produits";
    }
}
