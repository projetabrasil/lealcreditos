package br.com.lealbrasil.model.business;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import br.com.lealbrasil.model.dao.Pontuacao_ConfigDAO;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pontuacao_Config;

@SuppressWarnings("serial")
public class Pontuacao_ConfigBusiness implements Serializable {
	private static Pontuacao_Config pontuacao_ConfigExistente;

	public static Pontuacao_Config pontuacaoExiste(Pessoa associado) {
		Pontuacao_ConfigDAO pConfDAO = new Pontuacao_ConfigDAO();
		return pConfDAO.retPontuacao(associado);
			
			
		
	}

	public static Pontuacao_Config registroAtualdaLista(ActionEvent evento) {
		return (Pontuacao_Config) evento.getComponent().getAttributes().get("registroAtual");
	}

	public static Pontuacao_Config getPontuacao_ConfigExistente() {
		return pontuacao_ConfigExistente;
	}

	public static void setPontuacao_ConfigExistente(Pontuacao_Config pontuacao_ConfigExistente) {
		Pontuacao_ConfigBusiness.pontuacao_ConfigExistente = pontuacao_ConfigExistente;
	}

}
