package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {
	
	@SuppressWarnings("unchecked")
	public List<Estado> listar(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Estado.class);			
			List<Estado> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}
	}

}
