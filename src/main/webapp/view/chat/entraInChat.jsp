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
<body>
	<%@include file='../../header.jsp' %>
	<%@include file='../../topMenu.jsp' %>
	<article>
		<div class="container">
			<header><h2>${titolo}</h2></header>
			<form action="chat" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				<div class="form-group">
			    	<label for="nome">Nome:</label>
			    	<input type="text" class="form-control" id="nome" name="nome" placeholder="Mario Rossi">
				</div>
				<button type="submit" class="btn btn-default">Entra in chat</button>
			</form>
			<br/>
		</div>
	</article>
	<%@include file='../../footer.jsp'%>
</body>

</html>
