package pe.gob.minam.seguridad.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Usuario;


public interface IServiceUsuario {

    public Usuario validarUsuarioPorCredencialesActivo(String usuario) throws ServiceException;

    public ResultadoServicio guardarUsuario(Usuario usuario) throws ServiceException;
    
    public void actualizarUsuario(Usuario usuario) throws ServiceException;
    
    public void actualizarContracena(Usuario usuario) throws ServiceException;

    public void eliminacionLogica(Usuario usuario) throws ServiceException;

    public Usuario obtenerUsuarioPorId(Integer idUsuario) throws ServiceException;

    public List<Usuario> obtenerListaUsuarios(Usuario usuario) throws ServiceException;
    
    public List<Usuario> listarUsuarios() throws ServiceException;
}
