package model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Espectaculo {

	@Id
	public String id;
	private String nombre;
	private String fecha;
	private List<String> artistas;


	public Espectaculo(String unNombre, String unaFecha, List<String> listaArtistas) {
		nombre = unNombre;
		fecha = unaFecha;
		artistas = listaArtistas;
	}
	
	public Espectaculo() {
		
	}

	
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public List<String> getArtistas() {
		return artistas;
	}

	public void setId(String unId) {
		id = unId;
	}
	
	public void setNombre(String n) {
		nombre = n;
	}
	
	public void setFecha(String f) {
		fecha = f;
	}
	
	public void addArtista(String a) {
		artistas.add(a);
	}
	
	public void setArtistas(List<String> a) {
		artistas = a;
	}
}
