package br.com.clientcare.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

import br.com.lealbrasil.model.dao.PessoaDAO;
import br.com.lealbrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.lealbrasil.model.dao.UsuarioDAO;
import br.com.lealbrasil.model.entities.Enum_Aux_Dia_da_Semana;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Sexo;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.HibernateUtil;
import br.com.lealbrasil.util.Utilidades;

public class Pessoa_VinculoDAOTest {

	@SuppressWarnings("unused")
	private List<Pessoa_Vinculo> pessoas_Vinculos = null;
	@SuppressWarnings("unused")
	private List<Pessoa> pessoas = null;
	@SuppressWarnings("unused")
	private List<Pessoa_Enum_Aux_Perfil_Pessoa> pessoas_Perfis = null;

	@Test
	public String sendNvpRequest(String requestNvp, boolean sandbox )
	{
	    String apiEndpoint;
	    apiEndpoint  = "https://api-3t." +(sandbox? "sandbox.": null);
	    apiEndpoint += "paypal.com/nvp";
	    
	    return apiEndpoint;
	    
	}

	
	@Test
	@Ignore
	public void randon(){
		  
	        {  
	            // Determia as letras que poderão estar presente nas chaves  
	        	String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";  
	        	String cpf = "89230906115";  
	        	Random random = new Random();  
	        	  
	        	String armazenaChaves = "";
	        	Long valor;
	        	int index = -1;  
	        	
	        	for( int i = 0; i < 4; i++ ) {  
	        	   index = random.nextInt( letras.length() );  
	        	   armazenaChaves += letras.substring( index, index + 1 );
	        	   valor =random.nextInt(10)*Long.valueOf(cpf); 
	        	   armazenaChaves += ""+valor.toString().substring(1,2);
	        	   if(i%2 == 0)
	        		   armazenaChaves +="-";
	        	}
	        	System.out.println( armazenaChaves );	        	
	        	String invertida = new StringBuilder(armazenaChaves).reverse().toString();
	        	
	        	System.out.println(invertida);
	        }
	  

	}

	@Test
	@Ignore
	public void testacalendario() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes = (cal.get(Calendar.MONDAY) + 1);
		int ano = cal.get(Calendar.YEAR);

		Calendar dd = GregorianCalendar.getInstance();
		for (int i = dd.get(GregorianCalendar.DAY_OF_MONTH); i <= dia; i++) {
			try {
				Date data = (new SimpleDateFormat("dd/MM/yyyy")).parse(i + "/" + mes + "/" + ano);
				dd.setTime(data);
				System.out.println("nova data: " + data + " -- " + dd.get(GregorianCalendar.DAY_OF_WEEK));
				String dSemana = "" + dd.get(GregorianCalendar.DAY_OF_WEEK);
				System.out.println("dia da semana: "
						+ Enum_Aux_Dia_da_Semana.retornapeloId(dd.get(GregorianCalendar.DAY_OF_WEEK)).getExtenso());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		try {
			Date data = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes + "/" + ano);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void listar2() {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();

	}

	@Ignore
	@Test
	public void criarUsuario() {
		try {
			PessoaDAO pDAO = new PessoaDAO();
			Pessoa p = new Pessoa();
			p.setCpf_Cnpj("89230906115");

			// p.setDataNascimento(date);
			p.setDescricao("Paulo Marcos Rodrigues Pereira");
			p.setEmail("paulo.logicabrasil@gmail.com");
			p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
			p.setFantasia_Apelido("");
			p.setFone_1(p.getCpf_Cnpj());
			p.setFone_2("48996453129");
			p.setFone_3("");
			p.setIdentificador("89230906115");
			p.setRg_Insc("3739787");
			p.setSexo(Enum_Aux_Sexo.MASCULINO);
			p.setUltimaAtualizacao(Utilidades.retornaCalendario());
			p.setId_Pessoa_Registro(null);
			p.setId_Empresa(1);
			p = pDAO.merge(p);
			Usuario us = new Usuario();
			us.setAtivo(true);
			// já criptografa dentro do setconfsenha
			us.setSenhaSemCript("P2a3u0l9");
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
