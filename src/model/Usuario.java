package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQueries({
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
@NamedQuery(name="Usuario.getLogin", query="SELECT u FROM Usuario u WHERE u.username= :username AND u.password= :password")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private String nombre;

	@Lob
	private String password;

	@Lob
	private String telefono;

	@Lob
	private String username;

	public Usuario() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}