package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.controller.entitiesconfig.PessoaConfig;
import br.com.lealbrasil.model.business.PaisBusiness;
import br.com.lealbrasil.model.entities.Pais;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PaisjsfController extends GenericController implements Serializable {
	
	private Pais pais;
	private List<Pais> paises;
	private PessoaConfig pessoaConfig;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
		paises = PaisBusiness.listar(perfilLogado);
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		pais = new Pais();		
		Utilidades.abrirfecharDialogos("dialogoIdentidade",true);
	}
	
	public void merge() {
		this.pais.setDescricao(Utilidades.FormataNomeDoPais(pais.getDescricao()));
		Pais pais = PaisBusiness.buscaPaisPeloNome(this.pais.getDescricao());
		if(pais.getId() != null){
			mensagensDisparar("Este pais já está cadastrado: " + pais.getDescricao());
			this.pais.setId(pais.getId());
		}
		this.pais.setUltimaAtualizacao(Utilidades.retornaCalendario());
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.pais.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.pais.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		PaisBusiness.merge(this.pais);
		listar();

	}
	
	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Pais> getPaises() {
		listar();
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public PerfilLogado getPerfilLogadoTemp() {
		return perfilLogadoTemp;
	}

	public void setPerfilLogadoTemp(PerfilLogado perfilLogadoTemp) {
		this.perfilLogadoTemp = perfilLogadoTemp;
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	
	
	
}