package br.com.lealbrasil.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="Pessoa")
public class Pessoa extends GenericDomain implements Serializable{	
	
	@Id
	@SequenceGenerator(name="pk_pessoa",sequenceName="messsounds_pessoa", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pessoa")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Tipo_Identificador", nullable=false)	
	private Enum_Aux_Tipo_Identificador enum_Aux_Tipo_Identificador ;
	
	@Column(name="descricao", length = 90, nullable=false)
	private String descricao;
	@Column(name="fantasia_Apelido", length = 90, nullable=true)
	private String fantasia_Apelido;
	@Column(name="identificador", length = 40, nullable=false)
	private String identificador;
	@Column(name="dataNascimento", nullable=true)
	@Temporal(TemporalType.DATE)	
	private Date dataNascimento;
	
	
	@Column(name="cpf_Cnpj", length=20)
	private String cpf_Cnpj;
	@Column(name="rg_Insc", length=20)
	private String rg_Insc;
	@Enumerated(EnumType.STRING)
	private Enum_Sexo sexo; 
	@Column(name="fone_1", length=20, nullable=true)
	private String fone_1;
	@Column(name="fone_2", length=20,nullable=true)
	private String fone_2;
	@Column(name="fone_3", length=20,nullable=true)
	private String fone_3;
	@Column(name="email", length=90,nullable=true) 
	private String email;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn ( name ="id_Pessoa_Registro")	
	private Pessoa id_Pessoa_Registro ;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Enumerated ( EnumType.STRING )
	public Enum_Aux_Tipo_Identificador getEnum_Aux_Tipo_Identificador() {
		return enum_Aux_Tipo_Identificador;
	}
	public void setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador enum_Aux_Tipo_Identificador) {
		this.enum_Aux_Tipo_Identificador = enum_Aux_Tipo_Identificador;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf_Cnpj() {
		return cpf_Cnpj;
	}	
	public void setCpf_Cnpj(String cpf_Cnpj) {
		this.cpf_Cnpj = cpf_Cnpj;
	}	
	public String getRg_Insc() {
		return rg_Insc;
	}
	public void setRg_Insc(String rg_Insc) {
		this.rg_Insc = rg_Insc;
	}
	public Enum_Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Enum_Sexo sexo) {
		this.sexo = sexo;
	}
	public String getFone_1() {
		return fone_1;
	}
	public void setFone_1(String fone_1) {
		this.fone_1 = fone_1;
	}
	public String getFone_2() {
		return fone_2;
	}
	public void setFone_2(String fone_2) {
		this.fone_2 = fone_2;
	}
	public String getFone_3() {
		return fone_3;
	}
	public void setFone_3(String fone_3) {
		this.fone_3 = fone_3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFantasia_Apelido() {
		return fantasia_Apelido;
	}
	public void setFantasia_Apelido(String fantasia_Apelido) {
		this.fantasia_Apelido = fantasia_Apelido;
	}
	
	
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	/**
	 * @param id_Pessoa_Registro the id_Pessoa_Registro to set
	 */
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", enum_Aux_Tipo_Identificador=" + enum_Aux_Tipo_Identificador + ", descricao="
				+ descricao + ", fantasia_Apelido=" + fantasia_Apelido + ", identificador=" + identificador
				+ ", dataNascimento=" + dataNascimento + ", cpf_Cnpj=" + cpf_Cnpj + ", rg_Insc=" + rg_Insc + ", sexo="
				+ sexo + ", fone_1=" + fone_1 + ", fone_2=" + fone_2 + ", fone_3=" + fone_3 + ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf_Cnpj == null) ? 0 : cpf_Cnpj.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enum_Aux_Tipo_Identificador == null) ? 0 : enum_Aux_Tipo_Identificador.hashCode());
		result = prime * result + ((fantasia_Apelido == null) ? 0 : fantasia_Apelido.hashCode());
		result = prime * result + ((fone_1 == null) ? 0 : fone_1.hashCode());
		result = prime * result + ((fone_2 == null) ? 0 : fone_2.hashCode());
		result = prime * result + ((fone_3 == null) ? 0 : fone_3.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((rg_Insc == null) ? 0 : rg_Insc.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf_Cnpj == null) {
			if (other.cpf_Cnpj != null)
				return false;
		} else if (!cpf_Cnpj.equals(other.cpf_Cnpj))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enum_Aux_Tipo_Identificador != other.enum_Aux_Tipo_Identificador)
			return false;
		if (fantasia_Apelido == null) {
			if (other.fantasia_Apelido != null)
				return false;
		} else if (!fantasia_Apelido.equals(other.fantasia_Apelido))
			return false;
		if (fone_1 == null) {
			if (other.fone_1 != null)
				return false;
		} else if (!fone_1.equals(other.fone_1))
			return false;
		if (fone_2 == null) {
			if (other.fone_2 != null)
				return false;
		} else if (!fone_2.equals(other.fone_2))
			return false;
		if (fone_3 == null) {
			if (other.fone_3 != null)
				return false;
		} else if (!fone_3.equals(other.fone_3))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (rg_Insc == null) {
			if (other.rg_Insc != null)
				return false;
		} else if (!rg_Insc.equals(other.rg_Insc))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
		
}
