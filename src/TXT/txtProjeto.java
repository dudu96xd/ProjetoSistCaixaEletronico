package TXT;
import java.io.*;
import java.nio.channels.FileChannel;

import javax.swing.*;
import Dispenser.Dispenser;
import TO.ClienteTO;
import TO.ContaTO;
import Criptografia.CriptoProj;
import java.util.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
public class txtProjeto
{
   static String nome;
   static File arquivo,arquivoTmp;
   static int contas[];
   static File saida;
   
   public txtProjeto()
   {}
   
   public static  int numContas()throws IOException
   {
	  File file = getProjPath();
      InputStream entrada = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
      String linha = null;
      int cont=0;
      while((linha = br.readLine())!=null)
      {
         if(linha.equals(""))
         {}
         else
            cont++;
      }
      br.close();
      entrada.close();
      return cont;
   }     
   public static void atualizaContas() throws IOException
   {
      File file = getProjPath();
      InputStream entrada = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
      String linha = null;
      String aux[];
         
      contas = new int[numContas()];
      int cont=0;
      br = new BufferedReader(new InputStreamReader(entrada));
      while((linha = br.readLine())!=null)
      {
         if(linha.equals("")){}
         else{
            aux = linha.split(";");
            contas[cont]=Integer.parseInt(aux[3]);
            cont++;}
      }
      entrada.close();
      br.close();
   }
   
   public static boolean binaria(int conta)
   {
      int n = contas.length, inicio=0,fim=n-1,meio;
      while(inicio<=fim)
      {
         meio = (inicio+fim)/2;
         if(conta==contas[meio])
            return true;
         else if(contas[meio]<conta)
            inicio=meio+1;
         else
            fim=meio-1;
      }
      return false;
   }
   public static boolean confere(ContaTO cli)  
           throws IOException,Exception {  
	   File file = getProjPath();
      InputStream entrada = new FileInputStream(file);
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));  
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String agenciaLida = vet[0];
         String contaLida = vet[1];
         String senhaLida = vet[2];
         String idLido = vet[3];
         if(agenciaLida.equals(cli.getAgencia())&&contaLida.equals(cli.getConta())&&senhaLida.equals(cli.getSenha())){
        	 cli.setIdConta(Integer.parseInt(idLido));
        	 return true;
         }
      }
      br.close();
      entrada.close();
      
      return false;  
   }
   public static void copyFile(File source, File destination) throws IOException {
       if (destination.exists())
           destination.delete();
       FileChannel sourceChannel = null;
       FileChannel destinationChannel = null;
       try {
           sourceChannel = new FileInputStream(source).getChannel();
           destinationChannel = new FileOutputStream(destination).getChannel();
           sourceChannel.transferTo(0, sourceChannel.size(),
                   destinationChannel);
       } finally {
           if (sourceChannel != null && sourceChannel.isOpen())
               sourceChannel.close();
           if (destinationChannel != null && destinationChannel.isOpen())
               destinationChannel.close();
      }
  }
   public static String[] confereId(String[]saida,int id)  
           throws IOException {  
	   File file = getProjPath();
	   String linha =null;
	   InputStream entrada = new FileInputStream(file);
	   BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
	   while((linha = br.readLine())!=null)
	   {
		  String vet[] = linha.split(";");
		  String idLido = vet[3];
		  String agenciaLida=vet[0];
		  String contaLida=vet[1];
		  if(idLido.equals(id+""))
		  {
		     saida[0]=agenciaLida;
		     saida[1]=contaLida;
		     return saida;}
		  }
		   br.close();
		   entrada.close();
      return null;  
   }
   
   public static boolean confere(String agencia,String conta,String senha)  
           throws IOException {  
	  File file = getProjPath();
      InputStream entrada = new FileInputStream(file);
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String agenciaLida = vet[0];
         String contaLida = vet[1];
         String senhaLida = vet[2];
         if(agenciaLida.equals(agencia)&&contaLida.equals(conta)&&senhaLida.equals(senha))
            return true;
      }
      br.close();
      entrada.close();
      return false;
   }
   
   public static int confere(String agencia,String conta)  
           throws IOException {  
	   File file = getProjPath();
      InputStream entrada = new FileInputStream(file);
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String agenciaLida = vet[0];
         String contaLida = vet[1];
         if(agenciaLida.equals(agencia)&&contaLida.equals(conta))
            return Integer.parseInt(vet[3]);
      }
      br.close();
      entrada.close();
      return -1;
   }

   
   
   
   
   public static String retornaTransacoes(int id,String pais)throws IOException
   {
      String linha =null;
      String saida="";
      File file = getExtratoPath(pais);
      InputStream entrada = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada)); 
      while((linha = br.readLine())!=null)
      {
    	  String vet[] = linha.split(";");
    	  String idLido = vet[0];
    	  String Transacao = vet[1];
    	  if(Integer.parseInt(idLido)==id)
    	  saida+=Transacao;
      }
      br.close();
      entrada.close();
      return saida;
   }
   
   
   public static void salvarExtrato(File file,int id,String operacao,String pais)throws IOException
   {
	  file = getExtratoPath(pais);
      BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
	  if(file.canWrite())
	  {
		  bw.write(id+";"+operacao);
		  bw.newLine();
		  bw.flush();
		  bw.close();
		  return;
	  }
   }

private static File getExtratoPath(String pais) {
	File file;
	JFileChooser chooser = new JFileChooser();
	  chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	  file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Extratos_"+pais+".txt");
	return file;
}
   
   public static void criarDispenser(Dispenser dis) throws IOException
   {
	  File arq = getDispenserPath();
      FileWriter file = new FileWriter(arq);
      PrintWriter print = new PrintWriter(file);
      print.printf("nota1="+dis.nota1+"%nnota5="+dis.nota5+"%nnota10="+dis.nota10+"%nnota20="+dis.nota20+"%nnota50="+dis.nota50+"%nnota100="+dis.nota100);
      print.close();
   }
   
   public static void atualizarDispenser(Dispenser dis) throws IOException
   {
      criarDispenser(dis);
   }
   
   public static void setUp() throws IOException
   {
	   JFileChooser chooser = new JFileChooser();
	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	   File file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa");
	   if(!file.exists())
	   {
		   file.mkdir();
		   copyFile(new File("proj.txt"),file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\proj.txt"));
		   copyFile(new File("Extratos_BR.txt"),file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Extratos_BR.txt"));
		   copyFile(new File("Extratos_US.txt"),file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Extratos_US.txt"));
		   copyFile(new File("Extratos_ES.txt"),file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Extratos_ES.txt"));
		   copyFile(new File("Dispenser.txt"),file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Dispenser.txt"));
	   }
   }
   
   public static  boolean confereDois(ContaTO cli)
   throws IOException,Exception {  
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      CriptoProj cripto = new CriptoProj();
      cripto.encrypt(cli.getSenha());
      cli.setSenha(cripto.getBytes());
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         if(!linha.equals(""))
         {
            String vet[] = linha.split(";");
            String agenciaLida = vet[0];
            String contaLida = vet[1];
            String senhaLida = vet[2];
            if(agenciaLida.equals(cli.getAgencia())&&contaLida.equals(cli.getConta())&&!senhaLida.equals(cli.getSenha()))
               return true;
         }
      }
      entrada.close();
      br.close();
      return false; 
   }  
   
   public static boolean confereSenha(ContaTO cli)
   throws IOException,Exception {
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      CriptoProj.encrypt(cli.getSenha());
      cli.setSenha(CriptoProj.getBytes());
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         
         String senhaLida = vet[2];
         if(senhaLida.equals(cli.getSenha()))
            return true;
      }
      br.close();
      entrada.close();
      return false; 
   }  
   
   public static String getValor1()
   throws IOException {  
      Properties p = new Properties();  
      File file = getDispenserPath();
      p.load(new FileInputStream(/*"C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"*/file));  
      String nota1Lido = p.getProperty("nota1");  
      return nota1Lido;
   }

private static File getDispenserPath() {
	JFileChooser chooser = new JFileChooser();
	  chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	  File file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\Dispenser.txt");
	return file;
} 
   public static String getValor5()
   throws IOException {  
      Properties p = new Properties();
      File file = getDispenserPath();
      p.load(new FileInputStream(/*"C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"*/file)); 
      String nota5Lido = p.getProperty("nota5");  
      return nota5Lido;
   }
   public static String getValor10()
   throws IOException {  
      Properties p = new Properties();
      File file = getDispenserPath();
      p.load(new FileInputStream(/*"C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"*/file)); 
      String nota10Lido = p.getProperty("nota10");  
      return nota10Lido;
   }
   public static String getValor20()
   throws IOException {  
      Properties p = new Properties();  
      File file = getDispenserPath();
      p.load(new FileInputStream(/*"C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"*/file)); 
      String nota20Lido = p.getProperty("nota20");  
      return nota20Lido;
   }
   public static String getValor50()
   throws IOException {  
      Properties p = new Properties();  
      File file = getDispenserPath();
      p.load(new FileInputStream(/*"C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"*/file));  
      String nota50Lido = p.getProperty("nota50");  
      return nota50Lido;
   }
   public static String getValor100()
   throws IOException {  
      Properties p = new Properties();  
      File file = getDispenserPath();
      p.load(new FileInputStream(/*"C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"*/file));  
      String nota100Lido = p.getProperty("nota100");  
      return nota100Lido;
   }
   
   
   public static String getIdConta(String conta)throws IOException
   {
	   File file = getProjPath();
	   InputStream entrada = new FileInputStream(file);
	   String linha =null;
	   BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
	   String vet[];
	   while((linha = br.readLine())!=null)
	   {
		   vet = linha.split(";");
		   if(conta.equals(vet[1]))
		   {
			   return vet[3];
		   }
	   }
	   br.close();
	   entrada.close();
	   return "-1"; 
   }

private static File getProjPath() {
	JFileChooser chooser = new JFileChooser();
	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	   File file = new File(chooser.getCurrentDirectory()+"\\ProjetoSistCaixa\\proj.txt");
	return file;
}
   
   
   
}