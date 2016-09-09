package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;

import br.com.lealbrasil.model.business.Pontuacao_ConfigBusiness;
import br.com.lealbrasil.model.dao.Pontuacao_ConfigDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipos_Mensagens;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pontuacao_Config;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class Pontuacao_ConfigjsfController extends GenericController implements Serializable {
	private Pontuacao_Config pontuacao_Config;
	private List<Pontuacao_Config> pontuacoes_Config;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	
	

	@PostConstruct
	
	public void listar() {
		
		if(perfilLogado==null || perfilLogado.getAssLogado()==null ||perfilLogado.getAssLogado().getId()<=0 ){
			mensagensDisparar("Não existe associado logado");
			Faces.navigate("/pages/index.xhtml");
			return;
		}
		try {
			Pontuacao_ConfigDAO pontuacao_ConfigDAO = new Pontuacao_ConfigDAO();
			pontuacoes_Config = pontuacao_ConfigDAO.retornarListaPontuacaoConfig(perfilLogado.getAssLogado());
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
	}	
	public void novo() {
		//pontuacao_Config = Pontuacao_ConfigBusiness.pontuacaoExiste(perfilLogado.getAssLogado());
		pontuacao_Config = new Pontuacao_Config();
		if (pontuacao_Config==null){
			pontuacao_Config = new Pontuacao_Config();
			pontuacao_Config.setDescricao("");
			pontuacao_Config.setDiasValidade(30);			
			pontuacao_Config.setUnidadeporPonto(1);
			pontuacao_Config.setId_Pessoa_Associado(perfilLogado.getAssLogado());
			pontuacao_Config.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			pontuacao_Config.setId_Empresa(1);
		}
		pontuacao_Config.setUltimaAtualizacao(Utilidades.retornaCalendario());
	}
	public void merge() {
		try {
		Pontuacao_ConfigDAO pConfig = new Pontuacao_ConfigDAO();
		pontuacao_Config.setId_Pessoa_Associado(perfilLogado.getAssLogado());
		pontuacao_Config.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		pontuacao_Config.setId_Empresa(1);
		pontuacao_Config.setUltimaAtualizacao(Utilidades.retornaCalendario());
		pConfig.merge(pontuacao_Config);
		
		if (pontuacao_Config.getId()!=null)
		mensagensDisparar(Enum_Aux_Tipos_Mensagens.ALTERACAO.getMensagem());	
		else
			mensagensDisparar(Enum_Aux_Tipos_Mensagens.INCLUSAO.getMensagem());
		
		
		listar();
		}catch (RuntimeException erro) {
			if (pontuacao_Config.getId()!=null)
			mensagensDisparar(Enum_Aux_Tipos_Mensagens.ERRALTERACAO.getMensagem());	
			else
			mensagensDisparar(Enum_Aux_Tipos_Mensagens.ERRINCLUSAO.getMensagem());			
			erro.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento){
		setPontuacao_Config(Pontuacao_ConfigBusiness.registroAtualdaLista(evento));
		if (pontuacao_Config!=null){		
			try {				
				Pontuacao_ConfigDAO pontuacao_ConfigDAO = new Pontuacao_ConfigDAO();
				pontuacao_ConfigDAO.excluir(pontuacao_Config);
				mensagensDisparar(Enum_Aux_Tipos_Mensagens.EXCLUSAO.getMensagem());
				listar();				
			} catch (RuntimeException erro) {
				mensagensDisparar(Enum_Aux_Tipos_Mensagens.ERREXCLUSAO.getMensagem());
				erro.printStackTrace();
			}
		}
	}
	public void editar(ActionEvent evento){
		setPontuacao_Config(Pontuacao_ConfigBusiness.registroAtualdaLista(evento));
	}
	public List<Pontuacao_Config> getPontuacoes_Config() {
		return pontuacoes_Config;
	}
	public void setPontuacoes_Config(List<Pontuacao_Config> pontuacoes_Config) {
		this.pontuacoes_Config = pontuacoes_Config;
	}
	public Pontuacao_Config getPontuacao_Config() {
		return pontuacao_Config;
	}
	public void setPontuacao_Config(Pontuacao_Config pontuacao_Config) {
		this.pontuacao_Config = pontuacao_Config;
	}
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}
}
