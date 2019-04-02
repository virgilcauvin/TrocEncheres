<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/nouvelleVente.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nouvelle Vente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Nouvelle vente</p>
			</div>
		</div>
	</div>
	<div>
		<h3 class="text-center">${requestScope.messageErreurArticle}</h3>
		<h3 class="text-center">${requestScope.messageErreurCategorie}</h3>
		<h3 class="text-center">${requestScope.messageErreurDescription}</h3>
		<h3 class="text-center">${requestScope.messageErreurFinEnchere}</h3>
		<h3 class="text-center">${requestScope.messageErreurPrixPositif}</h3>
		<h3 class="text-center">${requestScope.messageErreurPrix}</h3>
	</div>
	<form action="${pageContext.request.contextPath}/Secure/ServletVendre" method="post">
		<div class="container">
			<div class="row">
				<label class="col-3" for="inputGroupSelect01" name="libelle" id="libelle">Catégorie</label>
				<select class="col-7 custom-select" id="inputGroupSelect01" name="libelle"
					required>
					<option selected>${sessionScope.libelle}</option>
					<option value="animaux">Animaux</option>
					<option value="vehicule">Véhicules</option>
					<option value="figurine">Figurines</option>
				</select>
			</div>
			<div class="row">
				<label name="article" class="col-3">Article : </label>
				<input class="col-7" required type="text" name="article" value="${sessionScope.article}">
			</div>
			<div  class="row">
				<label  class="col-4" for="exampleFormControlTextarea1" name="descritpion" id="description">Description :</label>
				<span class="container"><textarea required id="exampleFormControlTextarea1" name="descritpion">${sessionScope.descritpion}</textarea></span>
			</div>
			<div class="row">
				<label class="col-5" name="lienPhoto">Lien vers la photo : </label>
				<input class="col-5" type="text" name="lienPhoto" value="${sessionScope.lienPhoto}">
			</div>
			<div  class="row">
				<label class="col-12" name="photo">Photo de l'article :</label>
				<img src="${sessionScope.lienPhoto}">
			</div>
			<div  class="row">
				<label class="col-3" name="prixInitial">Prix initial : </label>
				<input class="col-7" required value="${sessionScope.prixInitial}" type="number" value="0" min="1" max="10000" name="prixInitial">
			</div>
			<div  class="row">
				<label class="col-5" name="finEnchere">Fin de l'enchère : </label>
				<input class="col-5" required value="${sessionScope.finEchere}" type="date" name="finEnchere">
			</div>
			<div class="row">
				<label class="col-3" name="adresse">Retrait : </label> ${requestScope.rue} ${requestScope.codePostal} ${requestScope.ville}
			</div>
		</div>
		<div class="container">
			<button name="bouton" id="publier" value="publier">Publier</button>
			<button name="bouton" id="enregistrer" value="enregistrer">Enregistrer</button>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil">
		<button name="annuler" id="annuler" value="annuler">Annuler</button>
	</a>
</body>
</html>