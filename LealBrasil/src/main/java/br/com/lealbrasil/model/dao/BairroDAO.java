package br.com.lealbrasil.model.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.util.HibernateUtil;

public class BairroDAO extends GenericDAO<Bairro> {

	public Bairro buscaBairroPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Bairro.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Bairro) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}


