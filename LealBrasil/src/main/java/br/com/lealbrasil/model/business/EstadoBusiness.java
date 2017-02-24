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
			estados = estadoDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return estados;
		
	}
}
