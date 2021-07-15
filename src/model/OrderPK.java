package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the orders database table.
 * 
 */
@Embeddable
public class OrderPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String glnCliente;

	private String numeroOrden;

	public OrderPK() {
	}

	public OrderPK(String glnCliente, String numeroOrden) {
		this.glnCliente = glnCliente;
		this.numeroOrden = numeroOrden;
	}

	public String getGlnCliente() {
		return this.glnCliente;
	}

	public void setGlnCliente(String glnCliente) {
		this.glnCliente = glnCliente;
	}

	public String getNumeroOrden() {
		return this.numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderPK)) {
			return false;
		}
		OrderPK castOther = (OrderPK) other;
		return this.glnCliente.equals(castOther.glnCliente) && this.numeroOrden.equals(castOther.numeroOrden);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.glnCliente.hashCode();
		hash = hash * prime + this.numeroOrden.hashCode();

		return hash;
	}
}