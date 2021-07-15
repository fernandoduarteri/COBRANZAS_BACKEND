package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the prestamo database table.
 * 
 */
@Entity
@Table(name="prestamo")
@NamedQuery(name="Prestamo.findAll", query="SELECT p FROM Prestamo p")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private java.math.BigInteger cliente;

	private int cuotas;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private double monto;

	@Column(name="monto_total")
	private double montoTotal;

	@Column(name="mora_aplicada")
	private byte moraAplicada;

	@Column(name="numero_dias")
	private int numeroDias;

	@Lob
	private String observaciones;

	private boolean pagado;

	@Column(name="porcentaje_interes")
	private double porcentajeInteres;

	@Column(name="porcentaje_mora")
	private int porcentajeMora;

	private java.math.BigInteger propietario;

	@Column(name="tipo_prestamo")
	private java.math.BigInteger tipoPrestamo;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="prestamoBean")
	private List<Cuota> cuotasSet;

	public Prestamo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.math.BigInteger getCliente() {
		return this.cliente;
	}

	public void setCliente(java.math.BigInteger cliente) {
		this.cliente = cliente;
	}

	public int getCuotas() {
		return this.cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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

	public double getMontoTotal() {
		return this.montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public byte getMoraAplicada() {
		return this.moraAplicada;
	}

	public void setMoraAplicada(byte moraAplicada) {
		this.moraAplicada = moraAplicada;
	}

	public int getNumeroDias() {
		return this.numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean getPagado() {
		return this.pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public double getPorcentajeInteres() {
		return this.porcentajeInteres;
	}

	public void setPorcentajeInteres(double porcentajeInteres) {
		this.porcentajeInteres = porcentajeInteres;
	}

	public int getPorcentajeMora() {
		return this.porcentajeMora;
	}

	public void setPorcentajeMora(int porcentajeMora) {
		this.porcentajeMora = porcentajeMora;
	}

	public java.math.BigInteger getPropietario() {
		return this.propietario;
	}

	public void setPropietario(java.math.BigInteger propietario) {
		this.propietario = propietario;
	}

	public java.math.BigInteger getTipoPrestamo() {
		return this.tipoPrestamo;
	}

	public void setTipoPrestamo(java.math.BigInteger tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	public List<Cuota> getCuotasSet() {
		return this.cuotasSet;
	}

	public void setCuotasSet(List<Cuota> cuotasSet) {
		this.cuotasSet = cuotasSet;
	}

	public Cuota addCuotasSet(Cuota cuotasSet) {
		getCuotasSet().add(cuotasSet);
		cuotasSet.setPrestamoBean(this);

		return cuotasSet;
	}

	public Cuota removeCuotasSet(Cuota cuotasSet) {
		getCuotasSet().remove(cuotasSet);
		cuotasSet.setPrestamoBean(null);

		return cuotasSet;
	}

}