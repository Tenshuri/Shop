<%@ page import="shop.beans.Commande" %>
<%@ page import="java.util.List" %>
<jsp:include page="common/header.jsp"/>

<h1>Mes commandes</h1>

<%  List<Commande> commandes = (List<Commande>) request.getAttribute("orderList"); %>
<%if (commandes != null && commandes.size() > 0) { %>
<table class="table">
    <tr><th>Num�ro commande</th><th>Montant</th><th>Date</th><th>�tat</th></tr>

<%
    for (Commande c : commandes) { %>
        <tr>
            <td><a href="vieworder?num=<%=c.getNum()%>"><%=c.getNum()%></a></td>
            <td><%=c.getMtTotal()%> &euro;</td>
            <td>16/06/2017</td>
            <td>En pr�paration</td>
        </tr>
    <% } %>
</table>
<% } %>


<jsp:include page="common/footer.jsp"/>
