package br.com.lealbrasil.model.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.lealbrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.lealbrasil.model.dao.Pessoa_VinculoDAO;
import br.com.lealbrasil.model.dao.Pontuacao_ConfigDAO;

@SuppressWarnings("serial")
public class PerfilLogado implements Serializable {

	Usuario usLogado;
	Pessoa assLogado;
	Enum_Perfil_Pagina_Atual paginaAtual;
	Enum_Aux_Perfil_Pessoa perfilUsLogado;
	private List<Enum_Aux_Perfil_Pessoa> listaPerfisdousLogado;
	private String foco;
	private String identificadorAssociado;
	private String identificadorUsuario;
	private String senhaUsuario;
	private boolean renderizaAssociado;
	private boolean renderizaPontuacao;
	private boolean renderizapessoanovo;
	private boolean renderizapessoaeditar;
	@ManagedProperty(value = "#{pontuacao.listaPontuacoesConfig}")
	private List<Pontuacao_Config> listaPontuacoesConfig;

	public boolean isRenderizapessoanovo() {
		return renderizapessoanovo;
	}

	public void setRenderizapessoanovo(boolean renderizapessoanovo) {
		this.renderizapessoanovo = renderizapessoanovo;
	}

	public boolean isRenderizapessoaeditar() {
		return renderizapessoaeditar;
	}

	public void setRenderizapessoaeditar(boolean renderizapessoaeditar) {
		this.renderizapessoaeditar = renderizapessoaeditar;
	}

	public PerfilLogado() {
		if (!renderizaAssociado)
			foco = "usuario";
		else
			foco = "ass";

		identificadorAssociado = "";
		identificadorUsuario = "";
		senhaUsuario = "";
		usLogado = new Usuario();
		assLogado = new Pessoa();
		setPaginaAtual(Enum_Perfil_Pagina_Atual.PAGINAAUTENTICACAO);
		setRenderizapessoaeditar(true);
		setRenderizapessoanovo(true);
	}

	public void verificaAssinante() {

		if (perfilUsLogado != null
				&& (perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ASSINANTES)
						|| perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)
						|| perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.VENDAS))
				&& perfilUsLogado.isTemPerfilMestre() && assLogado.getId() == null) {
			if (perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ASSINANTES)||perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.VENDAS))
				setAssLogado(usLogado.getPessoa());
			else if (perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)) {
				Pessoa_VinculoDAO pVincDAO = new Pessoa_VinculoDAO();
				Pessoa_Vinculo pessoa_Vinculo = new Pessoa_Vinculo();
				pessoa_Vinculo.setId_pessoa_d(usLogado.getPessoa());
				pessoa_Vinculo = pVincDAO.retornaVinculo_Mestre(pessoa_Vinculo, perfilUsLogado);
				if (pessoa_Vinculo != null && pessoa_Vinculo.getId_pessoa_m() != null) {
					setAssLogado(pessoa_Vinculo.getId_pessoa_m());
				}
			}
			setRenderizaAssociado(true);
			if (assLogado != null && assLogado.getIdentificador() != null && identificadorAssociado != null
					&& identificadorAssociado.length() > 0
					&& !assLogado.getIdentificador().equals(identificadorAssociado)) {
				try {

					Faces.redirect("./faces/pages/alfapage.xhtml");
				} catch (IOException error) {
					mensagensDisparar("erro ao tentar chamar a pagina alfa");
					error.printStackTrace();
				}
			}

		} else {
			setRenderizaAssociado(false);
		}

	}

	public void escondeDialogoAltenticacacao(boolean escolherPerfil) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('dialogoAutenticacao').hide();");
		if (escolherPerfil)
			requestContext.execute("PF('dialogoPerfil').show();");
	}

	public boolean temPermissoes(List<String> permissoes) {
		if (usLogado != null && permissoes != null) {
			if (perfilUsLogado != null) {
				String perf = perfilUsLogado.getDescricao().toUpperCase();
				for (String permissao : permissoes) {
					if (perf.equals(permissao.toUpperCase()))
						return true;
				}
			}
		}

		return false;
	}

	public boolean temPermissoes2(List<String> permissoes) {
		boolean retorno;
		retorno = false;
		if (usLogado == null || permissoes == null || perfilUsLogado == null) {
			return retorno;
		}
		String perf = perfilUsLogado.getDescricao().toUpperCase();
		for (String permissao : permissoes) {
			if (perf.equals(permissao.toUpperCase()) && retorno == false)
				retorno = true;
		}

		Pontuacao_ConfigDAO pConfigDAO = new Pontuacao_ConfigDAO();
		List<Pontuacao_Config> listPConfig;
		if (assLogado != null && assLogado.getId() != null)
			listPConfig = pConfigDAO.retornarListaPontuacaoConfig(assLogado);
		else
			listPConfig = pConfigDAO.retornarListaPontuacaoConfig(usLogado.getPessoa());

		setListaPontuacoesConfig(listPConfig);
		if (retorno)
			if (listaPontuacoesConfig == null || listaPontuacoesConfig.isEmpty())
				retorno = false;

		return retorno;

	}

	public void listagemPerfisdousLogado() {
		Pessoa_Enum_Aux_Perfil_PessoasDAO pf = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
		List<Pessoa_Enum_Aux_Perfil_Pessoa> pessoaPerfis = pf.retornaListaPerfil(usLogado.getPessoa());
		List<Enum_Aux_Perfil_Pessoa> perfis = new ArrayList<Enum_Aux_Perfil_Pessoa>();
		listaPerfisdousLogado = new ArrayList<Enum_Aux_Perfil_Pessoa>(pessoaPerfis.size());

		for (Pessoa_Enum_Aux_Perfil_Pessoa pessoaPerfil : pessoaPerfis) {
			perfis.add(pessoaPerfil.getEnum_Aux_Perfil_Pessoa());
		}

		setListaPerfisdousLogado(perfis);
	}

	private void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public Usuario getUsLogado() {
		return usLogado;
	}

	public void setUsLogado(Usuario usLogado) {
		this.usLogado = usLogado;
	}

	public Enum_Perfil_Pagina_Atual getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Enum_Perfil_Pagina_Atual paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	@Override
	public String toString() {
		return "PerfilLogado [usLogado=" + usLogado + ", assLogado=" + assLogado + ", paginaAtual=" + paginaAtual + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assLogado == null) ? 0 : assLogado.hashCode());
		result = prime * result + ((paginaAtual == null) ? 0 : paginaAtual.hashCode());
		result = prime * result + ((usLogado == null) ? 0 : usLogado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilLogado other = (PerfilLogado) obj;
		if (assLogado == null) {
			if (other.assLogado != null)
				return false;
		} else if (!assLogado.equals(other.assLogado))
			return false;
		if (paginaAtual != other.paginaAtual)
			return false;
		if (usLogado == null) {
			if (other.usLogado != null)
				return false;
		} else if (!usLogado.equals(other.usLogado))
			return false;
		return true;
	}

	public List<Enum_Aux_Perfil_Pessoa> getListaPerfisdousLogado() {
		return listaPerfisdousLogado;
	}

	public void setListaPerfisdousLogado(List<Enum_Aux_Perfil_Pessoa> listaPerfisdousLogado) {
		this.listaPerfisdousLogado = listaPerfisdousLogado;
	}

	public String getFoco() {
		return foco;
	}

	public void setFoco(String foco) {
		this.foco = foco;
	}

	public String getIdentificadorAssociado() {
		return identificadorAssociado;
	}

	public void setIdentificadorAssociado(String identificadorAssociado) {
		this.identificadorAssociado = identificadorAssociado;
	}

	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Enum_Aux_Perfil_Pessoa getPerfilUsLogado() {
		return perfilUsLogado;
	}

	public void setPerfilUsLogado(Enum_Aux_Perfil_Pessoa perfilUsLogado) {
		this.perfilUsLogado = perfilUsLogado;
	}

	public boolean isRenderizaAssociado() {
		return renderizaAssociado;
	}

	public void setRenderizaAssociado(boolean renderizaAssociado) {
		this.renderizaAssociado = renderizaAssociado;
	}

	public void setAssLogado(Pessoa assLogado) {
		this.assLogado = assLogado;
	}

	public Pessoa getAssLogado() {
		return assLogado;
	}

	/**
	 * @return the renderizaPontuacao
	 */
	public boolean isRenderizaPontuacao() {
		return renderizaPontuacao;
	}

	/**
	 * @param renderizaPontuacao
	 *            the renderizaPontuacao to set
	 */
	public void setRenderizaPontuacao(boolean renderizaPontuacao) {
		this.renderizaPontuacao = renderizaPontuacao;
	}

	public List<Pontuacao_Config> getListaPontuacoesConfig() {
		return listaPontuacoesConfig;
	}

	public void setListaPontuacoesConfig(List<Pontuacao_Config> listaPontuacoesConfig) {
		this.listaPontuacoesConfig = listaPontuacoesConfig;
	}

}
