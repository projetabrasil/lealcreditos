package br.com.clientcare.utiltest;

import org.junit.Test;

import br.com.lealbrasil.util.CepWebService;

public class CepWebServiceTest {
	
	@Test
	public void testaWebService() {
		CepWebService cep = new CepWebService("88110768");
		System.out.println(cep.toString());
		
	}
}
