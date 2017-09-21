<%@ page import="shop.beans.Commande" %>
<%@ page import="java.util.List" %>
<jsp:include page="common/header.jsp"/>

<h1>Mes commandes</h1>

<%  List<Commande> commandes = (List<Commande>) request.getAttribute("orderList"); %>
<%if (commandes != null && commandes.size() > 0) { %>
<table class="table">
    <tr><th>Numéro commande</th><th>Montant</th><th>Date</th><th>État</th></tr>

<%
    for (Commande c : commandes) { %>
        <tr>
            <td><a href="vieworder?num=<%=c.getNum()%>"><%=c.getNum()%></a></td>
            <td><%=c.getMtTotal()%> &euro;</td>
            <td>16/06/2017</td>
            <td>En préparation</td>
        </tr>
    <% } %>
</table>
<% } %>


<jsp:include page="common/footer.jsp"/>
