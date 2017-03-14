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
import br.com.lealbrasil.model.business.CidadeBusiness;
import br.com.lealbrasil.model.business.EnderecoBusiness;
import br.com.lealbrasil.model.business.EstadoBusiness;
import br.com.lealbrasil.model.business.LogradouroBusiness;
import br.com.lealbrasil.model.business.PessoaBusiness;
import br.com.lealbrasil.model.business.PessoaBusiness2;
import br.com.lealbrasil.model.business.PessoaGenericBusiness;
import br.com.lealbrasil.model.business.UsuarioBusiness;
import br.com.lealbrasil.model.dao.PaisDAO;
import br.com.lealbrasil.model.dao.Pessoa_VinculoDAO;
import br.com.lealbrasil.model.dao.UsuarioDAO;
import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Endereco;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Logradouro;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.Logradouro;
import br.com.lealbrasil.model.entities.Pais;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.CepWebService;
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
	private Endereco endereco;
	private Estado estado;
	private Cidade cidade;
	private Bairro bairro;
	private Pais pais;
	private Logradouro logradouro;
	private List<Pais> paises;

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;
	
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;

	@PostConstruct
	public void listar() {
		if(perfilLogado!=null && perfilLogado.getPerfilUsLogado()!=null)
		pessoas = PessoaBusiness.listar(perfilLogado);
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		pessoa = new Pessoa();
		
		PaisDAO pDAO = new PaisDAO();		
		this.paises = pDAO.listar();
		
		this.pais = new Pais();
		 		
		this.endereco = EnderecoBusiness.buscaEnderecoPorPessoa(perfilLogado.getAssLogado());
		if(this.endereco == null){
			this.endereco = new Endereco(new Bairro(), new Cidade(), new Estado());
			this.pais = pDAO.buscaPaisPeloNome("Brasil");
		}else{
			this.pais = endereco.getLogradouro().getCidade().getEstado().getPais();
		}
		
		int a = 0;
		for(int i = 0; i < paises.size(); i++){
			if(this.paises.get(i).getDescricao().equals(this.pais.getDescricao())){ // GO HORSE PRA MANTER O PAÍS PADRÃO
				a = i;
				break;
			}
		}
		
		Pais p = this.paises.get(this.paises.size() - 1);
		this.paises.set(a, p);
		this.paises.set(paises.size() - 1, pais);
		
		associaEstadosAoPais();
		this.estado = endereco.getBairro().getCidade().getEstado();
		associaCidadesAoEstado();
		this.endereco.setComplemento("");
		this.endereco.setNumero(null);

		
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
		this.pessoa = new Pessoa();
		this.pessoa = p;
		
		this.endereco = new Endereco(new Bairro(), new Cidade(), new Estado());	
		
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
			pVin = pVinDAO.retornaVinculo_Mestre(pessoa, Enum_Aux_Perfil_Pessoa.ATENDENTES);
			if (pVin != null && !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
				Pessoa p = pVin.getId_pessoa_m();
				mensagensDisparar(
						"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
				return;
			}
		}
		
		pessoa = PessoaGenericBusiness.merge(pessoa, usuario, perfilLogado, true);
		// Endereço MERGE------------
		if(pessoa != null){
			this.endereco.setBairro(this.bairro);
			this.endereco.setLogradouro(this.logradouro);
			this.endereco.setId_Empresa(0);
			this.endereco.setUltimaAtualizacao(Utilidades.retornaCalendario());	
			this.endereco.setPessoa(pessoa);
			this.endereco.setId(null);
			EnderecoBusiness.merge(endereco);
		}
		
		listar();
		this.estado = null;
		this.cidade = null;
		this.bairro = null;
		this.estado = null;
		this.endereco.setBairro(new Bairro());
		this.endereco.setLogradouro(new Logradouro());
		this.endereco.getBairro().setCidade(new Cidade());
		this.endereco.getBairro().getCidade().setEstado(new Estado());		
		cancela();

	}
	
	public void setCEP(){
		CepWebService cep = new CepWebService(this.endereco.getBairro().getCidade().getCep());
		System.out.println(cep.toString());
		if(cep != null){
			Estado e = EstadoBusiness.buscaEstadoPelaSigla(cep.getEstado());
			if(e == null){
				e = new Estado();
				e.setDescricao("");
				e.setSigla(cep.getEstado());
				e.setPais(this.pais);
				e.setUltimaAtualizacao(Utilidades.retornaCalendario());
				e.setId_Empresa(0);
				e.setId(null);
				e.setId_Pessoa_Registro(perfilLogado.getAssLogado());
				EstadoBusiness.merge(e);
			}
			this.estado = e;
			associaEstadosAoPais();
			
			Cidade c = CidadeBusiness.buscaCidadePeloNome(cep.getCidade());
			if(c == null){
				c = new Cidade();
				c.setDescricao(cep.getCidade());
				c.setCep(endereco.getBairro().getCidade().getCep());
				c.setId(null);
				c.setId_Empresa(0);
				c.setUltimaAtualizacao(Utilidades.retornaCalendario());
				c.setId_Pessoa_Registro(perfilLogado.getAssLogado());
				c.setEstado(estado);
				CidadeBusiness.merge(c);
			}
			this.cidade = c;
			associaCidadesAoEstado();
			
			Bairro b = BairroBusiness.buscaBairroPeloNome(cep.getBairro());
			if(b == null){
				b = new Bairro();
				b.setDescricao(cep.getBairro());
				b.setId(null);
				b.setId_Empresa(0);
				b.setUltimaAtualizacao(Utilidades.retornaCalendario());
				b.setId_Pessoa_Registro(perfilLogado.getAssLogado());
				b.setCidade(cidade);
				BairroBusiness.merge(b);
			}
			this.bairro = b;
			
			Logradouro l = LogradouroBusiness.buscaLogradouroPeloNome(cep.getLogradouro());
			if(l == null){
				l = new Logradouro();
				l.setDescricao(cep.getLogradouro());
				l.setId(null);
				l.setId_Empresa(0);
				l.setUltimaAtualizacao(Utilidades.retornaCalendario());
				l.setId_Pessoa_Registro(perfilLogado.getAssLogado());
				l.setCidade(cidade);
				l.setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.valueOf(cep.getTipoLogradouro().toUpperCase()));
				LogradouroBusiness.merge(l);
			}
			this.logradouro = l;
		}		
		
	}

	public void cancela() {		
		Utilidades.abrirfecharDialogos("dialogoCadastro",false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage",true);			
		    
			
		}
	}
	
	public void adicionarLogradouro(ActionEvent event){	 
		this.logradouro.setCidade(this.cidade);
		this.logradouro.setId_Empresa(0);
		this.logradouro.setUltimaAtualizacao(Utilidades.retornaCalendario());
		this.logradouro.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		this.logradouro.setId(null);
		System.out.println(this.logradouro.toString());
		Utilidades.abrirfecharDialogos("dialogoCadastroL",false);
	}
	
	public void associaEstadosAoPais(){		
		this.cidade = null;
		this.bairro = null;
		this.logradouro = null;
		this.pais.setEstados(PessoaBusiness.associaEstadosAoPais(this.pais.getId()));
	}
	
	public void associaCidadesAoEstado(){
		this.cidade = null;
		this.bairro = null;
		this.logradouro = null;
		this.estado.setCidades(PessoaBusiness.associaCidadesAoEstado(this.estado.getId()));
	}
	
	public void associaBLACidade(){
		this.bairro = null;
		this.logradouro = null;
		this.cidade.setBairros(PessoaBusiness.associaBairrosACidade(this.cidade.getId()));
		this.cidade.setLogradouros(PessoaBusiness.associaLogradourosACidade(this.cidade.getId()));
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
		else{
			 if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAATENDENTES)){
				 
				 Pessoa_Vinculo pVin = new Pessoa_Vinculo();
					Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();					
					pVin = pVinDAO.retornaVinculo_Mestre(pessoa, Enum_Aux_Perfil_Pessoa.ATENDENTES);
					if (pVin != null && !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
						Pessoa p = pVin.getId_pessoa_m();
						mensagensDisparar(
								"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
						return;
					}
				 
			 }
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	
	
}
