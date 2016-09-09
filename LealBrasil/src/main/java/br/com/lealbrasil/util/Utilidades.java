package br.com.lealbrasil.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antlr.StringUtils;


@SuppressWarnings("serial")
public class Utilidades implements Serializable {
	 private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	   


	
	public static Calendar retornaCalendario()  {		
		TimeZone tz = TimeZone.getDefault();
		Calendar c = Calendar.getInstance(tz);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		String sData = sd.format(c.getTime());
		
		
		try {
			c.setTime(sd.parse(sData));
			c.clear(Calendar.ZONE_OFFSET);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;
		
	}
	public static Calendar retornaData(){		
		Calendar c = Calendar.getInstance();
		return c;
	}
	public static Date retornaValidade(int diasValidade){
		Calendar c = Calendar.getInstance();		
		c.add(Calendar.DATE, +diasValidade);	
		
				
		return c.getTime() ;
	}
	
	
	public static String retiraCaracteres(String texto) {
		if (texto==null)
			texto ="";
		texto = texto.replaceAll("[^\\p{ASCII}]", "").replaceAll("[.-]", "").
		replaceAll("\\/", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "") ;
		texto = texto.replaceAll("_", "");		
		return texto;
	}
	public static String retiraVazios(String texto){
		if (texto == null)
		texto ="";
		texto.trim();
		return texto;
	}
	public static boolean estaVazio(String texto){
		texto = StringUtils.stripBack(texto, " \t");
		texto = StringUtils.stripFront(texto, " \t");
		return texto.length()<=0;
	}
	
	
	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	   public static boolean isValidCPF(String cpf) {
	      if ((cpf==null) || (cpf.length()!=11)) return false;

	      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	      boolean retorno = cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	      return retorno;
	   }

	   public static boolean isValidCNPJ(String cnpj) {
	      if ((cnpj==null)||(cnpj.length()!=14)) return false;

	      Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
	      Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
	      return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	   }
	   
	   public static boolean isEmailValid(String email) {
	        if ((email == null) || (email.trim().length() == 0))
	            return false;

	        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }

}
