
package pe.gob.minam.sistema.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.dao.IDAOEvaluar;
import pe.gob.minam.sistema.entidades.Evaluar;


@SuppressWarnings({ "unchecked", "serial","rawtypes","deprecation"})
@Repository("daoEvaluar")
public class DAOEvaluar extends DaoImpl<Evaluar, Integer> implements IDAOEvaluar,Serializable{

    @Override
    public int actualizarEvaluarEstado(final int i, final Integer idpractica) {
      Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {
               
                String sql = "BEGIN BD_CVERDE.ACTUALIZAR_PRACTICA_ESTADOREG(?,?); END;";
                CallableStatement callableStatement = cnctn.prepareCall(sql);
                callableStatement.setInt(1,i);
                callableStatement.setInt(2, idpractica);               
              
                callableStatement.executeUpdate();
                
            }
        });

        return 1 ;
    }
    
    
}
