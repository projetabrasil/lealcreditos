package br.com.lealbrasil.model.entities;

import java.io.Serializable;

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

@SuppressWarnings("serial")
@Entity
@Table(name="Logradouro")
public class Logradouro extends GenericDomain implements Serializable {
	
	@Id
	@SequenceGenerator(name="pk_logradouro",sequenceName="messsounds_logradouro", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_logradouro")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Tipo_Logradouro", nullable=false)	
	private Enum_Aux_Tipo_Logradouro enum_Aux_Tipo_Logradouro ;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enum_Aux_Tipo_Logradouro getEnum_Aux_Tipo_Logradouro() {
		return enum_Aux_Tipo_Logradouro;
	}

	public void setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro enum_Aux_Tipo_Logradouro) {
		this.enum_Aux_Tipo_Logradouro = enum_Aux_Tipo_Logradouro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((enum_Aux_Tipo_Logradouro == null) ? 0 : enum_Aux_Tipo_Logradouro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Logradouro other = (Logradouro) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (enum_Aux_Tipo_Logradouro != other.enum_Aux_Tipo_Logradouro)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Logradouro [id=" + id + ", enum_Aux_Tipo_Logradouro=" + enum_Aux_Tipo_Logradouro + ", descricao="
				+ descricao + ", cidade=" + cidade + "]";
	}
	
	
}	
