<%@page import="it.pennino.uni.piazzaAffari.user.model.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UserDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UserDao"%>
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
	
	<article>
		<div class="container">
			<div>
				<form class="form-inline" method="POST" action="#">
					<div class="form-group">
				    	<label for="nome">Nome (contiene):</label>
				    	<input type="search" class="form-control" id="nome">
				  	</div>
				  	<div class="form-group">
				    	<label for="cognome">Cognome (contiene):</label>
				    	<input type="search" class="form-control" id="cognome">
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
					<th>Cognome</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Ruoli</th>
					<th>Stato</th>
				</tr>
				</thead>
				<tbody>
					<%
								UserDao uDao = new UserDaoImp();
								ArrayList<User> utenti = uDao.findAll(null);
								Iterator<User> itrutenti = utenti.iterator();
								while(itrutenti.hasNext()){
									User ut = itrutenti.next();
							%>
								<tr>
									<td>
										<a id="deleteUser" href="utente/delete/<%=ut.getId()%>">
								          <span class="glyphicon glyphicon-trash"></span>
								        </a>
									</td>
									<td>
										<a id="approvaUser" href="utente/approva/<%=ut.getId()%>">
								          <span class="glyphicon glyphicon glyphicon-ok"></span>
								        </a>
									</td>
									<td>
										<a id="visualizzaUser" href="utente/<%=ut.getId()%>">
								          <span class="glyphicon glyphicon-eye-open"></span>
								        </a>
									</td>
									<td><%=ut.getCognome()%></td>
									<td><%=ut.getNome()%></td>
									<td><%=ut.getEmail()%></td>
									<td><%=ut.getUserRuoli()%></td>
									<td>
										<%if(ut.getApprovato().equals("N")){%>
											Da approvare
										<% } else if(ut.getApprovato().equals("R")){ %>
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
