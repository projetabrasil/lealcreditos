package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.LogradouroDAO;
import br.com.lealbrasil.model.entities.Logradouro;
import br.com.lealbrasil.model.entities.PerfilLogado;

@SuppressWarnings("serial")
public class LogradouroBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Logradouro> listar(PerfilLogado perfilLogado) {
		List<Logradouro> logradouro = null;
		
		try {
			LogradouroDAO logradouroDAO = new LogradouroDAO();			
			logradouro = logradouroDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return logradouro;
		
	}

	public static Logradouro buscaLogradouroPeloNome(String descricao) {
		LogradouroDAO lDAO = new LogradouroDAO();
		Logradouro logradouro = lDAO.buscaLogradouroPeloNome(descricao);
		return logradouro;
	}

	public static void merge(Logradouro logradouro) {
		LogradouroDAO lDAO = new LogradouroDAO();
		lDAO.merge(logradouro);
	}
}
