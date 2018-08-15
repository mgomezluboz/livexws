package model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Compra {

    @Id
    public String id;
    private Date fecha;
    private int importe;
    private List<Ticket> tickets;

    public Compra() {}

    public Compra(Date f, int i) {
        fecha = f;
        importe = i;
    }

    /**
	 * @return the tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}
	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
    }
    
    public void addTicket(Ticket t) {
        tickets.add(t);
    }

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @return the importe
	 */
	public int getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(int importe) {
		this.importe = importe;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}