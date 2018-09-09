package model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
	
	@DBRef
	private List<Publicacion> publicaciones;

	private static final long serialVersionUID = -3861374349926843566L;

	//CONSTRUCTOR
	public Usuario(String unUser, String unaPass) {
		username = unUser;

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		password = encoder.encode(unaPass);
	}

	public Usuario(String name) {
		username = name;
	}
	
	public Usuario() {
		super();
	}

	public void addPublicacion(Publicacion s) {
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
}
