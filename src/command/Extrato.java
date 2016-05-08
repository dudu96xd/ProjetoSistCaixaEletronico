package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Conta;

public class Extrato implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pContaId = request.getParameter("idConta");
		String pSaldo = request.getParameter("saldo");
		String nome = "";
		String agencia = request.getParameter("agencia");
		String cont = request.getParameter("conta");
		String senha = request.getParameter("senha");
		boolean estado = true;
		int clienteId = 0;
		int contaId = 0;
		int saldo = 0;
		try {
			contaId = Integer.parseInt(pContaId);
			saldo = Integer.parseInt(pSaldo);
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}

		Conta conta = new Conta(nome, agencia, cont, senha, saldo, estado, contaId, clienteId);
		RequestDispatcher view = null;

		conta.carregarConta();
		view = request.getRequestDispatcher("extrato.jsp");
		view.forward(request, response);

	}

}
