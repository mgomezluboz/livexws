package model;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Funcion {

	@Id
    public String id;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date fecha;
    private String artista;
    private String setList;

    private String imagenRef = "";

    public Funcion() {

    }

    public Funcion(Date f, String a) {
        fecha = f;
        artista = a;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getArtista() {
        return artista;
    }

    public void setFecha(Date f) {
        fecha = f;
    }

    public void setArtista(String a) {
        artista = a;
    }

    /**
     * @param setList the setList to set
     */
    public void setSetList(String setList) {
        this.setList = setList;
    }

    /**
     * @return the setList
     */
    public String getSetList() {
        return setList;
    }

    /**
     * @return the imagenRef
     */
    public String getImagenRef() {
        return imagenRef;
    }
    /**
     * @param imagenRef the imagenRef to set
     */
    public void setImagenRef(String imagenRef) {
        this.imagenRef = imagenRef;
    }

    @Override
	public String toString() {
		String result = "";
		try {
			result = (new ObjectMapper().writeValueAsString(this));
		} catch(Exception e) {

		}
		return result;
	}

}