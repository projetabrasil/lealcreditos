package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.EnderecoDAO;
import br.com.lealbrasil.model.entities.Endereco;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;

@SuppressWarnings("serial")
public class EnderecoBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Endereco> listar(PerfilLogado perfilLogado) {
		List<Endereco> endereco = null;
		
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();			
			endereco = enderecoDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return endereco;
		
	}

	public static void merge(Endereco endereco) {
		EnderecoDAO eDAO = new EnderecoDAO();
		try {
			eDAO.merge(endereco);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		}
		
	}

	public static Endereco buscaEnderecoPorPessoa(Pessoa pessoa) {
		Endereco endereco = new EnderecoDAO().buscaEnderecoPorPessoa(pessoa);
		return endereco;
	}
}
