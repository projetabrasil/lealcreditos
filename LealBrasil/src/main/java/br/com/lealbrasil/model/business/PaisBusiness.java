package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.PaisDAO;
import br.com.lealbrasil.model.entities.Pais;
import br.com.lealbrasil.model.entities.PerfilLogado;


@SuppressWarnings("serial")
public class PaisBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public static List<Pais> listar(PerfilLogado perfilLogado) {
		List<Pais> paises = null;
		
		try {
			PaisDAO paisDAO = new PaisDAO();			
			paises = paisDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return paises;
		
	}

	public static Pais buscaPaisPeloNome(String descricao) {
		PaisDAO pDAO = new PaisDAO();
		Pais pais = pDAO.buscaPaisPeloNome(descricao);
		return pais;
	}

	public static void merge(Pais pais) {
		PaisDAO pDAO = new PaisDAO();
		pDAO.merge(pais);
	}
	
	
}
