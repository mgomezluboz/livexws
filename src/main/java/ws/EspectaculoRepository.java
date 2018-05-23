package ws;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Espectaculo;

public interface EspectaculoRepository extends MongoRepository<Espectaculo, String> {

	public Espectaculo findById(String id);
	
}
