package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Estado;
import br.com.lealbrasil.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {
	
	public Cidade buscaCidade(Cidade cidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Cidade.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("estado",cidade.getEstado()));			        
					crit.add(Restrictions.eq("descricao",cidade.getDescricao()));
			return (Cidade) crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> associaEstadosAoPais(Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Estado.class).createAlias("pais", "a");
			consulta.add(Restrictions.eq("a.id", id));
			List<Estado> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}

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
