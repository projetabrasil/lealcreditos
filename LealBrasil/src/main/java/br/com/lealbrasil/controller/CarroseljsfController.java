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

import br.com.lealbrasil.model.dao.Item_de_MovimentoDAO;
import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Ponto_MovimentoDAO;
import br.com.lealbrasil.model.entities.Carrossel;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.lealbrasil.model.entities.Item_de_Movimento;
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

	
	public void listarBrindes(Pessoa id_Cliente,Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento) {
	Item_de_MovimentoDAO pDAO = new Item_de_MovimentoDAO();	
		List<Item_de_Movimento> brindes = new ArrayList<Item_de_Movimento>();
		
		brindes = pDAO.listar(id_Cliente, enum_Aux_Tipo_Item_de_Movimento);
		int i =0;
		for (Item_de_Movimento brinde : brindes) {		
			try{
				
				brinde.setCaminhodaImagem(Utilidades.caminho("Brindes"));
				brinde.setTipodeImagem(Utilidades.tipodeImagem());
				brinde.setCaminhodaImagem(brinde.getCaminhodaImagem() + brinde.getId() + ".png");		
				
			if (brinde. getPonto() > pHCliente.getPontos()){
				brinde.setCaminhodaImagem2("/images/naoatingido.png");				
				}
			else
				brinde.setCaminhodaImagem2("/images/atingido.png");
			brinde.setFoto(retornafoto(brinde.getCaminhodaImagem()));
			brinde.setFoto2(retornafoto(brinde.getCaminhodaImagem2()));
			brindes.set(i,brinde);
			i++;
			
			}catch(IOException error){
				error.printStackTrace();
				mensagensDisparar("erro ao tentar converter foto 2");
			}
		}
		this.brindes =  brindes;
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
		listarBrindes(pHCliente.getId_Assinante(),Enum_Aux_Tipo_Item_de_Movimento.BRINDE);
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

	
}
