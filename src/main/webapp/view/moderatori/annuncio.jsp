<%@page import="java.util.Iterator"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDao"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"	media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css"	media="screen" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Piazza affari - moderatori</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	<div class="container">
		<section>
			<header class="page-header">
				<h1 id="titolo">${annuncio.getTitolo()}</h1> 
			</header>
			<article>
				<label id="lDescrizione" for="descrizione">Descrizione:</label>
				<p class="lead" id="descrizione">${annuncio.getDescrizione()}</p> 
				
				<label id="lPrezzo" for="prezzo">Prezzo:</label>
				<p class="lead" id="prezzo">${annuncio.getPrezzo()}</p> 
 				
	 			<label id="lTipoPrezzo" for="tipoPrezzo">Tipo prezzo:</label>
				<p class="lead" id="tipoPrezzo">${annuncio.getTipoPrezzo()}</p> 
			</article>
		</section>
		<br/>
	</div>
	<%@include file='footer.html'%>
</body>
</html>
