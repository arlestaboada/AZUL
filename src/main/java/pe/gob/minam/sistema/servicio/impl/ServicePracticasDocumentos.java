/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.servicio.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.dao.IDAOPracticasDocumentos;
import pe.gob.minam.sistema.entidades.Practicas;
import pe.gob.minam.sistema.entidades.PracticasDocumentos;
import pe.gob.minam.sistema.servicio.IServicePracticasDocumentos;

/**
 *
 * @author Jorge
 */
@Service
public class ServicePracticasDocumentos implements IServicePracticasDocumentos, Serializable{

    @Inject
    private IDAOPracticasDocumentos daoPracticasDocumentos;
        
    @Override
    public ResultadoServicio guardarPracticasDocumentos(PracticasDocumentos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoPracticasDocumentos.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServicePracticasDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarPracticasDocumentos(PracticasDocumentos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPracticasDocumentos.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePracticasDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarPracticasDocumentos(PracticasDocumentos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPracticasDocumentos.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePracticasDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<PracticasDocumentos> listarPracticasDocumentos() throws ServiceException {
          try {
            return new ArrayList<>(daoPracticasDocumentos.listarTodos(PracticasDocumentos.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }


    @Override
    public PracticasDocumentos getPracticasDocumentos(Integer id) throws ServiceException {
         try {
            return daoPracticasDocumentos.obtenerEntidadPorId(PracticasDocumentos.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<PracticasDocumentos> listarPracticasDocumentos(Practicas practica,int tipo) throws ServiceException {
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("idpractica.idpractica",practica.getIdpractica()); 
        parametros.put("tipo",tipo); 
        try {
            Collection<PracticasDocumentos> coleccionDocumentos = daoPracticasDocumentos.obtenerColeccionEntidadPorFiltros(PracticasDocumentos.class,parametros);
            return (List<PracticasDocumentos>) coleccionDocumentos;
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
}
