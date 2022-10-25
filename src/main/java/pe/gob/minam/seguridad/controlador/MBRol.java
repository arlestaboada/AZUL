/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.controlador;

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
import pe.gob.minam.sistema.entidades.Rol;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.controlador.backingBean.BKRol;
import pe.gob.minam.seguridad.servicio.IServiceRol;



/**
 *
 * @author Jorge
 */
@Named(value="MBRol")
@Scope("session")
public class MBRol extends MBGenerico implements IMantenedor, Serializable {

    private static final long serialVersionUID = 7763651480661834960L;

    private static Logger logger = Logger.getLogger(MBUsuario.class);
	
    @Inject
    private BKRol bkRol;

    @Inject
    private IServiceRol serviceRol;

    public MBRol() {
    }

    
    @Override
    public String mostrar() {
        
        try {
            bkRol.setRolFormulario(new Rol());
            bkRol.setListaRoles(serviceRol.listarRoles());

        } catch (ServiceException ex) {
             mostrarError("Error al obtener la información");
        }
        return URLPaginacion.Roles.URL_LISTA_ROL;
    }
    
    @Override
    public String actualizar() {
        try {
            serviceRol.actualizarRol(bkRol.getRolFormulario());
        } catch (ServiceException ex) {
            mostrarError("Error al obtener la información");
             logger.error(ex.getMessage(), ex);
        }
        return mostrar();
    }

    @Override
    public String guardar() {
        try {
            serviceRol.guardarRol(bkRol.getRolFormulario());
        } catch (ServiceException ex) {
            mostrarError("Error al obtener la información");
            logger.error(ex.getMessage(), ex);
        }
        return mostrar();
    }

    @Override
    public void eliminar(ActionEvent actionEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editar() {
        bkRol.setRolFormulario(bkRol.getRolSeleccionado());
        return URLPaginacion.Roles.URL_EDITAR_ROL;
    }

    @Override
    public String nuevo() {
        bkRol.setRolFormulario(new Rol());        
        return URLPaginacion.Roles.URL_CREAR_ROL;
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
       return mostrar();
    }
    
}
