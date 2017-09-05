<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="it.pennino.uni.piazzaAffari.user.controller.UserSession"%>
<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.Annuncio"%>
<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDao"%>
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
<title>Piazza affari - professionisti</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	<article>
		<div class="container">
			
			
			<%
				String msg = (String)request.getAttribute("msgStr");
				if(msg!=null && msg.equals("del-ok")){
					%>
					<div class="alert alert-success">
					  <strong>Cancellazione</strong> avvenuta con successo.
					</div>
					<%
				}else if(msg!=null && msg.equals("ins-ok")){
					%>
					<div class="alert alert-success">
					  <strong>Salvataggio</strong> avvenuta con successo.
					</div>
					<%
				}
				
				
			%>
			
			
			<div class="table-responsive">
				<table class="table table-striped table-condensed">				
				<thead>
					<tr>
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
								UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					
								AnnuncioDao aDao = new AnnuncioDaoImp();
								ArrayList<Annuncio> annunci = aDao.findAll(userSession.getUser());
								if(annunci!=null && annunci.size()>0){
								Iterator<Annuncio> itrAnnuncio = annunci.iterator();
								
								while(itrAnnuncio.hasNext()){
									Annuncio ann = itrAnnuncio.next();
							%>
								<tr>
									<td>
										<a id="deleteAnnuncio" href="${pageContext.request.contextPath}/professionisti/annuncio/delete/<%=ann.getIdAnnuncio()%>">
								          <span class="glyphicon glyphicon-trash"></span>
								        </a>
									</td>
									<td>
										<a id="editAnnuncio" href="${pageContext.request.contextPath}/professionisti/annuncio/edit/<%=ann.getIdAnnuncio()%>">
								          <span class="glyphicon glyphicon-edit"></span>
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
								}else{
									%>
									<tr>
										<td colspan="8">
											<center>Non ci sono annunci da visualizzare</center>
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
