/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import pe.gob.minam.sistema.controlador.backingBean.BKCriterios;
import pe.gob.minam.sistema.entidades.Criterios;
import pe.gob.minam.sistema.servicio.IServiceCriterios;



/**
 *
 * @author Jorge
 */
@Named(value = "MBCriterios")
@Scope("request")
public class MBCriterios extends MBGenerico implements IMantenedor, Serializable {

    private static final Logger logger = Logger.getLogger(MBCriterios.class);

    @Inject
    private BKCriterios bkCriterios;
    @Inject
    private IServiceCriterios serviceCriterios;

            
    @Override
    public String actualizar() {
        
        try {            
            serviceCriterios.actualizarCriterios(bkCriterios.getCriteriosFormulario());
            bkCriterios.setListaCriterios(serviceCriterios.listarCriterios());
            
        } catch (ServiceException e) {
             mostrarError("Ocurrieron algunos problemas al actualizar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
         return null;
    }

    @Override
    public String guardar() {
        try {
           
            serviceCriterios.guardarCriterios(bkCriterios.getCriteriosFormulario());
             bkCriterios.setListaCriterios(serviceCriterios.listarCriterios());
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
        bkCriterios.setCriteriosFormulario(bkCriterios.getCriteriosSeleccionado());
        bkCriterios.setAccion(TIPO_ACCION.ACTUALIZAR.getValor());
       
        return  null;
    }

    @Override
    public String nuevo() {
        bkCriterios.setCriteriosFormulario(new Criterios());        
        bkCriterios.setAccion(TIPO_ACCION.GUARDAR.getValor());
        System.out.println("aqui estoy");
        return  null;
    }

    @Override
    public String ver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String aceptar() {
        
        if(bkCriterios.getAccion()==TIPO_ACCION.GUARDAR.getValor()){
            this.guardar();
        }
        if(bkCriterios.getAccion()==TIPO_ACCION.ACTUALIZAR.getValor()){
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
          bkCriterios.setListaCriterios(serviceCriterios.listarCriterios());

        } catch (ServiceException e) {
            mostrarError("Ocurrieron algunos problemas al mostrar la informacion. Vuelva a intentar.");
            logger.error(e.getMessage(), e);
        }
        return URLPaginacion.Maestros.URL_CRITERIOS_LISTA;
    }
    
    public void eliminarRegistro(){
            
    }
}
