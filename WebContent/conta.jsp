<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="TO.ContaTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cliente</title>
</head>
<body>
<%ContaTO to = (ContaTO)request.getAttribute("conta"); %>
Id: <%=to.getId() %><br>
Id Conta: <%=to.getIdConta() %><br>
Nome: <%=to.getNome() %><br>
Estado: <%=to.isEstado() %><br>
Saldo: <%=to.getSaldo() %><br>
</body>
</html>