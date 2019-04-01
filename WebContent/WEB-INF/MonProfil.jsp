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
	  			<input type="text" name="pseudo" value="${!empty pseudoValide?pseudoValide:utilisateur.pseudo}" required>
	  		</div>
	  		<div>${pseudoMessageErreur}</div>
	  		<div>
	  			<label for="nom">Nom : </label>
	  			<input type="text" name="nom" value="${!empty nomValide?nomValide:utilisateur.nom}" required>
	  		</div>
	  		<div>${nomMessageErreur}</div>
	  		<div>
	  			<label for="prenom">Prénom :</label>
	  			<input type="text" name="prenom" value="${!empty prenomValide?prenomValide:utilisateur.prenom}" required>
	  		</div>
	  		<div>${prenomMessageErreur}</div>
	  		<div>
	  			<label for="email">Email :</label>
	  			<input type="email" name="email" value="${!empty emailValide?emailValide:utilisateur.email}" required>
	  		</div>
	  		<div>${emailMessageErreur}</div>
	  		<div>
	  			<label for="telephone">Téléphone : </label>
	  			<input type="tel" name="telephone" value="${!empty telephoneValide?telephoneValide:utilisateur.telephone}" required>
	  		</div>
	  		<div>${telephoneMessageErreur}</div>
	  		<div>
	  			<label for="rue">Rue : </label>
	  			<input type="text" name="rue" value="${!empty rueValide?rueValide:utilisateur.rue}" required>
	  		</div>
	  		<div>${rueMessageErreur}</div>
	  		<div>
	  			<label for="codePostal">Code postal : </label>
	  			<input type="text" name="codePostal" value="${!empty codePostalValide?codePostalValide:utilisateur.codePostal}" required>
	  		</div>
	  		<div>${codePostalMessageErreur}</div>
	  		<div>
	  			<label for="ville">Ville : </label>
	  			<input type="text" name="ville" value="${!empty villeValide?villeValide:utilisateur.ville}" required>
	  		</div>
	  		<div>${villeMessageErreur}</div>
	  		<div>
	  			<label for="credit">Crédit : </label>
	  			<span>${!empty credit?credit:utilisateur.credit}</span>
	  		</div>
	  		<div>
	  			<label for="motDePasse">Mot de passe :</label>
			    <input type="password" name="motDePasse" required value="${!empty motDePasseValide?motDePasseValide:utilisateur.motDePasse}">
	  		</div>
	  		<div>${motDePasseMessageErreur}</div>
	  		<div>
			    <label for="confirmation">Confirmation :</label>
			    <input type="password" name="confirmation" required value="${!empty confirmationMDPValide?confirmationMDPValide:utilisateur.motDePasse}">
			</div>
			<div>${confirmationMDPMessageErreur}</div>
		</div>
		<div class="container">
			<button name="enregistrer" type="submit">Enregistrer</button>
			<button name="supprimer">Supprimer mon compte</button>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/Secure/ServletAccueil"><button name="retour">Retour</button></a>
</body>
</html>