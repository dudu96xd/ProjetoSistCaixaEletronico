package Dispenser;
import TXT.txtProjeto;
import java.io.*;
public class Dispenser
{
   public int nota1,nota5,nota10,nota20,nota50,nota100;
   
   
   public Dispenser()
   {
      try
      {
         nota1 = Integer.parseInt(txtProjeto.getValor1());
         nota5 = Integer.parseInt(txtProjeto.getValor5());
         nota10 = Integer.parseInt(txtProjeto.getValor10());
         nota20 = Integer.parseInt(txtProjeto.getValor20());
         nota50 = Integer.parseInt(txtProjeto.getValor50());
         nota100 = Integer.parseInt(txtProjeto.getValor100());
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   Dispenser(int nota1,int nota5,int nota10,int nota20,int nota50,int nota100)
   {
      this.nota1=nota1;
      this.nota5=nota5;
      this.nota10=nota10;
      this.nota20=nota20;
      this.nota50=nota50;
      this.nota100=nota100;
   }
   
   public int getNota1() {
		return nota1;
	}

	public void setNota1(int nota1) {
		this.nota1 = nota1;
	}

	public int getNota5() {
		return nota5;
	}

	public void setNota5(int nota5) {
		this.nota5 = nota5;
	}

	public int getNota10() {
		return nota10;
	}

	public void setNota10(int nota10) {
		this.nota10 = nota10;
	}

	public int getNota20() {
		return nota20;
	}

	public void setNota20(int nota20) {
		this.nota20 = nota20;
	}

	public int getNota50() {
		return nota50;
	}

	public void setNota50(int nota50) {
		this.nota50 = nota50;
	}

	public int getNota100() {
		return nota100;
	}

	public void setNota100(int nota100) {
		this.nota100 = nota100;
	}
   
   public String separarNotas(Dispenser dispenser,int valor)
   {
	   String saida="";
	   int []vet = new int[6];
	   vet[0]=dispenser.nota1;
	   vet[1]=dispenser.nota5;
	   vet[2]=dispenser.nota10;
	   vet[3]=dispenser.nota20;
	   vet[4]=dispenser.nota50;
	   vet[5]=dispenser.nota100;
	   try 
	   {
		   while(0<valor)
		   {
			   int qnt=0;
			   int aux=valor;
			   if(valor>=100&&dispenser.nota100>0)
			   {
				   dispenser.nota100--;
				   valor-=100;
				   qnt++;
				   while(valor>=100&&dispenser.nota100>0)
				   {
					   valor-=100;
					   qnt++;
					   dispenser.nota100--;
				   }
				   saida+="\nNota de 100 Reais=> ";
			   }
			   else
				   if(valor>=50&&dispenser.nota50>0)
				   {
					   dispenser.nota50--;
					   valor-=50;
					   qnt++;
					   while(valor>=50&&dispenser.nota50>0)
					   {
						   valor-=50;
						   qnt++;
						   dispenser.nota50--;
					   }
					   saida+="\nNota de 50 Reais=> ";
				   }
				   else
					   if(valor>=20&&dispenser.nota20>0)
					   {
						   dispenser.nota20--;
						   valor-=20;
						   qnt++;
						   while(valor>=20&&dispenser.nota20>0)
						   {
							   valor-=20;
							   qnt++;
							   dispenser.nota20--;
						   }
						   saida+="\nNota de 20 Reais=> ";
					   }
					   else
						   if(valor>=10&&dispenser.nota10>0)
						   {
							   dispenser.nota10--;
							   valor-=10;
							   qnt++;
							   while(valor>=10&&dispenser.nota10>0)
							   {
								   valor-=10;
								   qnt++;
								   dispenser.nota10--;
							   }
							   saida+="\nNota de 10 Reais=> ";
						   }
						   else
							   if(valor>=5&&dispenser.nota5>0)
							   {
								   dispenser.nota5--;
								   valor-=5;
								   qnt++;
								   while(valor>=5&&dispenser.nota5>0)
								   {
									   valor-=5;
									   qnt++;
									   dispenser.nota5--;
								   }
								   saida+="\nNota de 5 Reais=> ";
							   }
							   else
								   if(valor>=1&&dispenser.nota1>0)
								   {  
									   dispenser.nota1--;
									   valor-=1;
									   qnt++;
									   while(valor>=1&&dispenser.nota1>0)
									   {
										   valor-=1;
										   qnt++;
										   dispenser.nota1--;
									   }
									   saida+="\nNota de 1 Real=> ";
								   }
								   else 
									   if(aux==valor)
									   {
										   dispenser.nota1 = vet[0];
										   dispenser.nota5 = vet[1];
										   dispenser.nota10 = vet[2];
										   dispenser.nota20 = vet[3];
										   dispenser.nota50 = vet[4];
										   dispenser.nota100 = vet[5];
										   txtProjeto.atualizarDispenser(dispenser);
										   return "Sem cédulas necessárias para sacar";
									   }
			   		saida+=qnt;
		   		}

		   	txtProjeto.atualizarDispenser(dispenser);
	   }
	   catch (IOException e) {
		e.printStackTrace();}
	   return saida+"\n";
   }

}