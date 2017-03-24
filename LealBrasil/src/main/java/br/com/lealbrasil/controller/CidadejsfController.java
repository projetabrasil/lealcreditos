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
import br.com.lealbrasil.model.business.CidadeBusiness;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadejsfController extends GenericController implements Serializable {
	
	private Cidade cidade;
	private List<Cidade> cidades;
	private PessoaConfig pessoaConfig;
	private Estado estado;
	private List<Estado> estados;
	
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	@PostConstruct
	public void listar() {
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
		cidades = CidadeBusiness.listar(perfilLogado);
		
		cidade = new Cidade();
		estado = new Estado();

		estados = new ArrayList<>();
	}
	
	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		cidade = new Cidade(new Estado());		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void editar(ActionEvent event) {

		Cidade c = (Cidade) event.getComponent().getAttributes().get("registroAtual");
		cidade = new Cidade();
		cidade = c;
		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
	public void merge() {
		this.cidade.setDescricao(Utilidades.formataNomeDaRegiao(this.cidade.getDescricao()));
		
		this.cidade.setEstado(this.estado);
		
		Cidade cidade2 = CidadeBusiness.buscaCidade(this.cidade);  //this.pais é diferente de pais
		if(this.cidade.getId() != null){ // Se this.pais.getId() for igual à nulo isso significa que o usuário clicou em novo, caso contrário ele está realizando uma edição
			if(cidade2 != null){
				if(!this.cidade.getId().equals(cidade2.getId())){
					mensagensDisparar("Tente novamente, cidade já cadastrada: " + cidade.getDescricao());
					listar();
					return;
				}else{
					mensagensDisparar("Cidade alterada com sucesso!!!");
				}
			}else{
				mensagensDisparar("Cidade alterada com sucesso!!!");
			}
		}else{
			if(cidade2 != null){
				mensagensDisparar("Cidade já cadastrada!!!");
				return;
			}else{
				mensagensDisparar("Cidade cadastrada com sucesso!!!");
			}
		}
	
		this.cidade.setUltimaAtualizacao(Utilidades.retornaCalendario());
		
		//Inseri no banco o usuário que registrou o país, SE usuário NÃO existir, o id_registro é feito com o associado
		if(perfilLogado.getUsLogado().getPessoa() != null){
			this.cidade.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		}else{
			this.cidade.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}
		
		CidadeBusiness.merge(this.cidade);
		listar(); 
		
		this.cidade.setEstado(new Estado());
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
		this.setEstados(CidadeBusiness.associaEstadosAoPais(cidade.getEstado().getPais().getId()));
		estado = new Estado();		
	}
	
	
	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}	
	
	
}
