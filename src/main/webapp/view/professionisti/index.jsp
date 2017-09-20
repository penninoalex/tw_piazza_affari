<%@page import="it.pennino.uni.piazzaAffari.annuncio.model.Annuncio"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDao"%>
<%@page session="true"%>
<% 
		Annuncio annuncioSel = (Annuncio)request.getAttribute("annuncio"); 
%>


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
<title>Piazza affari - professionisti</title>
</head>
<body>
	<%@include file='header.jsp'%>
	<%@include file='topMenu.jsp'%>
	
	<article>
		<div class="container">
			<form id="formAnnuncio" action="${pageContext.request.contextPath}/professionisti/pubblica_annuncio" method="POST">
				<div class="form-group">
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
								<%if(annuncioSel!=null && annuncioSel.getCategoria().getNome().equals(cat.getNome())){%>
									selected="selected"
								<%}%>
							><%=cat.getNome()%></option>
						<%			
							}
						%>
					</select>
				</div>
				<div class="form-group">
					<input type="hidden" id="idAnnuncio" name="idAnnuncio" <%if(annuncioSel!=null){%>value="<%=annuncioSel.getIdAnnuncio()%>"<%}%> />
				</div>
				<div class="form-group">
					<label for="">Titolo</label> 
					<input class="form-control" type="search" id="titolo" name="titolo" placeholder="titolo" maxlength="250"<%if(annuncioSel!=null){%>value="<%=annuncioSel.getTitolo().trim()%>"<%}%> />
				</div>
				<div class="form-group">
					<label for="descrizione">Descrizione</label>
					<textarea class="form-control" rows="10" id="descrizione" name="descrizione"><%if(annuncioSel!=null){%><%=annuncioSel.getDescrizione().trim()%><%}%></textarea>
				</div>
				<div class="form-group">
					<label for="prezzo">Prezzo</label> 
						<input type="number" id="prezzo" name="prezzo" <%if(annuncioSel!=null){%>value="<%=annuncioSel.getPrezzo()%>"<%}%> step="0.01" /> 
					<label class="radio-inline">
						<input type="radio" value="O" name="tipoPrezzo" 
						<%if(annuncioSel!=null){
							if(annuncioSel.getTipoPrezzo().equals("O")){
								%>
								checked="checked"
								<%
							}
						} else { %>
							checked="checked"
						<%} %>
						
						>Orario</label> 
					<label class="radio-inline">
						<input type="radio" value="T" name="tipoPrezzo"
						
						<%if(annuncioSel!=null){
							if(annuncioSel.getTipoPrezzo().equals("T")){
								%>
								checked="checked"
								<%
							}
						}%>
						
						>Totale</label>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-default">Invia</button>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			</form>
		</div>

	</article>
	<%@include file='footer.html'%>
</body>
</html>
