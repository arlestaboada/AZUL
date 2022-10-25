/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;


import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.PostulanteUsuario;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
public interface IServicePostulanteUsuario {
    
    public ResultadoServicio guardarPostulanteUsuario(PostulanteUsuario PostulanteUsuario);
    
    public ResultadoServicio actualizarPostulanteUsuario(PostulanteUsuario PostulanteUsuario);
    
    public ResultadoServicio eliminarPostulanteUsuario(PostulanteUsuario PostulanteUsuario);

    public List<PostulanteUsuario> listarPostulanteUsuario() throws ServiceException;
    
    public PostulanteUsuario getPostulanteUsuario(Long id) throws ServiceException;
}
