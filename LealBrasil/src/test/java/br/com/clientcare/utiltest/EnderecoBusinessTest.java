package br.com.clientcare.utiltest;

import org.junit.Test;

import br.com.lealbrasil.model.business.EnderecoBusiness;
import br.com.lealbrasil.model.entities.Endereco;
import br.com.lealbrasil.model.entities.Pessoa;

public class EnderecoBusinessTest {
	
	@Test
	public void testaBuscaPorEndereco(){
		Pessoa pessoa = new Pessoa();
		pessoa.setId(17l);
		Endereco endereco = EnderecoBusiness.buscaEnderecoPorPessoa(pessoa);
		System.out.println(endereco.toString());
	}
}
