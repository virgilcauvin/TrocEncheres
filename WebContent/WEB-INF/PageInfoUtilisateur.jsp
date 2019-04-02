<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Info Utilisateur</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<body>
	<div>Pseudo : ${requestScope.pseudo}</div>
	<div>Nom : ${requestScope.nom}</div>
	<div>Prénom : ${requestScope.prenom}</div>
	<div>Email : ${requestScope.email}</div>
	<div>Téléphone : ${requestScope.telephone}</div>
	<div>Rue : ${requestScope.rue}</div>
	<div>Code postal : ${requestScope.codePostal}</div>
	<div>Ville : ${requestScope.ville}</div>
	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button
			name="retour">Retour</button></a>
</body>
</html>