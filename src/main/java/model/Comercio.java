package model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Comercio {

    @Id
    private String id;
    public String nombre;
    public List<ItemComercio> productos;
    public Position posicion;

    public Comercio() {}

    public Comercio(String n) {
        nombre = n;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the productos
     */
    public List<ItemComercio> getProductos() {
        return productos;
    }

    public void addProducto(ItemComercio p) {
        productos.add(p);
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<ItemComercio> productos) {
        this.productos = productos;
    }

    /**
     * @return the posicion
     */
    public Position getPosicion() {
        return posicion;
    }
    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Position posicion) {
        this.posicion = posicion;
    }
}