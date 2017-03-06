package br.com.lealbrasil.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	public Cidade buscaCidadePeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Cidade) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
