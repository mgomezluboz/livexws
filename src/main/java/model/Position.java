package model;

public class Position {

    public Double longitud;
    public Double latitud;

    public Position() {

    }

    public Position(Double x, Double y) {
        longitud = x;
        latitud = y;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }
    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

}