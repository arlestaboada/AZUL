/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;


import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Correos;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
public interface IServiceCorreos {
    
    public ResultadoServicio guardarCorreos(Correos Correos);
    
    public ResultadoServicio actualizarCorreos(Correos Correos);
    
    public ResultadoServicio eliminarCorreos(Correos Correos);

    public List<Correos> listarCorreos() throws ServiceException;
    
    public Correos getCorreos(Long id) throws ServiceException;
}
