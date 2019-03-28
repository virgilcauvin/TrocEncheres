<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Nouvelle Vente</title>
</head>
<body>
	<form action="">
		<div class="container">
	  		<h1>TrocEncheres.org</h1>
	  		<h2>Nouvelle vente</h2>
	  		<div>
	  			<label name="article">Article : </label>
	  			<input type="text" name="article">
	  		</div>
	  		<div>
	  			<label name="description">Description : </label>
	  			<textarea rows="2" cols="30" name="description"></textarea>
	  		</div>
	  		<div>
	  			<label name="photo">Photo de l'article :</label>
	  			<button name="photo">UPLOADER</button>
	  		</div>
	  		<div>
	  			Insere ici le code pour afficher la photo telechargé!
	  			<img alt="" src="">
	  		</div>
	  		<div>
	  			<label name="prixInitial">Prix initial : </label>
	  			<input type="number" value="0" min="1" max="10000" name="prixInitial">
	  		</div>
	  		<div>
	  			<label name="finEnchere">Fin de l'enchère : </label>
	  			<input type="date" name="finEnchere">
	  		</div>
	  		<div>
	  			<label name="adresse">Retrait : </label>
	  			${rue}
	  			${ville}
	  			${codePostal}
	  		</div>
		</div>
		<div class="container">
			<a href=""><button name="publier">Publier</button></a>
			<a href=""><button name="enregistrer">Enregistrer</button></a>
			<a href=""><button name="annuler">Annuler</button></a>
		</div>
	</form>
</body>
</html>