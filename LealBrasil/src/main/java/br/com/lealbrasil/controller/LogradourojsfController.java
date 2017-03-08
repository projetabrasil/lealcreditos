package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.controller.entitiesconfig.PessoaConfig;
import br.com.lealbrasil.model.business.LogradouroBusiness;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Logradouro;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.Logradouro;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LogradourojsfController extends GenericController implements Serializable {
	
	private Logradouro logradouro;
	private List<Logradouro> logradouros;
	private PessoaConfig pessoaConfig;
	private Estado estado;
	private Cidade cidade;
	
	//private Cidade aQualPertence;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	
	private List<Enum_Aux_Tipo_Logradouro> lista;
	
	@PostConstruct
	public void listar() {
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
		logradouros = LogradouroBusiness.listar(perfilLogado);
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		logradouro = new Logradouro(new Cidade(), new Estado());		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.logradouro.setDescricao(Utilidades.formataNomeDaRegiao(this.logradouro.getDescricao()));
		//this.logradouro.setCidade(cidadeDoLog);
		Logradouro logradouro2 = LogradouroBusiness.buscaLogradouroPeloNome(this.logradouro.getDescricao());  //this.pais é diferente de pais
		if(logradouro2 != null){
			mensagensDisparar("Este logradouro já está cadastrado: " + logradouro2.getDescricao());
			this.logradouro.setId(logradouro2.getId());
		}
		
		this.logradouro.setCidade(cidade);
		this.logradouro.getCidade().setEstado(estado);
		this.logradouro.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.logradouro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.logradouro.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		LogradouroBusiness.merge(this.logradouro);
		listar(); 
		this.estado = null;
		this.cidade = null;
		this.logradouro.setCidade(new Cidade());
		this.logradouro.getCidade().setEstado(new Estado());
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

	}
	
	
	public void cancela() {		
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage",true);				    			
		}
	}
	
	public void associaEstadosAoPais(){
		this.logradouro.getCidade().getEstado().getPais().setEstados(LogradouroBusiness.associaEstadosAoPais(this.logradouro.getCidade().getEstado().getPais().getId()));
	}
	
	public void associaCidadesAoEstado(){
		this.estado.setCidades(LogradouroBusiness.associaCidadesAoEstado(this.estado.getId()));
	}
	
	
	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
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

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Enum_Aux_Tipo_Logradouro> getLista() {
		return lista;
	}

	public void setLista(List<Enum_Aux_Tipo_Logradouro> lista) {
		this.lista = lista;
	}

	public List<Enum_Aux_Tipo_Logradouro> listarTiposLogradouros(){
		Enum_Aux_Tipo_Logradouro[] enums =  Enum_Aux_Tipo_Logradouro.values();
		
		lista = new ArrayList<Enum_Aux_Tipo_Logradouro>();
		
		for(int i=0; i < enums.length ; i++){
			lista.add(enums[i]);
		}
		
		return lista;
	}
	
	
	
	
}
