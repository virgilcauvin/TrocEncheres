<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/nouvelleVente.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle Vente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Nouvelle vente</p>
			</div>
		</div>
	</div>
	<div>
		<h3 class="erreur text-center">${requestScope.messageErreurArticle}</h3>
		<h3 class="erreur text-center">${requestScope.messageErreurCategorie}</h3>
		<h3 class="erreur text-center">${requestScope.messageErreurDescription}</h3>
		<h3 class="erreur text-center">${requestScope.messageErreurFinEnchere}</h3>
		<h3 class="erreur text-center">${requestScope.messageErreurPrixPositif}</h3>
		<h3 class="erreur text-center">${requestScope.messageErreurPrix}</h3>
	</div>
	<form action="${pageContext.request.contextPath}/Secure/ServletVendre"
		method="post">
		<div class="container">
			<div class="row">
				<label class="col-3">Catégories</label>
				<select class="col-7"
					id="pet-select" name="libelle">
					<c:forEach var="categorie" items="${sessionScope.listeCategories}">
						<c:choose>
							<c:when test="${sessionScope.libelle == categorie.libelle}">
								<option value="${categorie.libelle}" selected>${categorie.libelle}</option>
							</c:when>
							<c:otherwise>
								<option value="${categorie.libelle}">${categorie.libelle}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>

			<div class="row">
				<label name="article" class="col-3">Article : </label> <input
					class="col-7" required type="text" name="article"
					value="${sessionScope.article}">
			</div>
			<div class="row">
				<label class="col-4" for="exampleFormControlTextarea1"
					name="descritpion" id="description">Description :</label>
				<textarea class="col-6" required id="exampleFormControlTextarea1"
						name="descritpion">${sessionScope.descritpion}</textarea>
				
			</div>
			<div class="row">
				<label class="col-4" name="lienPhoto">Lien vers la photo : </label>
				<input class="col-6" type="text" name="lienPhoto"
					value="${sessionScope.lienPhoto}">
			</div>
			<div class="row">
				<label class="col-12" name="photo">Photo de l'article :</label>
				 <img src="${sessionScope.lienPhoto}">
			</div>
			<div class="row">
				<label class="col-3" name="prixInitial">Prix initial : </label> <input
					class="col-7" required value="${sessionScope.prixInitial}"
					type="number" value="0" min="1" max="10000" name="prixInitial">
			</div>
			<div class="row">
				<label class="col-5" name="finEnchere">Fin de l'enchère : </label> <input
					class="col-5" required value="${sessionScope.finEchere}"
					type="date" name="finEnchere">
			</div>
			<div class="row">
				<label class="col-3" name="adresse">Retrait : </label> <span
					id="adresse" class="col-7">${requestScope.rue}
					${requestScope.codePostal} ${requestScope.ville}</span>
			</div>
		</div>
		<div class="container">
			<button class="col-12 mt-2" name="bouton" id="publier" value="publier">Publier</button>
			<button class="col-12 mt-4" name="bouton" id="enregistrer" value="enregistrer">Enregistrer</button>
		</div>
	</form>
	<div class="container">
		<a href="${pageContext.request.contextPath}/Secure/ServletAccueil">
			<button class="col-12 mb-5" name="annuler" id="annuler" value="annuler">Annuler</button>
		</a>
	</div>
</body>
</html>