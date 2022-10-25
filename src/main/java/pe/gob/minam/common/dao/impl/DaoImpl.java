package pe.gob.minam.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.minam.common.dao.IDao;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_REGISTRO;

/**
 * @author zer0
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class DaoImpl<Entidad, id extends Serializable> implements IDao<Entidad, id> {

    private static final long serialVersionUID = 170320081L;
    private static Logger logger = Logger.getLogger(DaoImpl.class);
    @Autowired
    protected SessionFactory sessionFactory;

    @Transactional
    @Override
    public void insertar(final Entidad entidad) throws DAOException {
        try {

            sessionFactory.getCurrentSession().persist(entidad);
            sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
    
    @Transactional
    @Override
    public void actualizar(final Entidad entidad) throws DAOException {
        try {
            sessionFactory.getCurrentSession().merge(entidad);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().evict(entidad);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void actualizarEstadoInactivo(final Entidad entidad) throws DAOException {
        try {
            //TODO
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void eliminar(final Entidad entidad) throws DAOException {
        try {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().delete(entidad);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public List<Entidad> listarTodos(final Class clase) throws DAOException {
        List<Entidad> listaEntidad = new ArrayList<Entidad>();
        try {
            sessionFactory.getCurrentSession().flush();
            listaEntidad = sessionFactory.getCurrentSession().createCriteria(clase).list();
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return listaEntidad;
    }
    
    @Transactional
    @Override
    public Collection<Entidad> listarActivos(final Class clase) throws DAOException {
        Collection<Entidad> listaEntidad = new ArrayList<Entidad>();
        try {
            sessionFactory.getCurrentSession().flush();
            listaEntidad = sessionFactory.getCurrentSession().createCriteria(clase)
                    .add(Restrictions.eq("estadoRegistro", ESTADO_REGISTRO.ACTIVO.getValor()))
                    .addOrder(Order.desc("id"))
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage(), e);
            
        }
        return listaEntidad;
    }

    @Transactional
    public List<Entidad> ejecutarQuery(String query, List<Object> params) throws DAOException {
        List<Entidad> listaEntidad = new ArrayList<Entidad>();
        try {
//            listaEntidad = hibernateTemplate.find(query, params.toArray());
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return listaEntidad;
    }

    @Transactional
    public List<Entidad> ejecutarQuery(String query) throws DAOException {
        List<Entidad> listaEntidad = new ArrayList<Entidad>();
        try {
//            listaEntidad = hibernateTemplate.find(query);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return listaEntidad;
    }

    @Transactional
    public List<Entidad> ejecutarSQL(String sql) throws DAOException {
        List<Entidad> listaEntidad = new ArrayList<Entidad>();
        try {
            //listaEntidad = getSession().createSQLQuery(sql).list();
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
        return listaEntidad;
    }

    @Transactional
    public Entidad obtenerEntidadPorId(Class clase, Serializable id) throws DAOException {
        try {
            Entidad ent = (Entidad) sessionFactory.getCurrentSession().createCriteria(clase).add(Restrictions.idEq(id)).uniqueResult();
            // Completely clear the session, it's bad, it's possible to use in process batch
            //sessionFactory.getCurrentSession().clear();
            return ent;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage(), e);
        }
    }

    
    @Override
    @Transactional
	public id conseguirUltimoId(Class clase) throws DAOException {
		try {
			id Id = (id) sessionFactory
					.getCurrentSession()
					.createCriteria(clase)
					.setProjection(Projections.max("id"))					
					.uniqueResult();
			if(Id == null){
				Id = (id) new Integer(0);
			}
			return Id;			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

    @SuppressWarnings("rawtypes")
    @Transactional
    @Override
    public Collection<Entidad> obtenerColeccionEntidadPorFiltros(Class clase,
            Map<String, Object> parametros) throws DAOException {
        try {
            List<Entidad> list = null;
            String criterio = " ";
            String query = "from " + clase.getSimpleName() + " ";
            if (parametros != null && !parametros.isEmpty()) {
                criterio = " where ";
                for (Entry<String, Object> entri : parametros.entrySet()) {
                    if (entri.getValue() instanceof String) {
                        criterio = criterio + " " + entri.getKey() + " like '%"
                                + entri.getValue() + "%' and";
                    } else if(entri.getValue() instanceof Character){
                        criterio = criterio + " " + entri.getKey() + " = '"
                                + entri.getValue() + "' and";
                    }else{
                        criterio = criterio + " " + entri.getKey() + " = "
                                + entri.getValue() + " and";
                    }
                }
                criterio = criterio.substring(0, criterio.length() - 3);
                criterio+= "ORDER BY id DESC";

            } else {
                parametros = null;
            }
             logger.info(" Query " + query + (criterio == null ? "" : criterio));
             System.out.println("AQUI  Query " + query + (criterio == null ? "" : criterio));
            sessionFactory.getCurrentSession().flush();
            list = sessionFactory.getCurrentSession().find(query + (criterio == null ? "" : criterio));
             System.out.println("CANTIDAD  Query " + list.size());
            return list;
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Transactional
    public Collection<Entidad> obtenerColeccionEntidadPorFiltros(Class clase,
            Map<String, Object> parametros, String orderby) throws DAOException {
        try {
            List<Entidad> list = null;
            String criterio = " ";
            String query = "from " + clase.getSimpleName() + " ";
            if (parametros != null && !parametros.isEmpty()) {
                criterio = " where ";
                for (Entry<String, Object> entri : parametros.entrySet()) {
                    if (entri.getValue() instanceof String) {
                        criterio = criterio + " " + entri.getKey() + " like '%"
                                + entri.getValue() + "%' and";
                    } else if(entri.getValue() instanceof Character){
                        criterio = criterio + " " + entri.getKey() + " = '"
                                + entri.getValue() + "' and";
                    }else{
                        criterio = criterio + " " + entri.getKey() + " = "
                                + entri.getValue() + " and";
                    }
                }
                criterio = criterio.substring(0, criterio.length() - 3);
                criterio+= "ORDER BY "+orderby;

            } else {
                parametros = null;
            }
//            logger.info(" Query " + query + (criterio == null ? "" : criterio));
            sessionFactory.getCurrentSession().flush();
            list = sessionFactory.getCurrentSession().find(query + (criterio == null ? "" : criterio));
            return list;
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
    
    @SuppressWarnings("rawtypes")
    @Transactional
    public Collection<Entidad> obtenerColeccionEntidadPorFiltrosASC(Class clase,
            Map<String, Object> parametros) throws DAOException {
        try {
            List<Entidad> list = null;
            String criterio = " ";
            String query = "from " + clase.getSimpleName() + " ";
            if (parametros != null && !parametros.isEmpty()) {
                criterio = " where ";
                for (Entry<String, Object> entri : parametros.entrySet()) {
                    if (entri.getValue() instanceof String) {
                        criterio = criterio + " " + entri.getKey() + " like '%"
                                + entri.getValue() + "%' and";
                    } else if(entri.getValue() instanceof Character){
                        criterio = criterio + " " + entri.getKey() + " = '"
                                + entri.getValue() + "' and";
                    }else{
                        criterio = criterio + " " + entri.getKey() + " = "
                                + entri.getValue() + " and";
                    }
                }
                criterio = criterio.substring(0, criterio.length() - 3);
                criterio+= "ORDER BY id ASC";

            } else {
                parametros = null;
            }
//            logger.info(" Query " + query + (criterio == null ? "" : criterio));
            sessionFactory.getCurrentSession().flush();
            list = sessionFactory.getCurrentSession().find(query + (criterio == null ? "" : criterio));
            return list;
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
    @Override
    public id insert(Entidad entidad) throws DAOException {
         try {

            Serializable pk=sessionFactory.getCurrentSession().save(entidad);
            sessionFactory.getCurrentSession().flush();
            return (id) pk;
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

   

   
}
