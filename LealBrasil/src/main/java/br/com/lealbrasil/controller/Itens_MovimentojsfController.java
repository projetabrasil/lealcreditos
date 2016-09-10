package br.com.lealbrasil.controller;



import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.model.dao.Itens_MovimentoDAO;
import br.com.lealbrasil.model.entities.Itens_Movimento;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@ManagedBean(name="itens")
@ViewScoped
public class Itens_MovimentojsfController {
	private List<Itens_Movimento> itens;
	private Itens_Movimento item;
	@ManagedProperty(value="#{autenticacaojsfController.perfilLogado}")
	PerfilLogado perfilLogado;
	@PostConstruct 
	public void listar(){
		Itens_MovimentoDAO iMovDAO = new Itens_MovimentoDAO();
		itens = iMovDAO.listar(perfilLogado.getAssLogado());
	}
	public void novo(){
		configItens();
	}
	public void configItens(){
		item = new Itens_Movimento();
		item.setId_Empresa(1);
		item.setDescricao("");
		item.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		item.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		item.setUltimaAtualizacao(Utilidades.retornaCalendario());
		item.setValordaUnidade(0);
	}
	public void editar(ActionEvent event){		
		configItens();
		item = (Itens_Movimento)  event.getComponent().getAttributes().get("registroAtual");
	}
	public void merge(){
		Itens_MovimentoDAO iMovDAO = new Itens_MovimentoDAO();
		iMovDAO.merge(item);
		listar();
	}
	public List<Itens_Movimento> getItens() {
		return itens;
	}
	public void setItens(List<Itens_Movimento> itens) {
		this.itens = itens;
	}
	public Itens_Movimento getItem() {
		return item;
	}
	public void setItem(Itens_Movimento item) {
		this.item = item;
	}
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

}
