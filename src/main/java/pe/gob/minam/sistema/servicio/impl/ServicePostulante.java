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
import pe.gob.minam.sistema.dao.IDAOPostulante;
import pe.gob.minam.sistema.entidades.Postulante;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.sistema.servicio.IServicePostulante;

/**
 *
 * @author Jorge
 */
@Service
public class ServicePostulante implements IServicePostulante, Serializable{

    @Inject
    private IDAOPostulante daoPostulante;
        
    @Override
    public ResultadoServicio guardarPostulante(Postulante correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoPostulante.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServicePostulante.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarPostulante(Postulante correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPostulante.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePostulante.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarPostulante(Postulante correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPostulante.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrio un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePostulante.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<Postulante> listarPostulante() throws ServiceException {
          try {
            return new ArrayList<>(daoPostulante.listarTodos(Postulante.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }


    @Override
    public Postulante getPostulante(Integer id) throws ServiceException {
         try {
            return daoPostulante.obtenerEntidadPorId(Postulante.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Postulante getPostulante(int dni, String email) throws ServiceException {
        
        Map<String,Object> parametros = new HashMap<>();
       // parametros.put("nrodni",dni); 
        parametros.put("email",email); 
        try {
            Collection<Postulante> coleccion = daoPostulante.obtenerColeccionEntidadPorFiltros(Postulante.class,parametros);
            if(coleccion.isEmpty()){
                return null;
            }else{
                return ((List<Postulante>) coleccion).get(0);
            }
            
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public List<Postulante> listarPostulantes(){
        
        return daoPostulante.listarPostulantesProcesados();
    }
    
}
