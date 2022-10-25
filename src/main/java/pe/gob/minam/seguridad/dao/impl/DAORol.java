package pe.gob.minam.seguridad.dao.impl;

import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.entidades.Rol;
import pe.gob.minam.seguridad.dao.IDAORol;


@Repository("daoRol")
public class DAORol extends DaoImpl<Rol,Integer> implements IDAORol{
    
}
