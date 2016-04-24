package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DAO.ContaDAO;
import TO.ContaTO;

public class ContaDAOTest {
	ContaTO to,copia,copia1;
	ContaDAO dao;
	@Before
	public void setUp() throws Exception {
		to = new ContaTO("Igor Eduardo","12345","45678","78901",900,false,1,1);
		copia = new ContaTO("Igor Eduardo","12345","45678","78901",900,false,1,1);
		copia1 = new ContaTO("Renan Aragaki","12345","45678","78901",900,false,2,1);
		dao = new ContaDAO();
		dao.carregar(copia);
		dao.carregar(copia1);
	}

	@Test
	public void test00sacar() {
		dao.sacar(to, 20);
		to.setSaldo(dao.carregarSaldo(to));
		assertEquals("teste sacar",to.getSaldo(),copia.getSaldo()-20);
	}

	@Test
	public void test01transferir()
	{
		ContaTO to1 = new ContaTO("Renan Aragaki","12345","45678","78901",900,false,2,1);
		dao.transferir(to, 20, to1.getIdConta());
		dao.carregar(to1);
		to.setSaldo(dao.carregarSaldo(to));
		assertEquals("teste transferir",to.getSaldo(),copia.getSaldo()-20);
		assertEquals("teste transferir",to1.getSaldo(),copia1.getSaldo()+20);
	}
	
	@Test
	public void test02carregar()
	{
		to = new ContaTO("Igor Eduardo","12345","45678","78901",900,false,1,1);
		copia = new ContaTO("","","","",0,true,1,1);
		dao.carregar(to);
		dao.carregar(copia);
		assertEquals("teste carregar",to.getSaldo(),copia.getSaldo());
		assertEquals("teste carregar",to.getNome(),copia.getNome());
		assertEquals("teste carregar",to.isEstado(),copia.isEstado());
	}
	
	@Test
	public void test03depositar()
	{
		dao.depositar(to, 10);
		dao.depositar(copia,10);
		copia.setSaldo(dao.carregarSaldo(copia));
		to.setSaldo(dao.carregarSaldo(to));
		assertEquals("teste depositar",to.getSaldo(),copia.getSaldo());
	}
	
	@Test
	public void test04carregarSaldo()
	{
		to.setSaldo(dao.carregarSaldo(to));
		copia.setSaldo(dao.carregarSaldo(copia));
		assertEquals("teste carregarSaldo",to.getSaldo(),copia.getSaldo());
	}
}
