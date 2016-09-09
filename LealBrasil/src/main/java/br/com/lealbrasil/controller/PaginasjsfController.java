package br.com.lealbrasil.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Pontuacao;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipos_Mensagens;
import br.com.lealbrasil.model.entities.Enum_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pontuacao_Movimento;

@SuppressWarnings("serial")
@ManagedBean(name = "paginas")
@ViewScoped
public class PaginasjsfController implements Serializable {
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	@ManagedProperty(value = "#{pontuacao.tipoMovimentacao}")
	private Enum_Aux_Tipo_Mov_Pontuacao tipoMovimentacao;
	
	@ManagedProperty(value = "#{pontuacao.pontuacao}")
	private Pontuacao_Movimento pontuacao;

	private void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public void mudaPaginaAtual(ActionEvent event) {
		// String paginaAtual
		String paginaAtual = (String) event.getComponent().getAttributes().get("paginaAtual");
		if (Enum_Perfil_Pagina_Atual.valueOf(paginaAtual) != null)
			perfilLogado.setPaginaAtual(Enum_Perfil_Pagina_Atual.valueOf(paginaAtual));
		paginaAtual = perfilLogado.getPaginaAtual().getUrl();
		
		
		
		
		try {
			Faces.redirect("./faces/pages/" + paginaAtual);
		} catch (IOException error) {
			mensagensDisparar(Enum_Aux_Tipos_Mensagens.ERRACESSOPESSOAS.getMensagem());
			error.printStackTrace();
		}
		return;

	}
	
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	/**
	 * @return the tipoMovimentacao
	 */
	public Enum_Aux_Tipo_Mov_Pontuacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	/**
	 * @param tipoMovimentacao the tipoMovimentacao to set
	 */
	public void setTipoMovimentacao(Enum_Aux_Tipo_Mov_Pontuacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	/**
	 * @return the pontuacao
	 */
	public Pontuacao_Movimento getPontuacao() {
		return pontuacao;
	}

	/**
	 * @param pontuacao the pontuacao to set
	 */
	public void setPontuacao(Pontuacao_Movimento pontuacao) {
		this.pontuacao = pontuacao;
	}

}
