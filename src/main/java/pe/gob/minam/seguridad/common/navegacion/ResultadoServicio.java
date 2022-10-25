package pe.gob.minam.seguridad.common.navegacion;

/**
 *
 * @author zer0
 */
public class ResultadoServicio {

    private String mensaje;
    private ESTADO_RESULTADO estadoResultado;
    
    private Object ID;

    public ResultadoServicio() {
    }

    public ResultadoServicio(ESTADO_RESULTADO estadoResultado) {
        this.estadoResultado = estadoResultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ESTADO_RESULTADO getEstadoResultado() {
        return estadoResultado;
    }

    public void setEstadoResultado(ESTADO_RESULTADO estadoResultado) {
        this.estadoResultado = estadoResultado;
    }

    public Object getID() {
        return ID;
    }

    public void setID(Object ID) {
        this.ID = ID;
    }
    
    
}
