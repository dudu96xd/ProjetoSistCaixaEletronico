<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style_login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - Caixa Eletrônico </title>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="controller.do?command=Logar" method="post">
				<input type="text" name="agencia" required maxlength="5"
					placeholder="Agência" /> <input type="text" name="conta" required
					maxlength="5" placeholder="Conta" /> <input type="password"
					name="senha" required maxlength="5" placeholder="Senha" /> <input
					type="submit" name="acao" value="Entrar">
				<c:if test="${param.erro == 1}">
					<p class="message">Dados incorretos, por favor digite
						novamente.</p>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>