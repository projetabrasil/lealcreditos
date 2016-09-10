package br.com.lealbrasil.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
public class Itens_Movimento extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name="pk_itens_movimento",sequenceName="messounds_itens_movimento", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_itens_movimento")
	private Long id;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Pessoa_Registro", nullable=false)
	private Pessoa id_Pessoa_Registro;
	@Column(name="descricao", length = 90, nullable = false)
	private String descricao;
	@Column(name="valordaUnidade", precision= 18, scale=4, nullable=false)
	private double valordaUnidade;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Pessoa_Assinante", nullable = false)
	private Pessoa id_Pessoa_Assinante;
	@Override
	public String toString() {
		return "Itens_Movimento [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", descricao=" + descricao
				+ ", valordaUnidade=" + valordaUnidade + ", id_Pessoa_Assinante=" + id_Pessoa_Assinante + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		long temp;
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
		Itens_Movimento other = (Itens_Movimento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Pessoa_Assinante == null) {
			if (other.id_Pessoa_Assinante != null)
				return false;
		} else if (!id_Pessoa_Assinante.equals(other.id_Pessoa_Assinante))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (Double.doubleToLongBits(valordaUnidade) != Double.doubleToLongBits(other.valordaUnidade))
			return false;
		return true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValordaUnidade() {
		return valordaUnidade;
	}
	public void setValordaUnidade(double valordaUnidade) {
		this.valordaUnidade = valordaUnidade;
	}
	public Pessoa getId_Pessoa_Assinante() {
		return id_Pessoa_Assinante;
	}
	public void setId_Pessoa_Assinante(Pessoa id_Pessoa_Assinante) {
		this.id_Pessoa_Assinante = id_Pessoa_Assinante;
	}
	

	
	

	

}
