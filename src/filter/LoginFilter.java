package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Conta;

@WebFilter("/*")
public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Conta logado = (Conta) session.getAttribute("sessaoConta");
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String comando = req.getParameter("command");
		if(comando == null){
			comando = "";
		}

		if (logado == null && !uri.equals(path + "/login.jsp")
				&& !comando.equals("Logar")) {
			((HttpServletResponse) response).sendRedirect(path + "/login.jsp");
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
