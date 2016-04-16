package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Classes.Cliente;

public class ClienteTest {

	Cliente cli,copia;
	@Before
	public void setUp() throws Exception {
		cli = new Cliente("",1);
		copia = new Cliente("",1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test00Carregar() {
		cli.carregar();
		copia.carregar();
		assertEquals("Teste carregar",cli.toString(),copia.toString());
	}
}
