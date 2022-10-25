/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.controlador.backingBean;




import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;
import pe.gob.minam.sistema.entidades.CorreosDocumentos;

/**
 *
 * @author Jorge
 */
@Named(value = "BKCorreosDocumentos")
public class BKCorreosDocumentos {
    
    private static final long serialVersionUID = -874481855366455968L;
    
    private CorreosDocumentos correoDocumentoFormulario;    
    private CorreosDocumentos correoDocumentoSeleccionado;
    private List<CorreosDocumentos> listaAdjuntos;
   
    private StreamedContent reporte;
    

    public BKCorreosDocumentos() {
        correoDocumentoFormulario= new CorreosDocumentos();
        correoDocumentoSeleccionado= new CorreosDocumentos();
        listaAdjuntos =  new ArrayList<>();

    }

    public CorreosDocumentos getCorreoDocumentoFormulario() {
        return correoDocumentoFormulario;
    }

    public void setCorreoDocumentoFormulario(CorreosDocumentos correoDocumentoFormulario) {
        this.correoDocumentoFormulario = correoDocumentoFormulario;
    }

    public CorreosDocumentos getCorreoDocumentoSeleccionado() {
        return correoDocumentoSeleccionado;
    }

    public void setCorreoDocumentoSeleccionado(CorreosDocumentos correoDocumentoSeleccionado) {
        this.correoDocumentoSeleccionado = correoDocumentoSeleccionado;
    }

    public List<CorreosDocumentos> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<CorreosDocumentos> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }

    
    
    
  
    
}
