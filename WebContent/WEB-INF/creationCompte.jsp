<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Créer un compte</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
	<main class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1 class="display-4">TrocEncheres.org</h1>
		    <p class="lead">Créer un compte</p>
		  </div>
		</div>
		
		
			<form action="${pageContext.request.contextPath}/ServletCreationCompte" method="post">
			  <div class="row">
			  	<span class="col-sm-1"></span>
			    <label for="pseudo" class="col-sm-4">Pseudo :</label>
			    <input type="text" name="pseudo" class="col-sm-6" placeholder="Votre pseudo" autofocus required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="nom" class="col-sm-4">Nom :</label>
			    <input type="text" name="nom" class="col-sm-6" placeholder="Votre nom" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="prenom" class="col-sm-4">Prénom :</label>
			    <input type="text" name="prenom" class="col-sm-6" placeholder="Votre prénom" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="email" class="col-sm-4">Email :</label>
			    <input type="text" name="email" class="col-sm-6" placeholder="Votre email" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="telephone" class="col-sm-4">Téléphone :</label>
			    <input type="text" name="telephone" class="col-sm-6" placeholder="Votre téléphone" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="rue" class="col-sm-4">Rue :</label>
			    <input type="text" name="rue" class="col-sm-6" placeholder="Votre rue" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="codePostal" class="col-sm-4">Code postal :</label>
			    <input type="text" name="codePostal" class="col-sm-6" placeholder="Votre code postal" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="ville" class="col-sm-4">Ville :</label>
			    <input type="text" name="ville" class="col-sm-6" placeholder="Votre ville" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="motDePasse" class="col-sm-4">Mot de passe :</label>
			    <input type="password" name="motDePasse" class="col-sm-6" placeholder="Votre mot de passe" required>
			  </div>
			  <br>
			  <div class="row">
			    <span class="col-sm-1"></span>
			    <label for="confirmation" class="col-sm-4">Confirmation :</label>
			    <input type="password" name="confirmation" class="col-sm-6" placeholder="Votre mot de passe" required>
			  </div>
			  <br>
			  
			  	<span class="col-sm-2"></span>
			  	<span class="col-sm-4">
		  			<button type="submit" class="btn btn-primary" style="width:100px">Créer</button>
			  	</span>
			  	
			  
			</form>
				<span class="col-sm-4">
			  		<a href="${pageContext.request.contextPath}/ServletConnexionCompte"><button class="btn btn-primary" style="width:100px">Annuler</button></a>
			  	</span>
			  	<span class="col-sm-2"></span>
			  
		
	</main>
</body>
</html>