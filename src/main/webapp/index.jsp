<!DOCTYPE html>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDao"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.Categoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.pennino.uni.piazzaAffari.comuni.model.Comune"%>
<%@page import="it.pennino.uni.piazzaAffari.comuni.model.ComuneDaoImp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.pennino.uni.piazzaAffari.comuni.model.ComuneDao"%>
<html>
<head>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/recuperaPosizione.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/datiUltimaVisita.js" ></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Piazza affari</title>
</head>
<body onload="datiUtente()" onunload="aggiornaDatiVideo()">
	<%@include file='header.jsp' %>
	<%@include file='topMenu.jsp' %>
	<article>
		<div class="container">
			<div class="jumbotron">
					<section>
						<h2>Troviamo il professionista che stai cercando.</h2>
						<h3>Confronta preventivi in poche ore, gratis e senza impegno.</h3>
						<div class="col-sm-10">
						<form method="POST" action="ricerca">
							<div class="form-inline">
							
							<h4>Cosa stai cercando ? 
							<select  class="form-control" id="categoria" name="categoria">
								    	<% 	CategoriaDao cDao = new CategoriaDaoImp();
								    		ArrayList<Categoria> categorie = cDao.findAll();
								    		Iterator<Categoria> itrCategoria = categorie.iterator();
								    		while(itrCategoria.hasNext()){
								    		Categoria categoria = itrCategoria.next();
								    	%>
								   			<option id="<%=categoria.getNome()%>" value="<%=categoria.getNome()%>"><%=categoria.getNome()%></option> 	
								    	<%}%>
							</select>
							
							<select  class="form-control" id="comune" name="comune">
								    	<% 	ComuneDao comuniDao = new ComuneDaoImp();
								    		ArrayList<Comune> comuni = comuniDao.findAll();
								    		Iterator<Comune> itrComune = comuni.iterator();
								    		while(itrComune.hasNext()){
								    		Comune comune = itrComune.next();
								    	%>
								   			<option  data-istat="<%=comune.getIstat()%>" id="<%=comune.getComune()%>" value="<%=comune.getIstat()%>"><%=comune.getComune()%></option> 	
								    	<%}%>
							</select>
							<button type="submit" class="btn btn-default">Cerca</button>
							</h4>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
						</form>
						</div>
					</section>
				
			</div>
			<div>
				<video id="video" src="${pageContext.request.contextPath}/resources/media/video/Tutorial1.mp4"  width="100%"controls autoplay muted>Il browser non supporta il video</video>
			</div>
			<div class="row">
			<div class="col-sm-10">
				<br><br>
			</div>
			</div>
		</div>
		
	</article>
	<%@include file='footer.jsp'%>
</body>
</html>
