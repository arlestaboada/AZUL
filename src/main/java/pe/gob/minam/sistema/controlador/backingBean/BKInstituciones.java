package pe.gob.minam.sistema.controlador.backingBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.sistema.entidades.Instituciones;

@Named(value = "BKInstituciones")
@Scope("session")
public class BKInstituciones implements Serializable {

    private static final long serialVersionUID = -1L;
    private Instituciones instituciones;
    private List<Instituciones> listaInstituciones;
    private Instituciones institucionesFormulario;
    private int accion;

    

    public BKInstituciones() {
        institucionesFormulario = new Instituciones();
        instituciones = new Instituciones();
        listaInstituciones = new ArrayList<>();

    }

    public Instituciones getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(Instituciones instituciones) {
        this.instituciones = instituciones;
    }

    public List<Instituciones> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<Instituciones> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public Instituciones getInstitucionesFormulario() {
        return institucionesFormulario;
    }

    public void setInstitucionesFormulario(Instituciones institucionesFormulario) {
        this.institucionesFormulario = institucionesFormulario;
    }
    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
}
