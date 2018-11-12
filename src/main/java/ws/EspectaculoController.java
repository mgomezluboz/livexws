package ws;


import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.Espectaculo;
import model.Usuario;
import ws.EspectaculoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/espectaculos")
public class EspectaculoController extends AbstractController {
	
	@Autowired
	private EspectaculoRepository repo;
	@Autowired private EspectaculoStore contentStore;
	@Autowired private UsuarioRepository userRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Espectaculo> getEspectaculos(Principal principal) {
		logger.info("getEspectaculos()");

		List<Espectaculo> listado =  repo.findAll();
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = userRepo.findByUsername(userDetails.getUsername());

		Boolean atendido;

		for(Espectaculo espec : listado) {
			atendido = false;
			if (user.getEspectaculosAsistidos().contains(espec)) {
				atendido = true;
			}
			espec.setHasBeenTo(atendido);
		}

		return listado;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createEspectaculo(@RequestBody Espectaculo espec) throws Exception {
		logger.info("createEspectaculo()");

		Espectaculo buscarNombre = repo.findByNombre(espec.getNombre());
		if (null != buscarNombre) {
			return ResponseEntity.badRequest().body("Ya existe un espectaculo con ese nombre.");
		}

		Espectaculo result = repo.insert(espec);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateEspectaculo(@RequestBody Espectaculo espec) throws Exception {
		logger.info("createEspectaculo()");
		repo.save(espec);
		return ResponseEntity.accepted().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEspectaculo(@PathVariable String id) {
		logger.info("deleteEspectaculo()");
		repo.delete(id);
		return ResponseEntity.ok("{\"status\": \"Borrado con exito.\"}");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Espectaculo getEspectaculo(@PathVariable String id){
		logger.info("getEspectaculo()");
		return repo.findById(id);
	}

	@RequestMapping(value="/{id}/imagen", method = RequestMethod.PUT)
    public ResponseEntity<?> setContent(@PathVariable("id") String id, @RequestParam("file") MultipartFile file)
            throws IOException {

				Espectaculo f = repo.findById(id);
        if (null != f) {
            f.setMimeType(file.getContentType());

            contentStore.setContent(f, file.getInputStream());

            // save updated content-related info
            repo.save(f);

            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value="/{id}/imagen", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("id") String id) {

        Espectaculo f = repo.findById(id);
        if (null != f) {
            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.getContentLength());
            headers.set("Content-Type", f.getMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }
	
}
