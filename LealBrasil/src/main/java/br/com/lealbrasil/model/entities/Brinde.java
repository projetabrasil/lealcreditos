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
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

@SuppressWarnings("serial")
@Entity
public class Brinde extends GenericDomain implements Serializable {
	@Id
	@SequenceGenerator(name = "pk_brinde", sequenceName = "mess_sounds_brinde", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_brinde")
	private Long id;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_Pessoa_Registro", nullable = false)
	private Pessoa id_Pessoa_Registro;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_Pessoa_Assiante", nullable = false)
	private Pessoa id_Pessoa_Assinante;

	@Column(name = "descricao", length = 90, nullable = false)
	private String descricao;
	@Column(name="caminhodaImagem",length=500)
	private String caminhodaImagem;	
	@Column(name = "pontos")	
	int pontos;
	@Transient
	@Column(name = "caminhodaImagem2")	
	 private String caminhodaImagem2;
	
	
	
	
	
	@Transient
	@Column(name = "foto")	
	 private StreamedContent foto;
	
	@Transient
	@Column(name = "foto2")	
	 private StreamedContent foto2;
	
	public Long getId() {
		return id;
	}
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	public Pessoa getId_Pessoa_Assinante() {
		return id_Pessoa_Assinante;
	}
	public String getDescricao() {
		return descricao;
	}
	public int getPontos() {
		return pontos;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	public void setId_Pessoa_Assinante(Pessoa id_Pessoa_Assinante) {
		this.id_Pessoa_Assinante = id_Pessoa_Assinante;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}
	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}
	
	public StreamedContent getFoto() {
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	public StreamedContent getFoto2() {
		return foto2;
	}
	public void setFoto2(StreamedContent foto2) {
		this.foto2 = foto2;
	}
	public String getCaminhodaImagem2() {
		return caminhodaImagem2;
	}
	public void setCaminhodaImagem2(String caminhodaImagem2) {
		this.caminhodaImagem2 = caminhodaImagem2;
	}
	@Override
	public String toString() {
		return "Brinde [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Assinante="
				+ id_Pessoa_Assinante + ", descricao=" + descricao + ", caminhodaImagem=" + caminhodaImagem
				+ ", pontos=" + pontos + ", caminhodaImagem2=" + caminhodaImagem2 + ", foto=" + foto + ", foto2="
				+ foto2 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((caminhodaImagem == null) ? 0 : caminhodaImagem.hashCode());
		result = prime * result + ((caminhodaImagem2 == null) ? 0 : caminhodaImagem2.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((foto2 == null) ? 0 : foto2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
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
		if (caminhodaImagem == null) {
			if (other.caminhodaImagem != null)
				return false;
		} else if (!caminhodaImagem.equals(other.caminhodaImagem))
			return false;
		if (caminhodaImagem2 == null) {
			if (other.caminhodaImagem2 != null)
				return false;
		} else if (!caminhodaImagem2.equals(other.caminhodaImagem2))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
			return false;
		if (foto2 == null) {
			if (other.foto2 != null)
				return false;
		} else if (!foto2.equals(other.foto2))
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
		if (pontos != other.pontos)
			return false;
		return true;
	}

}