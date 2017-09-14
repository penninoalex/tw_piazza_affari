<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/datiUltimaVisita.js" ></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Piazza affari - ${titolo}</title>
</head>
<body onload="impostaDatiLogin()">
	<%@include file='../header.jsp' %>
	<%@include file='../topMenu.jsp' %>
	<article>
		<div class="container">
			<header><h2>${titolo}</h2></header>
			<%
				String errore = (String)request.getParameter("errore");
				if(errore!=null){
			%>
				<div class="alert alert-danger">
					<%=errore%>
				</div>
			<%}%>
			
			<form action="verificaLogin" method="POST">
				<div class="form-group">
			    	<label for="email">Email:</label>
			    	<input type="email" class="form-control" id="email" name="email" placeholder="mario.rossi@piazzaffari.it">
				</div>
				<div class="form-group">
			    	<label for="password">Password:</label>
			    	<input type="password" class="form-control" id="password" name="password" placeholder="password">
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				
				<button type="submit" class="btn btn-default glyphicon glyphicon-log-in" onclick="salvaDatiLogin(email.value,password.value)">Login</button>
			</form>
			<br/>
		</div>
	</article>
	<%@include file='../footer.jsp'%>
</body>

</html>
