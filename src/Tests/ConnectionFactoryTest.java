package Tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import ConnectionFactory.ConnectionFactory;

public class ConnectionFactoryTest {

	@Test
	public void test() {
		try {
			assertNotNull("testa se a conexao nao e nula", ConnectionFactory.obtemConexao());
		} 
		catch (SQLException e) {
			e.printStackTrace();
			fail("MYSQL Fail");
		}
	}

}
