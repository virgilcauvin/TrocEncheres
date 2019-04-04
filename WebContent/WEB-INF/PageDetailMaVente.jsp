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
<link rel="stylesheet" href="/TrocEncheres/css/mavente.css">
<title>Detail ma vente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Détail de ma vente</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
				<div class="col-12 text-center">
					<c:if test="${requestScope.etatVente == 0}">
						<h2>${requestScope.meilleurEncherisseur} a remporté l'enchère</h2>
					</c:if>
				</div>
				<div class="col-12 mt-5" id="article"><h2>${requestScope.nomArticle}</h2></div>
				<div class="col-12 text-center">
					<img alt="truc" src="${requestScope.photo}">
				</div>
				<div class="col-12 m-1">Meilleure offre : 
					<c:choose>
						<c:when test="${empty requestScope.meilleureOffre}">
							-
						</c:when>
						<c:otherwise>
							${requestScope.meilleureOffre} pts par 
							<c:choose>
								<c:when test="${requestScope.etatVente == 0}">
									<a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${requestScope.meilleurEncherisseur}&venteValidee">${requestScope.meilleurEncherisseur}</a>
								</c:when>
								<c:otherwise>
									${requestScope.meilleurEncherisseur}
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					
					
				</div>
				<div class="col-12 m-1">Mise à prix : ${requestScope.miseAPrix} points</div>
				<div class="col-12 m-1">Fin de l'enchère : ${requestScope.dateFinEchere}</div>
				<div class="col-3">Retrait : </div>
				<div class="col-7">${requestScope.rue}<br>${requestScope.codePostal} ${requestScope.ville}</div>
				<div class="col-12 m-1">Vendeur : ${requestScope.vendeur}</div>
		</div>
	</div>
	<c:if test="${requestScope.etatVente == 0}">
	<div class="container">
		<a href="${pageContext.request.contextPath}/Secure/ServletAccueil?mesVentes">
		<button class="col-12 mt-5" id="retrait" name="retraitEffectue" id="retraitEffectue">Retrait effectué</button></a>
		<a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${requestScope.meilleurEncherisseur}&venteValidee">
		<button class="col-12 mt-5" id="contact" name="retraitEffectue" id="retraitEffectue">Contacter ${requestScope.meilleurEncherisseur}</button></a>
	</div>
	</c:if>
	<div class="container">
		<a href="${pageContext.request.contextPath}/Secure/ServletAccueil?mesVentes">
		<button class="col-12 mt-5 mb-5" name="retour" id="retour">Retour</button></a>
	</div>
</body>
</html>