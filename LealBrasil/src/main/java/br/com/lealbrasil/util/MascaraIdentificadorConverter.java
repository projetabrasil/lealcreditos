package br.com.lealbrasil.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("mascaraIdentificadorConverter")
public class MascaraIdentificadorConverter implements Converter{
	
	
//	private String mascaraSelecionada;
//
//	public String getMascaraSelecionada() {
//		return mascaraSelecionada;
//	}
//
//	public void setMascaraSelecionada(String mascaraSelecionada) {
//		this.mascaraSelecionada = mascaraSelecionada;
//	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException{
		
        String resultado = value;
        if (resultado!= null && !resultado.equals("")){ //Sem Máscara
        	resultado = Utilidades.retiraCaracteres(resultado);
        }
        
        
    	if(resultado.length() == 14){
            resultado = resultado.substring(0, 2) + "." + resultado.substring(2, 5) + "." + resultado.substring(5, 8) + "/" 
                    + resultado.substring(8, 12)+"-"+resultado.substring(12, 14);
    	}else{
	       if (resultado != null && resultado.length() == 11){
	              resultado = resultado.substring(0, 3) + "." + resultado.substring(3, 6) + "." + 
	            		  										resultado.substring(6, 9) + "-" + resultado.substring(9, 11);
    		}
    	}
        
        return (Object)resultado;
	}
	
	public Object getAsObject(FacesContext context, UIComponent component, Object value) throws ConverterException{
		
        String resultado = (String)value;
        if (resultado!= null && !resultado.equals("")){ //Sem Máscara
        	resultado = Utilidades.retiraCaracteres(resultado);
        }
        
        
    	if(resultado.length() == 14){
            resultado = resultado.substring(0, 2) + "." + resultado.substring(2, 5) + "." + resultado.substring(5, 8) + "/" 
                    + resultado.substring(8, 12)+"-"+resultado.substring(12, 14);
    	}else{
	       if (resultado != null && resultado.length() == 11){
	              resultado = resultado.substring(0, 3) + "." + resultado.substring(3, 6) + "." + 
	            		  										resultado.substring(6, 9) + "-" + resultado.substring(9, 11);
    		}
    	}
        
        return (Object)resultado;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException{
		
        String resultado = (String)value;
        if (resultado!= null && !resultado.equals("")){ //Sem Máscara
        	resultado = Utilidades.retiraCaracteres(resultado);
        }
        
        
    	if(resultado.length() == 14){
            resultado = resultado.substring(0, 2) + "." + resultado.substring(2, 5) + "." + resultado.substring(5, 8) + "/" 
                    + resultado.substring(8, 12)+"-"+resultado.substring(12, 14);
    	}else{
	       if (resultado != null && resultado.length() == 11){
	              resultado = resultado.substring(0, 3) + "." + resultado.substring(3, 6) + "." + 
	            		  										resultado.substring(6, 9) + "-" + resultado.substring(9, 11);
    		}
    	}
        
        return resultado;
	}
	
	public String getAsString(FacesContext context, UIComponent component, String value) throws ConverterException{
		
        String resultado = value;
        if (resultado!= null && !resultado.equals("")){ //Sem Máscara
        	resultado = Utilidades.retiraCaracteres(resultado);
        }
        
        
    	if(resultado.length() == 14){
            resultado = resultado.substring(0, 2) + "." + resultado.substring(2, 5) + "." + resultado.substring(5, 8) + "/" 
                    + resultado.substring(8, 12)+"-"+resultado.substring(12, 14);
    	}else{
	       if (resultado != null && resultado.length() == 11){
	              resultado = resultado.substring(0, 3) + "." + resultado.substring(3, 6) + "." + 
	            		  										resultado.substring(6, 9) + "-" + resultado.substring(9, 11);
    		}
    	}
        
        return resultado;
	}
	

}
