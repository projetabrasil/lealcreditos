package br.com.lealbrasil.controller.entitiesconfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.lealbrasil.model.entities.Enum_Aux_Tipo_Pais;
import br.com.lealbrasil.model.entities.Pais;


public class PaisConfig implements Serializable{
			private Pais pais;

			private String descPais;
			private String zoneCodeMask;
			private Enum_Aux_Tipo_Pais zoneCodeNum;

			
			
		public void mudarLabels(Enum_Aux_Tipo_Pais zoneCodeNum){
			
			
			
			
		}
		
		public Pais getPais() {
			return pais;
		}

//		public void setPais(Pais pais) {
//			this.pais = pais;
//		}


		public String getDescPais() {
			return descPais;
		}

		public void setDescPais(String descPais) {
			this.descPais = descPais;
		}



		public Enum_Aux_Tipo_Pais getZoneCodeNum() {
			return this.zoneCodeNum;
		}
		
		public Enum_Aux_Tipo_Pais setZoneCodeNum() {
//			if(this.getZoneCodeNum()==null)
//				this.setDescPais();
			return this.zoneCodeNum;
		}
			
		public String getZoneCodeMask() {
//			if(this.getZoneCodeMask)==null)
//					this.setZoneCodeMask();
		return this.zoneCodeMask;
		}
		
		public void setZoneCodeMask() {
			if(this.getZoneCodeNum()==null)
				this.setZoneCodeNum();
			this.zoneCodeMask = Enum_Aux_Tipo_Pais.getZoneCodeMask(this.zoneCodeNum);
		}
		
				



		
		public PaisConfig(String desc) {
			this.descPais = "Brasil";
			this.zoneCodeNum=Enum_Aux_Tipo_Pais.percorrerTodos(this.descPais);
			this.zoneCodeMask = Enum_Aux_Tipo_Pais.getZoneCodeMask(this.zoneCodeNum);

		}

}
