/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.sistema.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.dao.IDAOPostulanteUsuario;
import pe.gob.minam.sistema.entidades.PostulanteUsuario;

/**
 *
 * @author Jorge
 */
@SuppressWarnings({ "unchecked", "serial","rawtypes","deprecation"})
@Repository("daoPostulanteUsuario")
public class DAOPostulanteUsuario extends DaoImpl<PostulanteUsuario, Integer> implements IDAOPostulanteUsuario,Serializable{
    
}
