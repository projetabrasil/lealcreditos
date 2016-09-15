package br.com.lealbrasil.model.entities;

public enum Enum_Aux_Perfil_Pagina_Atual {
	PAGINAADMINISTRADORES("PAGINAADMINISTRADORES", "PADM",0,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,null,false,true,"pessoas.xhtml", "administradores" ),
	PAGINASUPERVISORES("PAGINASUPERVISORES","PSUP",1,Enum_Aux_Perfil_Pessoa.SUPERVISORES,null,false,true,"pessoas.xhtml", "supervisores"),
	PAGINAASSINANTES("PAGINAASSINANTES","PASS",2,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,false,true,"pessoas.xhtml", "assinantes"),
	PAGINACLIENTES("PAGINACLIENTES","PCLI",3,Enum_Aux_Perfil_Pessoa.CLIENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true ,"pessoas.xhtml", "clientes"),	
	PAGINAATENDENTES("PAGINAATENDENTES","PATE",4,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true,"pessoas.xhtml", "atendentes"),
	PAGINAREPRESENTANTES("PAGINAREPRESENTANTES","PREP",5,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,Enum_Aux_Perfil_Pessoa.SUPERVISORES,false,true,"pessoas.xhtml", "representantes"),
	PAGINAVENDAS("PAGINAVENDAS","PVEN",6,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,false,true,"pessoas.xhtml", "vendas"),
	PAGINAAUTENTICACAO("PAGINAAUTENTICACAO","PAUT",7,Enum_Aux_Perfil_Pessoa.OUTROS ,Enum_Aux_Perfil_Pessoa.OUTROS,false,false,"autenticacao.xhtml", "autenticacao"),
	PAGINAPONTUACAOCONFIG("PAGINAPONTUACAOCONFIG","PCONFPONT",8, Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_config.xhtml", "configuração de pontuação"),
	PAGINAPONTUACAOC("PAGINAPONTUACAOC","PMOVPONT",9, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_movimento.xhtml", "de pontuações de fidelizados - Creditar"),
	PAGINAPONTUACAOD("PAGINAPONTUACAOD","PMOVPONT",10, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_movimento.xhtml","de pontuações de fidelizados - Debitar"),
	PAGINAPONTUACAOE("PAGINAPONTUACAOE","PMOVPONT",11, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_movimento.xhtml","de pontuações de fidelizados - Estornar"),
	PAGINAITENSMOVIMENTO("PAGINAITENSMOVIMENTO","PITENSMOV",12,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"itens_movimento.xhtml","itens de movimento"),																																					
	PAGINAOUTROS("PAGINAOUTROS","POUTROS",13,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,true,"pessoas.xhtml","outros"),
	PAGINAINDEX("PAGINAINDEX","PINDEX",14,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,false,"index.xhtml", "página principal"),
	PAGINACOMBO("PAGINACOMBO","PCOMBO",15,Enum_Aux_Perfil_Pessoa.VENDAS,Enum_Aux_Perfil_Pessoa.VENDAS ,false,false,"combos.xhtml","combo"),
	PAGINABRINDE("PAGINABRINDE","PBRINDE",16,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES ,true,false,"brinde.xhtml","brindes");
	
    private String descricao;    
	private String sigla;
	private int id;
	private Enum_Aux_Perfil_Pessoa PerfilPessoa;
	private Enum_Aux_Perfil_Pessoa mestre;
	private boolean necessitadoAssinante;
	private boolean renderizaCadastrodeUsuarios;
	private String url;
	private String descricao2;
	
	Enum_Aux_Perfil_Pagina_Atual(String descricao, String sigla, int id, Enum_Aux_Perfil_Pessoa perfilPessoa, 
			Enum_Aux_Perfil_Pessoa mestre, boolean necessitadoAssinante, boolean renderizaCadastrodeUsuarios, String url, String descricao2){
		this.descricao = descricao;
		this.sigla = sigla;
		this.id = id;
		this.mestre = mestre;
		this.necessitadoAssinante = necessitadoAssinante;
		this.PerfilPessoa = perfilPessoa;
		this.renderizaCadastrodeUsuarios = renderizaCadastrodeUsuarios;
		this.url = url;
		this.descricao2 = descricao2;
	}
	public String getSigla() {
		return sigla;
	}
	public int getId() {
		return id;
	}
	public Enum_Aux_Perfil_Pessoa getPerfilPessoa() {
		return PerfilPessoa;
	}
	public Enum_Aux_Perfil_Pessoa getMestre() {
		return mestre;
	}	
	public boolean isNecessitadoAssinante() {
		return necessitadoAssinante;
	}
	public boolean isRenderizaCadastrodeUsuarios() {
		return renderizaCadastrodeUsuarios;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getUrl() {
		return url;
	}
	public String getDescricao2() {
		return descricao2;
	}

	
}
