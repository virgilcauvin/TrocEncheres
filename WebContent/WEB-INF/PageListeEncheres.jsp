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
					<p>${sessionScope.pseudo} est connecté !</p>
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
					<form action="${pageContext.request.contextPath}/Secure/ServletAccueil" method="post">
						<div >
							<input type="checkbox" name="mesVentes">
							<label>Mes ventes</label>
						</div>
						<div>
							<input type="checkbox" name="mesEncheresEnCours">
							<label>Mes enchï¿½res en cours</label>
						</div>
						<div>
							<input type="checkbox" name="mesAcquisitions">
							<label>Mes acquisitions</label>
						</div>
						<div>
							<input type="checkbox" name="autresEncheres">
							<label>Autres enchères</label>
						</div>
						<div>
							<label>Catégories</label>
							<select id="pet-select" name="categorie">
			    				<option value="0">Toutes</option>
			    				<c:forEach var="categorie" items="${sessionScope.listeCategories}">
			    					<option value="${categorie.noCategorie}">${categorie.libelle}</option>
			    				</c:forEach>
							</select>
						</div>
						<div>
							<input type="text" name="recherche" class="rounded" placeholder="Votre recherche">
						</div>
						<div>
							<button type="submit" class="btn btn-primary p-3 m-2">Rechercher</button>
						</div>
					</form>
					
					<c:forEach var="venteEnCours" items="${listeVentesEnCours}">
	   					<div class="border">
							<img alt="${venteEnCours.nomArticle}" src="${venteEnCours.photo}">
	   						<a href="${pageContext.request.contextPath}/Secure/ServletEnchere?noVente=${venteEnCours.noVente}">${venteEnCours.nomArticle}</a>
	   						<p>
	   							<span>Prix : ${venteEnCours.prixVente==0 ? venteEnCours.miseAPrix : venteEnCours.prixVente} points </span><span>classement : (à faire ?)</span>
	   							
	   						</p>
							<p>Fin de l'enchère : ${venteEnCours.dateFinEncheres}</p>
							<p>Retrait : ${venteEnCours.rue}</p>
							<p>${venteEnCours.codePostal} ${venteEnCours.ville}</p>
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
	   				</c:forEach>
					
					<c:forEach var="venteUtilisateur" items="${listeVentesUtilisateur}">
	   					<div class="border">
	   						<img alt="${venteUtilisateur.nomArticle}" src="${venteUtilisateur.photo}">
	   						<a href="${pageContext.request.contextPath}/Secure/ServletEnchere" >${venteUtilisateur.nomArticle}</a>
	   						<p>Prix : ${venteUtilisateur.prixVente==0 ? venteUtilisateur.miseAPrix : venteUtilisateur.prixVente} points</p>
							<p>Fin de l'enchère : ${venteUtilisateur.dateFinEncheres}</p>
							<p>Retrait : ${venteUtilisateur.rue}</p>
							<p>${venteUtilisateur.codePostal} ${venteUtilisateur.ville}</p>
							<div>Vendeur : ${sessionScope.pseudo}</div>
	   					</div>
	   				</c:forEach>
					
					<c:forEach var="enchereUtilisateurEnCours" items="${listeEncheresUtilisateurEnCours}">
	   					<div class="border">
							<img alt="${enchereUtilisateurEnCours.nomArticle}" src="${enchereUtilisateurEnCours.photo}">
	   						<a href="${pageContext.request.contextPath}/Secure/ServletEnchere" >${enchereUtilisateurEnCours.nomArticle}</a>
	   						<p>
	   							<span>Prix : ${enchereUtilisateurEnCours.prixVente} points</span><span>classement : </span>
	   							<c:forEach var="enchereUtilisateur" items="${listeEncheresUtilisateur}">
	   								${enchereUtilisateur.noVente == enchereUtilisateurEnCours.noVente ? enchereUtilisateur.classement : erreur} 
	   							</c:forEach>
	   						</p>
							<p>Fin de l'enchère : ${enchereUtilisateurEnCours.dateFinEncheres}</p>
							<p>Retrait : ${enchereUtilisateurEnCours.rue}</p>
							<p>${enchereUtilisateurEnCours.codePostal} ${enchereUtilisateurEnCours.ville}</p>
							<span>Vendeur : </span><a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${enchereUtilisateurEnCours.pseudo}">${enchereUtilisateurEnCours.pseudo}</a>
	   					</div>
	   				</c:forEach>
	   				
	   				<c:forEach var="acquisitionUtilisateur" items="${listeAcquisitionsUtilisateur}">
	   					<div class="border">
							<img alt="${acquisitionUtilisateur.nomArticle}" src="${acquisitionUtilisateur.photo}">
	   						<a href="${pageContext.request.contextPath}/Secure/ServletEnchere" >${acquisitionUtilisateur.nomArticle}</a>
	   						<p>
	   							<span>Prix : ${acquisitionUtilisateur.prixVente} points</span>
	   						</p>
							<p>Fin de l'enchère : ${acquisitionUtilisateur.dateFinEncheres}</p>
							<p>Retrait : ${acquisitionUtilisateur.rue}</p>
							<p>${acquisitionUtilisateur.codePostal} ${acquisitionUtilisateur.ville}</p>
							<span>Vendeur : </span><a href="${pageContext.request.contextPath}/Secure/ServletInfoUtilisateur?pseudo=${acquisitionUtilisateur.pseudo}">${acquisitionUtilisateur.pseudo}</a>
	   					</div>
	   				</c:forEach>
	   				
	   				<c:forEach var="ventesPerduesUtilisateur" items="${listeVentesPerduesUtilisateur}">
	   					<div class="border">
							<img alt="${ventesPerduesUtilisateur.nomArticle}" src="${ventesPerduesUtilisateur.photo}">
	   						<a href="${pageContext.request.contextPath}/Secure/ServletEnchere" >${ventesPerduesUtilisateur.nomArticle}</a>
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
	</div>
</body>
</html>