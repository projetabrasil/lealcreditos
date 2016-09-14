package br.com.lealbrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pontuacao_Config;
import br.com.lealbrasil.util.HibernateUtil;

public class Pontuacao_ConfigDAO extends GenericDAO<Pontuacao_Config>{
	List<Pontuacao_Config> lista;
	@SuppressWarnings("unchecked")
	public List<Pontuacao_Config> retornarListaPontuacaoConfig(Pessoa usuarioLogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Pontuacao_Config.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			        crit.add(Restrictions.eq("id_Pessoa_Associado", usuarioLogado));
			lista =  (List<Pontuacao_Config>)  crit.list();
			return lista;
		}catch(RuntimeException erro){
			
			erro.printStackTrace();
			
			throw erro;
		}
		
	}
	
	public Pontuacao_Config retPontuacao(Pessoa usuarioLogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class)
					.setProjection(Property.forName("id"));
			subQuery1.add(Restrictions.eq("id",  usuarioLogado.getId()));
			Criteria crit = sessao.createCriteria(Pontuacao_Config.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.and(Subqueries.propertyIn("id_Pessoa_Associado", subQuery1)));
			crit.add(Restrictions.eq("id_Pessoa_Associado", usuarioLogado));
			return (Pontuacao_Config) crit.uniqueResult();
		}catch(RuntimeException erro){
			erro.printStackTrace();
			throw erro;
		}
		
	}
	
}
