package ws;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;
import util.ControllerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	@Autowired private EspectaculoRepository especRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Publicacion> getPublicaciones(Principal principal) {
		logger.info("getPublicaciones()");

		List<Publicacion> listado =  repo.findAll();
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = userRepo.findByUsername(userDetails.getUsername());
		List<Espectaculo> espectaculos = especRepo.findAll();

		Boolean mine;

		for(Publicacion pub : listado) {
			mine = false;
			if (null != pub.getUserId() && pub.getUserId().equals(user.getId())) {
				mine = true;
			}

			pub.setUsername(userDetails.getUsername());

			Espectaculo espectaculo = null;
			for (Espectaculo e : espectaculos) {
				if(e.getId().equals(pub.getEventoId())) {
					espectaculo = e;
				}
			}

			if (null != espectaculo) {
				pub.setEspectaculoName(espectaculo.getNombre());
			} else {
				pub.setEspectaculoName("NULL event");
			}
			
			pub.setIsMine(mine);
		}

		return listado;
	}	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Publicacion getPublicacionById(@PathVariable("id") String id){
		logger.info("getPublicacionById()");
		return  repo.findById(id);

	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> addPublicacion(@RequestBody Publicacion pub) {
		logger.info("addPublicacion()");
		repo.insert(pub);

		Usuario user = userRepo.findById(pub.userId);
		user.addPublicacion(pub);
		userRepo.save(user);

		return ResponseEntity.created(URI.create("http://localhost")).body("{\"message\": \"Creada con exito.\", \"id\": \"" + pub.getId() + "\"}");
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