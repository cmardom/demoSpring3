package org.iesvegademijas.tienda_informatica.modelo;

public class Fabricante {

	private int id;
	private String nombre;
	
	public Fabricante() {
		
	}
	
	public Fabricante(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return id;
	}

	public void setCodigo(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Fabricante [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
