package ws;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.Espectaculo;

import ws.EspectaculoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/espectaculos")
public class EspectaculoController extends AbstractController {
	
	@Autowired
	private EspectaculoRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Espectaculo> getEspectaculos() {
		logger.info("getEspectaculos()");
		return repo.findAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createEspectaculo(@RequestBody Espectaculo espec) throws Exception {
		logger.info("createEspectaculo()");
		Espectaculo result = repo.insert(espec);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEspectaculo(@PathVariable String id) {
		logger.info("deleteEspectaculo()");
		repo.delete(id);
		return ResponseEntity.ok("Borrado con exito.");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Espectaculo getEspectaculo(@PathVariable String id){
		logger.info("getEspectaculo()");
		return repo.findById(id);
	}
	
}
