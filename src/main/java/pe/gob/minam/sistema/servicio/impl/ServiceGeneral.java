/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.servicio.impl;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import pe.gob.minam.sistema.dao.IDAOCriterios;
import pe.gob.minam.sistema.dao.IDAOGeneral;
import pe.gob.minam.sistema.dto.DtoUbigeo;
import pe.gob.minam.sistema.servicio.IServiceCriterios;
import pe.gob.minam.sistema.servicio.IServiceGeneral;

/**
 *
 * @author jmarinc
 */
@Service
public class ServiceGeneral implements IServiceGeneral, Serializable{

    @Inject
    private IDAOGeneral daoGeneral;
    
    @Override
    public List<DtoUbigeo> listarDepartamento() {
        return daoGeneral.listarDepartamento();
    }

    @Override
    public List<DtoUbigeo> listarProvincia(String idDepartamento) {
        return daoGeneral.listarProvincia(idDepartamento);
    }

    @Override
    public List<DtoUbigeo> listarDistrito(String idDepartamento, String idProvincia) {
        return daoGeneral.listarDistrito(idDepartamento, idProvincia);
    }
    
}
