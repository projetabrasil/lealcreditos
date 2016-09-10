package br.com.lealbrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Itens_Movimento;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.util.HibernateUtil;

public class Itens_MovimentoDAO extends GenericDAO<Itens_Movimento> {
	
	@SuppressWarnings("unchecked")
	public List<Itens_Movimento> listar(Pessoa id_Pessoa_Assinante){
		List<Itens_Movimento> lista = new ArrayList<Itens_Movimento>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Itens_Movimento.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante));
			lista = crit.list();
		}catch(RuntimeException error){
	      error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}

}
