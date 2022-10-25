/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Indicadores;

/**
 *
 * @author jmarinc
 */
public interface IServiceIndicadores {
    
     public ResultadoServicio guardarIndicadores(Indicadores Indicadores);
    
    public ResultadoServicio actualizarIndicadores(Indicadores Indicadores);
    
    public ResultadoServicio eliminarIndicadores(Indicadores Indicadores);

    public List<Indicadores> listarIndicadores() throws ServiceException;
    
    public Indicadores getIndicadores(Integer id) throws ServiceException;
}
