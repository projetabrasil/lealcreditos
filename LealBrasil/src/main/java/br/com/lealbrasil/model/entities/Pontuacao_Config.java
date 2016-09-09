package br.com.lealbrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Pontuacao_Config")
public class Pontuacao_Config extends GenericDomain implements Serializable{
	
	@Id
	@SequenceGenerator(name="pk_pontuacao_Config",sequenceName="messsounds_pontuacao_Config", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pontuacao_Config")
	private Long id;
	
	@Column(name="descricao", nullable=true, length=90)
	private String descricao;
	@ManyToOne
	@JoinColumn ( name ="id_Pessoa_Registro", nullable=false)	
	private Pessoa id_Pessoa_Registro ;
	
	
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
	@ManyToOne	
	@JoinColumn ( name ="id_Pessoa_Associado", nullable=false)
	private Pessoa id_Pessoa_Associado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	public int getPontuacaoMinima() {
		return pontuacaoMinima;
	}
	public void setPontuacaoMinima(int pontuacaoMinima) {
		this.pontuacaoMinima = pontuacaoMinima;
	}
	public int getUnidadeporPonto() {
		return unidadeporPonto;
	}
	public void setUnidadeporPonto(int unidadeporPonto) {
		this.unidadeporPonto = unidadeporPonto;
	}
	public int getDiasValidade() {
		return diasValidade;
	}
	public void setDiasValidade(int diasValidade) {
		this.diasValidade = diasValidade;
	}
	public double getValordaUnidade() {
		return valordaUnidade;
	}
	public void setValordaUnidade(double valordaUnidade) {
		this.valordaUnidade = valordaUnidade;
	}
	public double getValorUnidadeTroca() {
		return valorUnidadeTroca;
	}
	public void setValorUnidadeTroca(double valorUnidadeTroca) {
		this.valorUnidadeTroca = valorUnidadeTroca;
	}
	public double getValorUnidadeDevolucao() {
		return valorUnidadeDevolucao;
	}
	public void setValorUnidadeDevolucao(double valorUnidadeDevolucao) {
		this.valorUnidadeDevolucao = valorUnidadeDevolucao;
	}
	public Pessoa getId_Pessoa_Associado() {
		return id_Pessoa_Associado;
	}
	public void setId_Pessoa_Associado(Pessoa id_Pessoa_Associado) {
		this.id_Pessoa_Associado = id_Pessoa_Associado;
	}
	@Override
	public String toString() {
		return "Pontuacao_Config [id=" + id + ", descricao=" + descricao + ", id_Pessoa_Registro=" + id_Pessoa_Registro
				+ ", pontuacaoMinima=" + pontuacaoMinima + ", unidadeporPonto=" + unidadeporPonto + ", diasValidade="
				+ diasValidade + ", valordaUnidade=" + valordaUnidade + ", valorUnidadeTroca=" + valorUnidadeTroca
				+ ", valorUnidadeDevolucao=" + valorUnidadeDevolucao + ", id_Pessoa_Associado=" + id_Pessoa_Associado
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + diasValidade;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Associado == null) ? 0 : id_Pessoa_Associado.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + pontuacaoMinima;
		result = prime * result + unidadeporPonto;
		long temp;
		temp = Double.doubleToLongBits(valorUnidadeDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnidadeTroca);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valordaUnidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pontuacao_Config other = (Pontuacao_Config) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diasValidade != other.diasValidade)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Associado == null) {
			if (other.id_Pessoa_Associado != null)
				return false;
		} else if (!id_Pessoa_Associado.equals(other.id_Pessoa_Associado))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (pontuacaoMinima != other.pontuacaoMinima)
			return false;
		if (unidadeporPonto != other.unidadeporPonto)
			return false;
		if (Double.doubleToLongBits(valorUnidadeDevolucao) != Double.doubleToLongBits(other.valorUnidadeDevolucao))
			return false;
		if (Double.doubleToLongBits(valorUnidadeTroca) != Double.doubleToLongBits(other.valorUnidadeTroca))
			return false;
		if (Double.doubleToLongBits(valordaUnidade) != Double.doubleToLongBits(other.valordaUnidade))
			return false;
		return true;
	}
			
}
	
	
	
	
	
