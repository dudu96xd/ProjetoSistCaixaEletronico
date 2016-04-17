package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Classes.Conta;
import TXT.txtProjeto;

public class ContaTest {
	Conta conta,copia;
	@Before
	public void setUp() throws Exception {
		txtProjeto.setUp();
		conta = new Conta("Igor Eduardo","12345","45678","78901",900,false,1,1);
		copia = new Conta();
		copia.setIdConta(1);
		copia.setId(1);
		copia.carregarConta();
		conta.carregarConta();
	}
	
	@Test
	public void test05carregarConta()
	{
		copia.carregarConta();
		assertEquals("teste carregar",conta.getNome(),copia.getNome());
		assertEquals("teste carregar",conta.getSaldo(),copia.getSaldo());
		assertEquals("teste carregar",conta.isEstado(),copia.isEstado());
	}
	@Test
	public void test00verSaldo() {
		assertEquals("teste saldo",conta.verSaldo(),conta.getSaldo());
	}
	
	@Test 
	public void test01depositar(){
		try {
			conta.depositar(50);
			assertEquals("teste deposito",copia.getSaldo()+50,conta.getSaldo());
		} catch (IOException e) {
			e.printStackTrace();
			fail("erro no txt");
		}
	}
	
	@Test
	public void test02sacar(){
		try {
			conta.sacar(150);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("falha no txt");
		}
		assertEquals("teste sacar",copia.getSaldo()-150,conta.getSaldo());
	}
	
	@Test
	public void test06Logar()
	{
		try {
			assertEquals("teste logar",conta.logar(),false);
		} catch (Exception e) {
			e.printStackTrace();
			fail("falha ao tentar logar");
		}
	}
	
	@Test
	public void test03transferir(){
		try {
			String contaAEnviar = txtProjeto.getIdConta("brbrb");
			conta.transferir(10, Integer.parseInt(contaAEnviar));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("fail txt");
		}
		assertEquals("teste transferencia",conta.getSaldo(),copia.getSaldo()-10);
	}
	
	@Test
	public void test04verExtrato()
	{
		try {
			assertEquals("teste verExtrato",conta.verExtrato(),copia.verExtrato());
		} catch (IOException e) {
			e.printStackTrace();
			fail("falha o no txt");
		}
	}
	
}
