package br.com.lealbrasil.model.entities;

public enum Enum_Perfil_Pagina_Atual {
	PAGINAADMINISTRADORES("PAGINAADMINISTRADORES", "PADM",0,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,null,false,true,"pessoas.xhtml" ),
	PAGINASUPERVISORES("PAGINASUPERVISORES","PSUP",1,Enum_Aux_Perfil_Pessoa.SUPERVISORES,null,false,true,"pessoas.xhtml"),
	PAGINAASSINANTES("PAGINAASSINANTES","PASS",2,Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,false,true,"pessoas.xhtml"),
	PAGINACLIENTES("PAGINACLIENTES","PCLI",3,Enum_Aux_Perfil_Pessoa.CLIENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true ,"pessoas.xhtml"),	
	PAGINAATENDENTES("PAGINAATENDENTES","PATE",4,Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,true,"pessoas.xhtml"),
	PAGINAREPRESENTANTES("PAGINAREPRESENTANTES","PREP",5,Enum_Aux_Perfil_Pessoa.REPRESENTANTES,Enum_Aux_Perfil_Pessoa.SUPERVISORES,false,true,"pessoas.xhtml"),
	PAGINAAUTENTICACAO("PAGINAAUTENTICACAO","PAUT",6,Enum_Aux_Perfil_Pessoa.OUTROS ,Enum_Aux_Perfil_Pessoa.OUTROS,false,false,"autenticacao.xhtml"),
	PAGINAPONTUACAOCONFIG("PAGINAPONTUACAOCONFIG","PCONFPONT",7, Enum_Aux_Perfil_Pessoa.ASSINANTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_config.xhtml"),
	PAGINAPONTUACAOC("PAGINAPONTUACAOC","PMOVPONT",9, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_movimento.xhtml"),
	PAGINAPONTUACAOD("PAGINAPONTUACAOD","PMOVPONT",10, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_movimento.xhtml"),
	PAGINAPONTUACAOE("PAGINAPONTUACAOE","PMOVPONT",11, Enum_Aux_Perfil_Pessoa.ATENDENTES,Enum_Aux_Perfil_Pessoa.ASSINANTES,true,false,"pontuacao_movimento.xhtml"),
	PAGINAITENSMOVIMENTO("PAGINAITENSMOVIMENTO","PITENSMOV",12,Enum_Aux_Perfil_Pessoa.ADMINISTRADORES,null ,false,false,""),
	PAGINAOUTROS("PAGINAOUTROS","POUTROS",13,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,true,"pessoas.xhtml"),
	PAGINAINDEX("PAGINAINDEX","PINDEX",14,Enum_Aux_Perfil_Pessoa.OUTROS,null,false,false,"index.xhtml");
    private String descricao;    
	private String sigla;
	private int id;
	private Enum_Aux_Perfil_Pessoa PerfilPessoa;
	private Enum_Aux_Perfil_Pessoa mestre;
	private boolean necessitadoAssinante;
	private boolean renderizaCadastrodeUsuarios;
	private String url;
	
	Enum_Perfil_Pagina_Atual(String descricao, String sigla, int id, Enum_Aux_Perfil_Pessoa perfilPessoa, 
			Enum_Aux_Perfil_Pessoa mestre, boolean necessitadoAssinante, boolean renderizaCadastrodeUsuarios, String url){
		this.descricao = descricao;
		this.sigla = sigla;
		this.id = id;
		this.mestre = mestre;
		this.necessitadoAssinante = necessitadoAssinante;
		this.PerfilPessoa = perfilPessoa;
		this.renderizaCadastrodeUsuarios = renderizaCadastrodeUsuarios;
		this.url = url;
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

	
}
