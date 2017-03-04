package br.com.lealbrasil.model.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Pais;
import br.com.lealbrasil.util.HibernateUtil;

public class PaisDAO extends GenericDAO<Pais> {
	
	public Pais buscaPaisPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pais.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Pais) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
}