/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio.impl;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.dao.IDAOCorreos;
import pe.gob.minam.sistema.servicio.IServiceCorreos;
import pe.gob.minam.sistema.entidades.Correos;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
@Service
public class ServiceCorreos implements IServiceCorreos, Serializable{

    @Inject
    private IDAOCorreos daoCorreos;
        
    @Override
    public ResultadoServicio guardarCorreos(Correos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoCorreos.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServiceCorreos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarCorreos(Correos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoCorreos.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServiceCorreos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarCorreos(Correos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoCorreos.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServiceCorreos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<Correos> listarCorreos() throws ServiceException {
          try {
            return new ArrayList<>(daoCorreos.listarTodos(Correos.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

//    @Override
//    public List<EdbCorreos> listarCorreos(int idusuario, int estado) throws ServiceException {
//         try {
//            return new ArrayList<>(daoCorreos.listarCorreos(idusuario, estado));
//        } catch (Exception ex) {
//            throw new ServiceException(ex.getMessage(), ex);
//        }
//    }

    @Override
    public Correos getCorreos(Long id) throws ServiceException {
         try {
            return daoCorreos.obtenerEntidadPorId(Correos.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
}
