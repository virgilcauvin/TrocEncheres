<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Page Liste Encheres</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-11 offset-1">
				<div>
					<h1>TrocEncheres.org</h1>
				</div>
				<div>
					<p>${utilisateur.pseudo}est connecté !</p>
				</div>
				<div>
					<a href="${pageContext.request.contextPath}/Secure/ServletVendre">Vendre
						un article</a>
				</div>
				<div>
					<a href="${pageContext.request.contextPath}/Secure/ServletProfil">Mon
						profil</a>
				</div>
				<div>
					<!-- /!\NEW -->
					<a
						href="${pageContext.request.contextPath}/ServletConnexionCompte?deconnexion">Déconnexion</a>
				</div>
				<h2>Filtres :</h2>
				<div>
					<div>
						<input type="checkbox"> <label>Mes ventes</label>
					</div>
					<div>
						<input type="checkbox"> <label>Mes enchères en
							cours</label>
					</div>
					<div>
						<input type="checkbox"> <label>Mes acquisitions</label>
					</div>
					<div>
						<input type="checkbox"> <label>Autres enchères</label>
					</div>
					<div>
						<label>Catégories</label> <select id="pet-select">
							<option value="">--Please choose an option--</option>
							<option value="toute">Toutes</option>
							<option value="rien">Aucune</option>
						</select>
					</div>
					<div>
						<input type="text" name="recherche" class="rounded"
							placeholder="Votre recherche">
					</div>
					<div>
						<button type="button" class="btn btn-primary p-3 m-2">Rechercher</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>