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


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/publicaciones")
public class PublicacionController extends AbstractController {

    @Autowired
	private PublicacionRepository repo;

	@Autowired private UsuarioRepository userRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Publicacion> getPublicaciones() {
		logger.info("getPublicaciones()");
		return repo.findAll();
	}	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Publicacion getPublicacionById(@PathVariable("id") String id){
		logger.info("getPublicacionById()");
		return  repo.findById(id);

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addPublicacion(@RequestBody Publicacion pub) {
		logger.info("addPublicacion()");
		repo.insert(pub);

		Usuario user = userRepo.findById(pub.userId);
		user.addPublicacion(pub);
		userRepo.save(user);

		return ResponseEntity.created(URI.create("http://localhost")).body("{\"message\": \"Creada con exito.\"}");
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updatePublicacion(@RequestBody Publicacion pub) {
		logger.info("updatePublicacion()");
		if(null == repo.findById(pub.getId())) {
			return ResponseEntity.badRequest().body("{\"message\": \"La publicacion no existe.\"}");
		}
		repo.save(pub);
		return ResponseEntity.accepted().build();
		
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePublicacion(@PathVariable("id") String id) {
		logger.info("deletePublicacion()");
		repo.delete(id);
		return ResponseEntity.ok("");
	}

	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET)
	public List<Publicacion> getPublicacionByUserId(@PathVariable("id") String id){
		logger.info("getPublicacionByUserId()");
		return  repo.findByUserId(id);
	}

	@RequestMapping(value="/evento/{id}", method = RequestMethod.GET)
	public List<Publicacion> getPublicacionByEventoId(@PathVariable("id") String id){
		logger.info("getPublicacionByEventoId()");
		return  repo.findByEventoId(id);
	}

}