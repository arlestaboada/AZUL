
package ape.rgob.lminam.ecommon.scontrolador;


import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
@ManagedBean

public class FileDownloadView {

    private StreamedContent file;
    public FileDownloadView() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/recursos/documentos/Instructivo.pdf");
        file = new DefaultStreamedContent(stream, "image/jpg", "Manual de CVERDE.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}
