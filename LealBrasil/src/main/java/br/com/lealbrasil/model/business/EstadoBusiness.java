package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.EstadoDAO;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.PerfilLogado;

@SuppressWarnings("serial")
public class EstadoBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Estado> listar(PerfilLogado perfilLogado) {
		List<Estado> estados = null;
		
		try {
			EstadoDAO estadoDAO = new EstadoDAO();			
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return estados;
		
	}

	public static Estado buscaEstado(Estado estado) {
		EstadoDAO eDAO = new EstadoDAO();
		Estado e = eDAO.buscaEstado(estado);
		return e;
	}
	
	public static Estado buscaEstadoPeloNome(String descricao) {
		EstadoDAO eDAO = new EstadoDAO();
		Estado estado = eDAO.buscaEstadoPeloNome(descricao);
		return estado;
	}
	
	public static Estado buscaEstadoPelaSigla(String descricao) {
		EstadoDAO eDAO = new EstadoDAO();
		descricao = descricao.toUpperCase();
		Estado estado = eDAO.buscaEstadoPelaSigla(descricao);
		return estado;
	}

	public static void merge(Estado estado) {
		EstadoDAO eDAO = new EstadoDAO();
		estado.setSigla(estado.getSigla().toUpperCase());
		eDAO.merge(estado);
		
	}
}
