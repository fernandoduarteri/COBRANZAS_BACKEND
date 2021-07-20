package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the status_visitas database table.
 * 
 */
@Entity
@Table(name="status_visitas")
@NamedQuery(name="StatusVisita.findAll", query="SELECT s FROM StatusVisita s")
public class StatusVisita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String cliente;

	private Timestamp fecha;

	private String status;
	
	private String usuario;

	public StatusVisita() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}