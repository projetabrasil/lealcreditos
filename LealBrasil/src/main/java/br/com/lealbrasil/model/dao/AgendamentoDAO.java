package br.com.lealbrasil.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Agendamento;
import br.com.lealbrasil.model.entities.Enum_Aux_Status_Agendamento;
import br.com.lealbrasil.model.entities.Movimento_Detalhe_A;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.util.HibernateUtil;

@SuppressWarnings("serial")
public class AgendamentoDAO extends GenericDAO<Agendamento> implements Serializable  {
	@SuppressWarnings("unchecked")
	public List<Agendamento> listar(Pessoa id_Pessoa_Assinante, Movimento_Detalhe_A idMov) {
		List<Agendamento> l = new ArrayList<Agendamento>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = sessao.createCriteria(Agendamento.class);

		try {
			crit.add(Restrictions.eq("id_Pessoa_Assinante", id_Pessoa_Assinante));
			crit.add(Restrictions.eq("id_Movimento_Detalhe_A", idMov));
			l = crit.list();
		} catch (RuntimeException error) {
			error.printStackTrace();
		} finally {
			sessao.close();
		}
		return l;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Agendamento> checaAgendamentodoCliente(
			Pessoa id_Pessoa_Cliente, Movimento_Detalhe_A idMov, Enum_Aux_Status_Agendamento status, Pessoa id_Pessoa_Assinante  ) {
		List<Agendamento> l = new ArrayList<Agendamento>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = sessao.createCriteria(Agendamento.class);
		try {
			if(id_Pessoa_Assinante!=null && id_Pessoa_Assinante.getId()!=null)
			crit.add(Restrictions.eq("id_Pessoa_Assinante", id_Pessoa_Assinante));
			
			if(id_Pessoa_Cliente!=null && id_Pessoa_Cliente!=null)
			crit.add(Restrictions.eq("id_Pessoa_Cliente", id_Pessoa_Cliente));
			if(idMov!=null && idMov.getId()!=null)
			crit.add(Restrictions.eq("id_Movimento_Detalhe_A", idMov));
			if(status!=null)
			crit.add(Restrictions.eq("enum_Aux_Status_Agendamento", status));
									  
			l = (List<Agendamento>) crit.list();
		} catch (RuntimeException error) {
			error.printStackTrace();
		} finally {
			sessao.close();
		}
		return l;
	}
	
	public Agendamento checaAgendamentoAtivodoCliente(
			Pessoa id_Pessoa_Cliente, Movimento_Detalhe_A idMov, Enum_Aux_Status_Agendamento status  ) {
		Agendamento l = new Agendamento();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = sessao.createCriteria(Agendamento.class);
		try {
			
			crit.add(Restrictions.eq("id_Pessoa_Assinante", idMov.getId_Pessoa_Assinante()));	
			
			crit.add(Restrictions.eq("id_Pessoa_Cliente", id_Pessoa_Cliente));
			
			crit.add(Restrictions.eq("id_Movimento_Detalhe_A", idMov));
			
			crit.add(Restrictions.eq("enum_Aux_Status_Agendamento", status));
			l = (Agendamento)crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
		} finally {
			sessao.close();
		}
		return l;
	}
	
	public double checaAgendamentodoClienteSoma(
			Pessoa id_Pessoa_Cliente, Movimento_Detalhe_A idMov, Enum_Aux_Status_Agendamento status  ) {
		double l = 0d;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = sessao.createCriteria(Agendamento.class);

		try {
			if(idMov.getId_Pessoa_Assinante()!=null)
			crit.add(Restrictions.eq("id_Pessoa_Assinante", idMov.getId_Pessoa_Assinante()));
			if(id_Pessoa_Cliente!=null)
			crit.add(Restrictions.eq("id_Pessoa_Cliente", id_Pessoa_Cliente));
			if(idMov!=null)
			crit.add(Restrictions.eq("id_Movimento_Detalhe_A", idMov));
			if(status!=null)
			crit.add(Restrictions.eq("enum_Aux_Status_Agendamento", status));
			crit.setProjection(Projections.sum("valor"));
			l = (double) crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
		} finally {
			sessao.close();
		}
		return l;
	}
	
	
	
}
