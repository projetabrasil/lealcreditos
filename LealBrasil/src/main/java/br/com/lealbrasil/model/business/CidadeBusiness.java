package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.CidadeDAO;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

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

	public static Cidade buscaCidade(Cidade cidade) {
		CidadeDAO cDAO = new CidadeDAO();
		Cidade c = cDAO.buscaCidade(cidade);
		return c;
	}
	
	public static Cidade buscaCidadePeloNome(String descricao) {
		CidadeDAO cDAO = new CidadeDAO();
		descricao = Utilidades.formataNomeDaRegiao(descricao);
		Cidade c = cDAO.buscaCidadePeloNome(descricao);
		return c;
	}

	public static void merge(Cidade cidade) {
		CidadeDAO cDAO = new CidadeDAO();
		cidade.setDescricao(Utilidades.formataNomeDaRegiao(cidade.getDescricao()));
		cDAO.merge(cidade);
	}
	
	public static List<Estado> associaEstadosAoPais(Long id) {
		CidadeDAO cDAO = new CidadeDAO();
		List<Estado> estados = cDAO.associaEstadosAoPais(id);
		return estados;
	}
}
