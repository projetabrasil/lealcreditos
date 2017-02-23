package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.lealbrasil.model.entities.Pais;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.HibernateUtil;

public class PaisDAO extends GenericDAO<Pais> {
	
	@SuppressWarnings("unchecked")
	public List<Pais> listar(PerfilLogado perfilLogado) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Pais.class);			
			List<Pais> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}
	}
	
}