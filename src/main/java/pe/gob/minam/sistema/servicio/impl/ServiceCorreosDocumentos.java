/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.dao.IDAOCorreosDocumentos;
import pe.gob.minam.sistema.servicio.IServiceCorreosDocumentos;
import pe.gob.minam.sistema.entidades.CorreosDocumentos;
import pe.gob.minam.sistema.entidades.CorreosDocumentosPK;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
@Service
public class ServiceCorreosDocumentos implements IServiceCorreosDocumentos,Serializable{
    
     @Inject
     private IDAOCorreosDocumentos daoCorreosDocumentos;

    @Override
    public ResultadoServicio guardarCorreosDocumentos(CorreosDocumentos CorreosDocumentos) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
		try {			
                        resultadoServicio.setID(daoCorreosDocumentos.insert(CorreosDocumentos));
			resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
		} catch (Exception ex) {
			resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
			resultadoServicio
					.setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
			Logger.getLogger(ServiceCorreosDocumentos.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultadoServicio;
    }

    @Override
    public ResultadoServicio actualizarCorreosDocumentos(CorreosDocumentos CorreosDocumentos) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
		try {			
                        daoCorreosDocumentos.actualizar(CorreosDocumentos);
			resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
		} catch (Exception ex) {
			resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
			resultadoServicio
					.setMensaje("Ocurrió un problema al tratar de guardar el registro. Vuelva a intentar");
			Logger.getLogger(ServiceCorreosDocumentos.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultadoServicio;
    }

    @Override
    public List<CorreosDocumentos> listarCorreosDocumentos() throws ServiceException {
        try {
            return new ArrayList<>(daoCorreosDocumentos.listarTodos(CorreosDocumentos.class));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public CorreosDocumentos getCorreosDocumentos(CorreosDocumentosPK id) throws ServiceException {
        try {
            return daoCorreosDocumentos.obtenerEntidadPorId(CorreosDocumentos.class, id);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    
   
     

//    @Override
//    public int ultimoRegistro(Long id) throws ServiceException {
//        int ultimo = 0;
//          try {
//            List<CorreosDocumentos> lista; 
//            Map<String,Object> parametros = new HashMap<>();
//            parametros.put("correosDocumentosPK.idcorreo", id);
//           
//            lista= new ArrayList<>(daoCorreosDocumentos.obtenerColeccionEntidadPorFiltros(CorreosDocumentos.class, parametros));
//            if(lista.isEmpty()){
//                ultimo=1;
//            }else{
//                ultimo=lista.get(lista.size()-1).getCorreosDocumentosPK().getIddocumento()+1;
//            }
//        } catch (DAOException ex) {
//            throw new ServiceException(ex.getMessage(), ex);
//        }
//        return ultimo;
//    }
    
    @Override
    public ResultadoServicio eliminarCorreosDocumentos(CorreosDocumentos CorreosDocumentos) {
         ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
            try {			
                    daoCorreosDocumentos.eliminar(CorreosDocumentos);
                    resultadoServicio.setMensaje("El registro se eliminó satisfactoriamente.");
            } catch (Exception ex) {
                    resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
                    resultadoServicio
                                    .setMensaje("Ocurrió un problema al tratar de eliminar el registro. Vuelva a intentar");
                    Logger.getLogger(ServiceCorreosDocumentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultadoServicio;
    }

    @Override
    public List<CorreosDocumentos> listarAdjuntos(long idcorreos) throws ServiceException {
          try {
            Map<String,Object> parametros = new HashMap<>();
            parametros.put("edbCorreosDocumentosPK.idcorreo", idcorreos);
            return new ArrayList<>(daoCorreosDocumentos.obtenerColeccionEntidadPorFiltros(CorreosDocumentos.class, parametros));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

   
}
