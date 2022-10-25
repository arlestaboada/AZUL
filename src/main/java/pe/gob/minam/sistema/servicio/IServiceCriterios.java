/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Criterios;

/**
 *
 * @author jmarinc
 */
public interface IServiceCriterios {
    
    public ResultadoServicio guardarCriterios(Criterios Criterios);
    
    public ResultadoServicio actualizarCriterios(Criterios Criterios);
    
    public ResultadoServicio eliminarCriterios(Criterios Criterios);

    public List<Criterios> listarCriterios() throws ServiceException;
    
    public Criterios getCriterios(Integer id) throws ServiceException;
}
