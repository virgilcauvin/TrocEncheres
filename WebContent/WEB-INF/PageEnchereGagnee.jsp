<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vous avez remporté l'enchère</title>.
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class="col-12 m-3">Vous avez remporté l'enchère :</div>

	<div class="col-12 m-3">${requestScope.nomArticle}</div>
	<div class="col-12 m-3">
		<img src="${requestScope.photo}">
	</div>
	<div class="col-12 m-3">Meilleure offre :
		${requestScope.meilleureOffre}</div>
	<div class="col-12 m-3">mise à prix : ${requestScope.miseAPrix}</div>
	<div class="col-12 m-3">${requestScope.rue}</div>
	<div class="col-12 m-3">${requestScope.codePostal}</div>
	<div class="col-12 m-3">${requestScope.ville}</div>
	<div class="col-12 m-3">Vendeur : ${requestScope.ville}</div>
	<div class="col-12 m-3">Téléphone : ${requestScope.telephone}</div>

	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button
			class="m-5" id="retour" name="retour">Retour</button></a>
</body>
</html>