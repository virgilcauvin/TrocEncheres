<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/listeencheres.css">
<title>Detail ma vente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
				<div class="col-12">
					<p>page détail ma vente</p>
				</div>
				<div class="col-12" id="article">${requestScope.nomArticle}</div>
				<div class="col-12 text-center">
					<img alt="truc" src="${requestScope.photo}">
				</div>
				<div class="col-12 m-1">Meilleure offre : ${requestScope.meilleureOffre} pts par 
					${requestScope.meilleurEncherisseur}</div>
				<div class="col-12 m-1">Mise à prix : ${requestScope.miseAPrix} points</div>
				<div class="col-12 m-1">Fin de l'enchère : ${requestScope.dateFinEchere}</div>
				<div class="col-3">Retrait : </div>
				<div class="col-7">${requestScope.rue}<br>${requestScope.codePostal} ${requestScope.ville}</div>
				<div class="col-12 m-1">Vendeur : ${requestScope.vendeur}</div>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button
			name="retour" id="retour">Retour</button></a>
</body>
</html>