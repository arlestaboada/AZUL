/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.dao;

import java.util.List;
import pe.gob.minam.common.dao.IDao;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Menu;

/**
 *
 * @author Jorge
 */
public interface IDAOMenu extends IDao<Menu,Integer>{
    
     public List<Menu> obtenerListaMenuPorRolPadre() throws ServiceException;
     
     public List<Menu> obtenerListaMenuPorRolHijo(int padre) throws ServiceException;
     
     public List<Menu> obtenerListaPermisos(int rol) throws ServiceException;
}
