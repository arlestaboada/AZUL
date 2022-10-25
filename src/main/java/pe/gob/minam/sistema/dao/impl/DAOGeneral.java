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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.gob.minam.sistema.dao.IDAOGeneral;
import pe.gob.minam.sistema.dto.DtoUbigeo;

/**
 *
 * @author jmarinc
 */
@SuppressWarnings({ "unchecked", "serial","rawtypes","deprecation"})
@Repository("daoGeneral")
public class DAOGeneral  implements IDAOGeneral,Serializable{

     @Autowired
    protected SessionFactory sessionFactory;
     
    @Override
    public List<DtoUbigeo> listarDepartamento() {
        
        final List<DtoUbigeo> lista = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();        
        session.doWork(new Work() {

            @Override
            public void execute(Connection cnctn) throws SQLException {
                
                 String sql = "BEGIN BD_MINAM.USP_LISTAR_DEPARTAMENTOS(?,?); END;";
                 CallableStatement callableStatement = cnctn.prepareCall(sql);
                 callableStatement.setString(1,"");//TODOS LOS DEPARTAMENTOS
                 callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
                 callableStatement.execute();
                    
                 ResultSet rs = (ResultSet) callableStatement.getObject(2);

                while (rs.next()) {

                   DtoUbigeo ubigeo = new DtoUbigeo();
                    ubigeo.setCodigoDep(rs.getString("codi_depa_dpt"));
                    ubigeo.setNombre(rs.getString("nomb_dpto_dpt"));
                    ubigeo.setValor(rs.getString("valo_desp_dpt"));

                    lista.add(ubigeo);
                }

                rs.close();
                callableStatement.close();

            }
        });

        return lista;
    }

    @Override
    public List<DtoUbigeo> listarProvincia(final String idDepartamento) {
        final List<DtoUbigeo> lista = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();        
        session.doWork(new Work() {

            @Override
            public void execute(Connection cnctn) throws SQLException {
                
                 String sql = "BEGIN BD_MINAM.USP_LISTAR_PROVINCIAS(?,?,?); END;";
                 CallableStatement callableStatement = cnctn.prepareCall(sql);
                 callableStatement.setString(1,idDepartamento.trim());
                 callableStatement.setString(2,"");//TODAS LAS PROVINCIAS
                 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
                 callableStatement.execute();

                ResultSet rs = (ResultSet) callableStatement.getObject(3);  

                while (rs.next()) {

                   DtoUbigeo ubigeo = new DtoUbigeo();
                     ubigeo.setCodigoDep(rs.getString("codi_depa_dpt"));
                     ubigeo.setCodigoPro(rs.getString("codi_prov_tpr"));
                     ubigeo.setNombre(rs.getString("nomb_prov_tpr"));

                    lista.add(ubigeo);
                }

                rs.close();
                callableStatement.close();

            }
        });

        return lista;
    }

    @Override
    public List<DtoUbigeo> listarDistrito(final String idDepartamento, final String idProvincia) {
        
         final List<DtoUbigeo> lista = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();        
        session.doWork(new Work() {

            @Override
            public void execute(Connection cnctn) throws SQLException {
                
                 String sql = "BEGIN BD_MINAM.USP_LISTAR_DISTRITOS(?,?,?,?); END;";
                 CallableStatement callableStatement = cnctn.prepareCall(sql);
                 callableStatement.setString(1,idDepartamento);
                 callableStatement.setString(2,idProvincia);
                 callableStatement.setString(3,"");

                 callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
                 callableStatement.execute();

                 ResultSet rs = (ResultSet) callableStatement.getObject(4);
                

                while (rs.next()) {

                   DtoUbigeo ubigeo = new DtoUbigeo();
                   ubigeo.setCodigoDep(rs.getString("codi_depa_dpt"));
                   ubigeo.setCodigoPro(rs.getString("codi_prov_tpr"));
                   ubigeo.setCodigoDis(rs.getString("codi_dist_tdi"));
                   ubigeo.setNombre(rs.getString("nomb_dist_tdi"));

                    lista.add(ubigeo);
                }

                rs.close();
                callableStatement.close();

            }
        });

        return lista;
    }

    
    
}
