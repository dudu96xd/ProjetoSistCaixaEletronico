<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head.jsp"/>
<title>Caixa Eletrônico - ${conta.nome}</title>
</head>
<body>

	<!-- Barra superior com os menus de navegação -->
	<c:import url="menu.jsp"/>
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Informações da conta #${conta.idConta}</h3>
		<p>
			Seja bem vindo ${conta.nome}.<br> Saldo: R$${conta.saldo},00.</p>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
