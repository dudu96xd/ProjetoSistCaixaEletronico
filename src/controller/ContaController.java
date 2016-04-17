package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Conta;
import TO.ContaTO;
import TXT.txtProjeto;

/**
 * Servlet implementation class ContaController
 */
@WebServlet("/ContaController.do")
public class ContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String pContaId = request.getParameter("idConta");
		String pSaldo = request.getParameter("saldo");
		String pAcao = request.getParameter("acao");
		String nome = "";
		String agencia = request.getParameter("agencia");
		String cont = request.getParameter("conta");
		String senha = request.getParameter("senha");
		String index = "";
		boolean estado = true;
		int clienteId = 0;
		int contaId = 0;
		int saldo = 0;
		int valor = 0;
		int idContaTransf =0;
		try {
			contaId = Integer.parseInt(pContaId);
			valor = Integer.parseInt(request.getParameter("valor"));
			saldo = Integer.parseInt(pSaldo);
		} catch (NumberFormatException e) {	}
		catch(NullPointerException e){}


		Conta conta = new Conta(nome,agencia,cont,senha,saldo,estado,contaId,clienteId);
		ContaTO to = new ContaTO(nome,agencia,cont,senha,saldo,estado,contaId,clienteId);
		RequestDispatcher view = null;
		if(pAcao.equals("Entrar"))
		{

			try {
				if(!conta.logar())
				{
					view = request.getRequestDispatcher("login.jsp?erro=1");
					view.forward(request, response);
				}else
				{

					HttpSession sessao = request.getSession();
					conta.carregarConta();
					sessao.setAttribute("idConta", conta.getIdConta());
					request.setAttribute("conta", conta);
					view = request.getRequestDispatcher("conta.jsp");
					view.forward(request, response);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		if(pAcao.equals("Extrato"))
		{
			conta.carregarConta();
			index = "extrato";
			backIndex(request, response,"conta",index, conta);
		}
		if(pAcao.equals("index"))
		{
			conta.carregarConta();
			request.setAttribute("conta", conta);
			view = request.getRequestDispatcher("conta.jsp");
			view.forward(request, response);
		}
		if(pAcao.equals("telaSacar"))
		{
			conta.carregarConta();
			index = "sacar";
			backIndex(request, response,"conta",index, conta);
		}
		if(pAcao.equals("telaDepositar"))
		{
			conta.carregarConta();
			index = "depositar";
			backIndex(request, response,"conta",index, conta);
		}
		if(pAcao.equals("telaTransf"))
		{
			conta.carregarConta();
			index = "transferir";
			backIndex(request, response,"conta",index, conta);
		}
		if(valor!=0)
		{
			conta.carregarConta();
			if(pAcao.equals("Transferir")){
				try {
					//conta.getTO(to);
					idContaTransf = txtProjeto.confere(agencia,cont);
					if(idContaTransf!=-1){
						conta.transferir(valor, idContaTransf);
						index = "conta";
						backIndex(request, response,"conta",index, conta);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(pAcao.equals("Sacar"))
			{
				conta.sacar(valor);
				index = "conta";
				backIndex(request, response,"conta",index, conta);
			}else if(pAcao.equals("Depositar"))
			{
				try {
					/*to.setIdConta(conta.getIdConta());
					to.setSenha(conta.getSenha());
					txtProjeto.confereSenha(to);*/
					conta.depositar(valor);
					index = "conta";
					backIndex(request, response, "conta",index, conta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected void backIndex(HttpServletRequest request, HttpServletResponse response, String nomeAtributo,String diretorio, Conta conta)
			throws ServletException, IOException {
		RequestDispatcher view;
		request.setAttribute(nomeAtributo, conta);
		view = request.getRequestDispatcher(diretorio+".jsp");
		view.forward(request, response);
	}
}
