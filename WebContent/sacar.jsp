<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessaoConta}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head.jsp"/>
<title>Sacar - ${sessaoConta.nome}</title>
</head>
<body>
<jsp:useBean id="idConta" class="Classes.Conta" scope="request"/>
 <!-- Barra superior com os menus de navega��o -->
    <c:import url="menu.jsp"/>
    <!-- Container Principal -->
    <div id="main" class="container">
    <h3 class="page-header">
			Sacar #${sessaoConta.idConta}</h3>
    <form action="controller.do?command=ContaSacar" method="post">
        <!-- area de campos do form -->
            <div class="row">
                <div class="form-group col-md-12">
                    <label for="valor">Valor</label>
                    <input type="text" class="form-control" name="valor" id="saldo" onKeyUp="mascaraMoeda(this)" required maxlength="100" placeholder="Valor desejado para sacar.">
                </div>
            </div>
            <hr />
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" name="acao" value="Sacar">Sacar</button>
                </div>
            </div>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</c:if>
<c:if test="${empty sessaoConta}">
	<jsp:forward page="login.jsp"></jsp:forward>
</c:if>
<script src="js/formatacao.js"></script>
</html>
