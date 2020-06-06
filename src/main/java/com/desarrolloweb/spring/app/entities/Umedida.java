package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * The persistent class for the umedida database table.
 * 
 */
@Entity
@NamedQuery(name="Umedida.findAll", query="SELECT u FROM Umedida u")
public class Umedida extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_uniad")
	private Long idUniad;

	private String descripcion;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="umedida", fetch=FetchType.EAGER)
	private List<Inventario> inventarios;

	public Umedida() {
	}



	public Long getIdUniad() {
		return idUniad;
	}



	public void setIdUniad(Long idUniad) {
		this.idUniad = idUniad;
	}



	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Inventario addInventario(Inventario inventario) {
		getInventarios().add(inventario);
		inventario.setUmedida(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setUmedida(null);

		return inventario;
	}

}