package br.com.lealbrasil.model.business;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.util.HibernateUtil;

@SuppressWarnings("serial")
public class PessoaBusiness implements Serializable {
	
	
	private static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	public static Pessoa identificadorExiste(Pessoa pessoa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pessoa.class);
			consulta.add(Restrictions.like("identificador", pessoa.getIdentificador()));
			Pessoa pessoaExistente = (Pessoa) consulta.uniqueResult();
			return pessoaExistente;

		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public static Pessoa merge(Pessoa pessoa) {
		
		if (pessoa.getId() != null) {
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.EXISTENTE.getMensagem() + "\n"
					+ pessoa.getEnum_Aux_Tipo_Identificador().getDescricao() + ": " + pessoa.getIdentificador() + " - "
					+ pessoa.getDescricao());
		}
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoa = (Pessoa) pessoaDAO.merge(pessoa);
			if (pessoa.getId() != null)
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ALTERACAO.getMensagem());
			else
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.INCLUSAO.getMensagem());
		} catch (RuntimeException erro) {
			if (pessoa.getId() != null)
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRALTERACAO.getMensagem());
			else
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRINCLUSAO.getMensagem());

			erro.printStackTrace();
		}
		return pessoa;
	}

	public static Pessoa registroAtualdaLista(ActionEvent evento) {
		return (Pessoa) evento.getComponent().getAttributes().get("registroAtual");
	}

	public static void excluir(Pessoa pessoa) {
		if (pessoa != null) {
			try {
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoaDAO.excluir(pessoa);
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.EXCLUSAO.getMensagem());
			} catch (RuntimeException erro) {
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERREXCLUSAO.getMensagem());
			}
		}
	}
	public static List<Pessoa> listar(PerfilLogado perfilLogado ) {
		
		List<Pessoa> pessoas = null;
		
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();			
			pessoas = pessoaDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return pessoas;
	}
	public static Pessoa buscaPessoa(Pessoa pessoa) {
		pessoa = PessoaBusiness.identificadorExiste(pessoa);
		if (pessoa==null)
			pessoa = new Pessoa();
		if (pessoa.getEnum_Aux_Tipo_Identificador() == null)
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		return pessoa;
	}
	
	

}
