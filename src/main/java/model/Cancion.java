package model;

import org.springframework.data.annotation.Id;

public class Cancion {

    @Id
    public String id;
    private String nombre;
    private int duracion;

    public Cancion() {}

    public Cancion(String n, int d) {
        nombre = n;
        duracion = d;
    }

    public String getNombre(){
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setDuracion(int d) {
        duracion = d;
    }

}