<%@ page import="shop.beans.Produit" %>
<%@ page import="java.util.List" %>
<jsp:include page="common/header.jsp"/>
<h1>Voir une commande</h1>
<h2>Numéro de commande : <%=request.getParameter("num")%></h2>
<h2>Articles achetés</h2>

<%
    List<Produit> produits = (List<Produit>) request.getAttribute("produits");
    if (produits != null && produits.size() > 0) {
        for (Produit p : produits) { %>
            <p>- <%=p.getNom()%> (<%=p.getPrix()%>&euro;)</p>
        <%}%>

    <%} else {%>
        <p>Aucun produit dans cette commande...</p>
    <%}%>

<jsp:include page="common/footer.jsp"/>
