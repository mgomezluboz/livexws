package model;

public class Position {

    public Float longitud;
    public Float latitud;

    public Position() {

    }

    public Position(Float x, Float y) {
        longitud = x;
        latitud = y;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }
    /**
     * @return the longitud
     */
    public Float getLongitud() {
        return longitud;
    }
    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }
    /**
     * @return the latitud
     */
    public Float getLatitud() {
        return latitud;
    }

}