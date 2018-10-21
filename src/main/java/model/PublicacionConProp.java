package model;

public class PublicacionConProp extends Publicacion {

    private Boolean isMine = false;
    private String username = "";
    private String espectaculoName = "";

    public PublicacionConProp() {
        super();
    }

    /**
     * @return the isMine
     */
    public Boolean getIsMine() {
        return isMine;
    }
    /**
     * @param isMine the isMine to set
     */
    public void setIsMine(Boolean isMine) {
        this.isMine = isMine;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the espectaculoName
     */
    public String getEspectaculoName() {
        return espectaculoName;
    }
    /**
     * @param espectaculoName the espectaculoName to set
     */
    public void setEspectaculoName(String espectaculoName) {
        this.espectaculoName = espectaculoName;
    }

}