/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.dao;

import java.util.List;
import pe.gob.minam.sistema.dto.DtoUbigeo;

/**
 *
 * @author jmarinc
 */
public interface IDAOGeneral{
    
    public List<DtoUbigeo> listarDepartamento();
    public List<DtoUbigeo> listarProvincia(String idDepartamento);
    public List<DtoUbigeo> listarDistrito(String idDepartamento,String idProvincia);
}
