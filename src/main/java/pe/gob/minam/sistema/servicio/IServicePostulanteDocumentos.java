/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;


import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Postulante;

/**
 *
 * @author Jorge
 */
public interface IServicePostulanteDocumentos {
    
    public ResultadoServicio guardarPostulanteDocumentos(PostulanteDocumentos PostulanteDocumentos);
    
    public ResultadoServicio actualizarPostulanteDocumentos(PostulanteDocumentos PostulanteDocumentos);
    
    public ResultadoServicio eliminarPostulanteDocumentos(PostulanteDocumentos PostulanteDocumentos);

    public List<PostulanteDocumentos> listarPostulanteDocumentos() throws ServiceException;
    
    public PostulanteDocumentos getPostulanteDocumentos(Integer id) throws ServiceException;
    
     public List<PostulanteDocumentos> listarPostulanteDocumentos(Postulante postulante, int tipo) throws ServiceException;
}
