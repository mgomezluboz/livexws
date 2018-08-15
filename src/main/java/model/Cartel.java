package model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Cartel {

    @Id
    public String id;
    private Date fecha;
    private Espectaculo espectaculo;

    public Cartel() {}

	public Cartel(Date f, Espectaculo e) {
        fecha = f;
        espectaculo = e;
    }

    /**
	 * @return the espectaculo
	 */
	public Espectaculo getEspectaculo() {
		return espectaculo;
	}
	/**
	 * @param espectaculo the espectaculo to set
	 */
	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}