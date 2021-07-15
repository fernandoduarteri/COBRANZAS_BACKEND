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
@NamedQuery(name="TipoCliente1.findAll", query="SELECT t FROM TipoCliente1 t")
public class TipoCliente1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private boolean activo;

	@Lob
	private String denominacion;

	//bi-directional many-to-one association to Cliente1
	@OneToMany(mappedBy="tipoClienteBean")
	private List<Cliente1> clientes;

	public TipoCliente1() {
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

	public List<Cliente1> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente1> clientes) {
		this.clientes = clientes;
	}

	public Cliente1 addCliente(Cliente1 cliente) {
		getClientes().add(cliente);
		cliente.setTipoClienteBean(this);

		return cliente;
	}

	public Cliente1 removeCliente(Cliente1 cliente) {
		getClientes().remove(cliente);
		cliente.setTipoClienteBean(null);

		return cliente;
	}

}