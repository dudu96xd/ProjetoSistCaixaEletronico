package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Conta;

public class ContaSacar implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Conta conta = (Conta)session.getAttribute("sessaoConta");
		int valor = 0;
		try {
			valor = Integer.parseInt(request.getParameter("valor"));
		} catch (NumberFormatException e) {	}
		catch(NullPointerException e){}


		RequestDispatcher view = null;
		if(valor!=0)
		{
			conta.carregarConta();
			conta.sacar(valor);
		}
		view = request.getRequestDispatcher("conta.jsp");
		view.forward(request, response);

	}

}
