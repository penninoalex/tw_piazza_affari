<!DOCTYPE html>
<%@page import="it.pennino.uni.piazzaAffari.webSocket.WebSocketServer"%>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>

	<link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon"> 

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/datiUltimaVisita.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/WebSoket.js" ></script>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Piazza affari - ${titolo}</title>
</head>
<body onload="connectWs('${nome}')">
	<%@include file='../../header.jsp' %>
	<%@include file='../../topMenu.jsp' %>
	<article>
		<div class="container">
			<header><h2>${titolo}</h2></header>
			<form id="formMsg" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				<div class="row">
					<div class="col-sm-3" >
						<div class="form-group">
						  <label for="sel1">Utenti collegati:</label>
						  <select multiple class="form-control" id="utentiCollegatiList" name="utentiCollegatiList">
						  	<option >Tutti</option>
						    <% for(int i=0; i<WebSocketServer.getUtentiConnessi().size(); i++){%>
						   		<option value="<%=WebSocketServer.getUtentiConnessi().get(i).getSession().getId()%>" ><%=WebSocketServer.getUtentiConnessi().get(i).getUsername()%></option>
						    <%}%>
						  </select>
						</div>
					</div>
					<div class="col-sm-7" >
					 	<div class="form-group">
						 <label for="textareaChat">Benvenuto : ${nome}</label>
						 <div id="textareaChat" > </div>
						</div>
					</div>	
				</div>
				<div class="row">
					<div class="col-sm-10" >
							<div class="form-group">
						    	<label for="msg">Messagio:</label>
						    	<input type="text" class="form-control" id="msg" name="msg" placeholder="Messagio">
							</div>
							<input type="hidden" value="${nome}" id="nome" name="nome">
							<input type="hidden" value="" id="idMittente" name="idMittente">
							<button id="inviaMsg" type="button" class="btn btn-default glyphicon glyphicon-log-in">Invia</button>
					</div>
				</div>
			</form>
			<br/>
			
		</div>
	</article>
	<%@include file='../../footer.jsp'%>
	
	<script type="text/javascript">
	$("#inviaMsg").click(function(e){
        $.ajax({
             url: 'sendMsg',
             type: 'POST',
             data:$("#formMsg").serialize(),
             success: function() {
                 msg.value="";
             }
         })        
    });

	</script>
</body>

</html>
