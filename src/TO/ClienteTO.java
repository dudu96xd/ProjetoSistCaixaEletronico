package TO;

public class ClienteTO {
	protected String nome;
	protected int id;
	public ClienteTO(String nome,int id)
	{
		this.id=id;
		this.nome=nome;
	}
	public ClienteTO() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Nome: "+nome+", Id: "+id;
	}
	
	
	
}
