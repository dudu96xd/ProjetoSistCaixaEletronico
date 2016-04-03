package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Classes.Conta;

public class ContaTest {
	Conta conta,copia;
	@Before
	public void setUp() throws Exception {
		
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
		conta.depositar(50);
		assertEquals("teste deposito",copia.getSaldo()+50,conta.getSaldo());
	}
	
	@Test
	public void test02sacar(){
		conta.sacar(150);
		assertEquals("teste sacar",copia.getSaldo()-150,conta.getSaldo());
	}
	
	@Test
	public void test03transferir(){
		conta.transferir(10, 2);
		assertEquals("teste transferencia",conta.getSaldo(),copia.getSaldo()-10);
	}
	
	@Test
	public void test04verExtrato()
	{
		try {
			assertEquals("teste verExtrato",conta.verExtrato(),copia.verExtrato());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
