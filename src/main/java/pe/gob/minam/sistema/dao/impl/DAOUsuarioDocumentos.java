/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.dao.IDAOUsuarioDocumentos;
import pe.gob.minam.sistema.entidades.UsuarioDocumentos;

/**
 *
 * @author Jorge
 */
@Repository("daoUsuarioDocumentos")
public class DAOUsuarioDocumentos extends DaoImpl<UsuarioDocumentos, Integer> implements IDAOUsuarioDocumentos, Serializable{

    public DAOUsuarioDocumentos() {
    }
}
