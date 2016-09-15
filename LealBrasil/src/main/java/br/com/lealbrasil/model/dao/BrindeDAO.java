package br.com.lealbrasil.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Brinde;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.util.HibernateUtil;

@SuppressWarnings("serial")
public class BrindeDAO extends GenericDAO<Brinde> implements Serializable{
	
	@SuppressWarnings("unchecked")
	public List<Brinde> listar(Pessoa id_Pessoa_Assinante){
		
		List<Brinde> l = new ArrayList<Brinde>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Brinde.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante));
			l = crit.list();
		}catch(RuntimeException error){
			error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		
		return l;
		
		
		
	}

}
