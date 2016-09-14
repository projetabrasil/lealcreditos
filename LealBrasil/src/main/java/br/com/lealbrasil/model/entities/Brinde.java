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
public class Brinde extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name="pk_brinde", sequenceName="mess_sounds_brinde",allocationSize =1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_brinde")
	private Long id;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Pessoa_Registro",nullable=false)
	private Pessoa id_Pessoa_Registro;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_Pessoa_Assiante", nullable =false)
	private Pessoa id_Pessoa_Assinante;
	@Column(name="nome_Imagem", length=90, nullable=false)
	private String nome_Imagem;
	@Column(name="descricao",length=90, nullable=false)
	private String descricao;
	@Column(name="pontos")
	int pontos;
	@Override
	public String toString() {
		return "Brinde [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Assinante="
				+ id_Pessoa_Assinante + ", nome_Imagem=" + nome_Imagem + ", descricao=" + descricao + ", pontos="
				+ pontos + "]";
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
	public String getNome_Imagem() {
		return nome_Imagem;
	}
	public void setNome_Imagem(String nome_Imagem) {
		this.nome_Imagem = nome_Imagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((nome_Imagem == null) ? 0 : nome_Imagem.hashCode());
		result = prime * result + pontos;
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
		Brinde other = (Brinde) obj;
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
		if (nome_Imagem == null) {
			if (other.nome_Imagem != null)
				return false;
		} else if (!nome_Imagem.equals(other.nome_Imagem))
			return false;
		if (pontos != other.pontos)
			return false;
		return true;
	}
	
	
	
	

}
