package ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected RestTemplate api = new RestTemplate();
	
}
