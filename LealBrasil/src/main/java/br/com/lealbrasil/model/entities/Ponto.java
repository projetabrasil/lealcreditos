package br.com.lealbrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Ponto")
public class Ponto extends Item_de_Movimento implements Serializable{
	@Column(name="pontuacaoMinima")	
	private int pontuacaoMinima;
	@Column(name="unidadeporPonto")	
	private int unidadeporPonto;	
	@Column(name="diasValidade")	
	private int diasValidade;
	
		
	@Column(name="valorUnidadeTroca",precision=18,scale=4)
	private double valorUnidadeTroca;
	@Column(name="valorUnidadeDevolucao",precision=18,scale=4)
	private double valorUnidadeDevolucao;
	
	public Ponto(){
		super();
	}
	
	public Ponto(Pessoa id_Pessoa_Registro, Pessoa id_Pessoa_Assinante, 
			 Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento){
		
		       super(id_Pessoa_Registro, id_Pessoa_Assinante, 
				Enum_Aux_Sim_ou_Nao.SIM,enum_Aux_Tipo_Item_de_Movimento);
		
				this.diasValidade =30;
				this.pontuacaoMinima =  1;
				this.unidadeporPonto = 1;
				this.valorUnidadeDevolucao =0d;
				this.valorUnidadeTroca =0d;
				
				
				
				
				
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
				+ diasValidade + ", valorUnidadeTroca=" + valorUnidadeTroca + ", valorUnidadeDevolucao="
				+ valorUnidadeDevolucao + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + diasValidade;
		result = prime * result + pontuacaoMinima;
		result = prime * result + unidadeporPonto;
		long temp;
		temp = Double.doubleToLongBits(valorUnidadeDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorUnidadeTroca);
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
		if (pontuacaoMinima != other.pontuacaoMinima)
			return false;
		if (unidadeporPonto != other.unidadeporPonto)
			return false;
		if (Double.doubleToLongBits(valorUnidadeDevolucao) != Double.doubleToLongBits(other.valorUnidadeDevolucao))
			return false;
		if (Double.doubleToLongBits(valorUnidadeTroca) != Double.doubleToLongBits(other.valorUnidadeTroca))
			return false;
		return true;
	}
			
			
}
	
	
	
	
	
