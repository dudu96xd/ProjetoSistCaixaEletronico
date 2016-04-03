package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Conta;

/**
 * Servlet implementation class ContaController
 */
@WebServlet("/ContaController.do")
public class ContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pContaId = request.getParameter("idConta");
		String pSaldo = request.getParameter("saldo");
		String pAcao = request.getParameter("acao");
		String nome = "", agencia = "", cont = "", senha = "";
		boolean estado = true;
		int clienteId = 0;
		int contaId = 0;
		int valor = 0;
		try {
			contaId = Integer.parseInt(pContaId);
			valor = Integer.parseInt(pSaldo);
		} catch (NumberFormatException e) {	}
		catch(NullPointerException e){	}
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Consulta de Cliente</title></head><body>");
		
		Conta conta = new Conta(nome,agencia,cont,senha,valor,estado,contaId,clienteId);
		conta.carregarConta();
		if(pAcao.equals("Sacar"))
			conta.sacar(valor);
		else if(pAcao.equals("Depositar"))
			conta.depositar(valor);
		
		
		out.println("Id Conta: "+conta.getIdConta()+"<br>");
		out.println("Id Cliente: "+conta.getId()+"<br>");
		out.println("Estado: "+conta.isEstado()+"<br>");
		out.println("Saldo: "+conta.getSaldo()+"<br>");
		out.println("Nome: "+conta.getNome()+"<br>");
		
		out.println("</body></html>");
		
	}

}
