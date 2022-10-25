/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.dao.impl;


import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.dao.IDAOCorreos;
import pe.gob.minam.sistema.entidades.Correos;

/**
 *
 * @author Jorge
 */
@SuppressWarnings({ "unchecked", "serial","rawtypes","deprecation"})
@Repository("daoCorreos")
public class DAOCorreos extends DaoImpl<Correos, BigDecimal> implements IDAOCorreos,Serializable{
   

   
    
}
