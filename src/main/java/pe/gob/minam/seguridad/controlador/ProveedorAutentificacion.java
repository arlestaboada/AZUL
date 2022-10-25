package pe.gob.minam.seguridad.controlador;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.seguridad.servicio.IServiceUsuario;



@SuppressWarnings("serial")
public class ProveedorAutentificacion implements AuthenticationProvider,Serializable{
    
    private static Logger logger = Logger.getLogger(ProveedorAutentificacion.class);
 
    @Inject
    private IServiceUsuario serviceUsuario;
    
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // String name = authentication.getName();
        String principal = authentication.getPrincipal().toString();
        String contracena = authentication.getCredentials().toString();
        Usuario objUsuario = null;
        Authentication usernamePasswordAuthToken = null;
            try {
                objUsuario = serviceUsuario.validarUsuarioPorCredencialesActivo(principal);
                String contracena_encrypted = passwordEncoder.encodePassword(contracena,null);
                if(objUsuario!=null){
                    if(!objUsuario.getContracena().equalsIgnoreCase(contracena_encrypted)){
                        objUsuario = null;
                    }
                }else{
                    objUsuario = null;
                }
            } catch (ServiceException ex) {
                objUsuario = null;
            }
                
            if (objUsuario != null) {
                Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
                grantedAuthority.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ROLE_ADMIN";
                    }
                });
                usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(objUsuario,objUsuario,grantedAuthority);
                return usernamePasswordAuthToken;
            }else{
                return usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(objUsuario,objUsuario);
            }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
