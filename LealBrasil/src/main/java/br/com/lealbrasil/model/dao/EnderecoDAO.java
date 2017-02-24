package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.lealbrasil.model.entities.Endereco;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.HibernateUtil;

public class EnderecoDAO {

	public List<Endereco> listar(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Endereco.class);			
			List<Endereco> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}
	}

}
