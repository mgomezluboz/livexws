package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import model.Usuario;
import ws.UsuarioRepository;

public class ControllerUtils {

    @Autowired private static UsuarioRepository repo;

    public static Usuario getUserFromContext() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}

		return repo.findByUsername(username);

	}

}