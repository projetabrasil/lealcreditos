package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Tipo_Logradouro {
	RUA("RUA",1,true),
	AVENIDA("AVENIDA",2,true),
	RODOVIA("RODOVIA",3,true),
	SERVIDAO("SERVIDÃO",4,true),
	OUTROS("OUTROS",5,false);
	
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
