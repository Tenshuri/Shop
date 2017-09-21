<%@ page import="shop.beans.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.beans.Produits" %>
<jsp:include page="common/header.jsp"/>

<h1>Affichage de votre panier</h1>
<p>Votre panier reste accessible pendant 24h, même si vous n'êtes pas connecté(e).</p>
<p>Pour commander, vous devez être connecté(e).</p>

<%  Produits produits = (Produits) request.getAttribute("produits");
    List<Produit> ps = produits.getProduits();
    if (ps != null && !ps.isEmpty()) { %>

<table class="table">
    <tr><th>N° Produit</th><th>Nom du produit</th><th>Prix du produit</th></tr> <%
    int pTot = 0;
    for (Produit p : ps) {
        pTot += p.getPrix(); %>
        <tr>
            <td><%=p.getNum()%></td>
            <td><%=p.getNom()%></td>
            <td><%=p.getPrix()%> &euro;</td>
        </tr>
    <% }
    %>
</table>
<p class="btpanier">Montant total : <%=pTot%>&euro;</p>
<div class="btpanier">
    <a href="cart?valider=1" class="btn btn-primary">Valider</a>
    <a href="cart?vider=1" class="btn btn-primary">Vider mon panier</a>
</div>
    <%
    }
    else { %>
        <p>Panier vide</p>
    <% } %>


<jsp:include page="common/footer.jsp"/>
