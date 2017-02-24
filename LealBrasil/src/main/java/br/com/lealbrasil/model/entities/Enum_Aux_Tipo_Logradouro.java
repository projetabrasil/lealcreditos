package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Tipo_Logradouro {
	AVENIDA("AVENIDA",1,true),
	RUA("RUA",2,true),
	OUTROS("OUTROS",3,false);
	
	private String descricao;
	private int id;
	private boolean selecionar;
	
	Enum_Aux_Tipo_Logradouro(String descricao, int id,boolean selecionar){
		this.descricao = descricao;
		this.id = id;
		this.selecionar = selecionar;
	}
	
	public String getDescricao() {
		return descricao;
	}	
	
	public int getId() {
		return id;
	}

	
	public boolean isSelecionar() {
		return selecionar;
	}
}
