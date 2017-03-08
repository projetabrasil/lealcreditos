package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.controller.entitiesconfig.PessoaConfig;
import br.com.lealbrasil.model.business.BairroBusiness;
import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class BairrojsfController extends GenericController implements Serializable {
	
	private Bairro bairro;
	private List<Bairro> bairros;
	private PessoaConfig pessoaConfig;
	private Estado estado;
	private Cidade cidade;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
		bairros = BairroBusiness.listar(perfilLogado);
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		bairro = new Bairro(new Cidade(), new Estado());		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.bairro.setDescricao(Utilidades.formataNomeDaRegiao(this.bairro.getDescricao()));
		Bairro bairro2 = BairroBusiness.buscaBairroPeloNome(this.bairro.getDescricao());  //this.pais é diferente de pais
		if(bairro2 != null){
			mensagensDisparar("Este bairro já está cadastrado: " + bairro2.getDescricao());
			this.bairro.setId(bairro2.getId());
		}
		
		this.bairro.setCidade(cidade);
		this.bairro.getCidade().setEstado(estado);
		this.bairro.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.bairro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.bairro.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		BairroBusiness.merge(this.bairro);
		listar(); 
		this.estado = null;
		this.cidade = null;
		this.bairro.setCidade(new Cidade());
		this.bairro.getCidade().setEstado(new Estado());
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
		this.bairro.getCidade().getEstado().getPais().setEstados(BairroBusiness.associaEstadosAoPais(this.bairro.getCidade().getEstado().getPais().getId()));
	}
	
	public void associaCidadesAoEstado(){
		this.estado.setCidades(BairroBusiness.associaCidadesAoEstado(this.estado.getId()));
	}
	
	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
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

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
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
	
	
	
	
}
