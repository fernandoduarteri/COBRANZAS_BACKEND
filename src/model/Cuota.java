package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuota database table.
 * 
 */
@Entity
@Table(name="cuota")
@NamedQuery(name="Cuota.findAll", query="SELECT c FROM Cuota c")
public class Cuota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name="monto_cuota")
	private double montoCuota;

	private int numero;

	private boolean pagada;

	//bi-directional many-to-one association to Prestamo
	@ManyToOne
	@JoinColumn(name="prestamo")
	
	private Prestamo prestamoBean;
	
	//bi-directional many-to-one association to Pago
	@OneToMany(mappedBy="cuotaBean")
	private List<Pago> pagos;

	public Cuota() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public double getMontoCuota() {
		return this.montoCuota;
	}

	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean getPagada() {
		return this.pagada;
	}

	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public Prestamo getPrestamoBean() {
		return this.prestamoBean;
	}

	public void setPrestamoBean(Prestamo prestamoBean) {
		this.prestamoBean = prestamoBean;
	}

	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setCuotaBean(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setCuotaBean(null);

		return pago;
	}

}