

package pe.gob.minam.sistema.controlador.backingBean;

import java.io.Serializable;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;


@Named(value = "BKReportes")
@Scope("session")
public class BKReportes implements Serializable {
    
    private static final long serialVersionUID = -1L;
    
    private String strEstrategia;
    
    private String strObjetivos;
    
    private String strMetasIndicadores;
    
    private String strMetasAcciones;
    
    private StreamedContent reporte;
     
    private int impresion;

    public int getImpresion() {
        return impresion;
    }

    public void setImpresion(int impresion) {
        this.impresion = impresion;
    }
    
    public StreamedContent getReporte() {
        return reporte;
    }

    public void setReporte(StreamedContent reporte) {
        this.reporte = reporte;
    }


    public String getStrEstrategia() {
        return strEstrategia;
    }

    public void setStrEstrategia(String strEstrategia) {
        this.strEstrategia = strEstrategia;
    }

    public String getStrObjetivos() {
        return strObjetivos;
    }

    public void setStrObjetivos(String strObjetivos) {
        this.strObjetivos = strObjetivos;
    }

    public String getStrMetasIndicadores() {
        return strMetasIndicadores;
    }

    public void setStrMetasIndicadores(String strMetasIndicadores) {
        this.strMetasIndicadores = strMetasIndicadores;
    }

    public String getStrMetasAcciones() {
        return strMetasAcciones;
    }

    public void setStrMetasAcciones(String strMetasAcciones) {
        this.strMetasAcciones = strMetasAcciones;
    }
    
    
    
    
    
}
