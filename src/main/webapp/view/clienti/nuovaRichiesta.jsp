<%@page import="it.pennino.uni.piazzaAffari.clienti.model.Richiesta"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDao"%>
<%@page session="true"%>
<% 
		Richiesta richiestaSel = (Richiesta)request.getAttribute("richiesta"); 
%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"	media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css"	media="screen" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Piazza affari - Richiesta</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	<article>
		<div class="container">
			<form id="formRichiesta" action="${pageContext.request.contextPath}/clienti/salvaNuovaRichiesta" method="POST">
				<div class="form-group">
						<div class="form-group">
							<input type="hidden" id="idRichiesta" name="idRichiesta" <%if(richiestaSel!=null){%>value="<%=richiestaSel.getIdRichiesta()%>"<%}%> />
						</div>
						
						<label for="selCat">Categoria:</label> 
						<select class="form-control" id="selCat" name="selCat">
							<%
								CategoriaDao cDao = new CategoriaDaoImp();
								ArrayList<Categoria> categorie = cDao.findAll();
								Iterator<Categoria> itrCat = categorie.iterator();
								while(itrCat.hasNext()){
									Categoria cat = itrCat.next();
							%>
								<option  value="<%=cat.getNome()%>"
									<%if(richiestaSel!=null && richiestaSel.getCategoria().equals(cat.getNome())){%>
										selected="selected"
									<%}%>
								><%=cat.getNome()%></option>
							<%			
								}
							%>
						</select>
						<div class="form-group">
							<label for="titolo">Titolo:</label> 
							<input class="form-control" type="search" id="titolo" name="titolo" placeholder="titolo" maxlength="250" <%if(richiestaSel!=null){%>value="<%=richiestaSel.getTitolo().trim()%>"<%}%>/>
						</div>
						<div class="form-group">
							<label for="descrizione">Descrizione:</label>
							<textarea class="form-control" rows="10" id="descrizione" name="descrizione"><%if(richiestaSel!=null){%><%=richiestaSel.getDescrizione().trim()%><%}%></textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-default">Salva</button>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				</div>
			</form>
		</div>
	</article>
	<%@include file='footer.html'%>
</body>
</html>
