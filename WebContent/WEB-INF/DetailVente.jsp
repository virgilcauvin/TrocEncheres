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
<link rel="stylesheet" href="../css/detailvente.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Détail Vente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Détail Vente</p>
			</div>
		</div>
	</div>
	<div>${requestScope.nomArticle}</div>
	<div>${requestScope.photo}</div>
	<div>Meilleure offre : ${requestScope.meilleureOffre}
		${requestScope.meilleurEncherisseur}</div>
	<div>Mise à prix : ${requestScope.miseAPrix}</div>
	<div>Fin de l'enchère : ${requestScope.dateFinEchere}</div>
	<div>${requestScope.rue}</div>
	<div>${requestScope.codePostal}${requestScope.ville}</div>
	<div>Vendeur : ${requestScope.vendeur}</div>
	<form action="${pageContext.request.contextPath}/Secure/ServletEnchere"
		method="post">
		<div>Ma proposition :</div>
		<div class="form-group row">
			<label for="enchereEntree" class="col-2 col-form-label">mon
				enchère</label>
			<div class="col-10">
				<input class="form-control" type="number"
					value="<%int enchereMin = (int) request.getAttribute("enchereMin");%>"
					name="enchere"
					min="<%enchereMin = (int) request.getAttribute("enchereMin");%>"
					max="<%int enchereMax = (int) request.getAttribute("enchereMax");%>">
			</div>
		</div>
		<div>
			<button name="bouton" value="encherir">Enchérir</button>
		</div>
	</form>
	<form action="${pageContext.request.contextPath}/Secure/ServletAccueil"
		method="post">
		<button name="retour" value="retour">Retour</button>
	</form>
</body>
</html>