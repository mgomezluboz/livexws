package model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Staff {

	@Id
    public String id;
    private String nombre;
    private Double dni;
    private List<Espectaculo> espectaculos;

    public Staff() {

    }

    public Staff(String n, Double d) {
        nombre = n;
        dni = d;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getDni() {
        return dni;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setDni(Double h) {
        dni = h;
    }

    public List<Espectaculo> getEspectaculos() {
        return espectaculos;
    }

    public void setEspectaculos(List<Espectaculo> lista) {
        espectaculos = lista;
    }

    public void addEspectaculo(Espectaculo e) {
        espectaculos.add(e);
    }

}