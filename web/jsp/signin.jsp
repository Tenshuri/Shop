<jsp:include page="common/header.jsp"/>

<h1>Formulaire de connexion</h1>
<form method="post">

    <div class="form-group">
        <label class="form-label" for="email-input">Adresse mail : </label>
        <input name="email" class="form-input" type="email" id="email-input" placeholder="Adresse mail" />
    </div>

    <div class="form-group">
        <label class="form-label" for="nom-input">Mot de passe : </label>
        <input name="password" class="form-input" type="password" id="nom-input" placeholder="Mot de passe" />
    </div>

    <div class="form-group">
        <input type="submit" class="btn btn-primary" name="sbm" value="Se connecter">
    </div>

</form>

<a href="signup">S'inscrire</a>
<jsp:include page="common/footer.jsp"/>
