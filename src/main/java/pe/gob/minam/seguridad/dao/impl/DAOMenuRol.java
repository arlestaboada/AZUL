package pe.gob.minam.seguridad.dao.impl;
 
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.entidades.MenuRol;
import pe.gob.minam.sistema.entidades.MenuRolPK;
import pe.gob.minam.seguridad.dao.IDAOMenuRol;


@Repository("daoMenuRol")
public class DAOMenuRol extends DaoImpl<MenuRol,MenuRolPK> implements IDAOMenuRol{
    
}
