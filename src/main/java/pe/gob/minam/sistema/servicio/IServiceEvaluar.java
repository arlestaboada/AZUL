/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Evaluar;

/**
 *
 * @author jmarinc
 */
public interface IServiceEvaluar {
    
    public ResultadoServicio guardarEvaluar(Evaluar evaluar);
    
    public ResultadoServicio actualizarEvaluar(Evaluar evaluar);
    
    public ResultadoServicio eliminarEvaluar(Evaluar evaluar);

    public List<Evaluar> listarEvaluar() throws ServiceException;
    
    public Evaluar getEvaluar(Integer id) throws ServiceException;

    public int actualizarEvaluarEstado(int i, Integer idpractica);
}
