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
import pe.gob.minam.sistema.dao.IDAOPracticasDocumentosFiles;
import pe.gob.minam.sistema.entidades.PracticasDocumentosFiles;
import pe.gob.minam.sistema.servicio.IServicePracticasDocumentosFiles;

/**
 *
 * @author Jorge
 */
@Service
public class ServicePracticasDocumentosFiles implements IServicePracticasDocumentosFiles, Serializable{

    @Inject
    private IDAOPracticasDocumentosFiles daoPracticasDocumentosFiles;
        
    @Override
    public ResultadoServicio guardarPracticasDocumentosFiles(PracticasDocumentosFiles correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoPracticasDocumentosFiles.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServicePracticasDocumentosFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarPracticasDocumentosFiles(PracticasDocumentosFiles correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPracticasDocumentosFiles.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePracticasDocumentosFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarPracticasDocumentosFiles(PracticasDocumentosFiles correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPracticasDocumentosFiles.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePracticasDocumentosFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<PracticasDocumentosFiles> listarPracticasDocumentosFiles() throws ServiceException {
          try {
            return new ArrayList<>(daoPracticasDocumentosFiles.listarTodos(PracticasDocumentosFiles.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }


    @Override
    public PracticasDocumentosFiles getPracticasDocumentosFiles(Integer id) throws ServiceException {
         try {
            return daoPracticasDocumentosFiles.obtenerEntidadPorId(PracticasDocumentosFiles.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
}
