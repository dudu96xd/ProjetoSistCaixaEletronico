<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty sessaoConta}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head.jsp"/>
<title>Transferência - ${sessaoConta.nome}</title>
</head>
<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">
			Transferência entre Contas #${sessaoConta.idConta}</h3>
		<form class="login-form"
			action="controller.do?command=ContaTransferir"
			method="post">
			<!-- area de campos do form -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="agencia">Agência:</label> <input type="text"
						class="form-control" name="agencia" id="agencia" required
						maxlength="5" placeholder="Agência que deseja transferir.">
				</div>
				<div class="form-group col-md-12">
					<label for="conta">Conta:</label> <input type="text"
						class="form-control" name="conta" id="conta" required
						maxlength="5" placeholder="Conta que deseja transferir.">
				</div>
				<div class="form-group col-md-12">
					<label for="valor">Valor(R$):</label> <input type="text"
						class="form-control" name="valor" id="valor" required
						maxlength="10" placeholder="Valor para transferência.">

				</div>
			</div>
			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao"
						value="Transferir">Transferir</button>
				</div>
		</form>
	</div>




	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</c:if>
<c:if test="${empty sessaoConta}">
	<jsp:forward page="login.jsp"></jsp:forward>
</c:if>
</html>