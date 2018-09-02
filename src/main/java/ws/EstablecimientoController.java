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

import model.Establecimiento;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/establecimientos")
public class EstablecimientoController extends AbstractController {
	
	@Autowired
	private EstablecimientoRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Establecimiento> getEstablecimientos() {
		logger.info("getEstablecimientos()");
		return repo.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createEstablecimiento(@RequestBody Establecimiento espec) throws Exception {
		logger.info("createEstablecimiento()");
		Establecimiento result = repo.insert(espec);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateEstablecimiento(@RequestBody Establecimiento espec) throws Exception {
		logger.info("updateEstablecimiento()");
		repo.save(espec);
		return ResponseEntity.accepted().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEstablecimiento(@PathVariable String id) {
		logger.info("deleteEstablecimiento()");
		repo.delete(id);
		return ResponseEntity.ok("{\"status\": \"Borrado con exito.\"}");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Establecimiento getEstablecimiento(@PathVariable String id){
		logger.info("getEstablecimiento()");
		return repo.findById(id);
	}
	
}
