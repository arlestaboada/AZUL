
package ape.rgob.lminam.ecommon.scontrolador;



import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 

@ManagedBean
public class dowload {
        
   private StreamedContent file;
     
    public dowload() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/documentos/Instructivo.pdf");
        file = new DefaultStreamedContent(stream, "documentos/pdf", "downloaded_Instructivo.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
}
