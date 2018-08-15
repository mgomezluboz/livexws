package ws;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Establecimiento;

public interface EstablecimientoRepository extends MongoRepository<Establecimiento, String> {

	public Establecimiento findById(String id);
	
}
