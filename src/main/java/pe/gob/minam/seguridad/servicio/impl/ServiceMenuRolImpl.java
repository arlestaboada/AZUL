package pe.gob.minam.seguridad.servicio.impl;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_REGISTRO;
import pe.gob.minam.seguridad.dao.IDAOMenu;
import pe.gob.minam.seguridad.dao.IDAOMenuRol;
import pe.gob.minam.seguridad.servicio.IServiceMenuRol;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.sistema.entidades.MenuRol;
import pe.gob.minam.sistema.entidades.MenuRolPK;

@Service("serviceMenuRol")
public class ServiceMenuRolImpl implements  IServiceMenuRol, Serializable{

    @Inject
    private IDAOMenuRol daoMenuRol;
    
    @Inject
    private IDAOMenu daoMenu;
    
    @Override
    public List<Menu> obtenerListaMenuPorRol(Integer idRol) throws ServiceException {
        List<Menu> listaMenu = new ArrayList<>();
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("menuRolPK.idRol",idRol);
        parametros.put("estadoRegistro",""+ESTADO_REGISTRO.ACTIVO.getValor());
        try {
            Collection<MenuRol> coleccionMenuRol = daoMenuRol.obtenerColeccionEntidadPorFiltros(MenuRol.class,parametros);
            for (MenuRol menuRol : coleccionMenuRol) {
                listaMenu.add(menuRol.getMenu());
            }
            return listaMenu;
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Menu> obtenerListaMenuPorRolPadre() throws ServiceException {
       return  daoMenu.obtenerListaMenuPorRolPadre();
    }
    
    @Override
    public List<Menu> obtenerListaMenuPorRolHijo(int padre) throws ServiceException {
       return  daoMenu.obtenerListaMenuPorRolHijo(padre);
    }

    @Override
    public List<Menu> obtenerListaPermisos(int rol) throws ServiceException {
       return  daoMenu.obtenerListaPermisos(rol);
    }

    @Override
    public void guardarMenuRol(MenuRol objeto) throws ServiceException {
        try {
            daoMenuRol.insertar(objeto);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizarMenuRol(MenuRol objeto) throws ServiceException {
        try {
            daoMenuRol.actualizar(objeto);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public MenuRol getMenuRol(MenuRolPK id) throws ServiceException {
        try {
            return daoMenuRol.obtenerEntidadPorId(MenuRol.class, id);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

   
}
