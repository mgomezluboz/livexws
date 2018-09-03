package model;

import org.springframework.data.annotation.Id;

public class Banda {

    @Id
    public String id;
    private String nombre;

    public Banda() {}
    public Banda(String n) {
        nombre = n;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        nombre = n;
    }

}