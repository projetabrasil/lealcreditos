package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Tipo_Item_de_Movimento {
 BRINDE("BRINDE",0,"Brinde"),
 ITEM("ITEM",1,"Item"),
 PONTO("PONTO",2,"Ponto"),
 VOUCHER("VOUCHER",3,"Voucher");
 
	
	private String descricao;
	private int id;
	private String descricao2;
	
	Enum_Aux_Tipo_Item_de_Movimento(String descricao,int id, String descricao2){
		this.descricao = descricao;
		this.id = id;
		this.descricao2 = descricao2;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao2() {
		return descricao2;
	}
}
