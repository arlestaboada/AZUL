/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.controlador.backingBean;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.sistema.entidades.PostulanteDocumentosFiles;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.sistema.entidades.PostulanteDocumentosFiles;

/**
 *
 * @author Jorge
 */
@Named(value = "BKPostulanteDocumentos")
@Scope("session")
public class BKPostulanteDocumentos implements Serializable {
    
    private static final long serialVersionUID = -874481855366455968L;
    
    private PostulanteDocumentos practicasDocumentoFormulario;    
    private PostulanteDocumentos practicasDocumentoSeleccionado;
    private PostulanteDocumentosFiles practicasDocumentosFiles;
    private List<PostulanteDocumentos> listaAdjuntos;
   
    private StreamedContent reporte;
    

    public BKPostulanteDocumentos() {
        practicasDocumentoFormulario= new PostulanteDocumentos();
        practicasDocumentoSeleccionado= new PostulanteDocumentos();
        listaAdjuntos =  new ArrayList<>();
        practicasDocumentosFiles= new PostulanteDocumentosFiles();

    }

    public PostulanteDocumentos getPostulanteDocumentoFormulario() {
        return practicasDocumentoFormulario;
    }

    public void setPostulanteDocumentoFormulario(PostulanteDocumentos practicasDocumentoFormulario) {
        this.practicasDocumentoFormulario = practicasDocumentoFormulario;
    }

    public PostulanteDocumentos getPostulanteDocumentoSeleccionado() {
        return practicasDocumentoSeleccionado;
    }

    public void setPostulanteDocumentoSeleccionado(PostulanteDocumentos practicasDocumentoSeleccionado) {
        this.practicasDocumentoSeleccionado = practicasDocumentoSeleccionado;
    }

    public List<PostulanteDocumentos> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<PostulanteDocumentos> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

    public PostulanteDocumentosFiles getPostulanteDocumentosFiles() {
        return practicasDocumentosFiles;
    }

    public void setPostulanteDocumentosFiles(PostulanteDocumentosFiles practicasDocumentosFiles) {
        this.practicasDocumentosFiles = practicasDocumentosFiles;
    }

    
    
    
  
    
}
