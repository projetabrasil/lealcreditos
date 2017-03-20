package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Tipo_Logradouro {
	AEROPORTO("RUA",1,true),
	ALAMEDA("ALAMEDA",2,true),
	ÁREA("ÁREA",3,true),
	AVENIDA("AVENIDA",4,true),
	CAMPO("CAMPO",5,true),
	SERVIDAO("SERVIDÃO",6,true),
	CHACÁRA("CHACÁRA",7,true),
	COLÔNIA("COLÔNIA",8,true),
	CONDOMÍNIO("CONDOMÍNIO",9,true),
	CONJUNTO("CONJUNTO",10,true),
	DISTRITO("DISTRITO",11,true),
	ESPLANADA("ESPLANADA",12,true),
	ESTAÇÃO("ESTAÇÃO",13,true),
	ESTRADA("ESTRADA",14,true),
	FAVELA("FAVELA",15,true),
	FAZENDA("FAZENDA",16,true),
	FEIRA("FEIRA",17,true),
	JARDIM("JARDIM",18,true),
	LADEIRA("LADEIRA",19,true),
	LAGO("LAGO",20,true),
	LAGOA("LAGOA",21,true),
	LARGO("LARGO",22,true),
	LOTEAMENTO("LOTEAMENTO",23,true),
	MORRO("MORRO",24,true),
	NÚCLEO("NÚCLEO",25,true),
	PARQUE("PARQUE",26,true),
	PASSARELA("PASSARELA",27,true),
	PÁTIO("PÁTIO",28,true),
	PRAÇA("PRAÇA",29,true),
	QUADRA("QUADRA",30,true),
	RECANTO("RECANTO",31,true),
	RESIDENCIAL("RESIDENCIAL",32,true),
	RODOVIA("RODOVIA",33,true),
	RUA("RUA",34,true),
	SETOR("SETOR",35,true),
	SÍTIO("SÍTIO",36,true),
	TRAVESSA("TRAVESSA",37,true),
	TRECHO("TRECHO",38,true),
	TREVO("TREVO",39,true),
	VALE("VALE",40,true),
	VEREDA("VEREDA",41,true),
	VIA("VIA",42,true),
	VIADUTO("VIADUTO",43,true),
	VIELA("VIELA",44,true),
	VILA("VILA",45,true),
	OUTROS("OUTROS",46,false);
	
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
