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
import pe.gob.minam.sistema.dao.IDAOInstituciones;
import pe.gob.minam.sistema.servicio.IServiceInstituciones;
import pe.gob.minam.sistema.entidades.Instituciones;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
/**
 *
 * @author Jorge
 */
@Service("serviceInstituciones")
public class ServiceInstituciones implements  IServiceInstituciones, Serializable{

    @Inject
    private IDAOInstituciones daoInstituciones;

    @Override
    public ResultadoServicio guardarInstituciones(Instituciones instituciones) throws ServiceException {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoInstituciones.insert(instituciones));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServiceInstituciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarInstituciones(Instituciones instituciones) throws ServiceException {
        ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoInstituciones.actualizar(instituciones);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServiceInstituciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<Instituciones> listarInstituciones() throws ServiceException {
         try {
            return new ArrayList<>(daoInstituciones.listarTodos(Instituciones.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Instituciones getInstituciones(int idInstituciones) throws ServiceException {
        try {
            return daoInstituciones.obtenerEntidadPorId(Instituciones.class,idInstituciones );
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Instituciones> listarInstitucionesNotificar() throws ServiceException {
        return new ArrayList<>(daoInstituciones.listarInstitucionesNotificar());
    }
            
   
}
