/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dao;

import java.util.List;
import pe.gob.minam.common.dao.IDao;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Instituciones;
/**
 *
 * @author Jorge
 */
public interface IDAOInstituciones extends IDao<Instituciones, Integer>{
    
    public List<Instituciones> listarInstitucionesNotificar() throws ServiceException;
            
}
