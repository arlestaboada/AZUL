/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;


import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.PostulanteDocumentosFiles;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
public interface IServicePostulanteDocumentosFiles {
    
    public ResultadoServicio guardarPostulanteDocumentosFiles(PostulanteDocumentosFiles PostulanteDocumentosFiles);
    
    public ResultadoServicio actualizarPostulanteDocumentosFiles(PostulanteDocumentosFiles PostulanteDocumentosFiles);
    
    public ResultadoServicio eliminarPostulanteDocumentosFiles(PostulanteDocumentosFiles PostulanteDocumentosFiles);

    public List<PostulanteDocumentosFiles> listarPostulanteDocumentosFiles() throws ServiceException;
    
    public PostulanteDocumentosFiles getPostulanteDocumentosFiles(Integer id) throws ServiceException;
}
