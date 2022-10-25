package pe.gob.minam.seguridad.servicio.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_REGISTRO;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.seguridad.dao.IDAOUsuario;
import pe.gob.minam.seguridad.servicio.IServiceUsuario;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.sistema.servicio.impl.ServiceCriterios;

@Service("serviceUsuario")
public class ServiceUsuarioImpl implements  IServiceUsuario, Serializable{

    @Inject
    private IDAOUsuario daoUsuarios;
    
    @Inject
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Usuario validarUsuarioPorCredencialesActivo(String usuario) throws ServiceException {
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("usuario",usuario);
        parametros.put("estado_registro",ESTADO_REGISTRO.ACTIVO.getValor());
        try{
            List<Usuario> listaUsuario = (List<Usuario>)daoUsuarios.obtenerColeccionEntidadPorFiltros(Usuario.class,parametros);
            if(listaUsuario!=null && !listaUsuario.isEmpty()){
                return listaUsuario.get(0);
            }else{
                return null;
            }
        }catch(DAOException e){
            throw new ServiceException(e.getMessage(), e); 
        }
    }

    @Override
    public ResultadoServicio guardarUsuario(Usuario usuario) throws ServiceException {

        usuario.setContracena(passwordEncoder.encodePassword(usuario.getContracena(),null));
        
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
       
            try {
                /* *** Guardar ***  */
                usuario.setEstadoRegistro('A');
               
                resultadoServicio.setID(daoUsuarios.insert(usuario));
                resultadoServicio.setMensaje("El registro se guardo satisfactoriamente.");
                
            } catch (DAOException ex) {
                 resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrio un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServiceCriterios.class.getName()).log(Level.SEVERE, null, ex);
            }
      return resultadoServicio;
    }

     @Override
    public void actualizarUsuario(Usuario usuario) throws ServiceException {
       
            try {
                /* *** Actualizar *** */
                daoUsuarios.actualizar(usuario);
            } catch (DAOException ex) {
                throw new ServiceException(ex.getMessage(), ex);
            }
       
    }
    
     @Override
    public void actualizarContracena(Usuario usuario) throws ServiceException {

        usuario.setContracena(passwordEncoder.encodePassword(usuario.getContracena(),null));

            try {
                /* *** Actualizar *** */
                daoUsuarios.actualizar(usuario);
            } catch (DAOException ex) {
                throw new ServiceException(ex.getMessage(), ex);
            }
        
    }
    @Override
    public List<Usuario> obtenerListaUsuarios(Usuario usuario) throws ServiceException {
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("usuario",usuario.getUsuario());
        parametros.put("nombres",usuario.getNombres());
        parametros.put("apellidos",usuario.getApellidos());
        parametros.put("estado_registro",ESTADO_REGISTRO.ACTIVO.getValor());
        try {
            Collection<Usuario> coleccionUsuario = daoUsuarios.obtenerColeccionEntidadPorFiltros(Usuario.class,parametros);
            return (List<Usuario>) coleccionUsuario;
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer idUsuario) throws ServiceException {
        try {
            return daoUsuarios.obtenerEntidadPorId(Usuario.class,idUsuario);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminacionLogica(Usuario usuario) throws ServiceException {
        try{
            usuario.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO.getValor());
            daoUsuarios.actualizar(usuario);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(),ex);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws ServiceException {
        try {
            return new ArrayList<>(daoUsuarios.listarTodos(Usuario.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
    
     
}

