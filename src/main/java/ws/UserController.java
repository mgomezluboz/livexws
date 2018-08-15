package ws;

import java.net.URI;
import java.util.List;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import exceptions.UserNotFoundException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UserController extends AbstractController{
	
	@Autowired
	private UsuarioRepository repo;
	
	@RequestMapping(value="/crearDb", method = RequestMethod.POST)
	public String crearUsuarioDb() {
		Usuario martin = new Usuario("martin", "1234");
		martin.setRol(new Rol("Administrador"));
		
		repo.save(martin);
		
		return "{\"result\":\"Ok\"}";
	}
	
	// Lista de Usuarios
	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> getUsuarios() {
		logger.info("getUsuarios()");
		return repo.findAll();
	}	
	
	// Mostrar Detalle de un usuario
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Usuario getUsuarioById(@PathVariable("id") String id) throws UserNotFoundException {
		logger.info("getUsuarioById()");
		return  repo.findById(id);

	}
	
	// Crear nuevo usuario
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody Usuario user) {
		logger.info("addUser()");
		if(getUsuarios().stream().filter(u -> user.getUsername().equalsIgnoreCase(u.getUsername())).count()==0){
			user.setRol(new Rol("Usuario"));
			repo.insert(user);
			return ResponseEntity.created(URI.create("http://localhost")).body("El usuario " + user.getUsername() + " ha sido creado");
		}else{
			return ResponseEntity.accepted().body("El nombre de usuario ya existe");
		}
		
	}
}
