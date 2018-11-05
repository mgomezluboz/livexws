package model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;
import org.springframework.data.annotation.Id;

public class Publicacion {

    @Id
    private String id;

    public String desc;
    public Date created = new Date();
    public String userId;
    public String eventoId;

    private Boolean isMine = false;
    private String username = "";
    private String espectaculoName = "";
    private Boolean admin = false;

    @ContentId
    private String contentId;
    @ContentLength
    private long contentLength;
    @MimeType
    private String mimeType = "text/plain";

    public Publicacion() {

    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @return the contentId
     */
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
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the eventoId
     */
    public String getEventoId() {
        return eventoId;
    }
    /**
     * @param eventoId the eventoId to set
     */
    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
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
    @JsonProperty("username")
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
    @JsonProperty("espectaculoName")
    public String getEspectaculoName() {
        return espectaculoName;
    }
    /**
     * @param espectaculoName the espectaculoName to set
     */
    public void setEspectaculoName(String espectaculoName) {
        this.espectaculoName = espectaculoName;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    /**
     * @return the admin
     */
    public Boolean getAdmin() {
        return admin;
    }

}