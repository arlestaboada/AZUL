/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.minam.sistema.dao.impl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.dao.IDAOPostulante;
import pe.gob.minam.sistema.entidades.Postulante;

/**
 *
 * @author Jorge
 */
@SuppressWarnings({"unchecked", "serial", "rawtypes", "deprecation"})
@Repository("daoPostulante")
public class DAOPostulante extends DaoImpl<Postulante, Integer> implements IDAOPostulante, Serializable {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Postulante> listarPostulantesProcesados() {
        final List<Postulante> lista = new ArrayList<>();


        org.hibernate.Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {

                String sql = "BEGIN BD_CVERDE. LISTARREPORTESPOSTULANTES(?); END;";
                CallableStatement callableStatement = cnctn.prepareCall(sql);
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.execute();

                ResultSet rs = (ResultSet) callableStatement.getObject(1);

                while (rs.next()) {

                    Postulante postulante = new Postulante();
                    postulante.setDepartamento(rs.getString("NOMB_DPTO_DPT"));
                    postulante.setProvincia(rs.getString("NOMB_PROV_TPR"));
                    postulante.setDistrito(rs.getString("NOMB_DIST_TDI"));
                    postulante.setNrodni(rs.getInt("NRODNI"));
                    postulante.setNombres(rs.getString("NOMBRES"));
                    postulante.setEdad(rs.getInt("EDAD"));
                    postulante.setGenero(rs.getInt("GENERO"));
                    postulante.setTelefonos(rs.getString("TELEFONOS"));
                    postulante.setEmail(rs.getString("EMAIL"));
                    postulante.setInstEducativa(rs.getString("INST_EDUCATIVA"));
                    postulante.setApellidos(rs.getString("APELLIDOS"));
                    postulante.setCategoria(rs.getInt("CATEGORIA"));
                    postulante.setBuenasPractica(rs.getInt("TOTAL"));
                    lista.add(postulante);
                }

                rs.close();
                callableStatement.close();

            }
        });
        return lista;
    }
}
