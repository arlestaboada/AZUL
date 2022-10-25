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
import pe.gob.minam.sistema.dao.IDAOPostulanteDocumentos;
import pe.gob.minam.sistema.entidades.Postulante;
import pe.gob.minam.sistema.entidades.PostulanteDocumentos;
import pe.gob.minam.sistema.servicio.IServicePostulanteDocumentos;

/**
 *
 * @author Jorge
 */
@Service
public class ServicePostulanteDocumentos implements IServicePostulanteDocumentos, Serializable{

    @Inject
    private IDAOPostulanteDocumentos daoPostulanteDocumentos;
        
    @Override
    public ResultadoServicio guardarPostulanteDocumentos(PostulanteDocumentos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    resultadoServicio.setID(daoPostulanteDocumentos.insert(correo));
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
                    Logger.getLogger(ServicePostulanteDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarPostulanteDocumentos(PostulanteDocumentos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPostulanteDocumentos.actualizar(correo);
                    resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePostulanteDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public ResultadoServicio eliminarPostulanteDocumentos(PostulanteDocumentos correo) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoPostulanteDocumentos.eliminar(correo);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (DAOException ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServicePostulanteDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<PostulanteDocumentos> listarPostulanteDocumentos() throws ServiceException {
          try {
            return new ArrayList<>(daoPostulanteDocumentos.listarTodos(PostulanteDocumentos.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }


    @Override
    public PostulanteDocumentos getPostulanteDocumentos(Integer id) throws ServiceException {
         try {
            return daoPostulanteDocumentos.obtenerEntidadPorId(PostulanteDocumentos.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
    
    
    @Override
    public List<PostulanteDocumentos> listarPostulanteDocumentos(Postulante postulante,int tipo) throws ServiceException {
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("idpostulante.idpostulante",postulante.getIdpostulante()); 
        parametros.put("tipo",tipo); 
        try {
            Collection<PostulanteDocumentos> coleccionDocumentos = daoPostulanteDocumentos.obtenerColeccionEntidadPorFiltros(PostulanteDocumentos.class,parametros);
            return (List<PostulanteDocumentos>) coleccionDocumentos;
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}
