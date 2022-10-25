/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.servicio;

import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Postulante;

/**
 *
 * @author Jorge
 */
public interface IServicePostulante {
    public List<Postulante> listarPostulantes();
    
     public ResultadoServicio guardarPostulante(Postulante Postulante);
    
    public ResultadoServicio actualizarPostulante(Postulante Postulante);
    
    public ResultadoServicio eliminarPostulante(Postulante Postulante);

    public List<Postulante> listarPostulante() throws ServiceException;
    
    public Postulante getPostulante(Integer id) throws ServiceException;
    
    public Postulante getPostulante(int dni, String email) throws ServiceException;
}
