<!DOCTYPE html>
<html>
<head>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/recuperaPosizione.js" ></script>
	<script src="${pageContext.request.contextPath}/resources/js/datiUltimaVisita.js" ></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Piazza affari</title>
</head>
<body onload="datiUtente()" onunload="aggiornaDatiVideo()">
	<%@include file='header.jsp' %>
	<%@include file='topMenu.jsp' %>
	<article>
		<div class="container">
			<div class="jumbotron">
					<header><h2>Troviamo il professionista che stai cercando.</h2></header>
					<section>
						<h3>Confronta preventivi in poche ore, gratis e senza impegno.</h3>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="cerca" placeholder="Cosa stai cercando ?">
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn btn-default">Cerca</button>
							</div>					
							
						</div>
					</section>
				
			</div>
			<div>
				<video id="video" src="${pageContext.request.contextPath}/resources/media/video/Tutorial1.mp4"  width="100%"controls autoplay muted>Il browser non supporta il video</video>
			</div>
			<div class="row">
			<div class="col-sm-10">
				<br><br>
			</div>
			</div>
		</div>
		
	</article>
	<%@include file='footer.jsp'%>
</body>
</html>
