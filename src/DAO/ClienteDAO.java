package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectionFactory.ConnectionFactory;
import TO.ClienteTO;

public class ClienteDAO {
	public void carregarDados(ClienteTO cli){
		String SQLCommand = "SELECT nome,idCliente FROM cliente WHERE idCliente=?";
		ResultSet rs = null;
		PreparedStatement stm = null;
		try (Connection conn = ConnectionFactory.obtemConexao();)
		   {
			  stm = conn.prepareStatement(SQLCommand);
		      stm.setInt(1, cli.getId());
		      rs = stm.executeQuery();
		      if(rs.next()){  
		         cli.setNome(rs.getString("nome"));
		         cli.setId(rs.getInt("idCliente"));
		      }
    	}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
}
