/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.sistema.entidades.UsuarioDocumentos;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Edison
 */
public interface IServiceUsuarioDocumentos {

    public ResultadoServicio guardarUsuarioDocumento(UsuarioDocumentos usuarioDocumento) throws ServiceException;

    public List<UsuarioDocumentos> listarUsuarioDocumentos(Usuario usuario) throws ServiceException;

    public ResultadoServicio eliminarDocumento(UsuarioDocumentos usuarioDocumento) throws ServiceException;
   
}
