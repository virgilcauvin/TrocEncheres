<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="../css/profil.css">
<title>Mon Profil</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Mon Profil</p>
			</div>
		</div>
	</div>
	<form action="${pageContext.request.contextPath}/Secure/ServletProfil" method="post">
		<div class="container">
	  		<div class="row">
	  			<label for="pseudo" class="col-sm-4">Pseudo : </label>
	  			<input type="text" name="pseudo" class="col-sm-6" value="${!empty pseudoValide?pseudoValide:utilisateur.pseudo}" required>
	  		</div>
	  		<div>${pseudoMessageErreur}</div>
	  		<div class="row">
	  			<label for="nom" class="col-sm-4">Nom : </label>
	  			<input type="text" name="nom" class="col-sm-6" value="${!empty nomValide?nomValide:utilisateur.nom}" required>
	  		</div>
	  		<div>${nomMessageErreur}</div>
	  		<div class="row">
	  			<label for="prenom" class="col-sm-4">Prénom :</label>
	  			<input type="text" name="prenom" class="col-sm-6" value="${!empty prenomValide?prenomValide:utilisateur.prenom}" required>
	  		</div>
	  		<div>${prenomMessageErreur}</div>
	  		<div class="row">
	  			<label for="email" class="col-sm-4">Email :</label>
	  			<input type="email" name="email" class="col-sm-6" value="${!empty emailValide?emailValide:utilisateur.email}" required>
	  		</div>
	  		<div>${emailMessageErreur}</div>
	  		<div class="row">
	  			<label for="telephone" class="col-sm-4">Téléphone : </label>
	  			<input type="tel" name="telephone" class="col-sm-6" value="${!empty telephoneValide?telephoneValide:utilisateur.telephone}" required>
	  		</div>
	  		<div>${telephoneMessageErreur}</div>
	  		<div class="row">
	  			<label for="rue" class="col-sm-4">Rue : </label>
	  			<input type="text" name="rue" class="col-sm-6" value="${!empty rueValide?rueValide:utilisateur.rue}" required>
	  		</div>
	  		<div>${rueMessageErreur}</div>
	  		<div class="row">
	  			<label for="codePostal" class="col-sm-4">Code postal : </label>
	  			<input type="text" name="codePostal" class="col-sm-6" value="${!empty codePostalValide?codePostalValide:utilisateur.codePostal}" required>
	  		</div>
	  		<div>${codePostalMessageErreur}</div>
	  		<div class="row">
	  			<label for="ville" class="col-sm-4">Ville : </label>
	  			<input type="text" name="ville" class="col-sm-6" value="${!empty villeValide?villeValide:utilisateur.ville}" required>
	  		</div>
	  		<div>${villeMessageErreur}</div>
	  		<div class="row">
	  			<label for="credit" class="col-sm-4">Crédit : </label>
	  			<span id="credit">${!empty credit?credit:utilisateur.credit}</span>
	  		</div>
	  		<div class="row">
	  			<label for="motDePasse" class="col-sm-4">Mot de passe :</label>
			    <input type="password" name="motDePasse" class="col-sm-6" required value="${!empty motDePasseValide?motDePasseValide:utilisateur.motDePasse}">
	  		</div>
	  		<div>${motDePasseMessageErreur}</div>
	  		<div class="row">
			    <label for="confirmation" class="col-sm-4">Confirmation :</label>
			    <input type="password" name="confirmation" class="col-sm-6" required value="${!empty confirmationMDPValide?confirmationMDPValide:utilisateur.motDePasse}">
			</div>
			<div>${confirmationMDPMessageErreur}</div>
		</div>
		<div class="container">
			<button name="enregistrer" id="enregistrer" type="submit">Enregistrer</button>
			<button name="supprimer" id="supprimer">Supprimer mon compte</button>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button name="retour" id="retour">Retour</button></a>
</body>
</html>