package br.com.lealbrasil.model.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {

	public Estado buscaEstado(Estado estado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Estado.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("pais",estado.getPais()));			        
					crit.add(Restrictions.eq("descricao",estado.getDescricao()));
			return (Estado) crit.setMaxResults(1).uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Estado buscaEstadoPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Estado) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Estado buscaEstadoPelaSigla(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.like("sigla", descricao));
			return (Estado) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
