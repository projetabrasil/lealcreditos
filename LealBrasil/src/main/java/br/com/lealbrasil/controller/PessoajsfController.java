package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.controller.entitiesconfig.PessoaConfig;
import br.com.lealbrasil.model.business.PessoaBusiness;
import br.com.lealbrasil.model.business.PessoaBusiness2;
import br.com.lealbrasil.model.business.PessoaGenericBusiness;
import br.com.lealbrasil.model.business.UsuarioBusiness;
import br.com.lealbrasil.model.dao.Pessoa_VinculoDAO;
import br.com.lealbrasil.model.dao.UsuarioDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoajsfController extends GenericController implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private PessoaConfig pessoaConfig;
	private Enum_Aux_Tipo_Pessoa enum_Aux_Tipo_Pessoa;
	private Usuario usuario;

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;

	@PostConstruct
	public void listar() {
		pessoas = PessoaBusiness.listar(perfilLogado);
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		pessoa = new Pessoa();
		configurarPessoa();
		if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAASSINANTES))
			pessoa = pessoaConfig.ConfiguraPessoa(Enum_Aux_Tipo_Identificador.CNPJ, perfilLogado.getUsLogado(), pessoa,
					false);
		else
			pessoa = pessoaConfig.ConfiguraPessoa(Enum_Aux_Tipo_Identificador.CPF, perfilLogado.getUsLogado(), pessoa,
					false);
		usuario = new Usuario();
		pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		usuario.setPessoa(pessoa);
		Utilidades.abrirfecharDialogos("dialogoIdentidade",true);
	}

	public void editar(ActionEvent event) {

		Pessoa p = (Pessoa) event.getComponent().getAttributes().get("registroAtual");
		pessoa = new Pessoa();
		pessoa = p;
		
		
		if (perfilLogado.getPaginaAtual().isRenderizaCadastrodeUsuarios()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario = usuarioDAO.retornaUsuarioPelaPessoa(pessoa);
			if (usuario == null)
				usuario = new Usuario();
		}
		configurarPessoa();

		if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAASSINANTES))
			pessoa = pessoaConfig.ConfiguraPessoa(Enum_Aux_Tipo_Identificador.CNPJ, perfilLogado.getUsLogado(), pessoa,
					true);
		else
			pessoa = pessoaConfig.ConfiguraPessoa(Enum_Aux_Tipo_Identificador.CPF, perfilLogado.getUsLogado(), pessoa,
					true);
		pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

		usuario.setPessoa(pessoa);
		mudaLabel();
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}

	public void merge() {
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null
				&& (perfilLogado.getUsLogado() == null || perfilLogado.getUsLogado().getPessoa() == null
						|| perfilLogado.getUsLogado().getId() == null))
			pessoa.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		usuario.setPessoa(pessoa);

		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, true, true, true))
			return;
		if (pessoa.getId() != null
				&& perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)) {
			Pessoa_Vinculo pVin = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
			pVin.setId_pessoa_d(pessoa);
			pVin = pVinDAO.retornaVinculo_Mestre(pVin, perfilLogado, Enum_Aux_Perfil_Pessoa.ATENDENTES);
			if (pVin != null && !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
				Pessoa p = pVin.getId_pessoa_m();
				mensagensDisparar(
						"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
				return;
			}
		}
		pessoa = PessoaGenericBusiness.merge(pessoa, usuario, perfilLogado, true);
		listar();
		cancela();

	}

	public void cancela() {		
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage",true);			
		    
			
		}
	}

	public void mudaLabel() {
		pessoaConfig.mudarLabels(pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa());
	}

	public void buscaPessoa() {
		/*
		 * o padrão aqui é física ou jurídica mas quando ele chama
		 * PessoasBusiness.buscaPessoa ele pode buscar perfil OUTROS
		 */
		pessoa.setCpf_Cnpj(Utilidades.retiraCaracteres(pessoa.getCpf_Cnpj()));
		pessoa.setIdentificador(pessoa.getCpf_Cnpj());
		Enum_Aux_Tipo_Identificador tipoIdent = pessoa.getEnum_Aux_Tipo_Identificador();
		usuario.setPessoa(pessoa);
		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, true, true, true))
			return;

		String identificador;
		identificador = pessoa.getIdentificador();
		pessoa = PessoaBusiness.buscaPessoa(pessoa);

		if (pessoa.getIdentificador() == null || pessoa.getIdentificador().length() <= 0) {
			pessoa.setIdentificador(identificador);
			pessoa.setCpf_Cnpj(identificador);
		}
		if (pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa().equals(Enum_Aux_Tipo_Pessoa.OUTROS))
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		if (!pessoa.getEnum_Aux_Tipo_Identificador().equals(tipoIdent))
			pessoa.setEnum_Aux_Tipo_Identificador(tipoIdent);

		if (pessoa.getId() != null)
			usuario = UsuarioBusiness.retornaUsuario(usuario, pessoa);
		mudaLabel();
		PessoaGenericBusiness.chamaDialogoCastro();
	}

	public void excluir(ActionEvent evento) {
		setPessoa(PessoaBusiness.registroAtualdaLista(evento));
		PessoaBusiness.excluir(pessoa);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public Enum_Aux_Tipo_Pessoa getEnum_Aux_Tipo_Pessoa() {
		return enum_Aux_Tipo_Pessoa;
	}

	public void setEnum_Aux_Tipo_Pessoa(Enum_Aux_Tipo_Pessoa enum_Aux_Tipo_Pessoa) {
		this.enum_Aux_Tipo_Pessoa = enum_Aux_Tipo_Pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the perfilLogado
	 */
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public PerfilLogado getPerfilLogadoTemp() {
		return perfilLogadoTemp;
	}

	public void setPerfilLogadoTemp(PerfilLogado perfilLogadoTemp) {
		this.perfilLogadoTemp = perfilLogadoTemp;
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

}
