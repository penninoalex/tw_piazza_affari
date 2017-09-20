<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>
	
	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon"> 
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Piazza affari - ${titolo}</title>
</head>
<body>
	<%@include file='header.jsp' %>
	<%@include file='topMenu.jsp' %>
	<section>
		<div class="container">
			<header><h2>${titolo}</h2></header>
			<article>
				${contenuto}
			</article>
		</div>
	</section>
	<%@include file='footer.jsp'%>
</body>

</html>
