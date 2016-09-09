package br.com.clientcare.utiltest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.junit.Test;

import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;
import br.com.lealbrasil.util.HibernateUtil;

public class HibernateUtilTest {
	
	
	@SuppressWarnings("unused")
	@Test
	public void calendario(){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = null;
		DetachedCriteria subQuery1 = null;
		
			
		subQuery1 = DetachedCriteria.forClass(Pessoa_Vinculo.class)
				    .setProjection(Property.forName("id_pessoa_d"));
		subQuery1.add(Restrictions.eq("id_pessoa_m",1l));
		
		
		crit = sessao.createCriteria(Pessoa.class)
			    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			    .add(Restrictions.and(
			      Subqueries.propertyIn("id", subQuery1)));
		
		
		
		
		
		
		
		
		
		
		 
		
		
		
		
	}
	
	
}