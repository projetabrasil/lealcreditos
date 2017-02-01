package br.com.clientcare.utiltest;

import java.text.ParseException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.junit.Ignore;
import org.junit.Test;

import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.lealbrasil.model.dao.UsuarioDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Sexo;
import br.com.lealbrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.HibernateUtil;
import br.com.lealbrasil.util.Utilidades;

public class HibernateUtilTest {

	private Pessoa p;
	private Usuario us;
	private Pessoa_Enum_Aux_Perfil_Pessoa pp;

	@SuppressWarnings("unused")
	@Ignore
	@Test	
	public void calendario() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = null;
		DetachedCriteria subQuery1 = null;

		subQuery1 = DetachedCriteria.forClass(Pessoa_Vinculo.class).setProjection(Property.forName("id_pessoa_d"));
		subQuery1.add(Restrictions.eq("id_pessoa_m", 1l));

		crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1)));

	}

	@Test
	public void cadastraUsuario() throws ParseException {
		p = new Pessoa();
		p.setCpf_Cnpj("89230906115");
		PessoaDAO pDAO = new PessoaDAO();
		p = pDAO.retornaPelaIdentificacao(p.getIdentificador());

		if (p == null) {
			p = new Pessoa(); 
			p.setCpf_Cnpj("89230906115");
			p.setIdentificador(p.getCpf_Cnpj());
			p.setRg_Insc("3739787");
			p.setSexo(Enum_Aux_Sexo.MASCULINO);
			p.setFone_1("48996453129");
			p.setFone_2("");
			p.setFone_3("");
			p.setEmail("paulo.logicabrasil@gmail.com");
			p.setId_Pessoa_Registro(null);
			p.setAutoPontuacao(Enum_Aux_Sim_ou_Nao.NAO);
			p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
			p.setDescricao("Paulo Marcos Rodrigues Pereira");
			p.setFantasia_Apelido("");
			p.setDataNascimento(Utilidades.retornaData("23/09/1978"));
		}
		p = pDAO.merge(p);
		us = new Usuario();
		us.setPessoa(p);
		UsuarioDAO usDAO = new UsuarioDAO();
		us = usDAO.retornaUsuarioPelaPessoa(p);
		if (us == null) {
			us.setAtivo(true);
			us.setConfSenha("");
			us.setId(null);
			us.setId_Empresa(1);
			us.setId_Pessoa_Registro(p);
			us.setPessoa(p);
			us.setSenhaSemCript(null);
		}
		us = usDAO.merge(us);
		
		pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
		Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
				
		pp.setId_pessoa(p);
		pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);	
		
		pp = ppDAO.retornaPerfildaPessoaPelaPessoa(pp);
		
		if(pp==null){
			pp.setId_pessoa(p);
			pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);
			pp.setId_Empresa(1);
			
			pp.setId_Pessoa_Registro(p);
			pp.setUltimaAtualizacao(Utilidades.retornaCalendario());
		}	
		
		pp = ppDAO.merge(pp);
		
		
		
		
	}

}