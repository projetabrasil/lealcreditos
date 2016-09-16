package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Ponto;
import br.com.lealbrasil.util.HibernateUtil;

public class PontoDAO extends GenericDAO<Ponto>{
	List<Ponto> lista;
	@SuppressWarnings("unchecked")
	public List<Ponto> retornarListaPontuacaoConfig(Pessoa usuarioLogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			        crit.add(Restrictions.eq("id_Pessoa_Assinante", usuarioLogado));
			lista =  (List<Ponto>)  crit.list();
			return lista;
		}catch(RuntimeException erro){
			
			erro.printStackTrace();
			
			throw erro;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Ponto> retornarListaPontos(Pessoa usuarioLogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			        crit.add(Restrictions.eq("id_Pessoa_Assinante", usuarioLogado));
			lista =  (List<Ponto>)  crit.list();
			return lista;
		}catch(RuntimeException erro){
			
			erro.printStackTrace();
			
			throw erro;
		}
		
	}
	
	public Ponto retornarPonto(Pessoa usuarioLogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class)
					.setProjection(Property.forName("id"));
			subQuery1.add(Restrictions.eq("id",  usuarioLogado.getId()));
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.and(Subqueries.propertyIn("id_Pessoa_Asssinante", subQuery1)));
			crit.add(Restrictions.eq("id_Pessoa_Assinante", usuarioLogado));
			return (Ponto) crit.uniqueResult();
		}catch(RuntimeException erro){
			erro.printStackTrace();
			throw erro;
		}
		
	}
	
}
