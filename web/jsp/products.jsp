<%@ page import="shop.beans.Produit" %>
<%@ page import="shop.beans.Produits" %>
<%@ page import="java.util.List" %>
<jsp:include page="common/header.jsp"/>

<h1>Liste des produits en vente</h1>
<span class="label-primary" style="color: white"><%=request.getAttribute("userMsg")%></span>
<p>Chaque produit est unique : vous ne pouvez commander qu'un seul exemplaire par produit</p>

<table class="table">
    <tr><th>Photo</th><th>Nom</th><th>Prix</th><th>Action</th></tr>

<%  Produits produits = (Produits) request.getAttribute("produits");
    List<Produit> ps = produits.getProduits();
    if (ps != null) {

    for (Produit p : ps) { %>
        <tr>
            <td><img class="img-responsive" width="200px" src="photos/<%=p.getPhoto()%>"></td>
            <td><%=p.getNom()%></td>
            <td><%=p.getPrix()%> &euro;</td>
            <td><a href="products?add=<%=p.getNum()%>">Ajouter au panier</a></td>
        </tr>
    <% }} %>

</table>

<jsp:include page="common/footer.jsp"/>
