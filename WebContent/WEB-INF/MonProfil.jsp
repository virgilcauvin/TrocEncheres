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
	<form action="/Secure/ServletProfil" method="post">
		<div class="container">
	  		<h1>TrocEncheres.org</h1>
	  		<h2>Mon Profil</h2>
	  		<div>
	  			<label for="pseudo">Pseudo : </label>
	  			<input type="text" name="pseudo" placeholder="${utilisateur.pseudo}" required>
	  		</div>
	  		<div>
	  			<label for="nom">Nom : </label>
	  			<input type="text" name="nom" placeholder="${utilisateur.nom}" required>
	  		</div>
	  		<div>
	  			<label for="prenom">Prénom :</label>
	  			<input type="text" name="prenom" placeholder="${utilisateur.prenom}" required>
	  		</div>
	  		<div>
	  			<label for="email">Email :</label>
	  			<input type="text" name="email" placeholder="${utilisateur.email}" required>
	  		</div>
	  		<div>
	  			<label for="telephone">Téléphone : </label>
	  			<input type="text" name="telephone" placeholder="${utilisateur.telephone}" required>
	  		</div>
	  		<div>
	  			<label for="rue">Rue : </label>
	  			<input type="text" name="rue" placeholder="${utilisateur.rue}" required>
	  		</div>
	  		<div>
	  			<label for="codePostal">Code postal : </label>
	  			<input type="text" name="codePostal" placeholder="${utilisateur.codePostal}" required>
	  		</div>
	  		<div>
	  			<label for="ville">Ville : </label>
	  			<input type="text" name="ville" placeholder="${utilisateur.ville}" required>
	  		</div>
	  		<div>
	  			<label for="credit">Crédit : </label>
	  			<input type="text" name="credit" placeholder="${utilisateur.credit}" required>
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
			<button name="retour">Retour</button>
		</div>
	</form>
</body>
</html>