<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

	if(request.getAttribute("idConta") != null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Depositar</title>
</head>
<body>
	<!-- Barra superior com os menus de navegação -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="ContaController.do?acao=index&idConta=<%= request.getAttribute("idConta") %>">Caixa
				Eletrônico</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="ContaController.do?acao=telaSacar&idConta=<%= request.getAttribute("idConta") %>">Sacar</a>
				</li>
				<li><a
					href="ContaController.do?acao=telaDepositar&idConta=<%= request.getAttribute("idConta") %>">Depositar</a>
				</li>
				<li><a
					href="ContaController.do?acao=Extrato&idConta=<%= request.getAttribute("idConta") %>">Extrato</a>
				</li>
				<li><a
					href="ContaController.do?acao=telaTransf&idConta=<%= request.getAttribute("idConta")%>">Transferir</a>
				</li>
			</ul>
		</div>
	</div>
	</nav>
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">
			Depositar #<%=request.getAttribute("idConta")%></h3>
		<form
			action="ContaController.do?idConta=<%= request.getAttribute("idConta") %>"
			method="post">
			<!-- area de campos do form -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="valor">Valor</label> <input type="text"
						class="form-control" name="valor" id="saldo" required
						maxlength="100" placeholder="Valor desejado para depositar.">
				</div>
			</div>
			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao" value="Depositar">Depositar</button>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
<%
	} else {
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%
}
%>