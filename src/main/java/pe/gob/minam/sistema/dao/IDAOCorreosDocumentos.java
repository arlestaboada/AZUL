/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.dao;


import java.util.List;
import pe.gob.minam.common.dao.IDao;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.dto.DtoCorreoDocumento;
import pe.gob.minam.sistema.entidades.CorreosDocumentos;
import pe.gob.minam.sistema.entidades.CorreosDocumentosPK;

/**
 *
 * @author Jorge
 */
public interface IDAOCorreosDocumentos extends IDao<CorreosDocumentos, CorreosDocumentosPK>{
    
    public List<DtoCorreoDocumento> listarDocumentos(long idcorreo) throws ServiceException;
        
}
