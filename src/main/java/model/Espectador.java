package model;

import org.springframework.data.annotation.Id;

public class Espectador {

    @Id
    public String id;
    private String nombre;
    private Double dni;
    private Compra compra;

    public Espectador() {}

    public Espectador(String n, Double d, Compra c) {
        nombre = n;
        dni = d;
        compra = c;
    }
    
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the compra
	 */
	public Compra getCompra() {
		return compra;
	}
	/**
	 * @param compra the compra to set
	 */
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	/**
	 * @return the dni
	 */
	public Double getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(Double dni) {
		this.dni = dni;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}