package pe.gob.minam.sistema.controlador;

import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import static pe.gob.minam.common.controlador.MBGenerico.mostrarError;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.URLPaginacion;
import pe.gob.minam.seguridad.controlador.backingBean.BKSession;
import pe.gob.minam.sistema.controlador.backingBean.BKPracticas;
import pe.gob.minam.sistema.servicio.IServicePracticas;

@Named(value = "MBPEvalua")
@Scope("session")
public class MBPEvalua {

    private static Logger logger = Logger.getLogger(MBEvaluar.class);
    @Inject
    private BKPracticas bkPracticas;
    @Inject
    private IServicePracticas servicePracticas;
    @Inject
    private BKSession bKSession;

    public String mostrar() {
        try {
            bkPracticas.setListaPracticasAEvaluar(servicePracticas.listarPracticasAEvaluar(bKSession.getUsuario().getDepartamento(),bKSession.getUsuario().getProvincia(), bKSession.getUsuario().getDistrito()));
        } catch (ServiceException ex) {
            mostrarError("Error al listar las buenas prácticas");
            logger.error(ex.getMessage(), ex);
        }
        return URLPaginacion.Evaluador.URL_practicas_a_evaluar;

    }
}