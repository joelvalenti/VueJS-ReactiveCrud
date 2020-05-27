package com.utn.demo.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Instrumento implements Serializable {
	
	private static final Long SerialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String instrumento;
	
	private String marca;
	
	private String modelo;
	
	private double precio;
	
	private String costoEnvio;
	
	private int cantidadVendida;
	
	private String imagen;
	
	private String descripcion;
	
	public Instrumento() {}

	public Instrumento(long id, String instrumento, String marca, String modelo, double precio, String costoEnvio,
			int cantidadVendida, String imagen, String descripcion) {
		this.id = id;
		this.instrumento = instrumento;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.costoEnvio = costoEnvio;
		this.cantidadVendida = cantidadVendida;
		this.imagen = imagen;
		this.descripcion = descripcion;
	}
	
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(String costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
