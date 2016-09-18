package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Tipo_Item_de_Movimento {
 BRINDE("BRINDE",0,"Brinde","Br"),
 ITEMDESERVICO("ITEMDESERVICO",1,"Item de serviço","Is"),
 PONTO("PONTO",2,"Ponto","Pt"),
 VOUCHER("VOUCHER",3,"Voucher","Vh");
 
	
	private String descricao;
	private int id;
	private String descricao2;
	private String referencia;
	
	Enum_Aux_Tipo_Item_de_Movimento(String descricao,int id, String descricao2,String referencia){
		this.descricao = descricao;		
		this.id = id;
		this.descricao2 = descricao2;
		this.referencia = referencia;
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

	public String getReferencia() {
		return referencia;
	}
}
