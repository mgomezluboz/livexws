package ws;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Publicacion;

public interface PublicacionRepository extends MongoRepository<Publicacion, String> {

	public List<Publicacion> findByUserId(String userId);
	public Publicacion findById(String id);
    public List<Publicacion> findByEventoId(String eventoId);

}
