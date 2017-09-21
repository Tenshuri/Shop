<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Moi Président</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/shop.css">
</head>
<body>

<div class="container">

<header class="navbar">
    <section>
        <div class="navbar-header">
        <a href="home" class="navbar-brand">Moi Président</a>
        </div>
        <div class="nav navbar-nav">
        <a href="products" class="nav navbar-brand">Produits</a>
        <%  String con = (String) session.getAttribute("connected");
            String email = (String) session.getAttribute("email");
            if (con == null) con = "";
            if (email == null) email = "";
            if (con.equals("true")) { %>
                <a href="orders" class="nav navbar-brand">Mes commandes</a>
                <a href="account" class="nav navbar-brand">Mon Compte</a>
                <a href="logout" class="nav navbar-brand">Déconnexion</a>
            <%} else { %>
        <a href="signin" class="nav navbar-brand">Connexion</a>
        <a href="signup" class="nav navbar-brand">S'inscrire</a>
        <% } %>
        </div>
    </section>
    <section class="navbar-right">
        <div class="input-group input-inline">
            <a href="cart" class="nav navbar-brand">Voir mon Panier</a>
        </div>
    </section>
</header>