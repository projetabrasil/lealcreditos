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
		pais = PaisBusiness.buscaPaisPeloNome("bRaSil");
		System.out.println(pais.toString());
	}
	
	public void testaInsertDePais() {
		Pais pais = new Pais();
		pais.setDescricao("Brasil");
		pais.setId(null);
		pais.setId_Empresa(0);
		pais.setMaskTel("Teste2");
		pais.setMaskZip("Teste");
		pais.setSigla("BR");
		Pessoa p = new PessoaDAO().retornaPelaIdentificacao("10554498928");
		pais.setId_Pessoa_Registro(p);
		PaisBusiness.merge(pais);
	}
}
