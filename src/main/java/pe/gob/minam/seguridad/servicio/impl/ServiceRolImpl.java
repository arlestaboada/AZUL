package pe.gob.minam.seguridad.servicio.impl;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Rol;
import pe.gob.minam.seguridad.dao.IDAORol;
import pe.gob.minam.seguridad.servicio.IServiceRol;


@Service("serviceRol")
public class ServiceRolImpl implements IServiceRol, Serializable{

    @Inject
    private IDAORol daoRol;
    

    @Override
    public Collection<Rol> obtenerListaRolesActivos() throws ServiceException {
        try {
            return daoRol.listarActivos(Rol.class);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Rol getRol(int idrol) throws ServiceException {
        try {
            return daoRol.obtenerEntidadPorId(Rol.class, idrol);
        } catch (DAOException ex) {
             throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void guardarRol(Rol rol) throws ServiceException {
        try {
            daoRol.insertar(rol);
        } catch (DAOException ex) {
             throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizarRol(Rol rol) throws ServiceException {
        try {
            daoRol.actualizar(rol);
        } catch (DAOException ex) {
             throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Rol> listarRoles() throws ServiceException {
        try {
            return new ArrayList<>(daoRol.listarTodos(Rol.class));
        } catch (DAOException ex) {
             throw new ServiceException(ex.getMessage(), ex);
        }
    }

   
    
}
