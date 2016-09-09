package br.com.lealbrasil.model.business;

import java.io.Serializable;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa;
import br.com.lealbrasil.model.entities.Usuario;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
public class PessoaBusiness2 implements Serializable {
	private static Enum_Aux_Perfil_Pessoa perfildaPagina;

	private static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public static Pessoa retiradadosembranco(Pessoa p) {
		p.setIdentificador(Utilidades.retiraCaracteres(p.getIdentificador()));
		p.setCpf_Cnpj(Utilidades.retiraCaracteres(p.getCpf_Cnpj()));
		p.setFone_1(Utilidades.retiraCaracteres(p.getFone_1()));
		p.setFone_2(Utilidades.retiraCaracteres(p.getFone_2()));
		p.setFone_3(Utilidades.retiraCaracteres(p.getFone_3()));
		p.setRg_Insc(Utilidades.retiraCaracteres(p.getRg_Insc()));
		if (p.getCpf_Cnpj() != null && p.getCpf_Cnpj().length() > 0)
			p.setIdentificador(p.getCpf_Cnpj());
		p.setDescricao(Utilidades.retiraVazios(p.getDescricao()));
		return p;
	}

	public static boolean validaDados(Usuario usuario, PerfilLogado perfilLogado, boolean validarEmail,
			boolean validarSenha, boolean validarCpf_Cnpj) {
		if (validarCpf_Cnpj) {
			if ( usuario.getPessoa().getCpf_Cnpj() != null && usuario.getPessoa().getCpf_Cnpj().length() > 0
					&& (usuario.getPessoa().getEnum_Aux_Tipo_Identificador().equals(Enum_Aux_Tipo_Identificador.CPF)
							|| usuario.getPessoa().getEnum_Aux_Tipo_Identificador()
									.equals(Enum_Aux_Tipo_Identificador.CNPJ))) {
				if (usuario.getPessoa().getEnum_Aux_Tipo_Identificador().equals(Enum_Aux_Tipo_Identificador.CPF)
						&& !Utilidades.isValidCPF(usuario.getPessoa().getCpf_Cnpj())) {
					mensagensDisparar("CPF informado é Inválido!!!");
					return false;
				} else if (usuario.getPessoa().getEnum_Aux_Tipo_Identificador().equals(Enum_Aux_Tipo_Identificador.CNPJ)
						&& !Utilidades.isValidCNPJ(usuario.getPessoa().getCpf_Cnpj())) {
					mensagensDisparar("CNPJ informado é Inválido!!!");
					return false;
				}

			}
		}
		if (validarEmail)
			if (usuario.getPessoa().getEmail() != null && usuario.getPessoa().getEmail().length() > 0) {
				boolean emailOk = Utilidades.isEmailValid(usuario.getPessoa().getEmail());
				if (!emailOk) {
					mensagensDisparar("Email é inválido!!!");
					return false;
				}
			}
		if (validarSenha)
			if (usuario != null && usuario.getSenha() != null && usuario.getConfSenha() != null) {
				usuario.setAtivo(true);
				usuario.setId_Empresa(1);
				usuario.setUltimaAtualizacao(Utilidades.retornaCalendario());
				usuario.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
				boolean resultado = UsuarioBusiness.confereSenha(usuario);
				if (!resultado)
					return false;
			}
		return true;
	}

	public static Enum_Aux_Perfil_Pessoa getPerfildaPagina() {
		return perfildaPagina;
	}

}
