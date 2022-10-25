package pe.gob.minam.sistema.controlador;

import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.IMantenedor;
import pe.gob.minam.common.controlador.MBGenerico;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.sistema.controlador.backingBean.BKPostulante;
import pe.gob.minam.sistema.servicio.IServicePostulante;

/**
 *
 * @author ataboada
 */
 @Named(value = "MBReportes")
    @Scope("session")
public class MBReportes extends MBGenerico implements IMantenedor, Serializable {

     @Inject
    private BKPostulante bKPostulante;
      @Inject
    private IServicePostulante servicePostulante;
    @Override
    public String actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String aceptar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String retroceder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String mostrar() {
        
        
//         bkPostulante.setListaPracticas(servicePracticas.listarPracticas(bkPostulante.getPostulante()));
         bKPostulante.setListaPostulantes(servicePostulante.listarPostulantes());
        return URLPaginacion.Postulante.URL_POSTULANTES;
    }

   
     
    
}
