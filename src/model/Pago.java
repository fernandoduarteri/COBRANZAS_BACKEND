package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the pago database table.
 * 
 */
@Entity
@Table(name="pago")
@NamedQuery(name="Pago.findAll", query="SELECT p FROM Pago p")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="fecha_pago")
	private Timestamp fechaPago;

	@Column(name="monto_pagado")
	private double montoPagado;

	@Lob
	private String usuario;

	//bi-directional many-to-one association to Cuota
	@ManyToOne
	@JoinColumn(name="cuota")
	private Cuota cuotaBean;

	public Pago() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Timestamp fechaPago) {
		  
		this.fechaPago = fechaPago;
	}

	public double getMontoPagado() {
		return this.montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Cuota getCuotaBean() {
		return this.cuotaBean;
	}

	public void setCuotaBean(Cuota cuotaBean) {
		this.cuotaBean = cuotaBean;
	}

}