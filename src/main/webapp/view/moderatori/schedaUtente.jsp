<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorieId"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.UsersCategorie"%>
<%@page import="it.pennino.uni.piazzaAffari.user.model.User"%>
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
	<div class="container">
		<section>
			<header class="page-header">
				<h1 id="cognomeNome">${user.getCognome()} ${user.getNome()}</h1> 
			</header>
			<article>
				<label id="lEmail" for="email">Email:</label>
				<p class="lead" id="email">${user.getEmail()}</p> 
				
				<label id="lRuoli" for="ruoli">Ruoli:</label>
				<p class="lead" id="ruoli">${user.getUserRuoli()}</p> 
				
				<% 
					User user = (User)request.getAttribute("user"); 
					if(user.getUserCategorie()!=null && user.getUserCategorie().size()>0){
						%>
						<ul class="list-group">
							<li class="list-group-item list-group-item-success">Categorie</li>
							<%
								Iterator<UsersCategorie> itrCat= user.getUserCategorie().iterator();
								while(itrCat.hasNext()){
									UsersCategorie uc = itrCat.next();
									out.print("<li  class=\"list-group-item\" >"+uc.getId().getCategoria()+"</li>");
								}
							%>
						</ul>
						<%
					}
				%>
			</article>
		</section>
		<br/>
	</div>
	<%@include file='footer.html'%>
</body>
</html>
