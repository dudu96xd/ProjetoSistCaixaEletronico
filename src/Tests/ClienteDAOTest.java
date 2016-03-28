package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DAO.ClienteDAO;
import TO.ClienteTO;

public class ClienteDAOTest {
	ClienteTO to,copia;
	ClienteDAO dao;
	@Before
	public void setUp() throws Exception {
		to = new ClienteTO("",1);
		copia = new ClienteTO("",1);
		dao = new ClienteDAO();
	}

	@Test
	public void test00carregarDados() {
		dao.carregarDados(to);
		dao.carregarDados(copia);
		assertEquals("Teste carregarDados",to.toString(),copia.toString());
	}

}
