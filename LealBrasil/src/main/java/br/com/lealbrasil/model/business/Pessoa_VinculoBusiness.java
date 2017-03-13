package br.com.lealbrasil.model.business;

import java.io.Serializable;

import org.omnifaces.util.Messages;

import br.com.lealbrasil.model.dao.Pessoa_VinculoDAO;
import br.com.lealbrasil.model.entities.Pessoa_Vinculo;

@SuppressWarnings("serial")
public class Pessoa_VinculoBusiness implements Serializable {
	
	public static Pessoa_Vinculo merge(Pessoa_Vinculo pV){
		Pessoa_VinculoDAO pessoa_Vinculo= new Pessoa_VinculoDAO();
		Pessoa_Vinculo pessoaVinculo = Pessoa_VinculoBusiness.buscaPorPessoa(pV);
		if(pessoaVinculo == null){
			pV = pessoa_Vinculo.merge(pV);
		}			 
		return pV;
	}
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static Pessoa_Vinculo buscaPorPessoa(Pessoa_Vinculo pessoaV){
		Pessoa_Vinculo pV = new Pessoa_VinculoDAO().buscaPorPessoa(pessoaV);
		return pV;
	}
	

}
