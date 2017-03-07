package br.com.clientcare.utiltest;

import java.util.List;

import org.junit.Test;

import br.com.lealbrasil.model.business.CidadeBusiness;
import br.com.lealbrasil.model.entities.Estado;

public class CidadeBusinessTest {
	
	@Test
	public void testaAssociacaoDeEstados(){
		Long id = 12l;
		List<Estado> estados = CidadeBusiness.associaEstadosAoPais(id);
		System.out.println("");
		for(Estado estado : estados) {
			System.out.println(estado.toString());
		}
		
	}
	
}
