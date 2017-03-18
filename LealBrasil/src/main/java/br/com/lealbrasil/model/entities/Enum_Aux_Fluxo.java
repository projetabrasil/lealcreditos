package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Fluxo {
	
	A1(0,"A+"),
	A2(1,"A-"),
	B1(2,"B+"),
	B2(3,"B-"),
	AB1(4,"AB+"),
	AB2(5,"AB-"),
	O1(6,"O+"),
	O2(7,"O+");
	
	private int id;
	private String descricao;
	
	private Enum_Aux_Fluxo(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
