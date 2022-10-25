package pe.gob.minam.common.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

@Controller(value="MBGenerico")
public class MBGenerico {

    public MBGenerico() {
    }

    public static void mostrarMensaje(String msjResumen) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, msjResumen, null);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public static void mostrarMensaje(String msjResumen, String msjDetalle) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, msjResumen, msjDetalle);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public static void mostrarWarning(String msjResumen) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_WARN, msjResumen, null);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public static void mostrarWarning(String msjResumen, String msjDetalle) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_WARN, msjResumen, msjDetalle);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public static void mostrarError(String msjResumen) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, msjResumen, null);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public static void mostrarError(String msjResumen, String msjDetalle) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, msjResumen, msjDetalle);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
    public static void mostrarErrorObjeto(String msjResumen,String objeto) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, msjResumen, null);
        FacesContext.getCurrentInstance().addMessage(objeto, msj);
    }

    public static void mostrarErrorObjeto(String msjResumen, String msjDetalle,String objeto) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, msjResumen, msjDetalle);
        FacesContext.getCurrentInstance().addMessage(objeto, msj);
    }

    public static void mostrarFatal(String msjResumen) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, msjResumen, null);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public static void mostrarFatal(String msjResumen, String msjDetalle) {
        FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, msjResumen, msjDetalle);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
}
