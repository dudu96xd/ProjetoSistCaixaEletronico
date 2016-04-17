<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="head.jsp"/>
<title>Depositar - ${conta.nome}</title>
</head>
<body>
 <!-- Barra superior com os menus de navegação -->
    <c:import url="menu.jsp"/>
    <!-- Container Principal -->
    <div id="main" class="container">
    <h3 class="page-header">
			Depositar #${conta.idConta}</h3>
    <form action="ContaController.do?idConta=${conta.idConta}" method="post">
        <!-- area de campos do form -->
            <div class="row">
                <div class="form-group col-md-12">
                    <label for="valor">Valor</label>
                    <input type="text" class="form-control" name="valor" id="saldo" required maxlength="100" placeholder="Valor desejado para depositar.">
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