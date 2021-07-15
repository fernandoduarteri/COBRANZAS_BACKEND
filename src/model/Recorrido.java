package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the recorrido database table.
 * 
 */
@Entity
@Table(name="recorrido")
@NamedQuery(name="Recorrido.findAll", query="SELECT r FROM Recorrido r")
public class Recorrido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String dia;

	private BigInteger usuario;

	private String zona;

	public Recorrido() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public BigInteger getUsuario() {
		return this.usuario;
	}

	public void setUsuario(BigInteger usuario) {
		this.usuario = usuario;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}