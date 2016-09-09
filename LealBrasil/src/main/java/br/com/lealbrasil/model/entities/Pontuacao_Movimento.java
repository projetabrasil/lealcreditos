package br.com.lealbrasil.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="Pontuacao_Movimento")
public class Pontuacao_Movimento extends GenericDomain implements Serializable {

	@Id
	@SequenceGenerator(name="pk_pontuacao_Movimento",sequenceName="messsounds_pontuacao_Movimento", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pontuacao_Movimento")
	private Long id;
	
	@ManyToOne	
	@JoinColumn ( name ="id_pessoa_associado", nullable=false)
	private Pessoa id_pessoa_associado;
	
	@ManyToOne	
	@JoinColumn ( name ="id_pessoa_cliente", nullable=false)
	private Pessoa id_pessoa_cliente;
	
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro;
	
	@ManyToOne
	@JoinColumn(name="id_pontuacao_config", nullable =false)
	private Pontuacao_Config id_pontuacao_config;
	@Column(name="pontuacaoMinima", nullable=false)	
	private int pontuacaoMinima;
	@Column(name="unidadeporPonto", nullable=false)	
	private int unidadeporPonto;	
	@Column(name="diasValidade",nullable=false)	
	private int diasValidade;
	
	@Column(name="valordaUnidade",precision=18,scale=4,nullable=false)
	private double valordaUnidade;	
	@Column(name="valorUnidadeTroca",precision=18,scale=4,nullable=false)
	private double valorUnidadeTroca;
	@Column(name="valorUnidadeDevolucao",precision=18,scale=4,nullable=false)
	private double valorUnidadeDevolucao;
	@Column(name="valoraPontuar", precision=18,scale=4,nullable=false)
	private double valoraPontuar;
	@Column(name="pontosAtingidos",nullable=false)
	private int pontosAtingidos;	
	@Enumerated(EnumType.STRING)
	@Column(name="creditaDebita", nullable=false)
	private Enum_Aux_Tipo_Mov_Pontuacao creditaDebita;
	
	@Temporal(TemporalType.DATE)
	private Date validade;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id_pessoa_associado
	 */
	public Pessoa getId_pessoa_associado() {
		return id_pessoa_associado;
	}

	/**
	 * @param id_pessoa_associado the id_pessoa_associado to set
	 */
	public void setId_pessoa_associado(Pessoa id_pessoa_associado) {
		this.id_pessoa_associado = id_pessoa_associado;
	}

	/**
	 * @return the id_pessoa_cliente
	 */
	public Pessoa getId_pessoa_cliente() {
		return id_pessoa_cliente;
	}

	/**
	 * @param id_pessoa_cliente the id_pessoa_cliente to set
	 */
	public void setId_pessoa_cliente(Pessoa id_pessoa_cliente) {
		this.id_pessoa_cliente = id_pessoa_cliente;
	}

	/**
	 * @return the id_pontuacao_config
	 */
	public Pontuacao_Config getId_pontuacao_config() {
		return id_pontuacao_config;
	}

	/**
	 * @param id_pontuacao_config the id_pontuacao_config to set
	 */
	public void setId_pontuacao_config(Pontuacao_Config id_pontuacao_config) {
		this.id_pontuacao_config = id_pontuacao_config;
	}

	/**
	 * @return the pontuacaoMinima
	 */
	public int getPontuacaoMinima() {
		return pontuacaoMinima;
	}

	/**
	 * @param pontuacaoMinima the pontuacaoMinima to set
	 */
	public void setPontuacaoMinima(int pontuacaoMinima) {
		this.pontuacaoMinima = pontuacaoMinima;
	}

	/**
	 * @return the unidadeporPonto
	 */
	public int getUnidadeporPonto() {
		return unidadeporPonto;
	}

	/**
	 * @param unidadeporPonto the unidadeporPonto to set
	 */
	public void setUnidadeporPonto(int unidadeporPonto) {
		this.unidadeporPonto = unidadeporPonto;
	}

	/**
	 * @return the diasValidade
	 */
	public int getDiasValidade() {
		return diasValidade;
	}

	/**
	 * @param diasValidade the diasValidade to set
	 */
	public void setDiasValidade(int diasValidade) {
		this.diasValidade = diasValidade;
	}

	/**
	 * @return the valordaUnidade
	 */
	public double getValordaUnidade() {
		return valordaUnidade;
	}

	/**
	 * @param valordaUnidade the valordaUnidade to set
	 */
	public void setValordaUnidade(double valordaUnidade) {
		this.valordaUnidade = valordaUnidade;
	}

	/**
	 * @return the valorUnidadeTroca
	 */
	public double getValorUnidadeTroca() {
		return valorUnidadeTroca;
	}

	/**
	 * @param valorUnidadeTroca the valorUnidadeTroca to set
	 */
	public void setValorUnidadeTroca(double valorUnidadeTroca) {
		this.valorUnidadeTroca = valorUnidadeTroca;
	}

	/**
	 * @return the valorUnidadeDevolucao
	 */
	public double getValorUnidadeDevolucao() {
		return valorUnidadeDevolucao;
	}

	/**
	 * @param valorUnidadeDevolucao the valorUnidadeDevolucao to set
	 */
	public void setValorUnidadeDevolucao(double valorUnidadeDevolucao) {
		this.valorUnidadeDevolucao = valorUnidadeDevolucao;
	}

	/**
	 * @return the valoraPontuar
	 */
	public double getValoraPontuar() {
		return valoraPontuar;
	}

	/**
	 * @param valoraPontuar the valoraPontuar to set
	 */
	public void setValoraPontuar(double valoraPontuar) {
		this.valoraPontuar = valoraPontuar;
	}

	/**
	 * @return the pontosAtingidos
	 */
	public int getPontosAtingidos() {
		return pontosAtingidos;
	}

	/**
	 * @param pontosAtingidos the pontosAtingidos to set
	 */
	public void setPontosAtingidos(int pontosAtingidos) {
		this.pontosAtingidos = pontosAtingidos;
	}

	/**
	 * @return the creditaDebita
	 */
	public Enum_Aux_Tipo_Mov_Pontuacao getCreditaDebita() {
		return creditaDebita;
	}

	/**
	 * @param creditaDebita the creditaDebita to set
	 */
	public void setCreditaDebita(Enum_Aux_Tipo_Mov_Pontuacao creditaDebita) {
		
		this.creditaDebita = creditaDebita;
	}

	/**
	 * @return the validade
	 */
	public Date getValidade() {
		return validade;
	}

	/**
	 * @param validade the validade to set
	 */
	public void setValidade(Date validade) {
		this.validade = validade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pontuacao_Movimento [id=" + id + ", id_pessoa_associado=" + id_pessoa_associado + ", id_pessoa_cliente="
				+ id_pessoa_cliente + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_pontuacao_config="
				+ id_pontuacao_config + ", pontuacaoMinima=" + pontuacaoMinima + ", unidadeporPonto=" + unidadeporPonto
				+ ", diasValidade=" + diasValidade + ", valordaUnidade=" + valordaUnidade + ", valorUnidadeTroca="
				+ valorUnidadeTroca + ", valorUnidadeDevolucao=" + valorUnidadeDevolucao + ", valoraPontuar="
				+ valoraPontuar + ", pontosAtingidos=" + pontosAtingidos + ", creditaDebita=" + creditaDebita
				+ ", validade=" + validade + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((creditaDebita == null) ? 0 : creditaDebita.hashCode());
		result = prime * result + diasValidade;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_pessoa_associado == null) ? 0 : id_pessoa_associado.hashCode());
		result = prime * result + ((id_pessoa_cliente == null) ? 0 : id_pessoa_cliente.hashCode());
		result = prime * result + ((id_pontuacao_config == null) ? 0 : id_pontuacao_config.hashCode());
		result = prime * result + pontosAtingidos;
		result = prime * result + pontuacaoMinima;
		result = prime * result + unidadeporPonto;
		result = prime * result + ((validade == null) ? 0 : validade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorUnidadeDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnidadeTroca);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valoraPontuar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valordaUnidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pontuacao_Movimento other = (Pontuacao_Movimento) obj;
		if (creditaDebita != other.creditaDebita)
			return false;
		if (diasValidade != other.diasValidade)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (id_pessoa_associado == null) {
			if (other.id_pessoa_associado != null)
				return false;
		} else if (!id_pessoa_associado.equals(other.id_pessoa_associado))
			return false;
		if (id_pessoa_cliente == null) {
			if (other.id_pessoa_cliente != null)
				return false;
		} else if (!id_pessoa_cliente.equals(other.id_pessoa_cliente))
			return false;
		if (id_pontuacao_config == null) {
			if (other.id_pontuacao_config != null)
				return false;
		} else if (!id_pontuacao_config.equals(other.id_pontuacao_config))
			return false;
		if (pontosAtingidos != other.pontosAtingidos)
			return false;
		if (pontuacaoMinima != other.pontuacaoMinima)
			return false;
		if (unidadeporPonto != other.unidadeporPonto)
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
			return false;
		if (Double.doubleToLongBits(valorUnidadeDevolucao) != Double.doubleToLongBits(other.valorUnidadeDevolucao))
			return false;
		if (Double.doubleToLongBits(valorUnidadeTroca) != Double.doubleToLongBits(other.valorUnidadeTroca))
			return false;
		if (Double.doubleToLongBits(valoraPontuar) != Double.doubleToLongBits(other.valoraPontuar))
			return false;
		if (Double.doubleToLongBits(valordaUnidade) != Double.doubleToLongBits(other.valordaUnidade))
			return false;
		return true;
	}

	/**
	 * @return the id_Pessoa_Registro
	 */
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}

	/**
	 * @param id_Pessoa_Registro the id_Pessoa_Registro to set
	 */
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	
	
	
	

}
