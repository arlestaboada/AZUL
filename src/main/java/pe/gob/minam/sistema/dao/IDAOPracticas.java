/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dao;

import java.util.List;
import pe.gob.minam.common.dao.IDao;
import pe.gob.minam.sistema.dto.DtoUbigeo;
import pe.gob.minam.sistema.entidades.Practicas;

/**
 *
 * @author Jorge
 */
public interface IDAOPracticas extends IDao<Practicas, Integer>{
    public List<Practicas> listarDepartamento();
     public List<Practicas> listarPracticasAEvaluar(final String idDepartamento, 
    final String idProvincia,final String idDistrito);

    public int getValor(final Integer idpractica,final Integer idindicadores);
    
}
