package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the cierre_app database table.
 * 
 */
@Entity
@Table(name = "cierre_app")
@NamedQuery(name = "CierreApp.findAll", query = "SELECT c FROM CierreApp c")
public class CierreApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String estado;

	@Column(name = "fecha_final")
	private Timestamp fechaFinal;

	@Column(name = "fecha_inicio")
	private Timestamp fechaInicio;

	private String usuario;

	public CierreApp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "CierreApp [id=" + id + ", estado=" + estado + ", fechaFinal=" + fechaFinal + ", fechaInicio=" + fechaInicio + ", usuario=" + usuario + "]";
	}

}