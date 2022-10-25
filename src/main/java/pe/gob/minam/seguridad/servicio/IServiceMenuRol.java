package pe.gob.minam.seguridad.servicio;
 
import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.sistema.entidades.MenuRol;
import pe.gob.minam.sistema.entidades.MenuRolPK;


public interface IServiceMenuRol {
    
    public List<Menu> obtenerListaMenuPorRol(Integer idRol) throws ServiceException;
    
    public List<Menu> obtenerListaMenuPorRolPadre() throws ServiceException;
    
    public List<Menu> obtenerListaMenuPorRolHijo(int padre) throws ServiceException;
    
    public List<Menu> obtenerListaPermisos(int rol) throws ServiceException;
    
    public void guardarMenuRol(MenuRol objeto) throws ServiceException;
    
    public void actualizarMenuRol(MenuRol objeto) throws ServiceException;
    
     public MenuRol getMenuRol(MenuRolPK id)throws ServiceException; 
}
