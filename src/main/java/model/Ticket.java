package model;

import org.springframework.data.annotation.Id;

public class Ticket {

    @Id
    public String id;
    private int numOperacion;
    private int ancho;
    private int largo;
    private Funcion funcion;

    public Ticket() {}

    public Ticket(int n, int a, int l, Funcion f) {
        setNumOperacion(n);
        setAncho(a);
        setLargo(l);
        setFuncion(f);
    }

    /**
	 * @return the funcion
	 */
	public Funcion getFuncion() {
		return funcion;
	}

	/**
	 * @param funcion the funcion to set
	 */
	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the largo
	 */
	public int getLargo() {
		return largo;
	}

	/**
	 * @param largo the largo to set
	 */
	public void setLargo(int largo) {
		this.largo = largo;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the numOperacion
	 */
	public int getNumOperacion() {
		return numOperacion;
	}

	/**
	 * @param numOperacion the numOperacion to set
	 */
	public void setNumOperacion(int numOperacion) {
		this.numOperacion = numOperacion;
	}

}