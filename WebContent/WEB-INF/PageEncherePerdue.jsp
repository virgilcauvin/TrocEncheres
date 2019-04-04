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
<link rel="stylesheet" href="/TrocEncheres/css/enchereperdu.css">
<title>Page Enchere Perdue</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Enchères perdues</p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-12 m-3">La vente s'est terminée le ${requestScope.dateFinEncheres}.</div>
			<div class="col-12 m-3">
				<c:choose>
					<c:when test="${requestScope.classement==1}">
						Vous avez fini à la ${requestScope.classement}ère place
					</c:when>
					<c:otherwise>
						Vous avez fini à la ${requestScope.classement}ème place
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-12 m-3">${requestScope.nomArticle}</div>
			<div class="col-12 m-3 text-center">
				<img src="${requestScope.photo}">
			</div>
			<div class="container">
				<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button
					class="col-12 mb-5 mt-5" id="retour" name="retour">Retour</button></a>
			</div>
		</div>
	</div>
</body>
</html>