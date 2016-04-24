package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TO.ContaTO;

public class ContaTOTest {
	ContaTO to,copia;
	
	@Before
	public void setUp() throws Exception {
		to = new ContaTO();
		copia = new ContaTO("Igor Eduardo","12345","45678","78901",900,false,1,1);
	}

	@Test
	public void test00sets() {
		to.setNome("Igor Eduardo");
		to.setId(1);
		to.setAgencia("12345");
		to.setConta("45678");
		to.setSenha("78901");
		to.setIdConta(1);
		to.setEstado(false);
		to.setSaldo(900);
		assertEquals("Teste sets",to.toString(),copia.toString());
	}
	
	@Test
	public void test01gets()
	{
		assertEquals("teste gets","Igor Eduardo",copia.getNome());
		assertEquals("teste gets","12345",copia.getAgencia());
		assertEquals("teste gets","45678",copia.getConta());
		assertEquals("teste gets","78901",copia.getSenha());
		assertEquals("teste gets",1,copia.getId());
		assertEquals("teste gets",1,copia.getIdConta());
		assertEquals("teste gets",false,copia.isEstado());
		assertEquals("teste gets",900,copia.getSaldo());
	}
	
	@Test
	public void test02toString()
	{
		to = new ContaTO("Igor Eduardo","12345","45678","78901",900,false,1,1);
		assertEquals("teste toString()",to.toString(),copia.toString());
	}

}
