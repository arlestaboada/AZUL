/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.servicio;

import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.UsuarioFiles;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;


/**
 *
 * @author Edison
 */
public interface IServiceUsuarioFiles {

    public ResultadoServicio guardarFile(UsuarioFiles usuarioFile) throws ServiceException;

    public UsuarioFiles obtenerUsuarioFiles(Integer id) throws ServiceException;

    public ResultadoServicio eliminarFile(UsuarioFiles usuarioFile) throws ServiceException;
   
}
