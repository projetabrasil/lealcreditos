package br.com.lealbrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Ponto")
public class Ponto extends Item_de_Movimento implements Serializable{
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
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Tipo_Item_de_Movimento")
	private 
	Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento;
	
	
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
	@Override
	public String toString() {
		return "Ponto [pontuacaoMinima=" + pontuacaoMinima + ", unidadeporPonto=" + unidadeporPonto + ", diasValidade="
				+ diasValidade + ", valordaUnidade=" + valordaUnidade + ", valorUnidadeTroca=" + valorUnidadeTroca
				+ ", valorUnidadeDevolucao=" + valorUnidadeDevolucao + ", enum_Aux_Tipo_Item_de_Movimento="
				+ enum_Aux_Tipo_Item_de_Movimento + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + diasValidade;
		result = prime * result
				+ ((enum_Aux_Tipo_Item_de_Movimento == null) ? 0 : enum_Aux_Tipo_Item_de_Movimento.hashCode());
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
		Ponto other = (Ponto) obj;
		if (diasValidade != other.diasValidade)
			return false;
		if (enum_Aux_Tipo_Item_de_Movimento != other.enum_Aux_Tipo_Item_de_Movimento)
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
	public Enum_Aux_Tipo_Item_de_Movimento getEnum_Aux_Tipo_Item_de_Movimento() {
		return enum_Aux_Tipo_Item_de_Movimento;
	}
	public void setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento) {
		this.enum_Aux_Tipo_Item_de_Movimento = enum_Aux_Tipo_Item_de_Movimento;
	}
		
			
}
	
	
	
	
	
