package br.com.clientcare.utiltest;

import org.junit.Test;

import br.com.lealbrasil.model.business.PaisBusiness;
import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.entities.Pais;
import br.com.lealbrasil.model.entities.Pessoa;

public class PaisBusinessTest {
	
	@Test
	public void testaBuscaDePais(){
		Pais pais = new Pais();
		pais = PaisBusiness.buscaPaisPeloNome("Brasil");
		System.out.println(pais.toString());
	}
	
	@Test
	public void testaInsertDePais() {
		Pais pais = new Pais();
		pais.setDescricao("Estados Unidos");
		pais.setId(null);
		pais.setId_Empresa(0);
		pais.setMaskTel("Teste");
		pais.setMaskZip("Teste");
		pais.setSigla("EUA");
		Pessoa p = new PessoaDAO().retornaPelaIdentificacao("10554498928");
		pais.setId_Pessoa_Registro(p);
		PaisBusiness.merge(pais);
	}
}
