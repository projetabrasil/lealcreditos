package br.com.lealbrasil.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.lealbrasil.model.dao.UsuarioDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipos_Mensagens;
import br.com.lealbrasil.model.entities.Enum_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.PerfilLogado;

@SuppressWarnings("serial")
@SessionScoped()
@ManagedBean(name = "autenticacaojsfController")
public class AutenticacaojsfController extends GenericController implements Serializable {
	public PerfilLogado perfilLogado;
	

	@PostConstruct
	public void iniciar() {
		perfilLogado = new PerfilLogado();	
	}
	public void renderizar(boolean renderizaAssociado) {
		iniciar();
		
		perfilLogado.setRenderizaAssociado(renderizaAssociado);
		if (renderizaAssociado)
			perfilLogado.setFoco("ass");
		else{
			 
			perfilLogado.setFoco("usuario");
			}
		
		Faces.navigate("/pages/autenticacao.xhtml");
	}
	
	public void cadastroAutomatico() {
		perfilLogado.setRenderizaAssociado(false);
		perfilLogado.setIdentificadorAssociado("99999999999");
		perfilLogado.setSenhaUsuario("98765432");		
		autenticar(true);		
		
		
		
	}
	public void autenticar(boolean cadAutomatico) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (perfilLogado.getIdentificadorUsuario() == null || perfilLogado.getIdentificadorUsuario().length() <= 0)
			perfilLogado.setIdentificadorUsuario(perfilLogado.getIdentificadorAssociado());

		perfilLogado.setUsLogado(
				usuarioDAO.autenticar(perfilLogado.getIdentificadorUsuario(), perfilLogado.getSenhaUsuario()));
		if (perfilLogado.getUsLogado() == null) {
			mensagensDisparar(Enum_Aux_Tipos_Mensagens.ERRAUTENTICACAO.getMensagem());
			return;
		}

		perfilLogado.listagemPerfisdousLogado();
		if (perfilLogado.getListaPerfisdousLogado()!=null & perfilLogado.getListaPerfisdousLogado().size() > 1) {
			perfilLogado.escondeDialogoAltenticacacao(true);
		} else {
			if (perfilLogado.getListaPerfisdousLogado()!=null & perfilLogado.getListaPerfisdousLogado().size() == 1) {
				if (perfilLogado.getPerfilUsLogado()==null ||!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)){ 
				perfilLogado.setPerfilUsLogado(perfilLogado.getListaPerfisdousLogado().get(0));
				}
				perfilLogado.escondeDialogoAltenticacacao(false);
				
				if(cadAutomatico){
					perfilLogado.setPaginaAtual(Enum_Perfil_Pagina_Atual.PAGINACLIENTES);
					perfilLogado.setPerfilUsLogado(Enum_Aux_Perfil_Pessoa.OUTROS);
					perfilLogado.setRenderizapessoaeditar(false);
					perfilLogado.setRenderizapessoanovo(false);
				}
				redirecionapaginaIndex(cadAutomatico);
				
					
			}
		}
	}

	public void logout() {
		try {
			perfilLogado = new PerfilLogado();
			Faces.redirect("./faces/pages/alfapage.xhtml");
			mensagensDisparar("Obrigado. !!!");
		} catch (

		IOException error) {
			mensagensDisparar("erro no redirecionamento de página de autenticacao para alfapage!!!");
			error.printStackTrace();
		}

	}

	public void cancelaAutenticacao() {
		try {
			Faces.redirect("./faces/pages/alfapage.xhtml");
			mensagensDisparar("autenticacao cancelada!!!");
		} catch (

		IOException error) {
			mensagensDisparar("erro no redirecionamento de página de autenticacao para alfapage!!!");
			error.printStackTrace();
		}
	}

	public void redirecionapaginaIndex(boolean cadastroAutomatico) {
		perfilLogado.verificaAssinante();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('dialogoPerfil').hide();");
		try {
			if(!cadastroAutomatico)
			Faces.redirect("./faces/pages/index.xhtml");
			else
				Faces.redirect("./faces/pages/pessoas.xhtml");
		} catch (IOException error) {
			mensagensDisparar("erro ao tentar chamar a pagina index ou pessoas");
			error.printStackTrace();
		}
	}

	public void renderizarAssociado(boolean renderiza) {
		if (renderiza)
			perfilLogado.setFoco("ass");
		else
			perfilLogado.setFoco("usuario");
		try {
			perfilLogado.setRenderizaAssociado(renderiza);
			Faces.redirect("./faces/pages/index.xhtml");
		} catch (IOException error) {
			mensagensDisparar("erro ao tentar chamar a pagina index");
			error.printStackTrace();
		}
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

}