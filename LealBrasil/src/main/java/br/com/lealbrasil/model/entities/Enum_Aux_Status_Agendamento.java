package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Status_Agendamento {
	AGENDADO("AGENDADO",0,"Agd"),
	CANCELADO("CANCELADO",1,"Cld"),
	UTILIZADO("UTILIZADO",2,"Uzd");
	
	private String descricao;
	private int id;
	private String abrev;
	
	Enum_Aux_Status_Agendamento(String descricao, int id,String abrev){
		this.descricao = descricao;
		this.id = id;
		this.abrev = abrev;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public String getAbrev() {
		return abrev;
	}

}
