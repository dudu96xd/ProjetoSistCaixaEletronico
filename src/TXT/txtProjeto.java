package TXT;
import java.io.*;
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
   
   
   
   public static boolean checaBloqueada(String id) throws IOException
   {
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String idLido = vet[3];
         if(id.equals(idLido)&&vet[4].equals("true"))
            return true;
      }
      entrada.close();
      br.close();
      return false; 
   }
   public static void alteraLinha(String palavraAntiga, String palavraNova,String id) {
      try{
         arquivo = new File("proj.txt");
         arquivoTmp = new File("proj-tmp.txt");
         BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
         BufferedReader reader = new BufferedReader(new FileReader(arquivo));
         String linha;
         String[]vet;
         boolean check=true;
         while ((linha = reader.readLine()) != null) {
            vet = linha.split(";");
            if (vet[4].equals(palavraAntiga)&&check&&vet[3].equals(id)) {
               linha = linha.replace(palavraAntiga, palavraNova);
               check=false;
            }
            writer.write(linha);
            writer.newLine();
         }
         writer.flush();
         writer.close();
         reader.close();
         sobrepoe(arquivo,arquivoTmp);
      }
      catch(IOException ex)
      {ex.printStackTrace();}
   }
   
   public static  void sobrepoe(File arquivo,File arquivoTmp)
   {
      arquivo.delete();
      arquivoTmp.renameTo(arquivo);
   }
   public static  int numContas()throws IOException
   {
      InputStream entrada = new FileInputStream("proj.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
      String linha = null;
      String aux[];
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
      InputStream entrada = new FileInputStream("proj.txt");
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
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));  
      CriptoProj cripto = new CriptoProj();
      cripto.encrypt(cli.getSenha());
      cli.setSenha(cripto.getBytes());
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String agenciaLida = vet[0];
         String contaLida = vet[1];
         String senhaLida = vet[2];
         String idLido = vet[3];
         if(agenciaLida.equals(cli.getAgencia())&&contaLida.equals(cli.getConta())&&senhaLida.equals(cli.getSenha())&&idLido.equals(cli.getId()))
            return true;
      }
      br.close();
      entrada.close();
      
      return false;  
   }
   
   public static String[] confereId(String[]saida,int id)  
           throws IOException {  
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String idLido = vet[3];
         String agenciaLida=vet[0];
         String contaLida=vet[1];
         if(idLido.equals(id+"")){
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
      InputStream entrada = new FileInputStream("proj.txt");
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
      InputStream entrada = new FileInputStream("proj.txt");
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

   
   
   public static void salvar(File file,ContaTO cli) throws IOException,Exception {  
      file = new File("proj.txt");
      BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
      CriptoProj cripto = new CriptoProj();
      cripto.encrypt(cli.getSenha());
      String senhaCripto = cripto.getBytes();
      if(file.canWrite())
      {
         bw.write(cli.getAgencia()+";"+cli.getConta()+";"+senhaCripto+";");
         bw.newLine();
         bw.flush();
         bw.close();
      }
   } 
   
   
   public static String retornaTransacoes(int id,String pais)throws IOException
   {
      InputStream entrada = new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Extratos_"+pais+".txt");
      String linha =null;
      String saida="";
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         String vet[] = linha.split(";");
         String idLido = vet[0];
         String Transacao = vet[1];
         if(Integer.parseInt(idLido)==id)
            saida+=Transacao+"\n";
      }
      br.close();
      entrada.close();
      return saida;
   }
   
   public static void salvarExtrato(File file,int id,String operacao,String pais)throws IOException
   {
      file = new File("Extratos_"+pais+".txt");
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
   
   public static String carregar(InputStream entrada) throws IOException 
   {  
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));  
      String linha = null;  
      String[] campos=null;
      ContaTO to = new ContaTO();
      CriptoProj proj = new CriptoProj();
      while ((linha = br.readLine()) != null) {  
         String vet[] = linha.split(";");
         to.setAgencia(vet[0]);
         to.setConta(vet[1]);
      }  
      entrada.close();
      br.close();
      return to.getAgencia()+"\n"+to.getConta();  
   }
   
   public static void encriptaSenha(String senha) throws IOException,Exception
   {
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
      String[]campos=null;
      CriptoProj cripto = new CriptoProj();
      cripto.encrypt(senha);
      senha = cripto.getBytes();
      while((linha = br.readLine())!=null)
      {
         campos = linha.split(";");
         String senhaCripto = campos[2];
         if(senhaCripto.equals(senha))System.out.println("OK");
      }
      entrada.close();
      br.close();
   }
   
      
   public void criarDispenser(Dispenser dis) throws IOException
   {
      FileWriter file = new FileWriter("Dispenser.txt");
      PrintWriter print = new PrintWriter(file);
      print.printf("nota1="+dis.nota1+"%nnota5="+dis.nota5+"%nnota10="+dis.nota10+"%nnota20="+dis.nota20+"%nnota50="+dis.nota50+"%nnota100="+dis.nota100);
      print.close();
   }
   
   public void atualizarDispenser(Dispenser dis) throws IOException
   {
      criarDispenser(dis);
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
      CriptoProj cripto = new CriptoProj();
      cripto.encrypt(cli.getSenha());
      cli.setSenha(cripto.getBytes());
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
      p.load(new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"));  
      String nota1Lido = p.getProperty("nota1");  
      return nota1Lido;
   } 
   public static String getValor5()
   throws IOException {  
      Properties p = new Properties();  
      p.load(new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"));  
      String nota5Lido = p.getProperty("nota5");  
      return nota5Lido;
   }
   public static String getValor10()
   throws IOException {  
      Properties p = new Properties();  
      p.load(new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"));  
      String nota10Lido = p.getProperty("nota10");  
      return nota10Lido;
   }
   public static String getValor20()
   throws IOException {  
      Properties p = new Properties();  
      p.load(new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"));  
      String nota20Lido = p.getProperty("nota20");  
      return nota20Lido;
   }
   public static String getValor50()
   throws IOException {  
      Properties p = new Properties();  
      p.load(new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"));  
      String nota50Lido = p.getProperty("nota50");  
      return nota50Lido;
   }
   public static String getValor100()
   throws IOException {  
      Properties p = new Properties();  
      p.load(new FileInputStream("C:\\Users\\dudu9.DESKTOP-35BTD19\\workspace\\Projeto\\src\\txt\\Dispenser.txt"));  
      String nota100Lido = p.getProperty("nota100");  
      return nota100Lido;
   }
   
   
   public static boolean confereSenha(String senha,String id)
   throws IOException,Exception {  
      InputStream entrada = new FileInputStream("proj.txt");
      String linha =null;
      CriptoProj cripto = new CriptoProj();
      cripto.encrypt(senha);
      senha = cripto.getBytes();
      BufferedReader br = new BufferedReader(new InputStreamReader(entrada));   
      while((linha = br.readLine())!=null)
      {
         if(!linha.equals(""))
         {
            String vet[] = linha.split(";");
         
            String senhaLida = vet[2];
            String idLido = vet[3];
            if(senhaLida.equals(senha)&&idLido.equals(id))
               return true;
         }
      }
      entrada.close();
      br.close();
      return false; 
   }   
   
   public static String getIdConta(String conta)throws IOException
   {
      InputStream entrada = new FileInputStream("proj.txt");
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
   
   
   
}