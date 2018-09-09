package ws;

import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import model.Publicacion;

@CrossOrigin(origins = "*")
public interface PublicacionStore extends ContentStore<Publicacion, String> {
}