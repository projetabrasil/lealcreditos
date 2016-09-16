package br.com.lealbrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Combo_Detalhe;
import br.com.lealbrasil.model.entities.Item_de_Movimento;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.util.HibernateUtil;

public class Combo_DetalheDAO extends GenericDAO<Combo_Detalhe> {
	@SuppressWarnings("unchecked")
	public List<Combo_Detalhe> listar(Pessoa id_Pessoa_Assinante){
		List<Combo_Detalhe> lista = new ArrayList<Combo_Detalhe>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Item_de_Movimento.class)
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
