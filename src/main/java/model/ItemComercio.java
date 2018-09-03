package model;

import org.springframework.data.annotation.Id;

public class ItemComercio {

    @Id
    private String id;
    public String nombre;
    public Float precio;
    public Integer stock;

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
    }/**
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }
    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}