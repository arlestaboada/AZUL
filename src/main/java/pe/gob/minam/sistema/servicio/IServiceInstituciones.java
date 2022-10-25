/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Instituciones;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;

/**
 *
 * @author Jorge
 */
public interface IServiceInstituciones {
    
    public ResultadoServicio guardarInstituciones(Instituciones instituciones) throws ServiceException;
    
    public ResultadoServicio actualizarInstituciones(Instituciones instituciones) throws ServiceException;
    
    public List<Instituciones> listarInstituciones() throws ServiceException;
    
    public Instituciones getInstituciones(int idInstituciones)throws ServiceException; 

    public List<Instituciones> listarInstitucionesNotificar() throws ServiceException;
    
}
