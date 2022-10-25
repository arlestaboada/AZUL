package pe.gob.minam.seguridad.servicio;
 

import java.util.Collection;
import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Rol;

public interface IServiceRol {
    
    public Collection<Rol> obtenerListaRolesActivos() throws ServiceException; 
    
    public void guardarRol(Rol rol) throws ServiceException;
    
    public void actualizarRol(Rol rol) throws ServiceException;
    
    public List<Rol> listarRoles() throws ServiceException;
    
    public Rol getRol(int idrol)throws ServiceException; 
}
