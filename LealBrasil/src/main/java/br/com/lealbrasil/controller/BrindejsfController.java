package br.com.lealbrasil.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.lealbrasil.model.dao.BrindeDAO;
import br.com.lealbrasil.model.entities.Brinde;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@ViewScoped
@ManagedBean(name = "brinde")
public class BrindejsfController extends GenericController {
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private List<Brinde> brindes;
	private Brinde brinde;
	private String caminho;
	private String tipoImagem;
	private String caminhodaImagem;
	private String caminhoTemp;

	@PostConstruct
	public void listar() {
		BrindeDAO bDAO = new BrindeDAO();
		brindes = bDAO.listar(perfilLogado.getAssLogado());
	}

	public void especificaCaminhoEImagem(String id) {
		setCaminho(Utilidades.caminho("Brindes"));
		setTipoImagem(Utilidades.tipodeImagem());
		setCaminhodaImagem(getCaminho() + id + getTipoImagem());
	}

	public void novo() {
		brinde = new Brinde();
		configuraBrinde();

		Utilidades.abrirfecharDialogos("dialogoCadastro", true);
		caminhoTemp = null;

	}

	public void configuraBrinde() {
		brinde.setUltimaAtualizacao(Utilidades.retornaCalendario());
		brinde.setDescricao("");
		brinde.setId_Empresa(1);
		brinde.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		brinde.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

	}

	public void merge() {

		if (getCaminhoTemp() == null) {
			mensagensDisparar("Imagem é obrigatória!!!");
			return;
		} else {
			Path caminhoTemp = Paths.get(getCaminhoTemp());
			if (!Files.exists(caminhoTemp)) {
				mensagensDisparar("Imagem é obrigatória!!!");
				return;
			}
		}
		BrindeDAO bDAO = new BrindeDAO();
		brinde = bDAO.merge(brinde);
		especificaCaminhoEImagem("" + brinde.getId());
		if (brinde.getCaminhodaImagem() == null || brinde.getCaminhodaImagem().length() <= 0) {
			brinde.setCaminhodaImagem(getCaminhodaImagem());
			brinde = bDAO.merge(brinde);
		}

		Path origem = Paths.get(getCaminhoTemp());
		Path destino = Paths.get(getCaminhodaImagem());
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
		brinde = (Brinde) event.getComponent().getAttributes().get("registroAtual");
		especificaCaminhoEImagem("" + brinde.getId());
		setCaminhoTemp(getCaminhodaImagem());
		Utilidades.abrirfecharDialogos("dialogoCadastro", true);

	}

	public void upload(FileUploadEvent event) {
		try {
			UploadedFile arquivoUpload = event.getFile();
			// Messages.addGlobalInfo(arquivoUpload.getContentType()+"-"+arquivoUpload.getSize()+"-"+arquivoUpload.getFileName()+"-");
			Path arquivoTemp = Files.createTempFile(null, null);
			
			
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			setCaminhoTemp(arquivoTemp.toString());
			setCaminhodaImagem(getCaminhoTemp());

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
		brinde = (Brinde) event.getComponent().getAttributes().get("registroAtual");
		especificaCaminhoEImagem("" + brinde.getId());
		Path arquivo = Paths.get(getCaminhodaImagem());
		try {
			Files.deleteIfExists(arquivo);
		} catch (IOException error) {

			error.printStackTrace();
		}
		BrindeDAO bDAO = new BrindeDAO();
		bDAO.excluir(brinde);
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

	public List<Brinde> getBrindes() {
		return brindes;
	}

	public void setBrindes(List<Brinde> brindes) {
		this.brindes = brindes;
	}

	public Brinde getBrinde() {
		return brinde;
	}

	public void setBrinde(Brinde brinde) {
		this.brinde = brinde;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getTipoImagem() {
		return tipoImagem;
	}

	public void setTipoImagem(String tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}

	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}

	public String getCaminhoTemp() {
		return caminhoTemp;
	}

	public void setCaminhoTemp(String caminhoTemp) {
		this.caminhoTemp = caminhoTemp;
	}

}
