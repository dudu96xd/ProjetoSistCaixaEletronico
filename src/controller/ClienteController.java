package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Cliente;
import TO.ClienteTO;


/**
 * Servlet implementation class ClienteController
 */
@WebServlet("/ClienteController.do")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("id");
		
		int id = 0;
		String nome = "";
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}
		
		Cliente cliente = new Cliente(nome, id);
		
		cliente.carregar();
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Consulta de Cliente</title></head><body>");
		out.println("id: "+cliente.getId()+"<br>");
		out.println("nome: "+cliente.getNome()+"<br>");
		out.println("</body></html>");
		
	}

}
