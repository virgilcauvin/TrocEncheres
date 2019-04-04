<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Page Connexion</title>
<link rel="stylesheet" href="/TrocEncheres/css/connexion.css">
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Identification</p>
			</div>
		</div>
	</div>
	<c:if test="${sessionScope.compteCree}">
		<div>
			<p class="text-center m-2">Votre compte est créé : vous pouvez vous connecter.</p>
		</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/ServletConnexionCompte"method="post">
		<div class="container" id="logPass">
			<div class="row">
				<div class="col-5 m-1">
					<p>Identifiant :
				</div>
				<div class="col-6">
					<input name="identifiant" type="text" placeholder="Votre identifiant ici" value="${pseudo}">
				</div>
			</div>
			<div class="row">
				<div class="col-5 m-1">
					<p>Mot de passe :
				</div>
				<div class="col-6">
					<input name="password" type="password" placeholder="Votre mot de passe ici" value="${motDePasse}">
				</div>
			</div>
		</div>
		<div class="text-center m-2" id="erreur">${messageErreurConnexion}</div>
		<div class="container">
			<div class="row">
				<div class="col-5 offset-1">
					<button type="submit" id="bouton-connexion">Connexion</button>
				</div>
				<div class="col-12 col-md-6">
					<div class="container">
						<div class="row">
							<input disabled type="checkbox" id="check"> <label for="check">Se souvenir de moi</label>
						</div>
						<div class="row">
							<a  href="">Mot de passe oublié</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div class="container">
		<div class="row">
			<a href="${pageContext.request.contextPath}/ServletCreationCompte"><button type="button" class="col-12" id="bouton-creer">Créer un compte</button></a>
		</div>
	</div>
</body>
</html>