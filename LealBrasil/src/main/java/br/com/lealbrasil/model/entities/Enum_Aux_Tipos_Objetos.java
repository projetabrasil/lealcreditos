package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Tipos_Objetos {
	
	PET("nome", "raça", "porte" , "nascimento"),
    MOVEL_PESSOAL("nome", "marca", "modelo" ,"fabricação"),
    MOVEL_COLETIVO("nome", "marca", "modelo" , "fabricação"),
    TRANSPORTE_PESSOAL("nome", "marca", "modelo" ,"fabricação"),
    TRANSPORTE_COLETIVO("nome", "marca", "modelo" ,"fabricação");   
    
    String lblDescricao01;
    String lblDescricao02;
    String lblDescricao03;
    String lblDataDe;
    
    Enum_Aux_Tipos_Objetos(String t01, String t02 , String t03 , String t04){
    	this.lblDescricao01 = t01;
    	this.lblDescricao02 = t02;
    	this.lblDescricao03 = t03;
        this.lblDataDe = t04;
    }

	public String getLblDescricao01() {
		return lblDescricao01;
	}

	public String getLblDescricao02() {
		return lblDescricao02;
	}

	public String getLblDescricao03() {
		return lblDescricao03;
	}

	public String getLblDataDe() {
		return lblDataDe;
	}
    
    
}
