<!DOCTYPE html>
<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.Annuncio"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%
	ArrayList<Annuncio> annunci = (ArrayList<Annuncio>)request.getAttribute("listaAnnunci");

%>

<html>
<head>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/recuperaPosizione.js" ></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Piazza affari - ricerca</title>
</head>
<body>
	<%@include file='header.jsp' %>
	<%@include file='topMenu.jsp' %>
	<article>
		<div class="container">
		
		<%if(annunci!=null){
			Iterator<Annuncio> itrAnnuncio = annunci.iterator();
			while(itrAnnuncio.hasNext()){
				Annuncio a = itrAnnuncio.next();
				%>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>
						<%=a.getTitolo()%>
						 <span style="float: right" class="label label-info"><%=a.getPrezzo()%>&euro; / 
							 <%if(a.getTipoPrezzo().equals("O")){ %>
							 	Ora
							 <% } else { %>
							 	Totale
							 <%}%>
						 </span>
						 </h4>
					</div>
					<div class="panel-body">
						<%=a.getDescrizione()%>
					</div>
					<div class="panel-footer">
						<a href="mailto:<%=a.getUsers().getEmail()%>" class="btn  btn-success">Contatta</a>
						<%=a.getUsers().getCognome()%> <%=a.getUsers().getNome()%>
					</div>
					
				</div>
				<%	
			}
		}else{
			%>
			
			<div class="alert alert-info">
			  <strong>Attenzione!</strong> Nessun risultato per i filtri inseriti.
			</div>
			<% 
		}%>
		
		</div>
	</article>
	<%@include file='footer.jsp'%>
</body>
</html>
