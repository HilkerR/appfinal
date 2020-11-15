package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the reservaciones database table.
 * 
 */
@Entity
@Table(name="reservaciones")
@NamedQuery(name="Reservacione.findAll", query="SELECT r FROM Reservacione r")
public class Reservacione extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reservaciones")
	private Long idReservaciones;

	private Long cantpersonas;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	private Long hora;

	private String notas;

	//bi-directional many-to-one association to Bebida
	@ManyToOne
	@JoinColumn(name="id_bebida")
	private Bebida bebida;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Platillo
	@ManyToOne
	@JoinColumn(name="id_plato")
	private Platillo platillo;

	//bi-directional many-to-one association to Postre
	@ManyToOne
	@JoinColumn(name="id_postre")
	private Postre postre;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private Tipo tipo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Reservacione() {
	}

	
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Long getIdReservaciones() {
		return idReservaciones;
	}


	public void setIdReservaciones(Long idReservaciones) {
		this.idReservaciones = idReservaciones;
	}


	public Long getCantpersonas() {
		return cantpersonas;
	}


	public void setCantpersonas(Long cantpersonas) {
		this.cantpersonas = cantpersonas;
	}


	public Long getHora() {
		return hora;
	}


	public void setHora(Long hora) {
		this.hora = hora;
	}


	public String getNotas() {
		return this.notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Bebida getBebida() {
		return this.bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Platillo getPlatillo() {
		return this.platillo;
	}

	public void setPlatillo(Platillo platillo) {
		this.platillo = platillo;
	}

	public Postre getPostre() {
		return this.postre;
	}

	public void setPostre(Postre postre) {
		this.postre = postre;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}