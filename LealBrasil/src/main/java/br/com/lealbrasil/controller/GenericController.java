package br.com.lealbrasil.controller;

import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

@ManagedBean
public class GenericController  {
	
	public void mensagensDisparar(String mensagem){	
		Messages.addGlobalInfo(mensagem);
	}

}
