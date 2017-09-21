<%@ page import="shop.beans.Client" %>
<jsp:include page="common/header.jsp"/>

<h1>Mon compte</h1>

<% Client client = (Client) request.getAttribute("client");
   if (client != null) { %>

<h2>Mes informations personnelles</h2>

<ul>
    <li>N� Client : <%=client.getNum()%></li>
    <li>Nom : <%=client.getNom()%></li>
    <li>Pr�nom : <%=client.getPrenom()%></li>
    <li>Adresse : <%=client.getAdresse()%></li>
    <li>Adresse mail : <%=client.getAdresseMail()%></li>
    <li>T�l�phone : <%=client.getTelephone()%></li>
    <li>Date de naissance : <%=client.getDateN()%></li>
    <li>Vous �tes :
        <% if (client.getGenre() == 0) {%> Un homme<%} else {%>
        Une femme
        <% } %>
    </li>
</ul>

<% } %>

<jsp:include page="common/footer.jsp"/>
