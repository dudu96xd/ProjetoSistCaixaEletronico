<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty sessaoConta}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head.jsp" />
<title>Extrato - ${sessaoConta.nome}</title>
</head>
<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Extrato ${sessaoConta.nome}</h3>
		<c:if test="${not empty sessaoConta.verExtrato()}">
		<table>
			<thead>
				<tr>
					<th width="10%">Data</th>
					<th width="75%">Lançamento</th>
					<th width="15%">Valor(R$)</th>
				</tr>
			</thead>
			<tbody>${sessaoConta.verExtrato()}
			</tbody>
		</table>
		</c:if>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</c:if>
<c:if test="${empty sessaoConta}">
	<jsp:forward page="login.jsp"></jsp:forward>
</c:if>
</html>