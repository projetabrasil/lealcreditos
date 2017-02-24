package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class BairroDAO extends GenericDAO<Bairro> {

	public List<Bairro> listar(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Bairro.class);			
			List<Bairro> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}
	}

}
