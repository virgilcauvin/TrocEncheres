<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE>
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/detailvente.css">
<head>
<meta charset="utf-8">
<title>Détail Vente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<h2 class="">Détail Vente</h2>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-12" id="article">${requestScope.nomArticle}</div>
			<div class="col-12 text-center">
				<img alt="truc" src="${requestScope.photo}">
			</div>
			<div class="col-12 m-1">Meilleure offre : ${requestScope.meilleureOffre}
				${requestScope.meilleurEncherisseur}</div>
			<div class="col-12 m-1">Mise à prix : ${requestScope.miseAPrix} points</div>
			<div class="col-12 m-1">Fin de l'enchère : ${requestScope.dateFinEchere}</div>
			<div class="col-3">Retrait : </div>
			<div class="col-7">${requestScope.rue}</br>${requestScope.codePostal} ${requestScope.ville}</div>
			<div class="col-12 m-1">Vendeur : ${requestScope.vendeur}</div>
		</div>
		<div class="col-12 m-1">Montant de votre enchère précédente : ${requestScope.montantEcnherePrecedent}</div>
		<div class="row mt-5">
			<form class="col-12" action="${pageContext.request.contextPath}/Secure/ServletEnchere" method="post">
				<span class="col-5">Ma proposition :
				</span>
				<span class="col-4">
					<input type="number"
						value="${requestScope.enchereMin}"
						name="enchere"
						min="${requestScope.enchereMin}"
						max="${requestScope.enchereMax}">
				</span>
				<div>
					<button class="col-12 mt-5" name="bouton" id="encherir" value="encherir">Enchérir</button>
				</div>
			</form>
		</div>
	</div>
	<div class="container">
		<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button class="col-12 mt-5 mb-5" name="retour" id="retour" value="retour">Retour</button></a>				
	</div>
</body>
</html>