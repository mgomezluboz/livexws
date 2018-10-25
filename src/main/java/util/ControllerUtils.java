package util;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import model.Usuario;
import ws.UsuarioRepository;

public class ControllerUtils {

    @Autowired private static UsuarioRepository repo;

    public static Usuario getUserFromContext(Principal principal) {

		String username;

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.getName();
		}

		return repo.findByUsername(username);

	}

}