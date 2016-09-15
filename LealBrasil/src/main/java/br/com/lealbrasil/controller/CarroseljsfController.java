package br.com.lealbrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.lealbrasil.model.dao.BrindeDAO;
import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Pontuacao_MovimentoDAO;
import br.com.lealbrasil.model.entities.Brinde;
import br.com.lealbrasil.model.entities.Carrossel;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Pontuacao;
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
	private List<Brinde> brindes;
	private Pontuacao_Historico_Cliente pHCliente;
	private StreamedContent fotoRetornada;
	
	

	@PostConstruct
	public void listar() {
		if (!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.CLIENTES)
				&& !perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS))
			setRenderizaCarrossel(false);
		else
			setRenderizaCarrossel(true);

		carrosseis = new ArrayList<Carrossel>();
		Date data = new Date();
		data = Utilidades.retornaValidade(1);
		carrossel = new Carrossel("Pizza Parma", "/images/pizza parma.png", "", data, 8d, "12A16-14");
		carrosseis.add(carrossel);
		data = Utilidades.retornaValidade(2);
		carrossel = new Carrossel("Salão da Corte", "/images/salao da corte.png", "", data, 10d, "15J17-09");
		carrosseis.add(carrossel);
		data = Utilidades.retornaValidade(5);
		carrossel = new Carrossel("Villa Kids", "/images/villa kids.png", "", data, 15d, "18T19-A2");
		carrosseis.add(carrossel);

	}

	public void pontuacao() {
		PessoaDAO pDao = new PessoaDAO();
		setEstabelecimentosPontuados(pDao.listaEstabelecimentosPontuados(perfilLogado.getUsLogado().getPessoa()));
		Utilidades.abrirfecharDialogos("dialogoPontuacao", true);
		pontuacaoHistorico();
	}

	public void pontuacaoHistorico() {
		pHClientes = new ArrayList<Pontuacao_Historico_Cliente>();
		Pontuacao_MovimentoDAO pMovDAO = new Pontuacao_MovimentoDAO();
		Pontuacao_Historico_Cliente pHCliente;
		double pontos;
		double credito;
		double debito;
		int i = 0;

		for (Pessoa estabPont : estabelecimentosPontuados) {
			/*
			 * somadePontos(PerfilLogado perfilLogado, Pessoa cliente, boolean
			 * pesqAss, Enum_Aux_Tipo_Mov_Pontuacao tipoSoma)
			 */
			credito = pMovDAO.somadePontos(estabPont, perfilLogado.getUsLogado().getPessoa(), true,
					Enum_Aux_Tipo_Mov_Pontuacao.C);
			debito = pMovDAO.somadePontos(estabPont, perfilLogado.getUsLogado().getPessoa(), true,
					Enum_Aux_Tipo_Mov_Pontuacao.D);

			debito = pMovDAO.somadePontos(estabPont, perfilLogado.getUsLogado().getPessoa(), true,
					Enum_Aux_Tipo_Mov_Pontuacao.E);
			pontos = credito - debito;
			pHCliente = new Pontuacao_Historico_Cliente();
			pHCliente.setId_Assinante(estabPont);
			pHCliente.setPontos(pontos);
			pHClientes.add(i, pHCliente);
			i++;

		}

	}

	public void listarBrindes(Pessoa id_Associado) {
		BrindeDAO bDAO = new BrindeDAO();		
		brindes = bDAO.listar(id_Associado);	
		for (Brinde brinde : brindes) {		
			try{
			if (brinde.getPontos() > pHCliente.getPontos())
				
				brinde.setFoto2(retornafoto("/imagens/naoatingido.png"));
			else
				brinde.setFoto2(retornafoto("/imagens/atingido.png"));
			}catch(IOException error){
				error.printStackTrace();
				mensagensDisparar("erro ao tentar converter foto 2");
			}
		}
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
			} 
			else {
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
		listarBrindes(pHCliente.getId_Assinante());
		Utilidades.abrirfecharDialogos("dialogoBrinde", true);
	}

	public void canceladialogPontuacao() {
		Utilidades.abrirfecharDialogos("dialogoPontuacao", false);
	}

	public void canceladialogBrinde() {
		Utilidades.abrirfecharDialogos("dialogoBrinde", false);
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

	@Override
	public String toString() {
		return "CarroseljsfController [carrosseis=" + carrosseis + ", carrossel=" + carrossel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrosseis == null) ? 0 : carrosseis.hashCode());
		result = prime * result + ((carrossel == null) ? 0 : carrossel.hashCode());
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
		CarroseljsfController other = (CarroseljsfController) obj;
		if (carrosseis == null) {
			if (other.carrosseis != null)
				return false;
		} else if (!carrosseis.equals(other.carrosseis))
			return false;
		if (carrossel == null) {
			if (other.carrossel != null)
				return false;
		} else if (!carrossel.equals(other.carrossel))
			return false;
		return true;
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

	public List<Brinde> getBrindes() {
		return brindes;
	}

	public void setBrindes(List<Brinde> brindes) {
		this.brindes = brindes;
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

	


}
