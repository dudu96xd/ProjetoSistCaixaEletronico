package TO;

public class ContaTO extends ClienteTO {
	String agencia,conta,senha;
	int saldo,idConta;
	boolean estado;
	public ContaTO(String nome,String agencia,String conta,String senha,int saldo,boolean estado,int idConta,int id)
	{
		super(nome,id);
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.saldo = saldo;
		this.estado = estado;
		this.idConta=idConta;
	}
	public ContaTO() {
		super("",0);
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Conta [agencia=" + agencia + ", conta=" + conta + ", senha=" + senha + ", saldo=" + saldo + ", idConta="
				+ idConta + ", estado=" + estado + "]";
	}
	
}
