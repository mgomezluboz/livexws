package model;

public class EspectaculoAtendido extends Espectaculo {

    public Boolean hasBeenTo;

    public EspectaculoAtendido() {
        super();
    }

    /**
     * @return the hasBeenTo
     */
    public Boolean getHasBeenTo() {
        return hasBeenTo;
    }
    /**
     * @param hasBeenTo the hasBeenTo to set
     */
    public void setHasBeenTo(Boolean hasBeenTo) {
        this.hasBeenTo = hasBeenTo;
    }

}