package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Usuario implements UserDetails {
	
	
	@Id
	private String id;
	private String username;
	private String password;
	private Rol rol;
	private Date ultimaSesion;
	private Integer friendStatus = 0;
	
	@DBRef(lazy = true)
	@JsonIgnore
	private List<Publicacion> publicaciones;

	@DBRef(lazy = true)
	@JsonIgnore
	private List<Usuario> amigos;

	@DBRef(lazy = true)
	@JsonIgnore
	private List<Usuario> amigosPendientes;

	@DBRef(lazy = true)
	@JsonIgnore
	private List<Usuario> amigosSolicitados;

	@JsonIgnore
	private Position posicion;

	@JsonIgnore
	private List<Espectaculo> espectaculosAsistidos;

	@ContentId @JsonIgnore
    private String contentId;
    @ContentLength @JsonIgnore
    private long contentLength;
    @MimeType @JsonIgnore
    private String mimeType = "text/plain";

	private static final long serialVersionUID = -3861374349926843566L;

	//CONSTRUCTOR
	public Usuario(String unUser, String unaPass) {
		username = unUser;

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		password = encoder.encode(unaPass);

		amigos = new ArrayList<Usuario>();
		amigosPendientes = new ArrayList<Usuario>();
		amigosSolicitados = new ArrayList<Usuario>();
		espectaculosAsistidos = new ArrayList<Espectaculo>();
	}

	public Usuario(String name) {
		username = name;
		amigos = new ArrayList<Usuario>();
		amigosPendientes = new ArrayList<Usuario>();
		amigosSolicitados = new ArrayList<Usuario>();
		espectaculosAsistidos = new ArrayList<Espectaculo>();
	}
	
	public Usuario() {
		super();
		amigos = new ArrayList<Usuario>();
		amigosPendientes = new ArrayList<Usuario>();
		amigosSolicitados = new ArrayList<Usuario>();
		espectaculosAsistidos = new ArrayList<Espectaculo>();
	}

	public void addPublicacion(Publicacion s) {
		if (null == this.publicaciones) {
			this.publicaciones = new ArrayList<>();
		}
		publicaciones.add(s);
	}

	/**
	 * @return the publicaciones
	 */
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	/**
	 * @param publicaciones the publicaciones to set
	 */
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	//GETTERS and SETTERS
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(password);
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public Date getUltimaSesion() {
		return ultimaSesion;
	}

	public void setUltimaSesion(Date ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}
	

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auths = 
				new HashSet<GrantedAuthority>(1);
		if (rol.getName().equals("Administrador")) {
			auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return auths;
	}

	public boolean sosAdmin() {
		if (rol.getName().equals("Administrador")) {
			return true;
		}
		
		return false;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the amigos
	 */
	public List<Usuario> getAmigos() {
		if (null == this.amigos) {
			this.amigos = new ArrayList<>();
		}
		return amigos;
	}
	/**
	 * @param amigos the amigos to set
	 */
	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}
	public void addAmigos(Usuario a) {
		this.amigos.add(a);
	}

	public void removeAmigo(Usuario a) {
		this.amigos.remove(a);
	}

	public List<Usuario> getAmigosPendientes() {
		if (null == this.amigosPendientes) {
			this.amigosPendientes = new ArrayList<>();
		}
		return amigosPendientes;
	}
	/**
	 * @param amigos the amigos to set
	 */
	public void setAmigosPendientes(List<Usuario> amigos) {
		this.amigosPendientes = amigos;
	}
	public void addAmigosPendientes(Usuario a) {
		this.amigosPendientes.add(a);
	}

	public void removeAmigoPendientes(Usuario a) {
		this.amigosPendientes.remove(a);
	}

	public List<Usuario> getAmigosSolicitados() {
		if (null == this.amigosSolicitados) {
			this.amigos = new ArrayList<>();
		}
		return amigosSolicitados;
	}
	/**
	 * @param amigos the amigos to set
	 */
	public void setAmigosSolicitados(List<Usuario> amigos) {
		this.amigosSolicitados = amigos;
	}
	public void addAmigosSolicitados(Usuario a) {
		this.amigosSolicitados.add(a);
	}

	public void removeAmigoSolicitados(Usuario a) {
		this.amigosSolicitados.remove(a);
	}

	/**
	 * @return the posicion
	 */
	public Position getPosicion() {
		return posicion;
	}
	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Position posicion) {
		this.posicion = posicion;
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
	
	/**
	 * @param espectaculosAsistidos the espectaculosAsistidos to set
	 */
	public void setEspectaculosAsistidos(List<Espectaculo> espectaculosAsistidos) {
		this.espectaculosAsistidos = espectaculosAsistidos;
	}
	/**
	 * @return the espectaculosAsistidos
	 */
	public List<Espectaculo> getEspectaculosAsistidos() {
		if (null == this.espectaculosAsistidos) {
			this.espectaculosAsistidos = new ArrayList<>();
		}
		return espectaculosAsistidos;
	}
	public void addEspectaculoAsistido(Espectaculo espec) {
		this.espectaculosAsistidos.add(espec);
	}
	/**
	 * @param friendStatus the friendStatus to set
	 */
	public void setFriendStatus(Integer friendStatus) {
		this.friendStatus = friendStatus;
	}
	/**
	 * @return the friendStatus
	 */
	public Integer getFriendStatus() {
		return friendStatus;
	}

	@Override
	public boolean equals(Object o) {
		if(this.getId().equals(((Usuario)o).getId())) {
			return true;
		}

		return false;
	}
}
