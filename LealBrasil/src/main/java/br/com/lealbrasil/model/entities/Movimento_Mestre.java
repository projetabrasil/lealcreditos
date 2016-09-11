package br.com.lealbrasil.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class Movimento_Mestre extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name="pk_movimento_mestre", sequenceName="mess_sounds_movimento_mestre", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pk_movimento_mestre")
	private Long id;	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Pessoa_Registro", nullable=false)
	private Pessoa id_Pessoa_Registro;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Pessoa_Assinante", nullable=false)
	private Pessoa id_Pessoa_Assinante;
	@Column(name="descricao",length =90, nullable=false)
	private String descricao;
	@Column(name="total", precision=18, scale=4, nullable=false)
	private double total;
	@Column(name="desconto", precision=18, scale=4, nullable=false)
	private double desconto;
	@Column(name="percDesc", precision=18, scale=4, nullable=false)
	private double percdesc;
	@Column(name="totalLiquido", precision=18,scale=4, nullable=false)
	private double totalLiquido;
	@Column(name="referencia", length=20, nullable=false )
	private String referencia;
	@Override
	public String toString() {
		return "ComboMestre [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Assinante="
				+ id_Pessoa_Assinante + ", descricao=" + descricao + ", total=" + total + ", desconto=" + desconto
				+ ", percdesc=" + percdesc + ", totalLiquido=" + totalLiquido + ", referencia=" + referencia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(desconto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		temp = Double.doubleToLongBits(percdesc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalLiquido);
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
		Movimento_Mestre other = (Movimento_Mestre) obj;
		if (Double.doubleToLongBits(desconto) != Double.doubleToLongBits(other.desconto))
			return false;
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
		if (Double.doubleToLongBits(percdesc) != Double.doubleToLongBits(other.percdesc))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		if (Double.doubleToLongBits(totalLiquido) != Double.doubleToLongBits(other.totalLiquido))
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
	public Pessoa getId_Pessoa_Assinante() {
		return id_Pessoa_Assinante;
	}
	public void setId_Pessoa_Assinante(Pessoa id_Pessoa_Assinante) {
		this.id_Pessoa_Assinante = id_Pessoa_Assinante;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getPercdesc() {
		return percdesc;
	}
	public void setPercdesc(double percdesc) {
		this.percdesc = percdesc;
	}
	public double getTotalLiquido() {
		return totalLiquido;
	}
	public void setTotalLiquido(double totalLiquido) {
		this.totalLiquido = totalLiquido;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
