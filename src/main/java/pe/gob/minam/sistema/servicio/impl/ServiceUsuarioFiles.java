/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio.impl;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import pe.gob.minam.common.dao.excepcion.DAOException;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.dao.IDAOUsuarioFiles;
import pe.gob.minam.sistema.servicio.IServiceUsuarioFiles;
import pe.gob.minam.sistema.entidades.UsuarioFiles;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author
 */
@Service("serviceUsuarioFiles")
public class ServiceUsuarioFiles implements IServiceUsuarioFiles, Serializable {

    @Inject
    private IDAOUsuarioFiles daoUsuarioFiles;

    @Override
    public ResultadoServicio guardarFile(UsuarioFiles segIndFile) throws ServiceException {
        ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
        try {
            resultadoServicio.setID(daoUsuarioFiles.insert(segIndFile));
            resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
        } catch (DAOException ex) {
            resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
            resultadoServicio
                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
            Logger.getLogger(ServiceUsuarioFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoServicio;
    }

    @Override
    public UsuarioFiles obtenerUsuarioFiles(Integer id) throws ServiceException {
        try {
            return daoUsuarioFiles.obtenerEntidadPorId(UsuarioFiles.class, id);
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public ResultadoServicio eliminarFile(UsuarioFiles segIndFile) throws ServiceException {
        ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
        try {
            daoUsuarioFiles.eliminar(segIndFile);
            resultadoServicio.setMensaje("El registro eliminó satisfactoriamente.");
        } catch (DAOException ex) {
            resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
            resultadoServicio
                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
            Logger.getLogger(ServiceUsuarioFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoServicio;
    }

}
