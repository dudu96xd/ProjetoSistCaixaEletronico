package DAO;
import java.sql.*;

import ConnectionFactory.ConnectionFactory;
import TO.ClienteTO;
//import TO.ClienteTO;
import TO.ContaTO;

public class ContaDAO {
	public void sacar(ContaTO to,int valor)
	{
		if(to.getSaldo()-valor>=0)
		{
			String sqlCommand = "UPDATE conta SET saldo = (saldo-?) WHERE idConta=?";
			try (Connection conn = ConnectionFactory.obtemConexao();)
        	{
        		PreparedStatement stm=null;
        		stm=conn.prepareStatement(sqlCommand);
        		stm.setInt(1,valor);
        		stm.setInt(2,to.getId());
        		stm.execute();
        		stm.close();
        	}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		else{
			System.out.println("Saldo insuficiente");
		}
	}
	public void transferir(ContaTO to,int valorATransferir,int ContaATransferir)
	{
		if(to.getSaldo()-valorATransferir>=0)
		{
			String sqlUpdate = "UPDATE conta SET saldo = (saldo-?) WHERE idConta=?";
	        try (Connection conn = ConnectionFactory.obtemConexao();)
	        {
	        	PreparedStatement stm=null;
	        	stm=conn.prepareStatement(sqlUpdate);
	        	stm.setInt(1,valorATransferir);
	        	stm.setInt(2,to.getId());
	        	stm.execute();
	        	sqlUpdate = "update conta set saldo=(saldo+?)where idConta=?";
	        	stm=null;
	        	stm=conn.prepareStatement(sqlUpdate);
	        	stm.setInt(1,valorATransferir);
	        	stm.setInt(2,ContaATransferir);
	        	stm.execute();
	        	stm.close();
	        }
	        catch(SQLException ex)
	        {
	        	ex.printStackTrace();
	        }
		}
	}
	
	public void depositar(ContaTO to,int valor)
	{
	   String sqlInsert = "UPDATE conta SET saldo = (saldo+?) WHERE idConta=?";
	   PreparedStatement stm = null;
	   try (Connection conn = ConnectionFactory.obtemConexao();)
	   {
	      stm = conn.prepareStatement(sqlInsert);
	      stm.setInt(1,valor);
	      stm.setInt(2,to.getId());
	      stm.execute();
	   }
	   catch (Exception e)
	   {
	         e.printStackTrace();
	   }
	}
	
	public void carregar(ContaTO to)
	{
	   String sqlSelect = "SELECT * FROM cliente,conta WHERE idConta = ?";
	   ResultSet rs = null;
	   try (Connection conn = ConnectionFactory.obtemConexao();)
	   {
		  PreparedStatement stm = conn.prepareStatement(sqlSelect);
	      stm.setInt(1, to.getIdConta());
	      rs = stm.executeQuery();
	      if(rs.next()){  
	         to.setSaldo(rs.getInt("saldo"));
	         to.setEstado(rs.getBoolean("estado"));
	         to.setIdConta(rs.getInt("idConta"));
	         to.setId(rs.getInt("idCliente"));
	         to.setNome(rs.getString("nome"));
	      }
	   }
	   catch (Exception e)
	   {
	      e.printStackTrace();
	   }
	}
	
	public int carregarSaldo(ContaTO to)
	{
	   String sqlSelect = "SELECT saldo FROM conta WHERE idConta = ?";
	   ResultSet rs = null;
	   try (Connection conn = ConnectionFactory.obtemConexao();)
	   {
		  PreparedStatement stm = conn.prepareStatement(sqlSelect);
	      stm.setInt(1, to.getId());
	      rs = stm.executeQuery();
	      if(rs.next()){  
	         to.setSaldo(rs.getInt("saldo"));
	      }
	      return to.getSaldo();
	      
	   }
	   catch (Exception e)
	   {
	      e.printStackTrace();
	   }
	   return to.getSaldo();
	}
	
}
