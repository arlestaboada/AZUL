/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.dao.impl;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.dao.IDAOCorreosDocumentos;
import pe.gob.minam.sistema.dto.DtoCorreoDocumento;
import pe.gob.minam.sistema.entidades.CorreosDocumentos;
import pe.gob.minam.sistema.entidades.CorreosDocumentosPK;

/**
 *
 * @author Jorge
 */
@SuppressWarnings({ "unchecked", "serial","rawtypes","deprecation"})
@Repository("daoCorreosDocumentos")
public class DAOCorreosDocumentos extends DaoImpl<CorreosDocumentos, CorreosDocumentosPK>
                                implements IDAOCorreosDocumentos,Serializable{

    @Override
   public List<DtoCorreoDocumento> listarDocumentos(long idcorreo) throws ServiceException {
        List<DtoCorreoDocumento> lista = new ArrayList<>();

        try {
          StringBuilder consulta = new StringBuilder();
          
          consulta.append("SELECT ")
                  .append(" idcorreo, ")
                  .append(" iddocumento, ")
                  .append(" nombre, ")
                  .append(" extension, ")
                  .append(" fecha_reg ")                  
                  .append(" FROM  EDB_CORREOS_DOCUMENTOS ")
                  .append(" WHERE ")
                  .append("  idcorreo = ").append(idcorreo)                  
                  .append(" ORDER BY extension, fecha_reg DESC");
           
            System.out.println(consulta.toString());        
                   
           Query query = sessionFactory.getCurrentSession().createSQLQuery(consulta.toString())
                        .addScalar("idcorreo", LongType.INSTANCE)
                        .addScalar("iddocumento", IntegerType.INSTANCE)
                        .addScalar("nombre", StringType.INSTANCE )
                        .addScalar("extension", StringType.INSTANCE)
                        .addScalar("fecha_reg", DateType.INSTANCE);
                    
           
            List list =query.list();
            
            for (Iterator it = list.iterator(); it.hasNext();) {
                Object[] object = (Object[])it.next();
                DtoCorreoDocumento documentos = new DtoCorreoDocumento();
                documentos.setIdcorreo((Long)object[0]);
                documentos.setIddocumento((Integer)object[1]);
                documentos.setNombre((String)object[2]);
                documentos.setExtension((String)object[3]);
                documentos.setFechaReg((Date)object[4]);
              
                lista.add(documentos);
            }
                
        } catch (HibernateException e) {
        }
        return lista;
    }

   

   

    
}