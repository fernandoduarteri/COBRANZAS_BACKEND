package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_cliente database table.
 * 
 */
@Entity
@Table(name="tipo_cliente")
@NamedQuery(name="TipoCliente.findAll", query="SELECT t FROM TipoCliente t")
public class TipoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private boolean activo;

	@Lob
	private String denominacion;

	

	public TipoCliente() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}




}