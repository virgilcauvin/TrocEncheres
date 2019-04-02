<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cr�er un compte</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/creation.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Cr�er un compte</p>
			</div>
		</div>
	</div>
	<div class="container">
		<form action="${pageContext.request.contextPath}/ServletCreationCompte" method="post">
			<div class="row">
				<label for="pseudo" class="col-sm-4">Pseudo :</label>
				<input type="text" name="pseudo" class="col-sm-6" placeholder="Votre pseudo" autofocus required value="${pseudoValide}">
			</div>
			<div>${pseudoMessageErreur}</div>
	
			<div class="row">
				<label for="nom" class="col-sm-4">Nom :</label>
				<input type="text" name="nom" class="col-sm-6" placeholder="Votre nom" required value="${nomValide}">
			</div>
			<div>${nomMessageErreur}</div>
			<div class="row">
				<label for="prenom" class="col-sm-4">Pr�nom :</label>
				<input type="text" name="prenom" class="col-sm-6" placeholder="Votre pr�nom" required value="${prenomValide}">
			</div>
			<div>${prenomMessageErreur}</div>
			<div class="row">
				<label for="email" class="col-sm-4">Email :</label>
				<input type="email" name="email" class="col-sm-6" placeholder="Votre email" required value="${emailValide}">
			</div>
			<div>${emailMessageErreur}</div>
			<div class="row">
				<label for="telephone" class="col-sm-4">T�l�phone :</label>
				<input type="tel" name="telephone" class="col-sm-6" placeholder="Votre t�l�phone" required value="${telephoneValide}">
			</div>
			<div>${telephoneMessageErreur}</div>
			<div class="row">
				<label for="rue" class="col-sm-4">Rue :</label>
				<input type="text" name="rue" class="col-sm-6" placeholder="Votre rue" required value="${rueValide}">
			</div>
			<div>${rueMessageErreur}</div>
			<div class="row">
				<label for="codePostal" class="col-sm-4">Code postal :</label>
				<input type="text" name="codePostal" class="col-sm-6" placeholder="Votre code postal" required value="${codePostalValide}">
			</div>
			<div>${codePostalMessageErreur}</div>
			<div class="row">
				<label for="ville" class="col-sm-4">Ville :</label>
				<input type="text" name="ville" class="col-sm-6" placeholder="Votre ville" required value="${villeValide}">
			</div>
			<div>${villeMessageErreur}</div>
			<div class="row">
				<label for="motDePasse" class="col-sm-4">Mot de passe :</label>
				<input type="password" name="motDePasse" class="col-sm-6" placeholder="Votre mot de passe" required value="${motDePasseValide}">
			</div>
			<div>${motDePasseMessageErreur}</div>
			<div class="row">
				<label for="confirmation" class="col-sm-4">Confirmation :</label>
				<input type="password" name="confirmation" class="col-sm-6" placeholder="Votre mot de passe" required value="${confirmationMDPValide}">
			</div>
			<div>${confirmationMDPMessageErreur}</div>
			<span class="col-4 offset-1">
				<button type="submit" class="btn btn-primary" id="creer">Cr�er</button>
			</span>
		</form>
		<span class="col-4"> 
			<a href="${pageContext.request.contextPath}/ServletConnexionCompte"><button class="btn btn-primary" id="annuler">Annuler</button></a>
		</span>
	</div>
</body>
</html>