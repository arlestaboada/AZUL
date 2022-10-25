/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Practicas;
import pe.gob.minam.sistema.entidades.PracticasDocumentos;

/**
 *
 * @author jmarinc
 */
public interface IServicePracticasDocumentos {
    
    public ResultadoServicio guardarPracticasDocumentos(PracticasDocumentos PracticasDocumentos);
    
    public ResultadoServicio actualizarPracticasDocumentos(PracticasDocumentos PracticasDocumentos);
    
    public ResultadoServicio eliminarPracticasDocumentos(PracticasDocumentos PracticasDocumentos);

    public List<PracticasDocumentos> listarPracticasDocumentos() throws ServiceException;
    
    public PracticasDocumentos getPracticasDocumentos(Integer id) throws ServiceException;
    
     public List<PracticasDocumentos> listarPracticasDocumentos(Practicas practica,int tipo) throws ServiceException;
}
