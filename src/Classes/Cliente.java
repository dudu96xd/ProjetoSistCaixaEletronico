package Classes;

import DAO.ClienteDAO;
import TO.ClienteTO;

public class Cliente {
	protected String nome;
	protected int id;
	public Cliente(String nome,int id)
	{
		this.id = id;
		this.nome=nome;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Nome: "+nome+", Id: "+id;
	}
	
	public void carregar()
	{
		ClienteTO to = new ClienteTO(nome,id);
		ClienteDAO dao = new ClienteDAO();
		dao.carregarDados(to);
		setNome(to.getNome());
	}
}
