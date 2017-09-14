<!DOCTYPE html>
<%@page import="it.pennino.uni.piazzaAffari.comuni.model.Comune"%>
<%@page import="it.pennino.uni.piazzaAffari.comuni.model.ComuneDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.comuni.model.ComuneDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp"%>
<%@page import="it.pennino.uni.piazzaAffari.categoria.model.CategoriaDao"%>
<html>
<head>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	
	<script src="${pageContext.request.contextPath}/resources/js/recuperaPosizione.js" ></script>
	
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	
	<title>Piazza affari - ${titolo}</title>
	
	
	<script type="text/javascript">
		function divToggle(){
			if(rProf.checked){
				divCategorie.setAttribute("class","");
			}else{
				divCategorie.setAttribute("class","hidden");
			}
		}
	</script>
	
</head>
<body onload="divToggle(),recuperaPosizione()">
	<%@include file='../header.jsp' %>
	<%@include file='../topMenu.jsp' %>
	<article>
		<div class="container">
			<header><h2>${titolo}</h2></header>
			<%
				String msgOk = (String)request.getAttribute("msgOk");
				String errore = (String)request.getAttribute("errore");
			%>
			
			<%
				if(msgOk!=null && !msgOk.equals("")){
					%>
					<div class="alert alert-success">
						<%=msgOk%>
					</div>
					<%
				}
				if(errore!=null && !errore.equals("")){
					%>
					<div class="alert alert-danger">
						<%=errore%>
					</div>
					<%
				}
			%>
			
			<form action="saveUser" method="POST">
				<div class="form-group">
			    	<label id="ruoli"> 
			    		Cliente : <input id="rCliente" name="rCliente" type="checkbox" checked="checked"> 
			    		Professionista : <input id="rProf" name="rProf" type="checkbox" onclick="divToggle()"> 
			    	</label>
				</div>
				<div id="divCategorie" class="hidden">
					<div class="form-group" >
						<label for="nome">Categorie:</label>
						<%
							CategoriaDao cDao = new CategoriaDaoImp();
							ArrayList<Categoria> categorie = cDao.findAll();
							Iterator<Categoria> itrCat = categorie.iterator();
							while(itrCat.hasNext()){
								Categoria cat = itrCat.next();
						%>
							<label class="checkbox-inline"><input type="checkbox" name="cat" value="<%=cat.getNome()%>"><%=cat.getNome()%></label>
						<%}%>
					</div>
				</div>
				<div class="form-group">
			    	<label for="nome">Nome:</label>
			    	<input type="text" class="form-control" id="nome" name="nome" placeholder="Mario" required>
				</div>
				<div class="form-group">
			    	<label for="nome">Cognome:</label>
			    	<input type="text" class="form-control" id="cognome" name="cognome" placeholder="Rossi" required>
				</div>
				<div class="form-group">
			    	<label for="comune">Comune:</label>
			    	<select  class="form-control" id="comune" name="comune">
			    	<% 	ComuneDao comuniDao = new ComuneDaoImp();
			    		ArrayList<Comune> comuni = comuniDao.findAll();
			    		Iterator<Comune> itrComune = comuni.iterator();
			    		while(itrComune.hasNext()){
			    		Comune comune = itrComune.next();
			    	%>
			   			<option  data-istat="<%=comune.getIstat()%>" id="<%=comune.getComune()%>" value="<%=comune.getIstat()%>"><%=comune.getComune()%></option> 	
			    	<%}%>
			    	</select>
			   </div>
				<div class="form-group">
			    	<label for="email">Email:</label>
			    	<input type="email" class="form-control" id="email" name="email" placeholder="mario.rossi@piazzaffari.it" required>
				</div>
				<div class="form-group">
			    	<label for="password">Password:</label>
			    	<input type="password" class="form-control" id="password" name="password" placeholder="password" required>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				<button type="submit" class="btn btn-default">Registrati</button>
			</form>
			<br/>
		</div>
	</article>
	<%@include file='../footer.jsp'%>
</body>

</html>
