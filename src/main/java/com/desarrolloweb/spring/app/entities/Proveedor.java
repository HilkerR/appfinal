package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor")
	private Long idProveedor;

	private String descripcion;

	private String encargado;

	private String nombre;

	private String notas;

	private Long telefono;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="proveedor", fetch=FetchType.EAGER)
	private List<Inventario> inventarios;

	public Proveedor() {
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEncargado() {
		return this.encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNotas() {
		return this.notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Inventario addInventario(Inventario inventario) {
		getInventarios().add(inventario);
		inventario.setProveedor(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setProveedor(null);

		return inventario;
	}

}