package br.com.lealbrasil.model.business;

import java.io.Serializable;

import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
public class PessoaGenericBusiness implements Serializable {
	
	
	
	public static Pessoa merge(Pessoa pessoaCadastro, Usuario usuarioCadastro, PerfilLogado perfilLogado,boolean origemCadastro) {
		Pessoa pessoa =    pessoaCadastro;
		Usuario usuario = usuarioCadastro;
		Pessoa_Enum_Aux_Perfil_Pessoa pessoa_Perfil = null;
		pessoa = PessoaBusiness2.retiradadosembranco(pessoa);		
		pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		
		// merge da pessoa;
		pessoa = PessoaBusiness.merge(pessoa);		
		
		// merge usuario;
		if (usuario != null && (usuario.getSenha()!=null && usuario.getConfSenha()!=null )) {
			usuario.setPessoa(pessoa);
			usuario.setUltimaAtualizacao(Utilidades.retornaCalendario());
			usuario.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			usuario = UsuarioBusiness.merge(usuario);
		}
		// merge pessoa_perfil;
		pessoa_Perfil = new Pessoa_Enum_Aux_Perfil_Pessoa();
		pessoa_Perfil.setId_pessoa(pessoa);
		pessoa_Perfil.setEnum_Aux_Perfil_Pessoa(perfilLogado.getPaginaAtual().getPerfilPessoa());
		if (origemCadastro)
			pessoa_Perfil.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		
		else
		// então vem do cadastro feito no movimento
		{
			pessoa_Perfil.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
		}
			
		pessoa_Perfil.setId_Empresa(1);
		pessoa_Perfil.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		pessoa_Perfil.setUltimaAtualizacao(Utilidades.retornaCalendario());
		pessoa_Perfil = Pessoa_Enum_Perfil_de_PessoaBusiness.merge(pessoa_Perfil);
		// merge pessoa_vinculo;
		
		if (perfilLogado.getPaginaAtual().getPerfilPessoa()!=null && perfilLogado.getPaginaAtual().getPerfilPessoa().isTemPerfilMestre() &&
			!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES )	) {
			Pessoa_Vinculo pVinc = new Pessoa_Vinculo();
			pVinc.setAtivo(true);
			pVinc.setId_Empresa(1);
			pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
			pVinc.setId_pessoa_d(pessoa);
			
			
			if (perfilLogado!=null && perfilLogado.getAssLogado()!=null && perfilLogado.getAssLogado().getId() !=null)
				pVinc.setId_pessoa_m(perfilLogado.getAssLogado());
			else
				pVinc.setId_pessoa_m(perfilLogado.getUsLogado().getPessoa());
			
			pVinc.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
			Pessoa_VinculoBusiness.merge(pVinc, perfilLogado);
		}
		return pessoa;
	}
	
	public static Pessoa  buscaPessoa(String identificacao, Pessoa pessoaCadastro){
		 Pessoa pessoa = pessoaCadastro;
		 pessoa.setIdentificador(identificacao);		 
		 pessoa = PessoaBusiness.identificadorExiste(pessoa);
		 
		 if (pessoa==null)
		 pessoa = pessoaCadastro; 
		 
		 return pessoa;
	}
	public static void chamaDialogoCastro(){		
		Utilidades.abrirfecharDialogos("dialogoIdentidade",false);		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
}
