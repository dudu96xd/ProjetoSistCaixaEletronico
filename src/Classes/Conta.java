package Classes;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFileChooser;

import Criptografia.CriptoProj;
import DAO.ContaDAO;
import Dispenser.Dispenser;
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
	public Conta() {
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
		return super.toString()+"// Conta [agencia=" + agencia + ", conta=" + conta + ", senha=" + senha + ", saldo=" + saldo + ", idConta="
				+ idConta + ", estado=" + estado + "]";
	}
	
	//FUNÇÕES
	public void transferir(int valorATransferir,int ContaATransferir) throws IOException
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		to.setSaldo(dao.carregarSaldo(to));
		setSaldo(to.getSaldo());
		if(saldo-valorATransferir>0)
		{
			dao.transferir(to, valorATransferir, ContaATransferir);
			to.setSaldo(dao.carregarSaldo(to));
			setSaldo(to.getSaldo());
			salvarTransferencia(valorATransferir, ContaATransferir, to);
		}
	}
	private void salvarTransferencia(int valorATransferir, int ContaATransferir, ContaTO to) throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Extratos_BR.txt");
		Calendar now = Calendar.getInstance();
        Date a = now.getTime();
        DateFormat formataData = DateFormat.getDateInstance(DateFormat.SHORT,new Locale("pt","BR"));
        String dataBR = String.format(formataData.format(a), now);
        formataData = DateFormat.getDateInstance(DateFormat.SHORT,new Locale("en","US"));
        String dataUS = String.format(formataData.format(a), now);
        formataData = DateFormat.getDateInstance(DateFormat.SHORT,new Locale("es","ES"));
        String dataES = String.format(formataData.format(a), now);
        String linhaBR = "<tr><td width='10%'>"+dataBR+"</td><td width='75%'>Transferência para a Agência "+agencia+" - Conta "+conta+"</td><td width='15%'> <p class='verm'>"+valorATransferir+",00-</p></td></tr>";
        String linhaUS = "<tr><td width='10%'>"+dataUS+"</td><td width='75%'>Transfer to the Agency "+agencia+" - Account "+conta+"</td><td width='15%'> <p class='verm'>"+valorATransferir+",00-</p></td></tr>";
        String linhaES = "<tr><td width='10%'>"+dataES+"</td><td width='75%'>Transferencia para la Agencia "+agencia+" - Cuenta "+conta+"</td><td width='15%'><p class='verm'>"+valorATransferir+",00-</p></td></tr>";
        txtProjeto.salvarExtrato(file,to.getIdConta(),linhaBR,"BR");
        txtProjeto.salvarExtrato(file,to.getIdConta(),linhaUS,"US");
        txtProjeto.salvarExtrato(file,to.getIdConta(),linhaES,"ES");
        String Conta[] = new String[4];
        Conta=txtProjeto.confereId(Conta,ContaATransferir);
        linhaBR = "<tr><td width='10%'>"+dataBR+"</td><td width='75%'>Recebeu da Agência "+Conta[0]+" - Conta "+Conta[1]+"</td><td width='15%'> "+valorATransferir+",00</td></tr> ;"+ContaATransferir;
        linhaUS = "<tr><td width='10%'>"+dataUS+"</td><td width='75%'>Received of the Agency "+Conta[0]+" - Account "+Conta[1]+"</td><td width='15%'> "+valorATransferir+",00</td></tr> ;"+ContaATransferir;
        linhaES = "<tr><td width='10%'>"+dataES+"</td><td width='75%'>Recibió da Agencia "+Conta[0]+" - Cuenta "+Conta[1]+"</td><td width='15%'> "+valorATransferir+",00</td></tr> ;"+ContaATransferir;
		txtProjeto.salvarExtrato(file,ContaATransferir,linhaBR,"BR");
	    txtProjeto.salvarExtrato(file,ContaATransferir,linhaUS,"US");
	    txtProjeto.salvarExtrato(file,ContaATransferir,linhaES,"ES");
	}
	public String verExtrato() throws IOException
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		dao.carregar(to);
		getTO(to);
		return txtProjeto.retornaTransacoes(to.getIdConta(), "BR");
	}
	public void sacar(int valor) throws IOException
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		Dispenser dispenser = new Dispenser();
		to.setSaldo(dao.carregarSaldo(to));
		getTO(to);
		if(saldo-valor>0)
		{
			String estadoDispenser ="";
			System.out.println(estadoDispenser = dispenser.separarNotas(dispenser, valor));
			if(!estadoDispenser.equals("Sem cédulas necessárias para sacar"))
			{
				dao.sacar(to, valor);
				to.setSaldo(dao.carregarSaldo(to));
				setSaldo(to.getSaldo());
				String operacao[] = new String[3];
				operacao[0] = "Saque";
				operacao[1] = "Withdraw";
				operacao[2] = "Saque";
				salvarExtrato(valor, to,"<p class='verm'>", operacao,"-</p>");
			}
		}
	}
	public void depositar(int valor)throws IOException
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		dao.depositar(to, valor);
		to.setSaldo(dao.carregarSaldo(to));
		setSaldo(to.getSaldo());
		String operacao[] = new String[3];
		operacao[0] = "Depósito";
		operacao[1] = "Deposit";
		operacao[2] = "Deposito";
		salvarExtrato(valor, to,"", operacao,"");
	}
	private void salvarExtrato(int valor, ContaTO to,String cor, String[] operacao,String sinal) throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Extratos_BR.txt");
		Calendar now = Calendar.getInstance();  
		Date a = now.getTime();
		DateFormat formataData = DateFormat.getDateInstance(DateFormat.SHORT,new Locale("pt","BR"));
		String dataBR = String.format(formataData.format(a), now);
		formataData = DateFormat.getDateInstance(DateFormat.SHORT,new Locale("en","US"));
		String dataUS = String.format(formataData.format(a), now);
		formataData = DateFormat.getDateInstance(DateFormat.SHORT,new Locale("es","ES"));
		String dataES = String.format(formataData.format(a), now);
		String linhaBR = "<tr><td width='10%'>"+dataBR+"</td><td width='75%'>"+operacao[0]+"</td><td width='15%'>"+cor+valor+",00"+sinal+"</td></tr>;"+id; 
		String linhaUS = "<tr><td width='10%'>"+dataUS+"</td><td width='75%'>"+operacao[1]+"</td><td width='15%'>"+cor+valor+",00"+sinal+"</td></tr>;"+id;
		String linhaES = "<tr><td width='10%'>"+dataES+"</td><td width='75%'>"+operacao[2]+"</td><td width='15%'>"+cor+valor+",00"+sinal+"</td></tr>;"+id; 
		txtProjeto.salvarExtrato(file,to.getIdConta(),linhaBR,"BR");
		txtProjeto.salvarExtrato(file,to.getIdConta(),linhaUS,"US");
		txtProjeto.salvarExtrato(file,to.getIdConta(),linhaES,"ES");
	}
	public int verSaldo()
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		to.setSaldo(dao.carregarSaldo(to));
		getTO(to);
		return to.getSaldo();
	}
	
	public void getTO(ContaTO to) {//pega dados da classe ContaTO e passa os mesmos para a classe Conta
		setNome(to.getNome());
		setSaldo(to.getSaldo());
		setId(to.getId());
		setIdConta(to.getIdConta());
		setEstado(to.isEstado());
	}
	
	public boolean logar() throws IOException, Exception
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		if(isEstado()){
			CriptoProj.encrypt(senha);
			to.setSenha(CriptoProj.getBytes());
			setSenha(to.getSenha());
			if(txtProjeto.confere(to))
			{
				setIdConta(to.getIdConta());
				return true;
			}
			else
				return false;
		}
		else return false;
	}
	
	public void carregarConta()
	{
		ContaTO to = new ContaTO(nome,agencia,conta,senha,saldo,estado,idConta,id);
		ContaDAO dao = new ContaDAO();
		dao.carregar(to);
		getTO(to);
	}
}
