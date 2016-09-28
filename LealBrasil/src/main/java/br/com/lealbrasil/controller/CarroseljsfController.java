package br.com.lealbrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.lealbrasil.model.dao.AgendamentoDAO;
import br.com.lealbrasil.model.dao.Item_de_MovimentoDAO;
import br.com.lealbrasil.model.dao.Movimento_Detalhe_ADAO;
import br.com.lealbrasil.model.dao.Movimento_Detalhe_Dias_DisponiveisDAO;
import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Ponto_MovimentoDAO;
import br.com.lealbrasil.model.entities.Agendamento;
import br.com.lealbrasil.model.entities.Carrossel;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Status_Agendamento;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.lealbrasil.model.entities.Item_de_Movimento;
import br.com.lealbrasil.model.entities.Movimento_Detalhe_A;
import br.com.lealbrasil.model.entities.Movimento_Detalhe_Dias_Disponiveis;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.joins.Pontuacao_Historico_Cliente;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class CarroseljsfController extends GenericController implements Serializable {
	private List<Carrossel> carrosseis;
	private Carrossel carrossel;
	private boolean renderizaCarrossel;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private List<Pessoa> estabelecimentosPontuados;
	private List<Pontuacao_Historico_Cliente> pHClientes;
	private List<Item_de_Movimento> brindes;
	private Pontuacao_Historico_Cliente pHCliente;
	private StreamedContent fotoRetornada;
	private List<Movimento_Detalhe_A> mDAs;
	private List<Movimento_Detalhe_Dias_Disponiveis> mDDDs;
	private List<Integer> diasDisponiveis;
	private ArrayList<Calendar> datasIndisponiveis;
	@ManagedProperty(value = "#{agendamento}")
	private AgendamentojsfController agendamento;

	@PostConstruct
	public void listar() {
		if (!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.CLIENTES)
				&& !perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS))
			setRenderizaCarrossel(false);
		else
			setRenderizaCarrossel(true);

		listaVouchers();

	}

	public void merge() {
		agendamento.merge();
		listar();
	}

	public void listaVouchers() {
		Movimento_Detalhe_ADAO mDADAO = new Movimento_Detalhe_ADAO();
		mDAs = mDADAO.vouchersParaoCliente(perfilLogado.getUsLogado().getPessoa(),
				Enum_Aux_Tipo_Item_de_Movimento.VOUCHER);
		checarAgendamentodoCliente();

	}

	public void checarAgendamentodoCliente() {
		int i = 0;
		for (Movimento_Detalhe_A movDet : mDAs) {
			Agendamento agendamento;
			AgendamentoDAO agDAO = new AgendamentoDAO();
			agendamento = agDAO.checaAgendamentoAtivodoCliente(perfilLogado.getUsLogado().getPessoa(), movDet,
					Enum_Aux_Status_Agendamento.AGENDADO);

			if (agendamento != null) {
				movDet.setDisponivel(false);
				movDet.setDescricaoDisponivel("Agendado!!!");
				movDet.setCodigo(agendamento.getCodigo());
			} else {
				movDet.setDisponivel(true);
				movDet.setDescricaoDisponivel("Agendar");
				movDet.setCodigo("");
			}
			mDAs.set(i, movDet);
			
			i++;

		}

	}

	public void pontuacao() {
		PessoaDAO pDao = new PessoaDAO();
		setEstabelecimentosPontuados(pDao.listaEstabelecimentosPontuados(perfilLogado.getUsLogado().getPessoa()));
		Utilidades.abrirfecharDialogos("dialogoPontuacao", true);
		pontuacaoHistorico();
	}

	public void pontuacaoHistorico() {
		pHClientes = new ArrayList<Pontuacao_Historico_Cliente>();
		Ponto_MovimentoDAO pMovDAO = new Ponto_MovimentoDAO();
		Pontuacao_Historico_Cliente pHCliente;
		double pontos;
		double credito;
		double debito;
		int i = 0;

		for (Pessoa estabPont : estabelecimentosPontuados) {

			credito = pMovDAO.somadePontos(estabPont, perfilLogado.getUsLogado().getPessoa(), true,
					Enum_Aux_Tipo_Mov_Ponto.C);
			debito = pMovDAO.somadePontos(estabPont, perfilLogado.getUsLogado().getPessoa(), true,
					Enum_Aux_Tipo_Mov_Ponto.D);

			debito += pMovDAO.somadePontos(estabPont, perfilLogado.getUsLogado().getPessoa(), true,
					Enum_Aux_Tipo_Mov_Ponto.E);
			pontos = credito - debito;
			pHCliente = new Pontuacao_Historico_Cliente();
			pHCliente.setId_Assinante(estabPont);
			pHCliente.setPontos(pontos);
			pHClientes.add(i, pHCliente);
			i++;

		}

	}

	public void listarBrindes(Pessoa id_Cliente, Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento) {
		Item_de_MovimentoDAO pDAO = new Item_de_MovimentoDAO();
		List<Item_de_Movimento> brindes = new ArrayList<Item_de_Movimento>();

		brindes = pDAO.listar(id_Cliente, enum_Aux_Tipo_Item_de_Movimento, null);
		int i = 0;
		for (Item_de_Movimento brinde : brindes) {
			try {

				brinde.setCaminhodaImagem(Utilidades.caminho("Brindes"));
				brinde.setTipodeImagem(Utilidades.tipodeImagem());
				brinde.setCaminhodaImagem(brinde.getCaminhodaImagem() + brinde.getId() + ".png");

				if (brinde.getPonto() > pHCliente.getPontos()) {
					brinde.setCaminhodaImagem2("/images/naoatingido.png");
				} else
					brinde.setCaminhodaImagem2("/images/atingido.png");
				brinde.setFoto(retornafoto(brinde.getCaminhodaImagem()));
				brinde.setFoto2(retornafoto(brinde.getCaminhodaImagem2()));
				brindes.set(i, brinde);
				i++;

			} catch (IOException error) {
				error.printStackTrace();
				mensagensDisparar("erro ao tentar converter foto 2");
			}
		}
		this.brindes = brindes;
	}

	public StreamedContent retornafoto(String caminhodaImagem) throws IOException {
		String branco;
		if (caminhodaImagem == null || caminhodaImagem.isEmpty()) {
			branco = "/imagens/branco.png";

			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				fotoRetornada = new DefaultStreamedContent(stream);
			}

		} else {
			Path path = Paths.get(caminhodaImagem);
			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				fotoRetornada = new DefaultStreamedContent(stream);
			} else {
				branco = "/imagens/branco.png";
				path = Paths.get(branco);

				if (Files.exists(path)) {
					InputStream stream = Files.newInputStream(path);
					fotoRetornada = new DefaultStreamedContent(stream);
				}
			}
		}
		return fotoRetornada;
	}

	public void mostradialogBrinde(ActionEvent event) {
		pHCliente = (Pontuacao_Historico_Cliente) event.getComponent().getAttributes().get("registroAtual");
		listarBrindes(pHCliente.getId_Assinante(), Enum_Aux_Tipo_Item_de_Movimento.BRINDE);
		Utilidades.abrirfecharDialogos("dialogoBrinde", true);
	}

	public void canceladialogPontuacao() {
		Utilidades.abrirfecharDialogos("dialogoPontuacao", false);
	}

	public void canceladialogBrinde() {
		Utilidades.abrirfecharDialogos("dialogoBrinde", false);
	}

	public void canceladialogCalendario() {
		Utilidades.abrirfecharDialogos("dialogoCalendario", false);
	}

	public void retornaDiasDisponiveis() {
		diasDisponiveis = new ArrayList<Integer>();
		for (Movimento_Detalhe_Dias_Disponiveis j : mDDDs)
			diasDisponiveis.add(j.getEnum_Aux_Dia_da_Semana().getId());
	}

	public void mostraCalendario(Movimento_Detalhe_A mDA) {
		Movimento_Detalhe_Dias_DisponiveisDAO mDDDDAO = new Movimento_Detalhe_Dias_DisponiveisDAO();
		mDDDs = mDDDDAO.retornaDiasDisponiveis(mDA);
		retornaDiasDisponiveis();
		agendamento.novo(mDA);

	}

	public void acao(ActionEvent event) {
		Movimento_Detalhe_A mDA = (Movimento_Detalhe_A) event.getComponent().getAttributes().get("registroAtual");
		String acao;
		acao = mDA.getDescricaoDisponivel().toUpperCase();
		if (acao.equals("AGENDAR")) 
			mostraCalendario(mDA);
		else{			 
			String temp = System.getProperty("java.io.tmpdir");
			Agendamento ag;		
   		    AgendamentoDAO agDAO = new AgendamentoDAO();
			ag = agDAO.checaAgendamentoAtivodoCliente(perfilLogado.getUsLogado().getPessoa(), mDA,
			Enum_Aux_Status_Agendamento.AGENDADO);			
			Utilidades.pdf(temp+mDA.getCodigo()+".pdf", mDA, ag);
		}
			

	}

	public void montadiasindisponiveis() {

	}

	public List<Carrossel> getCarrosseis() {
		return carrosseis;
	}

	public void setCarrosseis(List<Carrossel> carrosseis) {
		this.carrosseis = carrosseis;
	}

	public Carrossel getCarrossel() {
		return carrossel;
	}

	public void setCarrossel(Carrossel carrossel) {
		this.carrossel = carrossel;
	}

	public boolean isRenderizaCarrossel() {
		return renderizaCarrossel;
	}

	public void setRenderizaCarrossel(boolean renderizaCarrossel) {
		this.renderizaCarrossel = renderizaCarrossel;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Pessoa> getEstabelecimentosPontuados() {
		return estabelecimentosPontuados;
	}

	public void setEstabelecimentosPontuados(List<Pessoa> estabelecimentosPontuados) {
		this.estabelecimentosPontuados = estabelecimentosPontuados;
	}

	public List<Pontuacao_Historico_Cliente> getpHClientes() {
		return pHClientes;
	}

	public void setpHClientes(List<Pontuacao_Historico_Cliente> pHClientes) {
		this.pHClientes = pHClientes;
	}

	public Pontuacao_Historico_Cliente getpHCliente() {
		return pHCliente;
	}

	public void setpHCliente(Pontuacao_Historico_Cliente pHCliente) {
		this.pHCliente = pHCliente;
	}

	public StreamedContent getFotoRetornada() {
		return fotoRetornada;
	}

	public void setFotoRetornada(StreamedContent fotoRetornada) {
		this.fotoRetornada = fotoRetornada;
	}

	public List<Item_de_Movimento> getBrindes() {
		return brindes;
	}

	public void setBrindes(List<Item_de_Movimento> brindes) {
		this.brindes = brindes;
	}

	public List<Movimento_Detalhe_A> getmDAs() {
		return mDAs;
	}

	public void setmDAs(List<Movimento_Detalhe_A> mDAs) {
		this.mDAs = mDAs;
	}

	public List<Movimento_Detalhe_Dias_Disponiveis> getmDDDs() {
		return mDDDs;
	}

	public void setmDDDs(List<Movimento_Detalhe_Dias_Disponiveis> mDDDs) {
		this.mDDDs = mDDDs;
	}

	public List<Calendar> getDatasIndisponiveis() {
		return datasIndisponiveis;
	}

	public void setDatasIndisponiveis(ArrayList<Calendar> datasIndisponiveis) {
		this.datasIndisponiveis = datasIndisponiveis;
	}

	public List<Integer> getDiasDisponiveis() {
		return diasDisponiveis;
	}

	public void setDiasDisponiveis(List<Integer> diasDisponiveis) {
		this.diasDisponiveis = diasDisponiveis;
	}

	public AgendamentojsfController getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(AgendamentojsfController agendamento) {
		this.agendamento = agendamento;
	}

}
