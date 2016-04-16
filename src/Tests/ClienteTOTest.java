package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TO.ClienteTO;

public class ClienteTOTest {

	ClienteTO to,copia;
	@Before
	public void setUp() throws Exception {
		to = new ClienteTO();
		copia = new ClienteTO("Igor Eduardo",1);
	}

	@Test
	public void test00sets() {
		to.setId(copia.getId());
		to.setNome(copia.getNome());
		assertEquals("Teste sets",copia.getId(),to.getId());
		assertEquals("Teste sets",copia.getNome(),to.getNome());
	}
	
	@Test
	public void test01gets(){
		assertEquals("Teste gets",copia.getId(),1);
		assertEquals("Teste gets",copia.getNome(),"Igor Eduardo");
	}
	
	@Test
	public void test02toString()
	{
		to.setId(copia.getId());
		to.setNome(copia.getNome());
		assertEquals("Teste toString()",copia.toString(),to.toString());
	}

}
