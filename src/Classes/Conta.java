package Classes;

import java.io.IOException;

import DAO.ContaDAO;
import TO.ContaTO;
import TXT.txtProjeto;

public class Conta extends Cliente {
	String agencia,conta,senha;
	int saldo,idConta;
	boolean estado;
	public Conta(String nome,String agencia,String conta,String senha,int saldo,boolean estado,int idConta,int id)
	{
		super(nome,id);
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.saldo = saldo;
		this.estado = estado;
		this.idConta=idConta;
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
	
	//FUNÇÕES
	public void transferir(int valorATransferir,int ContaATransferir)
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		to.setSaldo(dao.carregarSaldo(to));
		dao.transferir(to, valorATransferir, ContaATransferir);
		to.setSaldo(dao.carregarSaldo(to));
		setSaldo(to.getSaldo());
	}
	public String verExtrato() throws IOException
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		dao.carregar(to);
		setNome(to.getNome());
		setSaldo(to.getSaldo());
		setId(to.getId());
		setIdConta(to.getIdConta());
		setEstado(to.isEstado());
		return txtProjeto.retornaTransacoes(to.getIdConta(), "BR");
	}
	public void sacar(int valor)
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		to.setSaldo(dao.carregarSaldo(to));
		setNome(to.getNome());
		setSaldo(to.getSaldo());
		setId(to.getId());
		setIdConta(to.getIdConta());
		setEstado(to.isEstado());
		dao.sacar(to, valor);
		to.setSaldo(dao.carregarSaldo(to));
		setSaldo(to.getSaldo());
	}
	public void depositar(int valor)
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		dao.depositar(to, valor);
		to.setSaldo(dao.carregarSaldo(to));
		setSaldo(to.getSaldo());
	}
	public int verSaldo()
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		to.setSaldo(dao.carregarSaldo(to));
		setNome(to.getNome());
		setSaldo(to.getSaldo());
		setId(to.getId());
		setIdConta(to.getIdConta());
		setEstado(to.isEstado());
		return to.getSaldo();
	}
	
	public void carregar()
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		dao.carregar(to);
		setNome(to.getNome());
		setSaldo(to.getSaldo());
		setId(to.getId());
		setIdConta(to.getIdConta());
		setEstado(to.isEstado());
		setSaldo(to.getSaldo());
	}
}
