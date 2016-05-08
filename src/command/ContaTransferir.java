package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Conta;
import TXT.txtProjeto;

public class ContaTransferir implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Conta conta = (Conta) session.getAttribute("sessaoConta");
		String nome = "";
		String agencia = request.getParameter("agencia");
		String cont = request.getParameter("conta");
		int valor = 0;
		int idContaTransf = 0;
		try {
			valor = Integer.parseInt(request.getParameter("valor"));
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}

		RequestDispatcher view = null;

		conta.carregarConta();

		idContaTransf = txtProjeto.confere(agencia, cont);
		if (idContaTransf != -1&&idContaTransf != conta.getIdConta()) {
			conta.transferir(valor, idContaTransf);
		}
		view = request.getRequestDispatcher("conta.jsp");
		view.forward(request, response);

	}

}
