package model;

import org.springframework.data.annotation.Id;

public class ItemComercio {

    @Id
    private String id;
    public String nombre;
    public Float precio;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the precio
     */
    public Float getPrecio() {
        return precio;
    }
    /**
     * @param precio the precio to set
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}