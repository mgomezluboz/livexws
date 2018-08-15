package model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Banda {

    @Id
    public String id;
    private String nombre;
    private List<Cancion> canciones;

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

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> lista){
        canciones = lista;
    }

    public void addCancion(Cancion c) {
        canciones.add(c);
    }

    public void removeCancion(Cancion c) {
        canciones.remove(c);
    }

}