
package pe.gob.minam.sistema.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.dao.IDAOInstituciones;
import pe.gob.minam.sistema.entidades.Instituciones;


@SuppressWarnings({ "unchecked", "serial","rawtypes","deprecation"})
@Repository("daoInstituciones")
public class DAOInstituciones extends DaoImpl<Instituciones ,Integer> implements IDAOInstituciones, Serializable{

    @Override
    public List<Instituciones> listarInstitucionesNotificar() throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
   
}
