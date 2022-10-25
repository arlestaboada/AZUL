package pe.gob.minam.seguridad.servicio.impl;
 
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.seguridad.servicio.IServiceUsuario;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

    @Inject
    private IServiceUsuario serviceUsuario;
        
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = null;
        try {
            Usuario objUsuario = serviceUsuario.validarUsuarioPorCredencialesActivo(username);
            if(objUsuario!=null){
                userDetails = new UserDetailImpl(objUsuario.getUsuario());
            }
            return userDetails;
        } catch (ServiceException ex) { 
            throw new UsernameNotFoundException(ex.getMessage(),ex);
        }
    }
}
