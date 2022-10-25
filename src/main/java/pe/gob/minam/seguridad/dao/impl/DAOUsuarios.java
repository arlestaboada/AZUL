package pe.gob.minam.seguridad.dao.impl;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.entidades.Usuario;
import pe.gob.minam.seguridad.dao.IDAOUsuario;


@Repository("daoUsuarios")
public class DAOUsuarios extends DaoImpl<Usuario, Integer> implements IDAOUsuario, Serializable{

    public DAOUsuarios() {
    }
    
    
}
