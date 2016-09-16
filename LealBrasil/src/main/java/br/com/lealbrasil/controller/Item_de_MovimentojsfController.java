package br.com.lealbrasil.controller;



import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.model.dao.Item_de_MovimentoDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.lealbrasil.model.entities.Item_de_Movimento;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@ManagedBean(name="itens")
@ViewScoped
public class Item_de_MovimentojsfController {
	private List<Item_de_Movimento> itens;
	private Item_de_Movimento item;
	@ManagedProperty(value="#{autenticacaojsfController.perfilLogado}")
	PerfilLogado perfilLogado;
	private List<Enum_Aux_Sim_ou_Nao> listaSN;
	
	@PostConstruct 
	public void listar(){
		Item_de_MovimentoDAO iMovDAO = new Item_de_MovimentoDAO();
		itens = iMovDAO.listar(perfilLogado.getAssLogado());
		setListaSN(Utilidades.listaSN());
	}
	public void novo(){
		configItens();
	}
	
	public void configItens(){
		item = new Item_de_Movimento();
		item.setId_Empresa(1);
		item.setDescricao("");
		item.setReferencia("");
		item.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		item.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		item.setUltimaAtualizacao(Utilidades.retornaCalendario());
		item.setValordaUnidade(0);
		item.setPonto(0d);
	}
	public void editar(ActionEvent event){		
		configItens();
		item = (Item_de_Movimento)  event.getComponent().getAttributes().get("registroAtual");
		
	}
	public void merge(){
		Item_de_MovimentoDAO iMovDAO = new Item_de_MovimentoDAO();
		iMovDAO.merge(item);
		listar();
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
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}
	public List<Enum_Aux_Sim_ou_Nao> getListaSN() {
		return listaSN;
	}
	public void setListaSN(List<Enum_Aux_Sim_ou_Nao> listaSN) {
		this.listaSN = listaSN;
	}

}
