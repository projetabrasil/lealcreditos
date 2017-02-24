package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.BairroDAO;
import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.model.entities.PerfilLogado;

@SuppressWarnings("serial")
public class BairroBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Bairro> listar(PerfilLogado perfilLogado) {
		List<Bairro> bairro = null;
		
		try {
			BairroDAO bairroDAO = new BairroDAO();			
			bairro = bairroDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return bairro;
		
	}
}
