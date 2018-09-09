package ws;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Publicacion;

public interface PublicacionRepository extends MongoRepository<Publicacion, String> {

	public Publicacion findByUserId(String userId);
	public Publicacion findById(String id);
	
}
