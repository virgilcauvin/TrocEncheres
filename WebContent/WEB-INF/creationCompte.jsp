<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/creation.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Créer un compte</p>
			</div>
		</div>
	</div>
	<div class="container">
		<form action="${pageContext.request.contextPath}/ServletCreationCompte" method="post">
			<div class="row">
				<label for="pseudo" class="col-sm-4">Pseudo :</label>
				<input type="text" name="pseudo" class="col-sm-6" placeholder="Votre pseudo" autofocus required value="${pseudoValide}">
			</div>
			<div class="erreur">${pseudoMessageErreur}</div>
			<div class="row">
				<label for="nom" class="col-sm-4">Nom :</label>
				<input type="text" name="nom" class="col-sm-6" placeholder="Votre nom" required value="${nomValide}">
			</div>
			<div class="erreur">${nomMessageErreur}</div>
			<div class="row">
				<label for="prenom" class="col-sm-4">Prénom :</label>
				<input type="text" name="prenom" class="col-sm-6" placeholder="Votre prénom" required value="${prenomValide}">
			</div>
			<div class="erreur">${prenomMessageErreur}</div>
			<div class="row">
				<label for="email" class="col-sm-4">Email :</label>
				<input type="email" name="email" class="col-sm-6" placeholder="Votre email" required value="${emailValide}">
			</div>
			<div class="erreur">${emailMessageErreur}</div>
			<div class="row">
				<label for="telephone" class="col-sm-4">Téléphone :</label>
				<input type="tel" name="telephone" class="col-sm-6" placeholder="Votre téléphone" required value="${telephoneValide}">
			</div>
			<div class="erreur">${telephoneMessageErreur}</div>
			<div class="row">
				<label for="rue" class="col-sm-4">Rue :</label>
				<input type="text" name="rue" class="col-sm-6" placeholder="Votre rue" required value="${rueValide}">
			</div>
			<div class="erreur">${rueMessageErreur}</div>
			<div class="row">
				<label for="codePostal" class="col-sm-4">Code postal :</label>
				<input type="text" name="codePostal" class="col-sm-6" placeholder="Votre code postal" required value="${codePostalValide}">
			</div>
			<div class="erreur">${codePostalMessageErreur}</div>
			<div class="row">
				<label for="ville" class="col-sm-4">Ville :</label>
				<input type="text" name="ville" class="col-sm-6" placeholder="Votre ville" required value="${villeValide}">
			</div>
			<div class="erreur">${villeMessageErreur}</div>
			<div class="row">
				<label for="motDePasse" class="col-sm-4">Mot de passe :</label>
				<input type="password" name="motDePasse" class="col-sm-6" placeholder="Votre mot de passe" required value="${motDePasseValide}">
			</div>
			<div class="erreur">${motDePasseMessageErreur}</div>
			<div class="row">
				<label for="confirmation" class="col-sm-4">Confirmation :</label>
				<input type="password" name="confirmation" class="col-sm-6" placeholder="Votre mot de passe" required value="${confirmationMDPValide}">
			</div>
			<div class="erreur">${confirmationMDPMessageErreur}</div>
			<div>
				<button type="submit" class="col-12 mt-5" id="creer">Créer</button>
			</div>
		</form>
	</div>
	<div class="container"> 
			<a href="${pageContext.request.contextPath}/ServletConnexionCompte"><button class="col-12 mt-5 mb-5" id="annuler">Annuler</button></a>
	</div>
</body>
</html>