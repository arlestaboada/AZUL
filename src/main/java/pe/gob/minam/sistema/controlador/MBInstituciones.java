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
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.TIPO_ACCION;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.sistema.controlador.backingBean.BKInstituciones;
import pe.gob.minam.sistema.entidades.Instituciones;
import pe.gob.minam.sistema.servicio.IServiceInstituciones;

@Named(value = "MBInstituciones")
@Scope("session")
public class MBInstituciones extends MBGenerico implements IMantenedor, Serializable {

    private static Logger logger = Logger.getLogger(MBPracticas.class);
    @Inject
    private BKInstituciones bkInstituciones;
    @Inject
    private IServiceInstituciones serviceInstituciones;

    @Override
    public String actualizar() {
         try {            
            serviceInstituciones.actualizarInstituciones(bkInstituciones.getInstitucionesFormulario());
            bkInstituciones.setListaInstituciones(serviceInstituciones.listarInstituciones());
            
        } catch (ServiceException e) {
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
         return null;
    }

    @Override
    public String guardar() {
       try {
           
            serviceInstituciones.guardarInstituciones(bkInstituciones.getInstitucionesFormulario());
             bkInstituciones.setListaInstituciones(serviceInstituciones.listarInstituciones());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nuevo() {
        bkInstituciones.setInstitucionesFormulario(new Instituciones());
        bkInstituciones.setAccion(TIPO_ACCION.GUARDAR.getValor());
        return null;
    }

    @Override
    public String ver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String aceptar() {
        
        if(bkInstituciones.getAccion()==TIPO_ACCION.GUARDAR.getValor()){
            this.guardar();
        }
        if(bkInstituciones.getAccion()==TIPO_ACCION.ACTUALIZAR.getValor()){
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

            bkInstituciones.setListaInstituciones(serviceInstituciones.listarInstituciones());

        } catch (ServiceException ex) {
            mostrarError("Error al listar las instituciones");
            logger.error(ex.getMessage(), ex);
        }

        return URLPaginacion.Intituciones.URL_LISTA_Instituciones;
    }
}
