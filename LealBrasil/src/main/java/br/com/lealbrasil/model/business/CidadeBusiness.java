package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.CidadeDAO;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.PerfilLogado;

@SuppressWarnings("serial")
public class CidadeBusiness implements Serializable {
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Cidade> listar(PerfilLogado perfilLogado) {
		List<Cidade> cidade = null;
		
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();			
			cidade = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return cidade;
		
	}

	public static Cidade buscaCidadePeloNome(String descricao) {
		CidadeDAO cDAO = new CidadeDAO();
		Cidade cidade = cDAO.buscaCidadePeloNome(descricao);
		return cidade;
	}

	public static void merge(Cidade cidade) {
		CidadeDAO cDAO = new CidadeDAO();
		cDAO.merge(cidade);
	}
}
