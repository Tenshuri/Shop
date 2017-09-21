/*
    Servlet d'accès au panier
 */
package shop.servlets;

import shop.beans.Produit;
import shop.beans.Produits;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import shop.dao.DAOClient;
import shop.dao.DAOCommande;

@WebServlet(name = "Cart")
public class Cart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Doit on valider le panier ?
        String validParam = request.getParameter("valider");
        if (validParam != null && validParam.equals("1")) {
            HttpSession sess = request.getSession();
            if(sess != null && sess.getAttribute("connected")!=null && sess.getAttribute("connected").equals("true")) {
                try {
                    Cookie[] cookies = request.getCookies();
                    boolean noCart = true;
                    Produits produits = null;
                    for (Cookie c : cookies) {
                        if (c.getName().equals("panier")) {
                            if (c.getValue().equals("")) {
                                // Cookie présent mais vide. Ne pas désérialiser
                                break;
                            } else {
                                noCart = false;
                                // Désérialiser le panier
                                produits = Produits.fromString(c.getValue());
                                c.setValue("");
                                c.setMaxAge(0);
                                c.setPath("/");
                                response.addCookie(c);
                            }
                        }
                    }
                    if (noCart == false && produits != null) {
                        // On peut passer la commande :
                        // Créer un commande
                        DAOCommande daoc = new DAOCommande();
                        DAOClient daocli = new DAOClient();
                        int numCli = daocli.getNumClient((String) sess.getAttribute("email"));
                        daoc.ajouterCommande(numCli, produits.getProduits());
                        request.getRequestDispatcher("jsp/order_success.jsp").forward(request, response);
                        return;
                    } else {
                        // ne pas passer la commande : panier vide
                        // Renvoyer sur les produits
                        request.getRequestDispatcher("jsp/products.jsp").forward(request, response);
                    }
                } catch (Exception ex) {
                    //
                }
                
            } else {
                // Il faut se connecter !
                RequestDispatcher rd = request.getRequestDispatcher("jsp/signin.jsp");
                rd.forward(request, response);
            }
        }

        // Doit on vider le panier ?
        String viderParam = request.getParameter("vider");
        if (viderParam != null && viderParam.equals("1")) {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("panier")) {
                    c.setValue("");
                    c.setPath("/");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        // Récupérer les produits dans le panier
        Produits produits = null;
        Cookie[] cookies = request.getCookies();
        boolean noCart = true;
        for (Cookie c : cookies) {
            if (c.getName().equals("panier")) {
                if (c.getValue().equals("")) {
                    // Cookie présent mais vide. Ne pas désérialiser
                    break;
                }
                noCart = false;
                // Désérialiser le panier
                try {
                    produits = Produits.fromString(c.getValue());
                } catch (ClassNotFoundException e) {
                    noCart = true;
                }
            }
        }
        // Sinon créer objet produits vide
        if (noCart) {
            produits = new Produits(new ArrayList<Produit>());
        }
        // Envoyer les produits
        request.setAttribute("produits", produits);
        // Rediriger vers la vue du Panier
        RequestDispatcher rd = request.getRequestDispatcher("jsp/cart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Servlet Cart";
    }
}
