package br.com.lealbrasil.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.lealbrasil.model.dao.Item_de_MovimentoDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.lealbrasil.model.entities.Item_de_Movimento;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@ViewScoped
@ManagedBean(name = "brinde")
public class BrindejsfController extends GenericController {
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private List<Item_de_Movimento> itens;
	private Item_de_Movimento item;
	

	@PostConstruct
	public void listar() {
		listadecaminhosdeimagem();
	}

	public  void listadecaminhosdeimagem(){
		itens = new ArrayList<Item_de_Movimento>(); 
		
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		List<Item_de_Movimento> l = iMDAO.listar(perfilLogado.getAssLogado(),Enum_Aux_Tipo_Item_de_Movimento.BRINDE);
		
		
		int x = 0;
		for (Item_de_Movimento i : l) {			
			i.setCaminhodaImagem(Utilidades.caminho("Brindes"));
			i.setTipodeImagem(Utilidades.tipodeImagem());
			i.setCaminhodaImagem(i.getCaminhodaImagem() + i.getId() + ".png");			
			itens.add(x,i);
			x++;
		}
		
	}

	public void especificaCaminhoEImagem(String id) {
		item.setCaminhodaImagem(Utilidades.caminho("Brindes"));
		item.setTipodeImagem(Utilidades.tipodeImagem());
		item.setCaminhodaImagem(item.getCaminhodaImagem() + id + item.getTipodeImagem());
	}

	public void novo() {
		item = new Item_de_Movimento(perfilLogado.getUsLogado().getPessoa(), perfilLogado.getAssLogado() , 
				 Enum_Aux_Sim_ou_Nao.SIM,Enum_Aux_Tipo_Item_de_Movimento.BRINDE);
		Utilidades.abrirfecharDialogos("dialogoCadastro", true);
	}

	

	public void merge() {
		Path caminhoTemp;
		if (item.getCaminhoTemp() == null || item.getCaminhoTemp()=="") {
			mensagensDisparar("Imagem é obrigatória!!!");
			return;
		} else {
			caminhoTemp = Paths.get(item.getCaminhoTemp());
			if (!Files.exists(caminhoTemp)) {
				mensagensDisparar("Imagem é obrigatória!!!");
				return;
			}
		}
		
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		
		
		item = iMDAO.merge(item);
		especificaCaminhoEImagem("" + item.getId());
		if (item.getCaminhodaImagem() == null || item.getCaminhodaImagem().length() <= 0) {
			item.setCaminhodaImagem(item.getCaminhodaImagem());
			item = iMDAO.merge(item);
		}

		Path origem = caminhoTemp;
		Path destino = Paths.get(item.getCaminhodaImagem());
		try {
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException error) {
			mensagensDisparar("Ocorreu um erro ao tentar salvar a imagem");

			error.printStackTrace();
		}

		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		listar();
	}

	public void editar(ActionEvent event) {
		item = (Item_de_Movimento) event.getComponent().getAttributes().get("registroAtual");
		especificaCaminhoEImagem("" + item.getId());
		item.setCaminhoTemp(item.getCaminhodaImagem());
		Utilidades.abrirfecharDialogos("dialogoCadastro", true);

	}

	public void upload(FileUploadEvent event) {
		try {
			UploadedFile arquivoUpload = event.getFile();
			// Messages.addGlobalInfo(arquivoUpload.getContentType()+"-"+arquivoUpload.getSize()+"-"+arquivoUpload.getFileName()+"-");
			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			item.setCaminhoTemp(arquivoTemp.toString());
			item.setCaminhodaImagem(item.getCaminhoTemp());
			

			mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}

	public void cancelar() {

		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		listar();
	}

	public void excluir(ActionEvent event) {
		item = (Item_de_Movimento) event.getComponent().getAttributes().get("registroAtual");
		especificaCaminhoEImagem("" + item.getId());
		Path arquivo = Paths.get(item.getCaminhodaImagem());
		try {
			Files.deleteIfExists(arquivo);
		} catch (IOException error) {

			error.printStackTrace();
		}
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		iMDAO.excluir(item);
		listar();
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Item_de_Movimento> getItens() {
		return itens;
	}

	public void setItens(List<Item_de_Movimento> itens) {
		this.itens = itens;
	}

	public Item_de_Movimento getItem() {
		return item;
	}

	public void setItem(Item_de_Movimento item) {
		this.item = item;
	}

	
}
