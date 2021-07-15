package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObjectReturn {

	private Object data;
	private Boolean exito;
	private String mensaje;
	private int total;
	
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Boolean getExito() {
		return exito;
	}
	public void setExito(Boolean exito) {
		this.exito = exito;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
