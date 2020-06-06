package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * The persistent class for the mesas database table.
 * 
 */
@Entity
@Table(name="mesas")
@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
public class Mesa extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mesa")
	private Long idMesa;

	private String nota;

	private Long numero;

	private Long sillas;

	public Long getIdMesa() {
		return idMesa;
	}


	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}


	public Long getNumero() {
		return numero;
	}


	public void setNumero(Long numero) {
		this.numero = numero;
	}


	public Long getSillas() {
		return sillas;
	}


	public void setSillas(Long sillas) {
		this.sillas = sillas;
	}

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="mesa", fetch=FetchType.EAGER)
	private List<Orden> ordens;

	public Mesa() {
	}


	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setMesa(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setMesa(null);

		return orden;
	}

}