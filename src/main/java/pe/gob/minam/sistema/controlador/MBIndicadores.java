
package pe.gob.minam.sistema.controlador;

import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import pe.gob.minam.common.controlador.IMantenedor;
import pe.gob.minam.common.controlador.MBGenerico;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarError;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarMensaje;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.*;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.sistema.controlador.backingBean.BKIndicadores;
import pe.gob.minam.sistema.entidades.Indicadores;
import pe.gob.minam.sistema.servicio.IServiceCriterios;
import pe.gob.minam.sistema.servicio.IServiceIndicadores;




@Named(value = "MBIndicadores")
@Scope("request")
public class MBIndicadores extends MBGenerico implements IMantenedor, Serializable {

    private static final Logger logger = Logger.getLogger(MBIndicadores.class);

    @Inject
    private BKIndicadores bkIndicadores;
    @Inject
    private IServiceIndicadores serviceIndicadores;
    @Inject
    private IServiceCriterios serviceCriterios;

            
    @Override
    public String actualizar() {
        
        try {   
            bkIndicadores.getIndicadoresFormulario().setIdcriterio(serviceCriterios.getCriterios(bkIndicadores.getIdCriterio()));
            serviceIndicadores.actualizarIndicadores(bkIndicadores.getIndicadoresFormulario());
            bkIndicadores.setListaIndicadores(serviceIndicadores.listarIndicadores());
            
        } catch (ServiceException e) {
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
         return null;
    }

    @Override
    public String guardar() {
        try {
            bkIndicadores.getIndicadoresFormulario().setIdcriterio(serviceCriterios.getCriterios(bkIndicadores.getIdCriterio()));
            serviceIndicadores.guardarIndicadores(bkIndicadores.getIndicadoresFormulario());
             bkIndicadores.setListaIndicadores(serviceIndicadores.listarIndicadores());
        } catch (ServiceException e) {
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar() {
        bkIndicadores.setIndicadoresFormulario(bkIndicadores.getIndicadoresSeleccionado());
        bkIndicadores.setAccion(TIPO_ACCION.ACTUALIZAR.getValor());
        bkIndicadores.setIdCriterio(bkIndicadores.getIndicadoresSeleccionado().getIdcriterio().getIdcriterio());
       
        return  null;
    }

    @Override
    public String nuevo() {
        bkIndicadores.setIndicadoresFormulario(new Indicadores());        
        bkIndicadores.setAccion(TIPO_ACCION.GUARDAR.getValor());
       
        return  null;
    }

    @Override
    public String ver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String aceptar() {
        
        if(bkIndicadores.getAccion()==TIPO_ACCION.GUARDAR.getValor()){
            this.guardar();
        }
        if(bkIndicadores.getAccion()==TIPO_ACCION.ACTUALIZAR.getValor()){
            this.actualizar();
        }
        mostrarMensaje("Se guardo los datos satisfactoriamente.");
        return null;
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
        try {
          bkIndicadores.setListaIndicadores(serviceIndicadores.listarIndicadores());
          bkIndicadores.setListaCriterios(serviceCriterios.listarCriterios());
        } catch (ServiceException e) {
            mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
        return URLPaginacion.Maestros.URL_INDICADORES_LISTA;
    }
    
    public void eliminarRegistro(){
            
    }
}
