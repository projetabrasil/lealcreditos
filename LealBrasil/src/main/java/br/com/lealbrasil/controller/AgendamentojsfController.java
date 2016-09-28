package br.com.lealbrasil.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.lealbrasil.model.dao.AgendamentoDAO;
import br.com.lealbrasil.model.entities.Agendamento;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Status_Agendamento;
import br.com.lealbrasil.model.entities.Movimento_Detalhe_A;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@ManagedBean(name="agendamento")
@ViewScoped
public class AgendamentojsfController {
	private List<Agendamento> agendamentos;
	private Agendamento agendamento;
	private boolean disponivel;
	@ManagedProperty(value="#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private String descricaoDisponivel;  

	@PostConstruct
	public void listar(){
		
		if (!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.CLIENTES) )
			return;
		AgendamentoDAO agDAO = new AgendamentoDAO();
		agendamentos = agDAO.checaAgendamentodoCliente(null, null, Enum_Aux_Status_Agendamento.AGENDADO, perfilLogado.getAssLogado());
		
		
	}
	public void novo(Movimento_Detalhe_A idMov ){
		agendamento = new Agendamento();
		agendamento.setDataAgendamento(Utilidades.retornaCalendario());
		agendamento.setDataBaixa(null);
		agendamento.setEnum_Aux_Status_Agendamento(Enum_Aux_Status_Agendamento.AGENDADO);
		agendamento.setId_Empresa(1);
		agendamento.setId_Movimento_Detalhe_A(idMov);
		agendamento.setId_Pessoa_Assinante(idMov.getId_Pessoa_Assinante());
		agendamento.setId_Pessoa_Baixa(null);
		agendamento.setId_Pessoa_Cliente(perfilLogado.getUsLogado().getPessoa());
		agendamento.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		agendamento.setUltimaAtualizacao(Utilidades.retornaCalendario());
		agendamento.setValor(idMov.getValor());
		Utilidades.abrirfecharDialogos("dialogoCalendario", true);
	}
	public void merge(){
		AgendamentoDAO AgDAO = new AgendamentoDAO();	
		agendamento.setCodigo(Utilidades.randon(perfilLogado.getUsLogado().getPessoa().getCpf_Cnpj()));
		AgDAO.merge(agendamento); 
		Utilidades.abrirfecharDialogos("dialogoCalendario", false);
	}
	public void editar(ActionEvent event){
		
	}
	public void excluir(ActionEvent event){
		
	}
	
	public void checar(ActionEvent event){
		Movimento_Detalhe_A movDet = (Movimento_Detalhe_A) event.getComponent().getAttributes().get("registroAtual");
		
		
		AgendamentoDAO agDAO = new AgendamentoDAO();
		agendamento = agDAO.checaAgendamentoAtivodoCliente(perfilLogado.getUsLogado().getPessoa(),movDet,
				Enum_Aux_Status_Agendamento.AGENDADO);
		if(agendamentos!=null){
			
			setDescricaoDisponivel("Imprimir");
		}else{
			
			setDescricaoDisponivel("Agendar");
		}
	}
	public void somar(){
		
	}
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	public Agendamento getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}
	public String getDescricaoDisponivel() {
		return descricaoDisponivel;
	}
	public void setDescricaoDisponivel(String descricaoDisponivel) {
		this.descricaoDisponivel = descricaoDisponivel;
	}
	
}
