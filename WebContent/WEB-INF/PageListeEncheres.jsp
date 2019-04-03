<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TrocEncheres/css/listeencheres.css">
<title>Page Liste Encheres</title>
</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">TrocEncheres.org</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
				<div class="col-12">
					<p>${sessionScope.pseudo} est connecté !</p>
				</div>
				<div class="col-12">
					<a href="${pageContext.request.contextPath}/Secure/ServletVendre"><button>Vendre un article</button></a>
				</div>
				<div class="col-12">
					<a href="${pageContext.request.contextPath}/Secure/ServletProfil"><button>Mon profil</button></a>
				</div>
				<div class="col-12">
					<a href="${pageContext.request.contextPath}/ServletConnexionCompte?deconnexion"><button>Déconnexion</button></a>
				</div>
				<h2 class="col-12">Filtres :</h2>
				<div class="col-12">
					<form action="${pageContext.request.contextPath}/Secure/ServletAccueil" method="post">
						<div>
							<input class="offset-1" type="checkbox" name="mesVentes" id="mesVentes">
							<label for="mesVentes">Mes ventes</label>
						</div>
						<div>
							<input class="offset-1" type="checkbox" name="mesEncheresEnCours" id="mesEncheresEnCours">
							<label for="mesEncheresEnCours">Mes enchères en cours</label>
						</div>
						<div>
							<input class="offset-1" type="checkbox" name="mesAcquisitions" id="mesAcquisitions">
							<label for="mesAcquisitions">Mes acquisitions</label>
						</div>
						<div>
							<input  class="offset-1" type="checkbox" name="autresEncheres" id="autresEncheres">
							<label for="autresEncheres">Autres enchères</label>
						</div>
						<div class="row">
							<label class="col-3">Catégories</label>
							<select class="col-9" id="pet-select" name="categorie">
			    				<option value="0">Toutes</option>
			    				<c:forEach var="categorie" items="${sessionScope.listeCategories}">
			    					<option value="${categorie.noCategorie}">${categorie.libelle}</option>
			    				</c:forEach>
							</select>
						</div>
						<div>
							<input type="text" name="recherche" class="col-12" id="finder" placeholder="Votre recherche">
						</div>
						<div>
							<button type="submit" class="col-12 p-3">Rechercher</button>
						</div>
					</form>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<c:forEach var="venteEnCours" items="${listeVentesEnCours}">
	   					<div class="col-12 border m-1">
	   						<div class="row">
	   							<div class="col-4">
									<img alt="${venteEnCours.nomArticle}" src="${venteEnCours.photo}">
								</div>
								<div class="col-8">
			   						<c:choose>
										<c:when test="${venteEnCours.pseudo == sessionScope.pseudo}">
											<!-- url à changer --><a href="${pageContext.request.contextPath}/Secure/ServletDetailMaVente?noVente=${venteEnCours.noVente}">${venteEnCours.nomArticle} redirection vente</a>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/Secure/ServletEnchere?noVente=${venteEnCours.noVente}">${venteEnCours.nomArticle} redirection enchere</a>
										</c:otherwise>
									</c:choose>
			   						
			   						<p>
			   							<span>Prix : ${venteEnCours.prixVente==0 ? venteEnCours.miseAPrix : venteEnCours.prixVente} points </span>
			   							
			   							<c:forEach var="enchereUtilisateur" items="${listeEncheresUtilisateur}">
			   								<c:if test="${enchereUtilisateur.noVente == venteEnCours.noVente}">
												<span>classement : </span>
												${enchereUtilisateur.classement}
											</c:if>
			   							</c:forEach>
			   							
			   						</p>
									<p>Fin de l'enchère : ${venteEnCours.dateFinEncheres}</p>
									<div class="row">
										<div class="col-4">Retrait : </div>
										<div class="col-8">${venteEnCours.rue} <br>${venteEnCours.codePostal} ${venteEnCours.ville}</div>
									</div>
									<span>Vendeur : </span>
									<c:choose>
										<c:when test="${venteEnCours.pseudo == sessionScope.pseudo}">
											${venteEnCours.pseudo}
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${venteEnCours.pseudo}">${venteEnCours.pseudo}</a>
										</c:otherwise>
									</c:choose>
								</div>
   							</div>
	   					</div>
	   				</c:forEach>
					
					<c:forEach var="venteUtilisateur" items="${listeVentesUtilisateur}">
	   					<div class="col-12 border m-1">
	   						<div class="row">
		   						<div class="col-4">
			   						<img alt="${venteUtilisateur.nomArticle}" src="${venteUtilisateur.photo}">
			   					</div>
		   						<div class="col-8">
			   						<!-- url à changer --><a href="${pageContext.request.contextPath}/Secure/ServletDetailMaVente?noVente=${venteUtilisateur.noVente}" >${venteUtilisateur.nomArticle}</a>
			   						<p>Prix : ${venteUtilisateur.prixVente==0 ? venteUtilisateur.miseAPrix : venteUtilisateur.prixVente} points</p>
									<p>Fin de l'enchère : ${venteUtilisateur.dateFinEncheres}</p>
									<div class="row">
										<div class="col-4">Retrait :</div>
										<div class="col-8">${venteUtilisateur.rue} ${venteUtilisateur.codePostal} ${venteUtilisateur.ville}</div>
									</div>
									<div>Vendeur : ${sessionScope.pseudo}</div>
								</div>
							</div>
	   					</div>
	   				</c:forEach>
					
					<c:forEach var="enchereUtilisateurEnCours" items="${listeEncheresUtilisateurEnCours}">
	   					<div class="col-12 border m-1">
	   						<div class="row">
		   						<div class="col-4">
									<img alt="${enchereUtilisateurEnCours.nomArticle}" src="${enchereUtilisateurEnCours.photo}">
			   					</div>
		   						<div class="col-8">	
			   						<a href="${pageContext.request.contextPath}/Secure/ServletEnchere?noVente=${enchereUtilisateurEnCours.noVente}" >${enchereUtilisateurEnCours.nomArticle}</a>
			   						<p><span>Prix : ${enchereUtilisateurEnCours.prixVente} points</span><span>classement : </span>
			   							<c:forEach var="enchereUtilisateur" items="${listeEncheresUtilisateur}">
			   								${enchereUtilisateur.noVente == enchereUtilisateurEnCours.noVente ? enchereUtilisateur.classement : erreur} 
			   							</c:forEach>
			   						</p>
			   						<p>Fin de l'enchère : ${enchereUtilisateurEnCours.dateFinEncheres}</p>
			   						<div class="row">
										<div class="col-4">Retrait :</div>
										<div class="col-8">${enchereUtilisateurEnCours.rue} ${enchereUtilisateurEnCours.codePostal} ${enchereUtilisateurEnCours.ville}</div>
									</div>
									<span>Vendeur : </span><a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${enchereUtilisateurEnCours.pseudo}">${enchereUtilisateurEnCours.pseudo}</a>
	   							</div>
	   						</div>
	   					</div>
	   				</c:forEach>
	   				
	   				<c:forEach var="acquisitionUtilisateur" items="${listeAcquisitionsUtilisateur}">
	   					<div class="col-12 border">
	   						<div class="row">
		   						<div class="col-4">
									<img alt="${acquisitionUtilisateur.nomArticle}" src="${acquisitionUtilisateur.photo}">
								</div>
								<div class="col-8">
	   								<a href="${pageContext.request.contextPath}/Secure/ServletEnchereGagnee?noVente=${acquisitionUtilisateur.noVente}" >${acquisitionUtilisateur.nomArticle}</a>
	   								<p><span>Prix : ${acquisitionUtilisateur.prixVente} points</span></p>
									<p>Fin de l'enchère : ${acquisitionUtilisateur.dateFinEncheres}</p>
									<div class="row">
										<div class="col-4">Retrait : ${acquisitionUtilisateur.rue}</div>
										<div class="col-8">${acquisitionUtilisateur.codePostal} ${acquisitionUtilisateur.ville}</div>
									</div>
									<span>Vendeur : </span><a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${acquisitionUtilisateur.pseudo}">${acquisitionUtilisateur.pseudo}</a>
	   							</div>
	   						</div>
	   					</div>
	   				</c:forEach>
	   				
	   				<c:forEach var="ventesPerduesUtilisateur" items="${listeVentesPerduesUtilisateur}">
	   					<div class="border">
							<img alt="${ventesPerduesUtilisateur.nomArticle}" src="${ventesPerduesUtilisateur.photo}">
   							<c:forEach var="enchereUtilisateur" items="${listeEncheresUtilisateur}">
   								<c:if test="${enchereUtilisateur.noVente == ventesPerduesUtilisateur.noVente}">
   								 <a href="${pageContext.request.contextPath}/Secure/ServletEncherePerdue?noVente=${ventesPerduesUtilisateur.noVente}&classement=${enchereUtilisateur.classement}">${ventesPerduesUtilisateur.nomArticle}</a>
   								 </c:if> 
   							</c:forEach>
	   						<p>
	   							<span>Prix : ${ventesPerduesUtilisateur.prixVente} points</span><span>classement : </span>
	   							<c:forEach var="enchereUtilisateur" items="${listeEncheresUtilisateur}">
	   								${enchereUtilisateur.noVente == ventesPerduesUtilisateur.noVente ? enchereUtilisateur.classement : erreur} 
	   							</c:forEach>
	   						</p>
							<p>Fin de l'enchère : ${ventesPerduesUtilisateur.dateFinEncheres}</p>
							<p>Retrait : ${ventesPerduesUtilisateur.rue}</p>
							<p>${ventesPerduesUtilisateur.codePostal} ${ventesPerduesUtilisateur.ville}</p>
							<span>Vendeur : </span><a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${ventesPerduesUtilisateur.pseudo}">${ventesPerduesUtilisateur.pseudo}</a>
	   					</div>
	   				</c:forEach>	
				</div>
		</div>
	</div>
</body>
</html>