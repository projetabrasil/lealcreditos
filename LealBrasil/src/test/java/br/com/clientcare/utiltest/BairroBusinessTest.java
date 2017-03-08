package br.com.clientcare.utiltest;

import org.junit.Test;

import br.com.lealbrasil.model.business.BairroBusiness;
import br.com.lealbrasil.model.entities.Estado;

public class BairroBusinessTest {
	
	@Test
	public void testaAssociacaoDeCidadeComEstado(){
		Estado estado = new Estado();
		estado.setCidades(BairroBusiness.associaCidadesAoEstado(11l));
		System.out.println(estado.toString());
	}
}
