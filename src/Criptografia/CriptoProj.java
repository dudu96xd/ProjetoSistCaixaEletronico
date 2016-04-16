package Criptografia;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
public class CriptoProj
{
   static byte[]cripto;
   public static byte[] encrypt(String a, String chaveencriptacao) throws Exception 
   { 
      String aux = "AAAAAAAAAAAAAAAA";
      Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE"); 
      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES"); 
      encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(aux.getBytes("UTF-8"))); 
      cripto = encripta.doFinal(a.getBytes("UTF-8")); 
      return cripto;
   }
   
   
   public static byte[] encrypt(String agencia,String conta,String senha, String chaveencriptacao) throws Exception 
   { 
      String aux = "AAAAAAAAAAAAAAAA";
      Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE"); 
      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES"); 
      encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(aux.getBytes("UTF-8"))); 
      return encripta.doFinal(agencia.getBytes("UTF-8")); 
   }
   
   public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception
   {
      String aux = "AAAAAAAAAAAAAAAA";
      Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE"); 
      SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES"); 
      decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(aux.getBytes("UTF-8"))); 
      return new String(decripta.doFinal(textoencriptado),"UTF-8"); 
   }
   public static byte[] encrypt(String senha) throws Exception 
   { 
      return encrypt(senha,"0123456789abcdef");
   }
   
   public static String getBytes()
   {
      String saida="";
      for(int i=0;i<cripto.length;i++)
      {
         if(i==cripto.length-1)
         {
            saida+=cripto[i];
         }
         else
         saida+= cripto[i]+ " ";
      }
      return saida;
   }
   
   
}