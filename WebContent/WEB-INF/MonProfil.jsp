<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/profil.css">
<title>Mon Profil</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid" id="entete">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
				<p class="lead">Mon Profil</p>
			</div>
		</div>
	</div>
	<form action="${pageContext.request.contextPath}/Secure/ServletProfil"
		method="post">
		<div class="container">
			<div class="row">
				<label for="pseudo" class="col-md-3 col-12">Pseudo : </label> <input
					type="text" name="pseudo" class="col-md-5 col-12"
					value="${!empty pseudoValide?pseudoValide:utilisateur.pseudo}"
					required>
			</div>
			<div class="erreur">${pseudoMessageErreur}</div>
			<div class="row">
				<label for="nom" class="col-md-3 col-12">Nom : </label>
				<input type="text"
						name="nom" class="col-md-5 col-12"
						value="${!empty nomValide?nomValide:utilisateur.nom}" required>
				<div class="custom-control custom-switch col-2">
					<input type="checkbox" class="custom-control-input align-bottom" name="visionNom" id="customSwitch1"> 
					<label class="custom-control-label" for="customSwitch1">Visible</label>
				</div>
			</div>

			<div class="erreur">${nomMessageErreur}</div>
			<div class="row">
				<label for="prenom" class="col-md-3">Prénom :</label> <input
					type="text" name="prenom" class="col-md-5"
					value="${!empty prenomValide?prenomValide:utilisateur.prenom}"
					required>
				<div class="custom-control custom-switch col-2">
					<input type="checkbox" class="custom-control-input" name="visionPrenom" id="customSwitch2"> 
					<label class="custom-control-label" for="customSwitch2">Visible</label>
				</div>
			</div>

			<div class="erreur">${prenomMessageErreur}</div>
			<div class="row">
				<label for="email" class="col-md-3">Email :</label> <input
					type="email" name="email" class="col-md-5"
					value="${!empty emailValide?emailValide:utilisateur.email}"
					required>
				<div class="custom-control custom-switch col-2">
					<input type="checkbox" class="custom-control-input" name="visionEmail" id="customSwitch3"> 
					<label class="custom-control-label" for="customSwitch3">Visible</label>
				</div>
			</div>


			<div class="erreur">${emailMessageErreur}</div>
			<div class="row">
				<label for="telephone" class="col-md-3">Téléphone :</label> <input
					type="tel" name="telephone" class="col-md-5"
					value="${!empty telephoneValide?telephoneValide:utilisateur.telephone}"
					required>
				<div class="custom-control custom-switch col-2">
					<input type="checkbox" class="custom-control-input" name="visionTelephone" id="customSwitch4"> 
					<label class="custom-control-label" for="customSwitch4">Visible</label>
				</div>
			</div>

			<div class="erreur">${telephoneMessageErreur}</div>
			<div class="row">
				<label for="rue" class="col-md-3">Rue : </label> <input type="text"
					name="rue" class="col-md-5"
					value="${!empty rueValide?rueValide:utilisateur.rue}" required>
			</div>
			<div class="erreur">${rueMessageErreur}</div>
			<div class="row">
				<label for="codePostal" class="col-md-3">Code postal : </label> <input
					type="text" name="codePostal" class="col-md-5"
					value="${!empty codePostalValide?codePostalValide:utilisateur.codePostal}"
					required>
			</div>
			<div class="erreur">${codePostalMessageErreur}</div>
			<div class="row">
				<label for="ville" class="col-md-3">Ville : </label> <input
					type="text" name="ville" class="col-md-5"
					value="${!empty villeValide?villeValide:utilisateur.ville}"
					required>
			</div>
			<div class="erreur">${villeMessageErreur}</div>
			<div class="row">
				<label for="credit" class="col-md-3">Crédit : </label> <span
					id="credit">${!empty credit?credit:utilisateur.credit}</span>
			</div>
			<div class="row">
				<label for="motDePasse" class="col-md-3">Mot de passe :</label> <input
					type="password" name="motDePasse" class="col-md-5" required placeholder="********">
					<!-- value="${!empty motDePasseValide?motDePasseValide:utilisateur.motDePasse}">  -->
			</div>
			<div class="erreur">${motDePasseMessageErreur}</div>
			<div class="row">
				<label for="confirmation" class="col-md-3">Confirmation :</label> <input
					type="password" name="confirmation" class="col-md-5" required placeholder="********">
					<!-- value="${!empty confirmationMDPValide?confirmationMDPValide:utilisateur.motDePasse}" -->
			</div>
			<div class="erreur">${confirmationMDPMessageErreur}</div>
		</div>
		<div class="container">
			<button class="col-12 mt-2" name="enregistrer" id="enregistrer" type="submit">Enregistrer</button>
			<button class="col-12 mt-4" name="supprimer" id="supprimer">Supprimer mon compte</button>
		</div>
	</form>
	<div class="container">
		<a href="${pageContext.request.contextPath}/Secure/ServletAccueil">
			<button class="col-12 mb-5" name="retour" id="retour">Retour</button>
		</a>
	</div>
</body>
</html>