/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import pe.gob.minam.sistema.dao.IDAOUsuarioDocumentos;
import pe.gob.minam.sistema.servicio.IServiceUsuarioDocumentos;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.sistema.entidades.UsuarioDocumentos;
import pe.gob.minam.seguridad.common.navegacion.ESTADO_RESULTADO;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author
 */
@Service("serviceUsuarioDocumentos")
public class ServiceUsuarioDocumentos implements IServiceUsuarioDocumentos, Serializable {

    @Inject
    private IDAOUsuarioDocumentos daoUsuarioDocumentos;
    
    @Override
    public ResultadoServicio guardarUsuarioDocumento(UsuarioDocumentos usuarioDocumento) throws ServiceException {
        ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
        try {
            resultadoServicio.setID(daoUsuarioDocumentos.insert(usuarioDocumento));
            resultadoServicio.setMensaje("El registro se guardó satisfactoriamente.");
        } catch (DAOException ex) {
            resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
            resultadoServicio
                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
            Logger.getLogger(ServiceUsuarioDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoServicio;
    }

    @Override
    public List<UsuarioDocumentos> listarUsuarioDocumentos(Usuario usuario) throws ServiceException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idUsuario.id", usuario.getId());
            return new ArrayList<>(daoUsuarioDocumentos.obtenerColeccionEntidadPorFiltros(UsuarioDocumentos.class, parametros));
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public ResultadoServicio eliminarDocumento(UsuarioDocumentos usuarioDocumento) throws ServiceException {
        ResultadoServicio resultadoServicio = new ResultadoServicio(ESTADO_RESULTADO.EXITOSO);
        try {
            daoUsuarioDocumentos.eliminar(usuarioDocumento);
            resultadoServicio.setMensaje("El registro eliminó satisfactoriamente.");
        } catch (DAOException ex) {
            resultadoServicio.setEstadoResultado(ESTADO_RESULTADO.FALLIDO);
            resultadoServicio
                    .setMensaje("Ocurrió un problema al tratar de guardar el documento. Vuelva a intentar");
            Logger.getLogger(ServiceUsuarioDocumentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoServicio;
    }

}
