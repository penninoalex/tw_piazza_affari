<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorie"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorieDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorieDao"%>
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
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Piazza affari - Lista richieste</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	<article>
		<div class="container">
			<%
				String msg = (String)request.getAttribute("msgStr");
				if(msg!=null && msg.equals("risp-ok")){
					%>
					<div class="alert alert-success">
					  <strong>Risposta</strong> inviata con successo.
					</div>
				<%} %>
			
			
			<div class="table-responsive">
				<table class="table table-striped table-condensed">				
				<thead>
					<tr>
						<th></th>
						<th>Categoria</th>
						<th>Titolo</th>
						<th>Descrizione</th>
					</tr>
				</thead>
				<tbody>
					<%
						UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					
						UsersCategorieDao usCatDao = new UsersCategorieDaoImp();
						ArrayList<UsersCategorie> categorie = usCatDao.findAllByUser(userSession.getUser());
					
						RichiestaDao rDao = new RichiestaDaoImp();
						ArrayList<Richiesta> richieste = rDao.findAllByCategoria(categorie, userSession.getUser().getComune());
								
						if(richieste!=null && richieste.size()>0){
						Iterator<Richiesta> itrRichieste = richieste.iterator();
								
						while(itrRichieste.hasNext()){
								Richiesta richiesta = itrRichieste.next();
					%>
								<tr>
									<td>
										<a id="rispondiRichiesta" href="${pageContext.request.contextPath}/professionisti/richiesta/risposta/<%=richiesta.getIdRichiesta()%>" title="Rispondi">
								          <span class="glyphicon glyphicon-edit"></span>
								        </a>
									</td>
									<td><%=richiesta.getCategoria()%></td>
									<td><%=richiesta.getTitolo()%></td>
									<td><%=richiesta.getDescrizione()%></td>
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
