<%@page import="it.pennino.uni.piazzaAffari.clienti.model.RichiesteRisposte"%>
<%@page import="it.pennino.uni.piazzaAffari.clienti.model.Richiesta"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorieId"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorie"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.User"%>
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
<title>Piazza affari - Risposta ${richiesta.idRichiesta}</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	<div class="container">
		<section>
			<header class="page-header">
				<h1 id="cognomeNome">${richiesta.getUser().getCognome()} ${richiesta.getUser().getNome()}</h1> 
			</header>
			<article>
				<label id="lCategoria" for="categoria">Categoria:</label>
				<p class="lead" id="categoria" >${richiesta.getCategoria()}</p> 
				<label id="lTitolo" for="titolo">Titolo:</label>
				<p class="lead" id="titolo" >${richiesta.getTitolo()}</p> 
				<label id="lRichiesta" for="richiesta">Richiesta:</label>
				<p class="lead" id="richiesta" >${richiesta.getDescrizione()}</p> 
			</article>
			<header class="page-header">
				<h1 id="elencoRisposte">Risposte</h1> 
			</header>
			<article>
				<%
				Richiesta richiesta = (Richiesta)request.getAttribute("richiesta");
				Iterator<RichiesteRisposte> itrRisposte = richiesta.getRichiesteRispostes().iterator();
				while(itrRisposte.hasNext()){
					RichiesteRisposte risp = itrRisposte.next();
				%>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>
							<%=risp.getUser().getNome()%> <%=risp.getUser().getCognome()%> 
						</h4>
					</div>
					<div class="panel-body">
						<%=risp.getTesto()%>
					</div>
					<div class="panel-footer">
						<a href="mailto:<%=risp.getUser().getEmail()%>" class="btn  btn-success">Contatta</a>
						
					</div>
					
				</div>
				<%	
			}
			%>
			</article>
		</section>
		<br/>
	</div>
	<%@include file='footer.html'%>
</body>
</html>
