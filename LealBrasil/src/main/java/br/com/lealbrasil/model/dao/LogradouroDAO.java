package br.com.lealbrasil.model.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Logradouro;
import br.com.lealbrasil.util.HibernateUtil;

public class LogradouroDAO extends GenericDAO<Logradouro>{

	public Logradouro buscaLogradouroPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Logradouro.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Logradouro) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
