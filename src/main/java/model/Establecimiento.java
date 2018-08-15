package model;


import org.springframework.data.annotation.Id;

public class Establecimiento {

    @Id
    public String id;
    private String nombre;
    private String direccion;
    private Integer capacidad;
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the capacidad
	 */
	public Integer getCapacidad() {
		return capacidad;
	}
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return this.id;
	}
}