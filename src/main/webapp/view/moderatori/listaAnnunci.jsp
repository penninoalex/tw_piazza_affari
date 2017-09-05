<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.Annuncio"%>
<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDao"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="it.pennino.uni.piazzaAffari.user.controller.UserSession"%>
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
	
	<%
		Boolean deleteOk = (Boolean)request.getAttribute("deleteOk");
		Annuncio annuncioOld = (Annuncio)request.getAttribute("annuncioOld");
		
	%>
	
	<%if(deleteOk!=null && deleteOk==true){ %>
		<div class="alert alert-success">
	  		<strong>Ok!</strong> Annuncio cancellato con successo.
		</div>
	<%} else if(deleteOk!=null && deleteOk==false){ %>
		<div class="alert alert-error">
	  		<strong>Errore!</strong> Errore cancellazione annuncio.
		</div>
	<%} %>
	
	<article>
		<div class="container">
			<div>
				<form class="form-inline" method="POST" action="#">
					<div class="form-group">
				    	<label for="titolo">Titolo (contiene):</label>
				    	<input type="search" class="form-control" id="titolo">
				  	</div>
				  	<div class="form-group">
				    	<label for="descrizione">Descrizione (contiene):</label>
				    	<input type="search" class="form-control" id="descrizione">
				  	</div>
				  	<button type="submit" class="btn btn-default glyphicon glyphicon-search"></button>
				</form>
			</div>
			
			<div class="table-responsive">
				<table class="table table-striped table-condensed">				
				<thead>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th>Categoria</th>
					<th>Titolo</th>
					<th>Descrizione</th>
					<th>Prezzo</th>
					<th>Tipo prezzo</th>
					<th>Stato</th>
				</tr>
				</thead>
				<tbody>
					<%
								AnnuncioDao aDao = new AnnuncioDaoImp();
								ArrayList<Annuncio> annunci = aDao.findAll(null);
								Iterator<Annuncio> itrAnnuncio = annunci.iterator();
								while(itrAnnuncio.hasNext()){
									Annuncio ann = itrAnnuncio.next();
							%>
								<tr>
									<td>
										<a id="deleteAnnuncio" href="annuncio/delete/<%=ann.getIdAnnuncio()%>">
								          <span class="glyphicon glyphicon-trash"></span>
								        </a>
									</td>
									<td>
										<a id="visualizza" href="annuncio/approva/<%=ann.getIdAnnuncio()%>">
								          <span class="glyphicon glyphicon glyphicon-ok"></span>
								        </a>
									</td>
									<td>
										<a id="visualizza" href="annuncio/<%=ann.getIdAnnuncio()%>">
								          <span class="glyphicon glyphicon-eye-open"></span>
								        </a>
									</td>
									<td><%=ann.getCategoria().getNome()%></td>
									<td><%=ann.getTitolo()%></td>
									<td><%=ann.getDescrizione()%></td>
									<td><%=ann.getPrezzo()%></td>
									<td>
										<%if(ann.getTipoPrezzo().equals("O")){%>
											Orario
										<%} else if(ann.getTipoPrezzo().equals("T")){%>
											Totale
										<%} %>
									</td>
									<td>
										<%if(ann.getApprovato().equals("N")){%>
											Da approvare
										<% } else if(ann.getApprovato().equals("R")){ %>
											Rifiutato
										<% }else { %>
											Approvato
										<% } %>
									</td>
									
									
								</tr>
							<%			
								}
							%>
				</tbody>
				</table>			
			</div>
		</div>
	</article>
	<%@include file='footer.html'%>
</body>
</html>
