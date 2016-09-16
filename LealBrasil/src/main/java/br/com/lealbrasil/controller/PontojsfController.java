package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.model.business.PontoBusiness;
import br.com.lealbrasil.model.dao.PontoDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Ponto;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PontojsfController extends GenericController implements Serializable {
	private Ponto ponto;
	private List<Ponto> pontos;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	

	@PostConstruct
	
	public void listar() {
		if(perfilLogado==null || perfilLogado.getAssLogado()==null ||perfilLogado.getAssLogado().getId()<=0 ){
			mensagensDisparar("Não existe associado logado");
			autenticacao.redirecionaPaginas("index.xhtml", "erro no redirecionamento de página de autenticacao para alfapage!!!",false);
			return;
		}
		try {
			PontoDAO pontuacao_ConfigDAO = new PontoDAO();
			pontos = pontuacao_ConfigDAO.retornarListaPontuacaoConfig(perfilLogado.getAssLogado());
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}	
	public void novo() {

		ponto = new Ponto();
		if (ponto==null){
			ponto = new Ponto();
			ponto.setDescricao("");
			ponto.setDiasValidade(30);			
			ponto.setUnidadeporPonto(1);
			ponto.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
			ponto.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			ponto.setId_Empresa(1);
			ponto.setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento.PONTO);
		}
		ponto.setUltimaAtualizacao(Utilidades.retornaCalendario());
	}
	public void merge() {
		try {
		PontoDAO pConfig = new PontoDAO();
		ponto.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		ponto.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		ponto.setId_Empresa(1);
		ponto.setUltimaAtualizacao(Utilidades.retornaCalendario());
		ponto.setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento.PONTO);
		pConfig.merge(ponto);
		
		if (ponto.getId()!=null)
		mensagensDisparar(Enum_Aux_Tipo_Mensagem.ALTERACAO.getMensagem());	
		else
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.INCLUSAO.getMensagem());
		
		
		listar();
		}catch (RuntimeException erro) {
			if (ponto.getId()!=null)
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRALTERACAO.getMensagem());	
			else
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRINCLUSAO.getMensagem());			
			erro.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento){
		setPonto(PontoBusiness.registroAtualdaLista(evento));
		if (ponto!=null){		
			try {				
				PontoDAO pontuacao_ConfigDAO = new PontoDAO();
				pontuacao_ConfigDAO.excluir(ponto);
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.EXCLUSAO.getMensagem());
				listar();				
			} catch (RuntimeException erro) {
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERREXCLUSAO.getMensagem());
				erro.printStackTrace();
			}
		}
	}
	public void editar(ActionEvent evento){
		setPonto(PontoBusiness.registroAtualdaLista(evento));
	}
	
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}
	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}
	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}
	public Ponto getPonto() {
		return ponto;
	}
	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}
	public List<Ponto> getPontos() {
		return pontos;
	}
	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
}
