package br.com.lealbrasil.model.business;

import java.io.Serializable;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.Pessoa_VinculoDAO;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;

@SuppressWarnings("serial")
public class Pessoa_VinculoBusiness implements Serializable {
	
	public static Pessoa_Vinculo merge(Pessoa_Vinculo pV, PerfilLogado perfilLogado ){
		Pessoa_Vinculo vinculo_Anterior = pV;		
		               
		Pessoa_VinculoDAO pessoa_Vinculo= new Pessoa_VinculoDAO();
		 pV = pessoa_Vinculo.pessoaEstaVinculada(pV, perfilLogado);
		 if (pV==null  || pV.getId()==null){			 
			 pV = vinculo_Anterior;
			 pV = pessoa_Vinculo.merge(pV);
			 pV.setId_pessoa_d(vinculo_Anterior.getId_pessoa_d());		 
			 pV.setId_pessoa_m(vinculo_Anterior.getId_pessoa_m());
			
			 String descricao;
			 if (perfilLogado.getPaginaAtual().getPerfilPessoa().getDescricao()!=null)
				 descricao =perfilLogado.getPaginaAtual().getPerfilPessoa().getDescricao();
				 else 
					 descricao = perfilLogado.getPaginaAtual().getSigla();
			  mensagensDisparar(""+ descricao
			          +" foi vinculado com sucesso ao seu cadastro!!!");
		 }	 
		return pV;
	}
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

}
