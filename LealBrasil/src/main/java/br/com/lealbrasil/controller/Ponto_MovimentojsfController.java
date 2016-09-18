package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.controller.entitiesconfig.PessoaConfig;
import br.com.lealbrasil.model.business.PessoaBusiness2;
import br.com.lealbrasil.model.business.PessoaGenericBusiness;
import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.PontoDAO;
import br.com.lealbrasil.model.dao.Ponto_MovimentoDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Ponto;
import br.com.lealbrasil.model.entities.Ponto_Movimento;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")

@ManagedBean(name = "ponto_mov")
@ViewScoped
public class Ponto_MovimentojsfController implements Serializable {
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	List<Ponto_Movimento> pontos_Mov;
	List<Ponto_Movimento> historico;
	private Ponto_Movimento ponto_movimento = new Ponto_Movimento();
	private Enum_Aux_Tipo_Identificador tipoIdentificador;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores;
	private Pessoa pessoaPontuada = new Pessoa();
	private Ponto ponto;
	private List<Ponto> listaPonto;
	private Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao;
	private PessoaConfig pessoaConfig;
	private Pessoa clienteHistorico;
	private Double pontoSoma;
	private Double pontoSomaCredito;
	private Double pontoSomaDebitoUtilizacao;
	private Double pontoSomaDebitoEstorno;

	@PostConstruct
	public void listar() {
		if (!perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC)
				&& !perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD)
				&& !perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE))
			return;
		
		if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC)) 
			setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto.C);

		else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD)) 
			setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto.D);
		 else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE)) 
			setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto.E);
		
		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		pontos_Mov = pDAO.listar(perfilLogado, null, true);
		litarPontuacoesConfig();
		listarTipodeIdentificadores();
		setTipoIdentificador(Enum_Aux_Tipo_Identificador.CPF);
	}

	public void listarHistorico(Pessoa cliente) {
		if (cliente != null)
			clienteHistorico = cliente;
		else
			clienteHistorico = new Pessoa();

		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		historico = pDAO.listar(perfilLogado, cliente, true);
		retornasomaPontuacao(cliente);
		Utilidades.abrirfecharDialogos("dialogoHistorico", true);
	}

	public void retornasomaPontuacao(Pessoa cliente) {
		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		pontoSomaCredito = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Ponto.C);
		pontoSomaDebitoEstorno = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Ponto.E);
		pontoSomaDebitoUtilizacao = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Ponto.D);

		if (pontoSomaCredito == null)
			pontoSomaCredito = 0d;
		if (pontoSomaDebitoUtilizacao == null)
			pontoSomaDebitoUtilizacao = 0d;
		if (pontoSomaDebitoEstorno == null)
			pontoSomaDebitoEstorno = 0d;
		pontoSoma = pontoSomaCredito - (pontoSomaDebitoUtilizacao + pontoSomaDebitoEstorno);
	}

	private void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public void novo() {
		configurarPessoa();
		if (listaPonto != null && listaPonto.size() > 0)
			setPonto(listaPonto.get(0));
		pessoaPontuada.setCpf_Cnpj("");
		pontoSoma = 0d;
		pontoSomaCredito = 0d;
		pontoSomaDebitoUtilizacao = 0d;
		pontoSomaDebitoEstorno = 0d;
		Utilidades.abrirfecharDialogos("dialogoPontuar", true);
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
		pessoaPontuada = new Pessoa();
		pessoaPontuada = pessoaConfig.ConfiguraPessoa(tipoIdentificador, perfilLogado.getUsLogado(), pessoaPontuada,
				false);
		pessoaPontuada.setCpf_Cnpj("");
	}

	public void litarPontuacoesConfig() {
		PontoDAO pDAO = new PontoDAO();
		setListaPonto(
				pDAO.retornarListaPontoConfig(perfilLogado.getAssLogado(), Enum_Aux_Tipo_Item_de_Movimento.PONTO));
	}

	public void configPonto_Movimento() {
		

		ponto_movimento = new Ponto_Movimento();
		if (ponto != null && ponto.getId() != null) {
			ponto_movimento.setId_ponto(ponto);
			ponto_movimento.setId_pessoa_associado(perfilLogado.getAssLogado());
			ponto_movimento.setPontuacaoMinima(ponto.getPontuacaoMinima());
			ponto_movimento.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			ponto_movimento.setUnidadeporPonto(ponto.getUnidadeporPonto());
			ponto_movimento.setValordaUnidade(ponto.getValordaUnidade());
			ponto_movimento.setValorUnidadeDevolucao(ponto.getValorUnidadeDevolucao());
			ponto_movimento.setValorUnidadeTroca(ponto.getValorUnidadeTroca());
			ponto_movimento.setDiasValidade(ponto.getDiasValidade());
			ponto_movimento.setValidade(Utilidades.retornaValidade(ponto_movimento.getDiasValidade()));
			ponto_movimento.setValoraPontuar(0);

			if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC))
				ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.C);
			else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD))
				ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.D);
			else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE))
				ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.E);
		}

	}

	public void configPessoaNova(String cpf_Cnpj) {
		pessoaPontuada = new Pessoa();
		configurarPessoa();
		pessoaPontuada = pessoaConfig.ConfiguraPessoa(tipoIdentificador, perfilLogado.getUsLogado(), pessoaPontuada,
				false);
		pessoaPontuada.setCpf_Cnpj(cpf_Cnpj);
		pessoaConfig.mudarLabels(tipoIdentificador.getAux_tipo_pessoa());

		pontoSomaCredito = 0d;
		pontoSomaDebitoUtilizacao = 0d;
		pontoSomaDebitoEstorno = 0d;
		pontoSoma = 0d;

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
				Utilidades.abrirfecharDialogos("dialogoCadastro", true);
			} else {
				retornasomaPontuacao(pessoaPontuada);
			}
			configPonto_Movimento();
		} else {
			configPessoaNova(cpf_Cnpj);
			configPonto_Movimento();
		}
	}

	public void pessoaMerge(boolean validarEmail, boolean validarSenha) {
		Usuario usuario = new Usuario();
		usuario.setPessoa(pessoaPontuada);

		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, validarEmail, validarSenha, true))
			return;
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		pessoaPontuada = PessoaGenericBusiness.merge(pessoaPontuada, perfilLogado.getUsLogado(), perfilLogado, false);
	}

	public void merge() {

		if (pessoaPontuada == null || pessoaPontuada.getId() == null)
			return;
		ponto_movimento.setId_pessoa_cliente(pessoaPontuada);
		ponto_movimento.setId_Empresa(1);

		if (ponto_movimento.getCreditaDebita().equals(Enum_Aux_Tipo_Mov_Ponto.C)) {
			int pontos = (int) (ponto_movimento.getUnidadeporPonto()
					* ((int) ponto_movimento.getValoraPontuar() / ponto_movimento.getValordaUnidade()));
			ponto_movimento.setPontosAtingidos(pontos);

		} else {
			if (pontoSoma < ponto_movimento.getPontosAtingidos()) {
				ponto_movimento.setPontosAtingidos(0);
				mensagensDisparar("Você não tem pontuações o suficiente ocorrrer DEBITO/ESTORNO!!!");

				return;

			}
			ponto_movimento
					.setValoraPontuar(ponto_movimento.getValordaUnidade() * ponto_movimento.getPontosAtingidos());
		}

		ponto_movimento.setUltimaAtualizacao(Utilidades.retornaCalendario());

		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		ponto_movimento = pDAO.merge(ponto_movimento);

		if (ponto_movimento != null && ponto_movimento.getId() != null) {
			mensagensDisparar("Pontuação para  " + ponto_movimento.getId_pessoa_cliente().getDescricao()
					+ " realizada com sucesso!!!");
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



	public List<Ponto_Movimento> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Ponto_Movimento> historico) {
		this.historico = historico;
	}

	public Ponto_Movimento getPonto_movimento() {
		return ponto_movimento;
	}

	public void setPonto_movimento(Ponto_Movimento ponto_movimento) {
		this.ponto_movimento = ponto_movimento;
	}

	public Enum_Aux_Tipo_Identificador getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Enum_Aux_Tipo_Identificador tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
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

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public List<Ponto> getListaPonto() {
		return listaPonto;
	}

	public void setListaPonto(List<Ponto> listaPonto) {
		this.listaPonto = listaPonto;
	}

	public Enum_Aux_Tipo_Mov_Ponto getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public Pessoa getClienteHistorico() {
		return clienteHistorico;
	}

	public void setClienteHistorico(Pessoa clienteHistorico) {
		this.clienteHistorico = clienteHistorico;
	}

	public Double getPontoSoma() {
		return pontoSoma;
	}

	public void setPontoSoma(Double pontoSoma) {
		this.pontoSoma = pontoSoma;
	}

	public Double getPontoSomaCredito() {
		return pontoSomaCredito;
	}

	public void setPontoSomaCredito(Double pontoSomaCredito) {
		this.pontoSomaCredito = pontoSomaCredito;
	}

	public Double getPontoSomaDebitoUtilizacao() {
		return pontoSomaDebitoUtilizacao;
	}

	public void setPontoSomaDebitoUtilizacao(Double pontoSomaDebitoUtilizacao) {
		this.pontoSomaDebitoUtilizacao = pontoSomaDebitoUtilizacao;
	}

	public Double getPontoSomaDebitoEstorno() {
		return pontoSomaDebitoEstorno;
	}

	public void setPontoSomaDebitoEstorno(Double pontoSomaDebitoEstorno) {
		this.pontoSomaDebitoEstorno = pontoSomaDebitoEstorno;
	}

	public List<Ponto_Movimento> getPontos_Mov() {
		return pontos_Mov;
	}

	public void setPontos_Mov(List<Ponto_Movimento> pontos_Mov) {
		this.pontos_Mov = pontos_Mov;
	}

		
}
