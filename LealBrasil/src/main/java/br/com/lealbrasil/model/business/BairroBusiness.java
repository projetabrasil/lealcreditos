package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.BairroDAO;
import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
public class BairroBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Bairro> listar(PerfilLogado perfilLogado) {
		List<Bairro> bairro = null;
		
		try {
			BairroDAO bairroDAO = new BairroDAO();			
			bairro = bairroDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return bairro;
		
	}

	public static Bairro buscaBairroPeloNome(String descricao) {
		BairroDAO bDAO = new BairroDAO();
		descricao = Utilidades.formataNomeDaRegiao(descricao);
		Bairro bairro = bDAO.buscaBairroPeloNome(descricao);
		return bairro;
	}

	public static void merge(Bairro bairro) {
		BairroDAO bDAO = new BairroDAO();
		bairro.setDescricao(Utilidades.formataNomeDaRegiao(bairro.getDescricao()));
		bDAO.merge(bairro);
	}

	public static List<Estado> associaEstadosAoPais(Long id) {
		BairroDAO bDAO = new BairroDAO();
		List<Estado> estados = bDAO.associaEstadosAoPais(id);
		return estados;
	}

	public static List<Cidade> associaCidadesAoEstado(Long id) {
		BairroDAO bDAO = new BairroDAO();
		List<Cidade> cidades = bDAO.associaCidadesAoEstado(id);
		return cidades;
	}
}
