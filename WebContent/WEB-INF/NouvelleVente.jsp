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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nouvelle Vente</title>
</head>
<body>
	<main class="container-fluid">
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">TrocEncheres.org</h1>
			<p class="lead">Nouvelle vente</p>
		</div>
	</div>
	<form action="${pageContext.request.contextPath}/Secure/ServletVendre"
		method="post">
		<div class="container">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<label class="input-group-text" for="inputGroupSelect01"
						name="libelle">Choisissez une catégorie</label>
				</div>
				<select class="custom-select" id="inputGroupSelect01" name="libelle"
					 required>
					<option selected>${sessionScope.libelle}</option>
					<option value="animaux">Animaux</option>
					<option value="vehicule">Véhicules</option>
					<option value="figurine">Figurines</option>
				</select>
			</div>
			<div class="row">
				<div class="form-group">
					<label name="article">Article : </label> <input required
						type="text" name="article" value="${sessionScope.article}">
				</div>
			</div>

			<div class="form-group">
				<label for="exampleFormControlTextarea1" name="descritpion">Description
					:</label>
				<textarea required class="form-control"
					id="exampleFormControlTextarea1" rows="3" name="descritpion"
					>${sessionScope.descritpion}</textarea>
			</div>

			<div class="form-group">
				<label name="lienPhoto">Lien vers la photo : </label> <input
					type="text" name="lienPhoto" value="${sessionScope.lienPhoto}">
			</div>
			<div>
				<label name="photo">Photo de l'article :</label> <img
					src="${sessionScope.lienPhoto}">
			</div>

			<div>
				<label name="prixInitial">Prix initial : </label> <input required
					value="${sessionScope.prixInitial}" type="number" value="0" min="1"
					max="10000" name="prixInitial">
			</div>
			<div>
				<label name="finEnchere">Fin de l'enchère : </label> <input required
					value="${sessionScope.finEchere}" type="date" name="finEnchere">
			</div>
			<div>
				<label name="adresse">Retrait : </label> ${requestScope.rue}
				${requestScope.codePostal} ${requestScope.ville}
			</div>
		</div>
		<div class="container">
			<button name="bouton" value="publier">Publier</button>
			<button name="bouton" value="enregistrer">Enregistrer</button>
			<button name="annuler" value="annuler">Annuler</button>
		</div>
	</form>
</body>
</html>