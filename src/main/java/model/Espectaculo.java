package model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Espectaculo {

	@Id
	public String id;
	private String nombre;
	private List<Funcion> funciones;
	@DBRef
	private Establecimiento establecimiento;
	private List<Comercio> comercios;

	@ContentId
    private String contentId;
    @ContentLength
    private long contentLength;
    @MimeType
    private String mimeType = "text/plain";

	public Espectaculo(String unNombre) {
		nombre = unNombre;
	}
	
	public Espectaculo() {
		
	}

	
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(String unId) {
		id = unId;
	}
	
	public void setNombre(String n) {
		nombre = n;
	}

	public List<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<Funcion> lista) {
		funciones = lista;
	}

	public void addFuncion(Funcion f) {
		funciones.add(f);
	}

	public void setEstablecimiento(Establecimiento e) {
		this.establecimiento = e;
	}

	public Establecimiento getEstablecimiento() {
		return this.establecimiento;
	}

	/**
	 * @return the comercios
	 */
	public List<Comercio> getComercios() {
		return comercios;
	}
	/**
	 * @param comercios the comercios to set
	 */
	public void setComercios(List<Comercio> comercios) {
		this.comercios = comercios;
	}
	
	public void addComercio(Comercio c) {
		this.comercios.add(c);
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

	public String getContentId() {
        return contentId;
    }
    /**
     * @param contentId the contentId to set
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the contentLength
     */
    public long getContentLength() {
        return contentLength;
    }
    /**
     * @param contentLength the contentLength to set
     */
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
	}
	 /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
