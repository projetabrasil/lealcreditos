package br.com.lealbrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mov_Pontuacao;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pontuacao_Movimento;
import br.com.lealbrasil.util.HibernateUtil;
import br.com.lealbrasil.util.Utilidades;

public class Pontuacao_MovimentoDAO extends GenericDAO<Pontuacao_Movimento> {

	@SuppressWarnings("unchecked")
	public List<Pontuacao_Movimento> listar(PerfilLogado perfilLogado, Pessoa cliente, boolean pesqAss) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Pontuacao_Movimento> pontuacoes = new ArrayList<Pontuacao_Movimento>();
		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null) {
				subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));

				crit = sessao.createCriteria(Pontuacao_Movimento.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)						
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_associado", subQuery1)));
				crit.add(Restrictions.eq("id_pessoa_associado", perfilLogado.getAssLogado()));
				
				
				java.util.Date d = Utilidades.retornaCalendario().getTime();
				java.sql.Date dt = new java.sql.Date (d.getTime());
				
				crit.add(Restrictions.ge("validade", dt ));
				
				
				
				crit.addOrder(Order.desc("ultimaAtualizacao"));
				crit.addOrder(Order.asc("id_pessoa_cliente"));

				if (cliente != null) {
					crit.add(Restrictions.eq("id_pessoa_cliente", cliente));
				}

			} else {
				crit = sessao.createCriteria(Pontuacao_Movimento.class);
				crit.add(Restrictions.eq("id", 0));
			}

			pontuacoes = (ArrayList<Pontuacao_Movimento>) crit.list();

			return pontuacoes;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Double somadePontos(PerfilLogado perfilLogado, Pessoa cliente, boolean pesqAss,
			Enum_Aux_Tipo_Mov_Pontuacao tipoSoma) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Long valorRetorno;

		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null) {
				subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));

				crit = sessao.createCriteria(Pontuacao_Movimento.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_associado", subQuery1)));
				crit.add(Restrictions.eq("id_pessoa_associado", perfilLogado.getAssLogado()));
				if (tipoSoma != null) {
					crit.add(Restrictions.eq("creditaDebita", tipoSoma));

				}
				
				java.util.Date d = Utilidades.retornaCalendario().getTime();
				java.sql.Date dt = new java.sql.Date (d.getTime());
				System.out.println("validade:"+dt);
				crit.add(Restrictions.ge("validade", dt ));
				
				

				crit.add(Restrictions.ge("validade", dt));
				if (cliente != null && cliente.getId() != null) {
					crit.add(Restrictions.eq("id_pessoa_cliente", cliente));
				}

			} else {
				crit = sessao.createCriteria(Pontuacao_Movimento.class);
				crit.add(Restrictions.eq("id", 0));
			}
			crit.setProjection(Projections.sum("pontosAtingidos"));
            
			if (crit.uniqueResult() != null) {				
				valorRetorno =  new Long((long) crit.uniqueResult());

			} else				
				valorRetorno =  new Long(0l);
			
			
			return valorRetorno.doubleValue();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
