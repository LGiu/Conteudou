package br.com.conteudou.Service;


import br.com.conteudou.Model.Usuario;
import br.com.conteudou.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUsuarioAtual() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = authentication.getPrincipal() instanceof UserDetails ? (UserDetails) authentication.getPrincipal() : null;
            if (userDetails != null) {
                return usuarioRepository.findByEmail(userDetails.getUsername());
            }
        }
        return null;
    }
}
