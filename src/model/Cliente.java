package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
@NamedStoredProcedureQuery(
	    name = "situacion_cliente", 
	    procedureName = "situacion_cliente", 
	    parameters = { 
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigInteger.class, name = "var_id"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "var_cedula"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = BigInteger.class, name = "var_limite_credito"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_total"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_saldo_vencido"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_saldo_no_vencido"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "var_estado"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_entrega_minima"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_disponible_potencial_en_orden"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_servicio_diario_total"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_linea_directa_permitida"), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "var_monto_ord_pendientes")
	    }
	)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private boolean activo;

	@Lob
	private String apellidos;

	private String cedula;

	@Column(name="cedula_real")
	private String cedulaReal;

	@Lob
	private String ciudad;

	@Lob
	private String direccion;

	@Column(name="ld_permitida")
	private int ldPermitida;

	@Column(name="limite_credito")
	private BigInteger limiteCredito;

	@Lob
	private String nombre;

	@Lob
	private String observaciones;

	private int orden;

	@Lob
	private String telefono;

	@Column(name="venc_permitido")
	private int vencPermitido;

	private int zona;

	//bi-directional many-to-one association to TipoCliente
	@ManyToOne
	@JoinColumn(name="tipo_cliente")
	private TipoCliente tipoClienteBean;

	public Cliente() {
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

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedulaReal() {
		return this.cedulaReal;
	}

	public void setCedulaReal(Object cedulaReal) {
		if(cedulaReal == null) {
			this.cedulaReal="";
		}
		this.cedulaReal = (String) cedulaReal;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getLdPermitida() {
		return this.ldPermitida;
	}

	public void setLdPermitida(int ldPermitida) {
		this.ldPermitida = ldPermitida;
	}

	public BigInteger getLimiteCredito() {
		return this.limiteCredito;
	}

	public void setLimiteCredito(BigInteger limiteCredito) {
		
			this.limiteCredito = limiteCredito;
		
		
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getVencPermitido() {
		return this.vencPermitido;
	}

	public void setVencPermitido(int vencPermitido) {
		this.vencPermitido = vencPermitido;
	}

	public int getZona() {
		return this.zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public TipoCliente getTipoClienteBean() {
		return this.tipoClienteBean;
	}

	public void setTipoClienteBean(TipoCliente tipoClienteBean) {
		this.tipoClienteBean = tipoClienteBean;
	}

}