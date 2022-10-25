package pe.gob.minam.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import pe.gob.minam.common.dao.excepcion.DAOException;

/**
 * @author zer0
 */
public interface IDao<Entidad, PK extends Serializable> {

    public void insertar(final Entidad entidad) throws DAOException;
        
    public PK insert(final Entidad entidad) throws DAOException;
    
    public void actualizar(final Entidad entidad) throws DAOException;

    public void actualizarEstadoInactivo(final Entidad entidad) throws DAOException;
    
    public void eliminar(final Entidad entidad) throws DAOException;

    public Collection<Entidad> listarTodos(final Class clase) throws DAOException;
   
     
    public Collection<Entidad> listarActivos(final Class clase) throws DAOException;
    
    public Entidad obtenerEntidadPorId(Class clase, Serializable id) throws DAOException;
    
    public PK conseguirUltimoId(Class clase) throws DAOException;
    
    public Collection<Entidad> obtenerColeccionEntidadPorFiltros(Class clase,Map<String,Object> parametros)throws DAOException;
    
    public Collection<Entidad> obtenerColeccionEntidadPorFiltros(Class clase,Map<String, Object> parametros, String orderby) throws DAOException;
    
    public Collection<Entidad> obtenerColeccionEntidadPorFiltrosASC(Class clase,Map<String,Object> parametros)throws DAOException;
    
   
}
