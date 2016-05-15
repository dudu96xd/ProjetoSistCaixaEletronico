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
import javax.servlet.http.HttpSession;

import Classes.Conta;

@WebFilter("/*")
public class LogFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		Conta to = (Conta)session.getAttribute("sessaoConta");
		
		if(to == null){
			System.out.println(req.getParameter("command"));
		} else {
			System.out.println(to.getConta()+ " -> " + req.getParameter("command"));
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
		if(to == null){
			System.out.println(req.getParameter("command"));
		} else {
			System.out.println(req.getParameter("command")+" -> " + to.getConta());
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
