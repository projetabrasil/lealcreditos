package br.com.lealbrasil.model.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="Objeto")
public class Objeto extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_objeto",sequenceName="messsounds_objeto", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_objeto")
	private Integer id;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_Ano_Nasc")
	private Date dt_ano_nasc;
	
	@Column(name="cor", length = 90, nullable=false)
	private String cor;
	
	@Column(name="modelo", length = 90, nullable=false)
	private String modelo;
	
	@Column(name="placa", length = 90, nullable=false)
	private String placa;
	
	@Column(name="tipo_Objeto")
	private Enum_Aux_Tipos_Objetos tipo_Objeto;
	
	@Column(name="tipo_Fluxo")
	private Enum_Aux_Fluxo tipo_Sanguineo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDt_ano_nasc() {
		return dt_ano_nasc;
	}

	public void setDt_ano_nasc(Date dt_ano_nasc) {
		this.dt_ano_nasc = dt_ano_nasc;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Enum_Aux_Tipos_Objetos getTipo_Objeto() {
		return tipo_Objeto;
	}

	public void setTipo_Objeto(Enum_Aux_Tipos_Objetos tipo_Objeto) {
		this.tipo_Objeto = tipo_Objeto;
	}

	public Enum_Aux_Fluxo getTipo_Sanguineo() {
		return tipo_Sanguineo;
	}

	public void setTipo_Sanguineo(Enum_Aux_Fluxo tipo_Sanguineo) {
		this.tipo_Sanguineo = tipo_Sanguineo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dt_ano_nasc == null) ? 0 : dt_ano_nasc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((tipo_Objeto == null) ? 0 : tipo_Objeto.hashCode());
		result = prime * result + ((tipo_Sanguineo == null) ? 0 : tipo_Sanguineo.hashCode());
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
		Objeto other = (Objeto) obj;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dt_ano_nasc == null) {
			if (other.dt_ano_nasc != null)
				return false;
		} else if (!dt_ano_nasc.equals(other.dt_ano_nasc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (tipo_Objeto != other.tipo_Objeto)
			return false;
		if (tipo_Sanguineo != other.tipo_Sanguineo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Objeto [id=" + id + ", descricao=" + descricao + ", dt_ano_nasc=" + dt_ano_nasc + ", cor=" + cor
				+ ", modelo=" + modelo + ", placa=" + placa + ", tipo_Objeto=" + tipo_Objeto + ", tipo_Sanguineo="
				+ tipo_Sanguineo + "]";
	}
	
	
	
}
