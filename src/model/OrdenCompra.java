package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orden_compra database table.
 * 
 */
@Entity
@Table(name="orden_compra")
@NamedQuery(name="OrdenCompra.findAll", query="SELECT o FROM OrdenCompra o")
public class OrdenCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="cedula_cliente")
	private String cedulaCliente;

	@Column(name="codigo_validacion")
	private String codigoValidacion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_otorgada")
	private Date fechaOtorgada;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private double monto;

	public OrdenCompra() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCedulaCliente() {
		return this.cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getCodigoValidacion() {
		return this.codigoValidacion;
	}

	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaOtorgada() {
		return this.fechaOtorgada;
	}

	public void setFechaOtorgada(Date fechaOtorgada) {
		this.fechaOtorgada = fechaOtorgada;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

}