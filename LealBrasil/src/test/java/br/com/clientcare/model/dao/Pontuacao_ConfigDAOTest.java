package br.com.clientcare.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.lealbrasil.model.dao.UsuarioDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Enum_Sexo;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.Utilidades;

public class Pontuacao_ConfigDAOTest {

	@Test
	public void listar() {

		try {
			PessoaDAO pDAO = new PessoaDAO();
			Pessoa p = new Pessoa();
			p.setCpf_Cnpj("89230906115");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String data = "16/10/2015";
			Date date = new Date();
			try {
				date = formatter.parse(data);
				System.out.println(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			//p.setDataNascimento(date);
			p.setDescricao("Paulo Marcos Rodrigues Pereira");
			p.setEmail("paulo.logicabrasil@gmail.com");
			p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
			p.setFantasia_Apelido("");
			p.setFone_1("48996453129");
			p.setFone_2("");
			p.setFone_3("");
			p.setIdentificador("89230906115");
			p.setRg_Insc("3739787");
			p.setSexo(Enum_Sexo.MASCULINO);
			p.setUltimaAtualizacao(Utilidades.retornaCalendario());
			p.setId_Pessoa_Registro(null);
			p.setId_Empresa(1);
			p = pDAO.merge(p);
			Usuario us = new Usuario();
			us.setAtivo(true);
			// já criptografa dentro do setconfsenha
			us.setConfSenha("");
			us.setId_Empresa(1);
			us.setId_Pessoa_Registro(p);
			us.setPessoa(p);
			us.setSenhaSemCript("");
			us.setUltimaAtualizacao(Utilidades.retornaCalendario());
			UsuarioDAO usDAO = new UsuarioDAO();
			usDAO.merge(us);

			Pessoa_Enum_Aux_Perfil_Pessoa pp = new Pessoa_Enum_Aux_Perfil_Pessoa();

			pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);
			pp.setId_Empresa(1);
			pp.setId_pessoa(p);
			pp.setId_Pessoa_Registro(p);
			pp.setUltimaAtualizacao(p.getUltimaAtualizacao());

			Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
			ppDAO.merge(pp);
		} catch (RuntimeException erro) {

		}
	}
}
