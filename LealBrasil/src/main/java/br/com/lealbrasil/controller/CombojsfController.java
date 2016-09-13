package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.model.dao.Combo_MestreDAO;
import br.com.lealbrasil.model.entities.Combo_Detalhe;
import br.com.lealbrasil.model.entities.Combo_Mestre;
import br.com.lealbrasil.model.entities.Itens_Movimento;
import br.com.lealbrasil.model.entities.PerfilLogado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CombojsfController extends GenericController implements Serializable{
	private List<Combo_Mestre> combosM;
	private Combo_Mestre comboM;
	private List<Combo_Detalhe> combosD;
	private Combo_Detalhe comboD;
	private List<Itens_Movimento> itens_Mov;
	private Itens_Movimento item_Mov;
	@ManagedProperty(value="#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
  
	@PostConstruct	
	public void listar(){
		Combo_MestreDAO cMDAO = new Combo_MestreDAO();
		combosM = cMDAO.listar(perfilLogado.getAssLogado());
		
	}
	public void editar(ActionEvent event){
		setComboM((Combo_Mestre) event.getComponent().getAttributes().get("RegistroAtual") );
		
	}
	public void merge(){
		Combo_MestreDAO cMDAO = new Combo_MestreDAO();
		cMDAO.merge(comboM);
		listar();
	}
	public List<Combo_Mestre> getCombosM() {
		return combosM;
	}
	public void setCombosM(List<Combo_Mestre> combosM) {
		this.combosM = combosM;
	}
	public Combo_Mestre getComboM() {
		return comboM;
	}
	public void setComboM(Combo_Mestre comboM) {
		this.comboM = comboM;
	}
	public List<Combo_Detalhe> getCombosD() {
		return combosD;
	}
	public void setCombosD(List<Combo_Detalhe> combosD) {
		this.combosD = combosD;
	}
	public Combo_Detalhe getComboD() {
		return comboD;
	}
	public void setComboD(Combo_Detalhe comboD) {
		this.comboD = comboD;
	}
	public List<Itens_Movimento> getItens_Mov() {
		return itens_Mov;
	}
	public void setItens_Mov(List<Itens_Movimento> itens_Mov) {
		this.itens_Mov = itens_Mov;
	}
	public Itens_Movimento getItem_Mov() {
		return item_Mov;
	}
	public void setItem_Mov(Itens_Movimento item_Mov) {
		this.item_Mov = item_Mov;
	}
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}
	
  
}
