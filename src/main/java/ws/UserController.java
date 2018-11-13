package ws;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import exceptions.UserNotFoundException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UserController extends AbstractController{
	
	@Autowired
	private UsuarioRepository repo;

	@Autowired private UsuarioStore contentStore;

	@Autowired private EspectaculoRepository repoEspectaculos;
	@Autowired private PublicacionController pubController;
	
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
		Usuario result = repo.findById(id);
		if (null == result) {
			throw new UserNotFoundException("Usuario no encontrado.");
		}
		return result;
 
	}

	@RequestMapping(value="/{id}/friends", method = RequestMethod.GET)
	public List<Usuario> getUserFriendList(@PathVariable("id") String id) {
		List<Usuario> friendlist = repo.findById(id).getAmigos();
		if (null == friendlist) {
			return new ArrayList<>();
		}
		return friendlist;
	}

	@RequestMapping(value="/{id}/friends/{fid}", method = RequestMethod.POST)
	public ResponseEntity<?> addUserToFriendList(@PathVariable("id") String id, @PathVariable("fid") String fid) {
		Usuario u = repo.findById(id);
		Usuario f = repo.findById(fid);

		if(null == u.getAmigos()) {
			u.setAmigos(new ArrayList<Usuario>());
		}

		if(null == f.getAmigos()) {
			f.setAmigos(new ArrayList<Usuario>());
		}

		if (u.getAmigosPendientes().contains(f)) {
			u.addAmigos(f);
			f.addAmigos(u);
			u.removeAmigoPendientes(f);
			f.removeAmigoSolicitados(u);
			repo.save(u);
			repo.save(f);
		} else if(u.getAmigos().contains(f)) {
			//ya son amigos
		} else if(u.getAmigosSolicitados().contains(f)) {
			//ya esta en la lista de solicitados
		} else {
			f.addAmigosPendientes(u);
			u.addAmigosSolicitados(f);
			repo.save(u);
			repo.save(f);
		}
		return ResponseEntity.ok("");
	}

	@RequestMapping(value="/{id}/friends/{fid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserFromFriendList(@PathVariable("id") String id, @PathVariable("fid") String fid) {
		Usuario u = repo.findById(id);
		Usuario f = repo.findById(fid);
		u.removeAmigo(f);
		f.removeAmigo(u);
		repo.save(u);
		repo.save(f);
		return ResponseEntity.ok("");
	}

	@RequestMapping(value="/{id}/posts", method = RequestMethod.GET)
	public List<Publicacion> getUserPosts(@PathVariable("id") String id) {
		return repo.findById(id).getPublicaciones();
	}

	@RequestMapping(value="/{id}/feed", method = RequestMethod.GET)
	public List<Publicacion> getFeed(@PathVariable("id") String id) {

		Usuario user = repo.findById(id);
		List<Publicacion> allPubs = pubController.getPublicaciones();
		Collections.reverse(allPubs);
		List<Publicacion> result = new ArrayList<>();
		List<Usuario> friendList = user.getAmigos();
		friendList.add(user);
		List<String> friendIdList = new ArrayList<>();

		for (Usuario u : friendList) {
			friendIdList.add(u.getId());
		}

		for(Publicacion p : allPubs) {
			if(p.getAdmin() || friendIdList.contains(p.getUserId())) {
				result.add(p);
			}
		}

		Collections.reverse(result);

		return result;
	}
	
	// Crear nuevo usuario
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody Usuario user) {
		logger.info("addUser()");
		if(repo.findByUsername(user.getUsername()) != null) {
			return ResponseEntity.badRequest().body("{\"message\": \"Nombre de usuario en uso.\"}");
		}
		repo.insert(user);
		return ResponseEntity.created(URI.create("http://localhost")).body("{\"message\": \"El usuario " + user.getUsername() + " ha sido creado\"}");
	}

	// Crear nuevo usuario
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody Usuario user) {
		logger.info("registrarUser()");
		if(repo.findByUsername(user.getUsername()) != null) {
			return ResponseEntity.badRequest().body("{\"message\": \"Nombre de usuario en uso.\"}");
		}
		Rol rol = new Rol("Usuario");
		user.setRol(rol);
		repo.insert(user);
		return ResponseEntity.created(URI.create("http://localhost")).body("{\"message\": \"El usuario " + user.getUsername() + " ha sido creado\"}");
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody Usuario user) {
		logger.info("updateUser()");
		if(null == repo.findById(user.getId())) {
			return ResponseEntity.badRequest().body("{\"message\": \"El usuario indicado no existe.\"}");
		}
		repo.save(user);
		return ResponseEntity.accepted().build();
		
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		logger.info("deleteUser()");
		repo.delete(id);
		return ResponseEntity.ok("");
	}

	@RequestMapping(value="/{id}/posicion", method = RequestMethod.GET)
	public Position getUserPosition(@PathVariable("id") String id) {
		return  repo.findById(id).getPosicion();
	}

	@RequestMapping(value="/{id}/posicion", method = RequestMethod.PUT)
	public ResponseEntity<?> setUserPosition(@PathVariable("id") String id, @RequestBody Position nuevaPosicion) {
		Usuario user = repo.findById(id);
		user.setPosicion(nuevaPosicion);
		repo.save(user);
		return ResponseEntity.ok("");
	}

	@RequestMapping(value="/buscar", method = RequestMethod.GET)
	public List<Usuario> findUserByName(@RequestParam("username") String username) {
		List<Usuario> result = repo.usernameStartsWith(username);
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = repo.findByUsername(userDetails.getUsername());

		logger.info("El usuario que busca es: " + user.getUsername());

		List<Usuario> amigosPendientes = user.getAmigosPendientes();
		List<Usuario> amigosSolicitados = user.getAmigosSolicitados();
		List<Usuario> amigos = user.getAmigos();

		for (Usuario u : result) {
			if(amigos.contains(u)) {
				u.setFriendStatus(1);
				logger.info("setteo friend status en 1");
			} else if (amigosPendientes.contains(u)) {
				u.setFriendStatus(2);
				logger.info("setteo friend status en 2");
			} else if (amigosSolicitados.contains(u)) {
				u.setFriendStatus(3);
				logger.info("setteo friend status en 3");
			} else {
				u.setFriendStatus(0);
				logger.info("setteo friend status en 0");
			}
		}

		return result;
	}

	@RequestMapping(value="/roles", method = RequestMethod.GET)
	public List<Rol> getRoles() {
		logger.info("getRoles()");
		List<Rol> result = new ArrayList<Rol>();
		Rol admin = new Rol("Administrador");
		Rol user = new Rol("Usuario");
		result.add(admin);
		result.add(user);
		return(result);
	}

	@RequestMapping(value="/{id}/profilePicture", method = RequestMethod.GET)
	public ResponseEntity<?> getContent(@PathVariable("id") String id) {

        Usuario f = repo.findById(id);
        if (null != f) {
            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.getContentLength());
            headers.set("Content-Type", f.getMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
	}
	
	@RequestMapping(value="/{id}/profilePicture", method = RequestMethod.PUT)
    public ResponseEntity<?> setContent(@PathVariable("id") String id, @RequestParam("file") MultipartFile file)
            throws IOException {

				Usuario f = repo.findById(id);
        if (null != f) {
            f.setMimeType(file.getContentType());

            contentStore.setContent(f, file.getInputStream());

            // save updated content-related info
            repo.save(f);

            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return null;
	}
	
	@RequestMapping(value="/{id}/espectaculos", method = RequestMethod.GET)
	public List<Espectaculo> getEspectaculosAsistidos(@PathVariable("id") String id) {
		return repo.findById(id).getEspectaculosAsistidos();
	}

	@RequestMapping(value="/{id}/espectaculos/{especId}", method = RequestMethod.POST)
	public ResponseEntity<?> addEspectaculoAsistido(@PathVariable("id") String id, @PathVariable("especId") String especId) {
		Usuario user = repo.findById(id);
		Espectaculo espec = repoEspectaculos.findById(especId);

		user.addEspectaculoAsistido(espec);
		repo.save(user);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
