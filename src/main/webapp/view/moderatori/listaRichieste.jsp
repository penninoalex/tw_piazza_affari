<%@page import="it.pennino.uni.piazzaAffari.clienti.model.RichiestaDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.clienti.model.RichiestaDao"%>
<%@page import="it.pennino.uni.piazzaAffari.clienti.model.Richiesta"%>
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
	
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon"> 
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Piazza affari - moderatori</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	<article>
		<div class="container">
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
						<th>Stato</th>
					</tr>
				</thead>
				<tbody>
					<%
								UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					
								RichiestaDao rDao = new RichiestaDaoImp();
								ArrayList<Richiesta> richieste = rDao.findAll(null);
								if(richieste!=null && richieste.size()>0){
								Iterator<Richiesta> itrRichieste = richieste.iterator();
								
								while(itrRichieste.hasNext()){
									Richiesta richiesta = itrRichieste.next();
							%>
								<tr>
									<td>
										<a id="deleteAnnuncio" href="${pageContext.request.contextPath}/moderatori/richiesta/annulla/<%=richiesta.getIdRichiesta()%>" title="Annulla">
								          <span class="glyphicon glyphicon-trash"></span>
								        </a>
									</td>
									<td>
										<a id="approva" href="${pageContext.request.contextPath}/moderatori/richiesta/approva/<%=richiesta.getIdRichiesta()%>" title="Approva">
								          <span class="glyphicon glyphicon glyphicon-ok"></span>
								        </a>
									</td>
									<td>
										<a id="visualizza" href="${pageContext.request.contextPath}/moderatori/richiesta/<%=richiesta.getIdRichiesta()%>" title="Visualizza">
								          <span class="glyphicon glyphicon-eye-open"></span>
								        </a>
									</td>
									<td><%=richiesta.getCategoria()%></td>
									<td><%=richiesta.getTitolo()%></td>
									<td><%=richiesta.getDescrizione()%></td>
									<td>
										<%if(richiesta.getApprovato().equals("N")){%>
											Da approvare
										<% } else if(richiesta.getApprovato().equals("R")){ %>
											Rifiutato
										<% }else { %>
											Approvato
										<% } %>
									</td>
									
									
								</tr>
							<%			
								}
								}else{
									%>
									<tr>
										<td colspan="8">
											<center>Non ci sono richieste da visualizzare</center>
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
