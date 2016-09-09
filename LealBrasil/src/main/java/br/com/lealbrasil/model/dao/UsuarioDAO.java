package br.com.lealbrasil.model.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticar(String usuario, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.identificador", usuario));
			SimpleHash hash = new SimpleHash("md5", senha);

			consulta.add(Restrictions.eq("senha", hash.toHex()));

			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	public String criptografaSenha(String senha){
		SimpleHash hash = new SimpleHash("md5", senha);
		return hash.toHex();
		
	}

	public Usuario retornaUsuarioPelaPessoa(Pessoa pessoa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("pessoa", pessoa));
			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}

	}

}
