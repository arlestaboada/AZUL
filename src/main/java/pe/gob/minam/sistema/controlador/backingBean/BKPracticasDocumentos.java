
package pe.gob.minam.sistema.controlador.backingBean;




import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;
import pe.gob.minam.sistema.entidades.PracticasDocumentos;
import pe.gob.minam.sistema.entidades.PracticasDocumentosFiles;


@Named(value = "BKPracticasDocumentos")
public class BKPracticasDocumentos {
    
    private static final long serialVersionUID = -874481855366455968L;
    
    private PracticasDocumentos practicasDocumentoFormulario;    
    private PracticasDocumentos practicasDocumentoSeleccionado;
    private PracticasDocumentosFiles practicasDocumentosFiles;
    private List<PracticasDocumentos> listaAdjuntos;
   
    private StreamedContent reporte;
    

    public BKPracticasDocumentos() {
        practicasDocumentoFormulario= new PracticasDocumentos();
        practicasDocumentoSeleccionado= new PracticasDocumentos();
        listaAdjuntos =  new ArrayList<>();
        practicasDocumentosFiles= new PracticasDocumentosFiles();

    }

    public PracticasDocumentos getPracticasDocumentoFormulario() {
        return practicasDocumentoFormulario;
    }

    public void setPracticasDocumentoFormulario(PracticasDocumentos practicasDocumentoFormulario) {
        this.practicasDocumentoFormulario = practicasDocumentoFormulario;
    }

    public PracticasDocumentos getPracticasDocumentoSeleccionado() {
        return practicasDocumentoSeleccionado;
    }

    public void setPracticasDocumentoSeleccionado(PracticasDocumentos practicasDocumentoSeleccionado) {
        this.practicasDocumentoSeleccionado = practicasDocumentoSeleccionado;
    }

    public List<PracticasDocumentos> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<PracticasDocumentos> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

    public PracticasDocumentosFiles getPracticasDocumentosFiles() {
        return practicasDocumentosFiles;
    }

    public void setPracticasDocumentosFiles(PracticasDocumentosFiles practicasDocumentosFiles) {
        this.practicasDocumentosFiles = practicasDocumentosFiles;
    }

    
    
    
  
    
}
