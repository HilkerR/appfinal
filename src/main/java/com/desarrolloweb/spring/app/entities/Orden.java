package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orden database table.
 * 
 */
@Entity
@NamedQuery(name="Orden.findAll", query="SELECT o FROM Orden o")
public class Orden extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_orden")
	private Long idOrden;

	@Column(name="cantidad_bebida")
	private Long cantidadBebida;

	@Column(name="cantidad_plato")
	private Long cantidadPlato;

	@Column(name="cantidad_postre")
	private Long cantidadPostre;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String hora;

	private String notas;

	@Column(name="orden_activa")
	private Boolean ordenActiva;

	@Column(name="orden_entregada")
	private Boolean ordenEntregada;

	@Column(name="orden_pagada")
	private Boolean ordenPagada;

	@Column(name="orden_preparada")
	private Boolean ordenPreparada;

	@Column(name="total_bebida")
	private double totalBebida;

	@Column(name="total_orden")
	private double totalOrden;

	@Column(name="total_plato")
	private double totalPlato;

	@Column(name="total_postre")
	private double totalPostre;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="orden", fetch=FetchType.EAGER)
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="orden", fetch=FetchType.EAGER)
	private List<Factura> facturas;

	//bi-directional many-to-one association to Bebida
	@ManyToOne
	@JoinColumn(name="id_bebida")
	private Bebida bebida;

	//bi-directional many-to-one association to Mesa
	@ManyToOne
	@JoinColumn(name="id_mesa")
	private Mesa mesa;

	//bi-directional many-to-one association to Platillo
	@ManyToOne
	@JoinColumn(name="id_plato")
	private Platillo platillo;

	//bi-directional many-to-one association to Postre
	@ManyToOne
	@JoinColumn(name="id_postre")
	private Postre postre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Orden() {
	}

	

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Long getIdOrden() {
		return idOrden;
	}



	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}



	public Long getCantidadBebida() {
		return cantidadBebida;
	}



	public void setCantidadBebida(Long cantidadBebida) {
		this.cantidadBebida = cantidadBebida;
	}



	public Long getCantidadPlato() {
		return cantidadPlato;
	}



	public void setCantidadPlato(Long cantidadPlato) {
		this.cantidadPlato = cantidadPlato;
	}



	public Long getCantidadPostre() {
		return cantidadPostre;
	}



	public void setCantidadPostre(Long cantidadPostre) {
		this.cantidadPostre = cantidadPostre;
	}



	public String getHora() {
		return hora;
	}



	public void setHora(String hora) {
		this.hora = hora;
	}



	public String getNotas() {
		return this.notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Boolean getOrdenActiva() {
		return this.ordenActiva;
	}

	public void setOrdenActiva(Boolean ordenActiva) {
		this.ordenActiva = ordenActiva;
	}

	public Boolean getOrdenEntregada() {
		return this.ordenEntregada;
	}

	public void setOrdenEntregada(Boolean ordenEntregada) {
		this.ordenEntregada = ordenEntregada;
	}

	public Boolean getOrdenPagada() {
		return this.ordenPagada;
	}

	public void setOrdenPagada(Boolean ordenPagada) {
		this.ordenPagada = ordenPagada;
	}

	public Boolean getOrdenPreparada() {
		return this.ordenPreparada;
	}

	public void setOrdenPreparada(Boolean ordenPreparada) {
		this.ordenPreparada = ordenPreparada;
	}

	public double getTotalBebida() {
		return this.totalBebida;
	}

	public void setTotalBebida(double totalBebida) {
		this.totalBebida = totalBebida;
	}

	public double getTotalOrden() {
		return this.totalOrden;
	}

	public void setTotalOrden(double totalOrden) {
		this.totalOrden = totalOrden;
	}

	public double getTotalPlato() {
		return this.totalPlato;
	}

	public void setTotalPlato(double totalPlato) {
		this.totalPlato = totalPlato;
	}

	public double getTotalPostre() {
		return this.totalPostre;
	}

	public void setTotalPostre(double totalPostre) {
		this.totalPostre = totalPostre;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setOrden(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setOrden(null);

		return cliente;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setOrden(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setOrden(null);

		return factura;
	}

	public Bebida getBebida() {
		return this.bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Mesa getMesa() {
		return this.mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}