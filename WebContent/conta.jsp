<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="TO.ContaTO"%>
<%@page import="Classes.Conta"%>
<%

	if(session.getAttribute("idConta") != null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%Conta to = (Conta)request.getAttribute("conta");%>
<title>Conta - <%=to.getNome() %></title>
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
			<a class="navbar-brand" href="ContaController.do?acao=index&idConta=<%= to.getIdConta()%>">Caixa
				Eletrônico</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="ContaController.do?acao=telaSacar&idConta=<%= to.getIdConta()%>">Sacar</a>
				</li>
				<li><a
					href="ContaController.do?acao=telaDepositar&idConta=<%= to.getIdConta()%>">Depositar</a>
				</li>
				<li><a
					href="ContaController.do?acao=Extrato&idConta=<%= to.getIdConta()%>">Extrato</a>
				</li>
				<li><a
					href="ContaController.do?acao=telaTransf&idConta=<%= to.getIdConta()%>">Transferir</a>
				</li>
			</ul>
            </div>
        </div>
    </nav>
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Informações da conta #<%=session.getAttribute("idConta") %></h3>
		<p>
			Seja bem vindo
			<%=to.getNome() %>.<br> Saldo: R$<%=to.getSaldo() %></p>
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