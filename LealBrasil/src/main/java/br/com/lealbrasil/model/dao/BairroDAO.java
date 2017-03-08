package br.com.lealbrasil.model.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Bairro;
import br.com.lealbrasil.model.entities.Cidade;
import br.com.lealbrasil.model.entities.Estado;
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
	
	@SuppressWarnings("unchecked")
	public List<Cidade> associaCidadesAoEstado(Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Cidade.class).createAlias("estado", "a");
			consulta.add(Restrictions.eq("a.id", id));
			List<Cidade> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}	
	}
	
	
	

}


