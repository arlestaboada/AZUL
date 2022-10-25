/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.dao.IDAOEvaluar;
import pe.gob.minam.sistema.entidades.Evaluar;
import pe.gob.minam.sistema.servicio.IServiceEvaluar;

/**
 *
 * @author Jorge
 */
@Service
public class ServiceEvaluar implements IServiceEvaluar, Serializable{

    @Inject
    private IDAOEvaluar daoEvaluar;
        
    @Override
    public ResultadoServicio guardarEvaluar(Evaluar correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoEvaluar.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServiceEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarEvaluar(Evaluar correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoEvaluar.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServiceEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarEvaluar(Evaluar correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoEvaluar.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServiceEvaluar.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<Evaluar> listarEvaluar() throws ServiceException {
          try {
            return new ArrayList<>(daoEvaluar.listarTodos(Evaluar.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }


    @Override
    public Evaluar getEvaluar(Integer id) throws ServiceException {
         try {
            return daoEvaluar.obtenerEntidadPorId(Evaluar.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    @Override
    public int actualizarEvaluarEstado(int i, Integer idpractica){
        return daoEvaluar.actualizarEvaluarEstado(i,idpractica);
    }
    
}
