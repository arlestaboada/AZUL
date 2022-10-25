/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio;


import java.util.List;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Practicas;
import pe.gob.minam.seguridad.common.navegacion.ResultadoServicio;
import pe.gob.minam.sistema.entidades.Postulante;

/**
 *
 * @author Jorge
 */
public interface IServicePracticas {
    
    public ResultadoServicio guardarPracticas(Practicas Practicas);
    
    public ResultadoServicio actualizarPracticas(Practicas Practicas);
    
    public ResultadoServicio eliminarPracticas(Practicas Practicas);

    public List<Practicas> listarPracticas() throws ServiceException;
    
    public Practicas getPracticas(Integer id) throws ServiceException;
    
    public List<Practicas> listarPracticas(int estado) throws ServiceException;
    
    public List<Practicas> listarPracticas(Postulante postulante) throws ServiceException;
    public List<Practicas> listarPracticasAEvaluar(String idDepartamento,String idProvincia, String idDistrito) throws ServiceException;

    public int getValor(Integer idpractica, Integer idindicadores);
}
