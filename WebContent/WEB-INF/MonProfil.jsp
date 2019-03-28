<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Mon Profil</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/Secure/ServletProfil" method="post">
		<div class="container">
	  		<h1>TrocEncheres.org</h1>
	  		<h2>Mon Profil</h2>
	  		<div>
	  			<label for="pseudo">Pseudo : </label>
	  			<input type="text" name="pseudo" value="${utilisateur.pseudo}" required>
	  		</div>
	  		<div>
	  			<label for="nom">Nom : </label>
	  			<input type="text" name="nom" value="${utilisateur.nom}" required>
	  		</div>
	  		<div>
	  			<label for="prenom">Prénom :</label>
	  			<input type="text" name="prenom" value="${utilisateur.prenom}" required>
	  		</div>
	  		<div>
	  			<label for="email">Email :</label>
	  			<input type="text" name="email" value="${utilisateur.email}" required>
	  		</div>
	  		<div>
	  			<label for="telephone">Téléphone : </label>
	  			<input type="text" name="telephone" value="${utilisateur.telephone}" required>
	  		</div>
	  		<div>
	  			<label for="rue">Rue : </label>
	  			<input type="text" name="rue" value="${utilisateur.rue}" required>
	  		</div>
	  		<div>
	  			<label for="codePostal">Code postal : </label>
	  			<input type="text" name="codePostal" value="${utilisateur.codePostal}" required>
	  		</div>
	  		<div>
	  			<label for="ville">Ville : </label>
	  			<input type="text" name="ville" value="${utilisateur.ville}" required>
	  		</div>
	  		<div>
	  			<label for="credit">Crédit : </label>
	  			<span>${utilisateur.credit}</span>
	  		</div>
	  		<div>
	  			<label for="motDePasse">Mot de passe :</label>
			    <input type="password" name="motDePasse" required>
	  		</div>
	  		<div>
			    <label for="confirmation">Confirmation :</label>
			    <input type="password" name="confirmation" required>
			</div>
		</div>
		<div class="container">
			<button name="enregistrer" type="submit">Enregistrer</button>
			<button name="supprimer">Supprimer mon compte</button>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button name="retour">Retour</button></a>
</body>
</html>