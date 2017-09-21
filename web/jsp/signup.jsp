<jsp:include page="common/header.jsp"/>

<h1>Formulaire d'inscription</h1>
<form method="post">
    <div class="form-group">
        <label class="form-label" for="nom-input">Nom : </label>
        <input name="nom" class="form-input" type="text" id="nom-input" placeholder="Nom" />
    </div>
    <div class="form-group">
        <label class="form-label" for="prenom-input">Prénom : </label>
        <input name="prenom" class="form-input" type="text" id="prenom-input" placeholder="Prénom" />
    </div>

    <div class="form-group">
        <label class="form-label">Vous êtes : </label>
        <label class="form-radio">
            <input type="radio" name="gender" value="0" checked/>
            <i class="form-icon"></i> Un homme
        </label>
        <label class="form-radio">
            <input type="radio" name="gender" value="1"/>
            <i class="form-icon"></i> Une femme
        </label>
    </div>

    <div class="form-group">
        <label class="form-label" for="email-input">Adresse mail : </label>
        <input name="email" class="form-input" type="email" id="email-input" placeholder="Adresse mail" />
    </div>

    <div class="form-group">
        <label class="form-label" for="email-input">Adresse : </label>
        <input name="address" class="form-input" type="text" id="address-input" placeholder="Votre adresse postale" />
    </div>

    <div class="form-group">
        <label class="form-label" for="phone-input">Téléphone : </label>
        <input name="phone" class="form-input" type="tel" id="phone-input" placeholder="Votre n°" />
    </div>

    <div class="form-group">
        <label class="form-label" for="datenaiss-input">Date de naissance : </label>
        <input name="datenaiss" class="form-input" type="date" id="datenaiss-input" placeholder="Date de naissance" />
    </div>

    <div class="form-group">
        <label class="form-label" for="pwd-input">Mot de passe : </label>
        <input name="password" class="form-input" type="password" id="pwd-input" placeholder="Mot de passe" />
    </div>

    <div class="form-group">
        <input type="submit" class="btn btn-primary" name="sbm" value="S'inscrire">
    </div>

</form>

<jsp:include page="common/footer.jsp"/>
