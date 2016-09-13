package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.lealbrasil.controller.entitiesconfig.PessoaConfig;
import br.com.lealbrasil.model.business.PessoaBusiness2;
import br.com.lealbrasil.model.business.PessoaGenericBusiness;
import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Pontuacao_ConfigDAO;
import br.com.lealbrasil.model.dao.Pontuacao_MovimentoDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Pontuacao;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pontuacao_Config;
import br.com.lealbrasil.model.entities.Pontuacao_Movimento;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "pontuacao")
public class Pontuacao_MovimentojsfController implements Serializable {
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	List<Pontuacao_Movimento> pontuacoes;
	List<Pontuacao_Movimento> historico;
	private Pontuacao_Movimento pontuacao = new Pontuacao_Movimento();
	private Enum_Aux_Tipo_Identificador tipoIdentificador;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores;
	private Pessoa pessoaPontuada = new Pessoa();
	private Pontuacao_Config pontuacaoConfig;
	private List<Pontuacao_Config> listaPontuacoesConfig;
	private Enum_Aux_Tipo_Mov_Pontuacao tipoMovimentacao;
	private PessoaConfig pessoaConfig;
	private Pessoa clienteHistorico;
	private Double pontuacaoSoma;
	private Double pontuacaoSomaCredito;
	private Double pontuacaoSomaDebitoUtilizacao;
	private Double pontuacaoSomaDebitoEstorno;

	@PostConstruct
	public void listar() {
		if (!perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC)
				&& !perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD)
				&& !perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE))
			return;
		Pontuacao_MovimentoDAO pDAO = new Pontuacao_MovimentoDAO();
		pontuacoes = pDAO.listar(perfilLogado, null, true);
		litarPontuacoesConfig();
		
		listarTipodeIdentificadores();
		setTipoIdentificador(Enum_Aux_Tipo_Identificador.CPF);
	}

	public void listarHistorico(Pessoa cliente) {
		if (cliente != null)
			clienteHistorico = cliente;
		else
			clienteHistorico = new Pessoa();

		Pontuacao_MovimentoDAO pDAO = new Pontuacao_MovimentoDAO();
		historico = pDAO.listar(perfilLogado, cliente, true);
		retornasomaPontuacao(cliente);

		RequestContext contexto = RequestContext.getCurrentInstance();
		contexto.execute("PF('dialogoHistorico').show();");
	}

	public void retornasomaPontuacao(Pessoa cliente) {
		Pontuacao_MovimentoDAO pDAO = new Pontuacao_MovimentoDAO();
		pontuacaoSomaCredito = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Pontuacao.C);
		pontuacaoSomaDebitoEstorno = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Pontuacao.E);
		pontuacaoSomaDebitoUtilizacao = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Pontuacao.D);

		if (pontuacaoSomaCredito == null)
			pontuacaoSomaCredito = 0d;
		if (pontuacaoSomaDebitoUtilizacao == null)
			pontuacaoSomaDebitoUtilizacao = 0d;
		if (pontuacaoSomaDebitoEstorno == null)
			pontuacaoSomaDebitoEstorno = 0d;
		pontuacaoSoma = pontuacaoSomaCredito - (pontuacaoSomaDebitoUtilizacao + pontuacaoSomaDebitoEstorno);
	}

	private void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public void novo() {		
		configurarPessoa();
		
		if (listaPontuacoesConfig != null && listaPontuacoesConfig.size() > 0)
		setPontuacaoConfig(listaPontuacoesConfig.get(0));
		pessoaPontuada.setCpf_Cnpj("");
		pontuacaoSoma = 0d;
		pontuacaoSomaCredito = 0d;
		pontuacaoSomaDebitoUtilizacao = 0d;
		pontuacaoSomaDebitoEstorno = 0d;
		RequestContext contexto = RequestContext.getCurrentInstance();
		contexto.execute("PF('dialogoPontuar').show();");
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
		pessoaPontuada = new Pessoa();
		pessoaPontuada = pessoaConfig.ConfiguraPessoa(tipoIdentificador, perfilLogado.getUsLogado(), pessoaPontuada,
				false);
		pessoaPontuada.setCpf_Cnpj("");
	}

	public void litarPontuacoesConfig() {
		Pontuacao_ConfigDAO pConfigDAO = new Pontuacao_ConfigDAO();
		setListaPontuacoesConfig(pConfigDAO.retornarListaPontuacaoConfig(perfilLogado.getAssLogado()));
	}

	public void configPontuacao() {
		
		pontuacao = new Pontuacao_Movimento();
		if (pontuacaoConfig != null && pontuacaoConfig.getId() != null) {
			pontuacao.setId_pontuacao_config(pontuacaoConfig);
			pontuacao.setId_pessoa_associado(perfilLogado.getAssLogado());
			pontuacao.setPontuacaoMinima(pontuacaoConfig.getPontuacaoMinima());
			pontuacao.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			pontuacao.setUnidadeporPonto(pontuacaoConfig.getUnidadeporPonto());
			pontuacao.setValordaUnidade(pontuacaoConfig.getValordaUnidade());
			pontuacao.setValorUnidadeDevolucao(pontuacaoConfig.getValorUnidadeDevolucao());
			pontuacao.setValorUnidadeTroca(pontuacaoConfig.getValorUnidadeTroca());
			pontuacao.setDiasValidade(pontuacaoConfig.getDiasValidade());
			pontuacao.setValidade(Utilidades.retornaValidade(pontuacao.getDiasValidade()));
			pontuacao.setValoraPontuar(0);

			if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC))
				pontuacao.setCreditaDebita(Enum_Aux_Tipo_Mov_Pontuacao.C);
			else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD))
				pontuacao.setCreditaDebita(Enum_Aux_Tipo_Mov_Pontuacao.D);
			else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE))
				pontuacao.setCreditaDebita(Enum_Aux_Tipo_Mov_Pontuacao.E);
		}

	}
	public void configPessoaNova(String cpf_Cnpj){
		pessoaPontuada = new Pessoa();
		configurarPessoa();
		pessoaPontuada = pessoaConfig.ConfiguraPessoa(tipoIdentificador, perfilLogado.getUsLogado(),
				pessoaPontuada, false);
		pessoaPontuada.setCpf_Cnpj(cpf_Cnpj);
		pessoaConfig.mudarLabels(tipoIdentificador.getAux_tipo_pessoa());

		pontuacaoSomaCredito = 0d;
		pontuacaoSomaDebitoUtilizacao = 0d;
		pontuacaoSomaDebitoEstorno = 0d;
		pontuacaoSoma = 0d;

		
	}

	public void verificaPessoa() {
		boolean validarEmail;
		String cpf_Cnpj = pessoaPontuada.getCpf_Cnpj();
		cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
		cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);
		pessoaPontuada.setCpf_Cnpj(cpf_Cnpj);
		pessoaPontuada.setEnum_Aux_Tipo_Identificador(tipoIdentificador);

		if (pessoaPontuada.getCpf_Cnpj().length() > 0) {
			Usuario us = new Usuario();
			us.setPessoa(pessoaPontuada);
			if (pessoaPontuada.getEmail() != null && pessoaPontuada.getEmail().length() > 0)
				validarEmail = true;
			else
				validarEmail = false;

			if (!PessoaBusiness2.validaDados(us, perfilLogado, validarEmail, false, true))
				return;
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaPontuada = pessoaDAO.retornaPelaIdentificacao(pessoaPontuada.getCpf_Cnpj());
			if (pessoaPontuada == null) {
				configPessoaNova(cpf_Cnpj);
				
				
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialogoCadastro').show();");
			} else {
				retornasomaPontuacao(pessoaPontuada);
			}
			configPontuacao();
		}else{			
			configPessoaNova(cpf_Cnpj);
			configPontuacao();
		}
	}

	public void pessoaMerge(boolean validarEmail, boolean validarSenha) {
		Usuario usuario = new Usuario();
		usuario.setPessoa(pessoaPontuada);

		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, validarEmail, validarSenha, true))
			return;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogoCadastro').hide();");
		pessoaPontuada = PessoaGenericBusiness.merge(pessoaPontuada, perfilLogado.getUsLogado(), perfilLogado, false);
	}

	public void merge() {

		if (pessoaPontuada == null || pessoaPontuada.getId() == null)
			return;
		pontuacao.setId_pessoa_cliente(pessoaPontuada);
		pontuacao.setId_Empresa(1);

		if (pontuacao.getCreditaDebita().equals(Enum_Aux_Tipo_Mov_Pontuacao.C)) {
			int pontos = (int) (pontuacao.getUnidadeporPonto()
					* ((int) pontuacao.getValoraPontuar() / pontuacao.getValordaUnidade()));
			pontuacao.setPontosAtingidos(pontos);
			

		} else {
			if (pontuacaoSoma < pontuacao.getPontosAtingidos()) {
				pontuacao.setPontosAtingidos(0);
				mensagensDisparar("Você não tem pontuações o suficiente ocorrrer DEBITO/ESTORNO!!!");
				
				return;

			}
			pontuacao.setValoraPontuar(pontuacao.getValordaUnidade() * pontuacao.getPontosAtingidos());
		}

		pontuacao.setUltimaAtualizacao(Utilidades.retornaCalendario());

		Pontuacao_MovimentoDAO pDAO = new Pontuacao_MovimentoDAO();
		pontuacao = pDAO.merge(pontuacao);

		if (pontuacao != null && pontuacao.getId() != null) {
			mensagensDisparar(
					"Pontuação para  " + pontuacao.getId_pessoa_cliente().getDescricao() + " realizada com sucesso!!!");
		}
		listar();
		novo();

	}

	public void listarTipodeIdentificadores() {
		Enum_Aux_Tipo_Identificador[] identificadores;
		identificadores = Enum_Aux_Tipo_Identificador.values();
		listaTipodeIdentificadores = new ArrayList<Enum_Aux_Tipo_Identificador>();
		for (Enum_Aux_Tipo_Identificador identificador : identificadores) {
			if (identificador.getAux_tipo_pessoa().isSelecionar())
				listaTipodeIdentificadores.add(identificador);
		}
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Pontuacao_Movimento> getPontuacoes() {
		return pontuacoes;
	}

	public void setPontuacoes(List<Pontuacao_Movimento> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}

	public Pontuacao_Movimento getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Pontuacao_Movimento pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Enum_Aux_Tipo_Identificador getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Enum_Aux_Tipo_Identificador tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	@Override
	public String toString() {
		return "Pontuacao_MovimentojsfController [perfilLogado=" + perfilLogado + ", pontuacoes=" + pontuacoes
				+ ", historico=" + historico + ", pontuacao=" + pontuacao + ", tipoIdentificador=" + tipoIdentificador
				+ ", listaTipodeIdentificadores=" + listaTipodeIdentificadores + ", pessoaPontuada=" + pessoaPontuada
				+ ", pontuacaoConfig=" + pontuacaoConfig + ", listaPontuacoesConfig=" + listaPontuacoesConfig
				+ ", tipoMovimentacao=" + tipoMovimentacao + ", clienteHistorico=" + clienteHistorico
				+ ", pontuacaoSoma=" + pontuacaoSoma + ", pontuacaoSomaCredito=" + pontuacaoSomaCredito
				+ ", pontuacaoSomaDebitoUtilizacao=" + pontuacaoSomaDebitoUtilizacao + ", pontuacaoSomaDebitoEstorno="
				+ pontuacaoSomaDebitoEstorno + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteHistorico == null) ? 0 : clienteHistorico.hashCode());
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + ((listaPontuacoesConfig == null) ? 0 : listaPontuacoesConfig.hashCode());
		result = prime * result + ((listaTipodeIdentificadores == null) ? 0 : listaTipodeIdentificadores.hashCode());
		result = prime * result + ((perfilLogado == null) ? 0 : perfilLogado.hashCode());
		result = prime * result + ((pessoaPontuada == null) ? 0 : pessoaPontuada.hashCode());
		result = prime * result + ((pontuacao == null) ? 0 : pontuacao.hashCode());
		result = prime * result + ((pontuacaoConfig == null) ? 0 : pontuacaoConfig.hashCode());
		result = prime * result + ((pontuacaoSoma == null) ? 0 : pontuacaoSoma.hashCode());
		result = prime * result + ((pontuacaoSomaCredito == null) ? 0 : pontuacaoSomaCredito.hashCode());
		result = prime * result + ((pontuacaoSomaDebitoEstorno == null) ? 0 : pontuacaoSomaDebitoEstorno.hashCode());
		result = prime * result
				+ ((pontuacaoSomaDebitoUtilizacao == null) ? 0 : pontuacaoSomaDebitoUtilizacao.hashCode());
		result = prime * result + ((pontuacoes == null) ? 0 : pontuacoes.hashCode());
		result = prime * result + ((tipoIdentificador == null) ? 0 : tipoIdentificador.hashCode());
		result = prime * result + ((tipoMovimentacao == null) ? 0 : tipoMovimentacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pontuacao_MovimentojsfController other = (Pontuacao_MovimentojsfController) obj;
		if (clienteHistorico == null) {
			if (other.clienteHistorico != null)
				return false;
		} else if (!clienteHistorico.equals(other.clienteHistorico))
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (listaPontuacoesConfig == null) {
			if (other.listaPontuacoesConfig != null)
				return false;
		} else if (!listaPontuacoesConfig.equals(other.listaPontuacoesConfig))
			return false;
		if (listaTipodeIdentificadores == null) {
			if (other.listaTipodeIdentificadores != null)
				return false;
		} else if (!listaTipodeIdentificadores.equals(other.listaTipodeIdentificadores))
			return false;
		if (perfilLogado == null) {
			if (other.perfilLogado != null)
				return false;
		} else if (!perfilLogado.equals(other.perfilLogado))
			return false;
		if (pessoaPontuada == null) {
			if (other.pessoaPontuada != null)
				return false;
		} else if (!pessoaPontuada.equals(other.pessoaPontuada))
			return false;
		if (pontuacao == null) {
			if (other.pontuacao != null)
				return false;
		} else if (!pontuacao.equals(other.pontuacao))
			return false;
		if (pontuacaoConfig == null) {
			if (other.pontuacaoConfig != null)
				return false;
		} else if (!pontuacaoConfig.equals(other.pontuacaoConfig))
			return false;
		if (pontuacaoSoma == null) {
			if (other.pontuacaoSoma != null)
				return false;
		} else if (!pontuacaoSoma.equals(other.pontuacaoSoma))
			return false;
		if (pontuacaoSomaCredito == null) {
			if (other.pontuacaoSomaCredito != null)
				return false;
		} else if (!pontuacaoSomaCredito.equals(other.pontuacaoSomaCredito))
			return false;
		if (pontuacaoSomaDebitoEstorno == null) {
			if (other.pontuacaoSomaDebitoEstorno != null)
				return false;
		} else if (!pontuacaoSomaDebitoEstorno.equals(other.pontuacaoSomaDebitoEstorno))
			return false;
		if (pontuacaoSomaDebitoUtilizacao == null) {
			if (other.pontuacaoSomaDebitoUtilizacao != null)
				return false;
		} else if (!pontuacaoSomaDebitoUtilizacao.equals(other.pontuacaoSomaDebitoUtilizacao))
			return false;
		if (pontuacoes == null) {
			if (other.pontuacoes != null)
				return false;
		} else if (!pontuacoes.equals(other.pontuacoes))
			return false;
		if (tipoIdentificador != other.tipoIdentificador)
			return false;
		if (tipoMovimentacao != other.tipoMovimentacao)
			return false;
		return true;
	}

	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadores() {
		return listaTipodeIdentificadores;
	}

	public void setListaTipodeIdentificadores(List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores) {
		this.listaTipodeIdentificadores = listaTipodeIdentificadores;
	}

	public Pessoa getPessoaPontuada() {
		return pessoaPontuada;
	}

	public void setPessoaPontuada(Pessoa pessoaPontuada) {
		this.pessoaPontuada = pessoaPontuada;
	}

	public Pontuacao_Config getPontuacaoConfigurada() {
		return pontuacaoConfig;
	}

	public void setPontuacaoConfigurada(Pontuacao_Config pontuacaoConfigurada) {
		this.pontuacaoConfig = pontuacaoConfigurada;
		
	}

	public Enum_Aux_Tipo_Mov_Pontuacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(Enum_Aux_Tipo_Mov_Pontuacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	/**
	 * @return the historico
	 */
	public List<Pontuacao_Movimento> getHistorico() {
		return historico;
	}

	/**
	 * @param historico
	 *            the historico to set
	 */
	public void setHistorico(List<Pontuacao_Movimento> historico) {
		this.historico = historico;
	}

	/**
	 * @return the clienteHistorico
	 */
	public Pessoa getClienteHistorico() {
		return clienteHistorico;
	}

	/**
	 * @param clienteHistorico
	 *            the clienteHistorico to set
	 */
	public void setClienteHistorico(Pessoa clienteHistorico) {
		this.clienteHistorico = clienteHistorico;
	}

	/**
	 * @return the pontuacaoSoma
	 */
	public Double getPontuacaoSoma() {
		return pontuacaoSoma;
	}

	/**
	 * @param pontuacaoSoma
	 *            the pontuacaoSoma to set
	 */
	public void setPontuacaoSoma(Double pontuacaoSoma) {
		this.pontuacaoSoma = pontuacaoSoma;
	}

	/**
	 * @return the pontuacaoSomaCredito
	 */
	public Double getPontuacaoSomaCredito() {
		return pontuacaoSomaCredito;
	}

	/**
	 * @param pontuacaoSomaCredito
	 *            the pontuacaoSomaCredito to set
	 */
	public void setPontuacaoSomaCredito(Double pontuacaoSomaCredito) {
		this.pontuacaoSomaCredito = pontuacaoSomaCredito;
	}

	/**
	 * @return the pontuacaoSomaDebitoUtilizacao
	 */
	public Double getPontuacaoSomaDebitoUtilizacao() {
		return pontuacaoSomaDebitoUtilizacao;
	}

	/**
	 * @param pontuacaoSomaDebitoUtilizacao
	 *            the pontuacaoSomaDebitoUtilizacao to set
	 */
	public void setPontuacaoSomaDebitoUtilizacao(Double pontuacaoSomaDebitoUtilizacao) {
		this.pontuacaoSomaDebitoUtilizacao = pontuacaoSomaDebitoUtilizacao;
	}

	/**
	 * @return the pontuacaoSomaDebitoEstorno
	 */
	public Double getPontuacaoSomaDebitoEstorno() {
		return pontuacaoSomaDebitoEstorno;
	}

	/**
	 * @param pontuacaoSomaDebitoEstorno
	 *            the pontuacaoSomaDebitoEstorno to set
	 */
	public void setPontuacaoSomaDebitoEstorno(Double pontuacaoSomaDebitoEstorno) {
		this.pontuacaoSomaDebitoEstorno = pontuacaoSomaDebitoEstorno;
	}

	public Pontuacao_Config getPontuacaoConfig() {
		return pontuacaoConfig;
	}

	public void setPontuacaoConfig(Pontuacao_Config pontuacaoConfig) {
		this.pontuacaoConfig = pontuacaoConfig;
		configPontuacao();
	}

	public List<Pontuacao_Config> getListaPontuacoesConfig() {
		return listaPontuacoesConfig;
	}

	public void setListaPontuacoesConfig(List<Pontuacao_Config> listaPontuacoesConfig) {
		this.listaPontuacoesConfig = listaPontuacoesConfig;
	}

}
