/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;


import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.CorreosDocumentos;
import pe.gob.minam.sistema.entidades.CorreosDocumentosPK;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
public interface IServiceCorreosDocumentos {
    
    public ResultadoServicio guardarCorreosDocumentos(CorreosDocumentos CorreosDocumentos);
    
    public ResultadoServicio actualizarCorreosDocumentos(CorreosDocumentos CorreosDocumentos);
    
    public ResultadoServicio eliminarCorreosDocumentos(CorreosDocumentos CorreosDocumentos);

    public List<CorreosDocumentos> listarCorreosDocumentos() throws ServiceException;
    
    public List<CorreosDocumentos> listarAdjuntos(long idcorreos) throws ServiceException;
       
    public CorreosDocumentos getCorreosDocumentos(CorreosDocumentosPK id) throws ServiceException;
    
   // public int ultimoRegistro(Long id) throws ServiceException;
}
 