<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Vous avez remporté l'enchère</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	<link rel="stylesheet" href="/TrocEncheres/css/gagne.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Enchères remportées</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
		<div class="col-12 m-3">Vous avez remporté l'enchère :</div>
		<div class="col-12 m-3">${requestScope.nomArticle}</div>
		<div class="col-12 m-3 text-center">
			<img src="${requestScope.photo}">
		</div>
		<div class="col-12 m-3">Meilleure offre : ${requestScope.meilleureOffre} points</div>
		<div class="col-12 m-3">mise à prix : ${requestScope.miseAPrix} points</div>
		<div class="col-12 m-3">Retrait :</div>
		<div class="col-12 m-3">${requestScope.rue}</div>
		<div class="col-12 m-3">${requestScope.codePostal}</div>
		<div class="col-12 m-3">${requestScope.ville}</div>
		<div class="col-12 m-3">Vendeur : 
			<a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${requestScope.pseudo}&venteValidee">${requestScope.pseudo}</a>
		</div>
		
		<div class="col-12 m-3">Téléphone : ${requestScope.telephone}</div>
		</div>
		<div class="container">
			<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button
				class="col-12 mb-5 mt-5" id="retour" name="retour">Retour</button></a>
		</div>
	</div>
</body>
</html>