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
import pe.gob.minam.sistema.dao.IDAOPracticas;
import pe.gob.minam.sistema.entidades.Postulante;
import pe.gob.minam.sistema.entidades.Practicas;
import pe.gob.minam.sistema.servicio.IServicePracticas;

/**
 *
 * @author Jorge
 */
@Service
public class ServicePracticas implements IServicePracticas, Serializable{

    @Inject
    private IDAOPracticas daoPracticas;
        
    @Override
    public ResultadoServicio guardarPracticas(Practicas correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoPracticas.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServicePracticas.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarPracticas(Practicas correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPracticas.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePracticas.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarPracticas(Practicas correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPracticas.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePracticas.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<Practicas> listarPracticas() throws ServiceException {
         
            return new ArrayList<>(daoPracticas.listarDepartamento());
        
    }


    @Override
    public Practicas getPracticas(Integer id) throws ServiceException {
         try {
            return daoPracticas.obtenerEntidadPorId(Practicas.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Practicas> listarPracticas(int estado) throws ServiceException {
          Map<String,Object> parametros = new HashMap<>();        
          parametros.put("estadoReg",estado); 
        try {
            Collection<Practicas> coleccion = daoPracticas.obtenerColeccionEntidadPorFiltros(Practicas.class,parametros);
            return (List<Practicas>) coleccion;
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Practicas> listarPracticas(Postulante postulante) throws ServiceException {
         Map<String,Object> parametros = new HashMap<>();        
          parametros.put("idpostulante.idpostulante",postulante.getIdpostulante()); 
        try {
            Collection<Practicas> coleccion = daoPracticas.obtenerColeccionEntidadPorFiltros(Practicas.class,parametros);
            return (List<Practicas>) coleccion;
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public List<Practicas> listarPracticasAEvaluar(String idDepartamento,String idProvincia, String idDistrito) throws ServiceException {
      return new ArrayList<>(daoPracticas.listarPracticasAEvaluar(idDepartamento, idProvincia, idDistrito));
    }
    
    @Override
     public int getValor(Integer idpractica, Integer idindicadores){
        return daoPracticas.getValor(idpractica,idindicadores);
    }
}
