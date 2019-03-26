<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Connexion</title>
</head>
<body>
	<main class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1 class="display-4">TrocEncheres.org</h1>
		    <p class="lead">Identification</p>
		  </div>
		</div>
	<form action="">
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-3">
					<p class="text-right">Identifiant :
				</div>
				<div class="col-7">
					<input class="form-control form-control-sm" type="text"
						placeholder="Votre identifiant ici">
				</div>
				<div class="col-1"></div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-3">
					<p class="text-right">Mot de passe :
				</div>
				<div class="col-7">
					<input class="form-control form-control-sm" type="password"
						placeholder="Votre identifiant ici">
				</div>
				<div class="col-1"></div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-2">
					<button type="submit" class="btn btn-primary"
						class="btn btn-primary btn-lg" style="padding: 16px 16px">Connexion</button>
				</div>
				<div class="col-8" class="input-group mb-3">
					<div class="container">
						<div class="input-group-prepend">

							<div class="input-group-text">
								<input type="checkbox">
							</div>

							<div class="row">
								<div class="col-12">Se souvenir de moi</div>
							</div>
						</div>
						<div class="container">
							<div class="row">
								<a href="https://example.com">Mot de passe oublié</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-1"></div>
			</div>
		</div>

	</form>

	<form action="">
		<div class="container">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<a href="creationCompte.jsp"><button type="button" style="padding: 48px 16px"
						class="btn btn-primary btn-lg btn-block">Créer un compte</button></a>
				</div>
				<div class="col-1"></div>

			</div>
		</div>
	</form>


</body>
</html>