<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Info Utilisateur</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/infoutilisateur.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Informations profil</p>
			</div>
		</div>
	</div>
	<div class="text-center m-5">Profil de ${requestScope.pseudo}</div>
	<div class="container">
		<div class="row">
			<div class="col-12 m-3">Pseudo : ${requestScope.pseudo}</div>
			<div class="col-12 m-3">Nom : ${requestScope.nom}</div>
			<div class="col-12 m-3">Prénom : ${requestScope.prenom}</div>
			<div class="col-12 m-3">Email : ${requestScope.email}</div>
			<div class="col-12 m-3">Téléphone : ${requestScope.telephone}</div>
			<div class="col-12 m-3">Rue : ${requestScope.rue}</div>
			<div class="col-12 m-3">Code postal : ${requestScope.codePostal}</div>
			<div class="col-12 m-3">Ville : ${requestScope.ville}</div>
		</div>
	</div>
	<div class="container">
		<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button class="col-12 mt-5 mb-5"id="retour" name="retour">Retour</button></a>
	</div>
	
</body>
</html>