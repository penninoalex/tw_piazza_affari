<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#nav2">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/clienti/home">Clienti</a>
		</div>
		<div class="collapse navbar-collapse" id="nav2">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/clienti/nuova_richiesta">Nuova richiesta</a></li>
				<li><a href="${pageContext.request.contextPath}/clienti/lista_richieste">Le mie richieste</a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span>LogOut</a></li>
      		</ul>
		</div>
	</div>
</nav>