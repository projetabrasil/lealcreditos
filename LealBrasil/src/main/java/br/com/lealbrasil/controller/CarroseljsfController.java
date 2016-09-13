package br.com.lealbrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.lealbrasil.model.entities.Carrossel;
import br.com.lealbrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.lealbrasil.model.entities.PerfilLogado;
import br.com.lealbrasil.util.Utilidades;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class CarroseljsfController implements Serializable {
	private List<Carrossel> carrosseis;
	private Carrossel carrossel;
	private boolean   renderizaCarrossel;
	@ManagedProperty(value="#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	
	@PostConstruct
	public void listar(){
		if(!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.CLIENTES)&&
				!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS))
				setRenderizaCarrossel(false);
		else
			setRenderizaCarrossel(true);
		 
		carrosseis = new ArrayList<Carrossel>();
		Date data = new Date();		
		data = Utilidades.retornaValidade(1);
		carrossel = new Carrossel("Pizza Parma","/images/pizza parma.png","",
				data, 8d, "12A16-14" );
		carrosseis.add(carrossel);
		data = Utilidades.retornaValidade(2);
		carrossel = new Carrossel("Salão da Corte","/images/salao da corte.png","",
				data, 10d, "15J17-09");
		carrosseis.add(carrossel);
		data = Utilidades.retornaValidade(5);
		carrossel = new Carrossel("Villa Kids","/images/villa kids.png","",
				data, 15d, "18T19-A2");
		carrosseis.add(carrossel);
		
	}
	
	public List<Carrossel> getCarrosseis() {
		return carrosseis;
	}
	public void setCarrosseis(List<Carrossel> carrosseis) {
		this.carrosseis = carrosseis;
	}

	public Carrossel getCarrossel() {
		return carrossel;
	}

	public void setCarrossel(Carrossel carrossel) {
		this.carrossel = carrossel;
	}

	@Override
	public String toString() {
		return "CarroseljsfController [carrosseis=" + carrosseis + ", carrossel=" + carrossel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrosseis == null) ? 0 : carrosseis.hashCode());
		result = prime * result + ((carrossel == null) ? 0 : carrossel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarroseljsfController other = (CarroseljsfController) obj;
		if (carrosseis == null) {
			if (other.carrosseis != null)
				return false;
		} else if (!carrosseis.equals(other.carrosseis))
			return false;
		if (carrossel == null) {
			if (other.carrossel != null)
				return false;
		} else if (!carrossel.equals(other.carrossel))
			return false;
		return true;
	}

	public boolean isRenderizaCarrossel() {
		return renderizaCarrossel;
	}

	public void setRenderizaCarrossel(boolean renderizaCarrossel) {
		this.renderizaCarrossel = renderizaCarrossel;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}
	
	

}
