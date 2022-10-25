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
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.sistema.dao.IDAOPracticas;
import pe.gob.minam.sistema.entidades.Evaluar;

import pe.gob.minam.sistema.entidades.Practicas;

@SuppressWarnings({"unchecked", "serial", "rawtypes", "deprecation"})
@Repository("daoPracticas")
public class DAOPracticas extends DaoImpl<Practicas, Integer> implements IDAOPracticas, Serializable {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Practicas> listarDepartamento() {

        final List<Practicas> lista = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {

                String sql = "BEGIN BD_CVERDE.MUESTRAMEDEPARTAMENTOS(?); END;";
                CallableStatement callableStatement = cnctn.prepareCall(sql);
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.execute();

                ResultSet rs = (ResultSet) callableStatement.getObject(1);

                while (rs.next()) {

                    Practicas practica = new Practicas();
                    practica.setIdpractica(rs.getInt("IDPRACTICA"));
                    practica.setTemas(rs.getInt("TEMAS"));
                    practica.setTemasOtros(rs.getString("TEMAS_OTROS"));
                    practica.setTitulo(rs.getString("TITULO"));
                    practica.setFechaIni(rs.getDate("FECHA_INI"));
                    practica.setFechaFin(rs.getDate("FECHA_FIN"));
                    practica.setNropersonas(rs.getInt("NROPERSONAS"));

                    practica.setExpDescripcion(rs.getString("EXP_Descripcion"));
                    practica.setExpResultados(rs.getString("EXP_RESULTADOS"));
                    practica.setPraProblema(rs.getString("PRA_PROBLEMA"));
                    practica.setPraComparteOtro(rs.getString("PRA_COMPARTE_OTRO"));
                    practica.setPraComparteDes(rs.getString("PRA_COMPARTE_DES"));
                    practica.setPraApoyoOtro(rs.getString("PRA_APOYO_OTRO"));
                    practica.setPraApoyoDes(rs.getString("PRA_APOYO_DES"));

                    practica.setPraMejora(rs.getString("PRA_MEJORA"));
                    practica.setPraSustento(rs.getString("PRA_SUSTENTO"));
                    practica.setEvdFoto(rs.getInt("EVD_FOTO"));
                    practica.setEvdVideo(rs.getString("EVD_VIDEO"));
                    practica.setInsReconoce(rs.getInt("INS_RECONOCE"));
                    practica.setInsInstitucion(rs.getString("INS_INSTITUCION"));
                    practica.setPersona1(rs.getString("PERSONA1"));


                    practica.setDni1(rs.getInt("DNI1"));
                    practica.setInstitucion1(rs.getString("INSTITUCION1"));

                    practica.setPersona2(rs.getString("PERSONA2"));
                    practica.setDni2(rs.getInt("DNI2"));
                    practica.setInstitucion2(rs.getString("INSTITUCION2"));

                    practica.setDepartamento(rs.getString("nomb_dpto_dpt"));
                    practica.setFechaReg(rs.getDate("fecha_reg"));
                    practica.setEstadoReg(rs.getInt("estado_reg"));
                    practica.setProvincia(rs.getString("NOMB_PROV_TPR"));
                    practica.setDistrito(rs.getString("NOMB_DIST_TDI"));
                    practica.setNombresPostulante(rs.getString("NOMBRES"));
                    practica.setApellidosPostulante(rs.getString("APELLIDOS"));
                    lista.add(practica);
                }

                rs.close();
                callableStatement.close();

            }
        });

        return lista;
    }

    @Override
    public List<Practicas> listarPracticasAEvaluar(final String idDepartamento,
            final String idProvincia, final String idDistrito) {
        final List<Practicas> lista = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {

                String sql = "BEGIN BD_CVERDE.PRACTICAS_A_EVALUAR(?,?,?,?); END;";
                CallableStatement callableStatement = cnctn.prepareCall(sql);
                callableStatement.setString(1, idDepartamento);
                callableStatement.setString(2, idProvincia);
                callableStatement.setString(3, idDistrito);
                callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
                callableStatement.execute();

                ResultSet rs = (ResultSet) callableStatement.getObject(4);

                while (rs.next()) {

                    Practicas practica = new Practicas();
                    practica.setIdpractica(rs.getInt("IDPRACTICA"));
                    practica.setTemas(rs.getInt("TEMAS"));
                    practica.setTemasOtros(rs.getString("TEMAS_OTROS"));
                    practica.setTitulo(rs.getString("TITULO"));
                    practica.setFechaIni(rs.getDate("FECHA_INI"));
                    practica.setFechaFin(rs.getDate("FECHA_FIN"));
                    practica.setNropersonas(rs.getInt("NROPERSONAS"));

                    practica.setExpDescripcion(rs.getString("EXP_Descripcion"));
                    practica.setExpResultados(rs.getString("EXP_RESULTADOS"));
                    practica.setPraProblema(rs.getString("PRA_PROBLEMA"));
                    practica.setPraComparteOtro(rs.getString("PRA_COMPARTE_OTRO"));
                    practica.setPraComparteDes(rs.getString("PRA_COMPARTE_DES"));
                    practica.setPraApoyoOtro(rs.getString("PRA_APOYO_OTRO"));
                    practica.setPraApoyoDes(rs.getString("PRA_APOYO_DES"));

                    practica.setPraMejora(rs.getString("PRA_MEJORA"));
                    practica.setPraSustento(rs.getString("PRA_SUSTENTO"));
                    practica.setEvdFoto(rs.getInt("EVD_FOTO"));
                    practica.setEvdVideo(rs.getString("EVD_VIDEO"));
                    practica.setInsReconoce(rs.getInt("INS_RECONOCE"));
                    practica.setInsInstitucion(rs.getString("INS_INSTITUCION"));
                    practica.setPersona1(rs.getString("PERSONA1"));


                    practica.setDni1(rs.getInt("DNI1"));
                    practica.setInstitucion1(rs.getString("INSTITUCION1"));

                    practica.setPersona2(rs.getString("PERSONA2"));
                    practica.setDni2(rs.getInt("DNI2"));
                    practica.setInstitucion2(rs.getString("INSTITUCION2"));

                    practica.setDepartamento(rs.getString("nomb_dpto_dpt"));
                    practica.setFechaReg(rs.getDate("fecha_reg"));
                    practica.setEstadoReg(rs.getInt("estado_reg"));
                    practica.setProvincia(rs.getString("NOMB_PROV_TPR"));
                    practica.setDistrito(rs.getString("NOMB_DIST_TDI"));
                    practica.setNombresPostulante(rs.getString("NOMBRES"));
                    practica.setApellidosPostulante(rs.getString("APELLIDOS"));
                    lista.add(practica);
                }

                rs.close();
                callableStatement.close();

            }
        });

        return lista;
    }

    @Override
    public int getValor(final Integer idpractica, final Integer idindicadores) {
         final Evaluar evaluar=new Evaluar(); 
        Session session = sessionFactory.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection cnctn) throws SQLException {
               
                String sql = "BEGIN BD_CVERDE.VALOR_PRACTICA_A_EVALUAR(?,?,?); END;";
                CallableStatement callableStatement = cnctn.prepareCall(sql);
                callableStatement.setInt(1, idpractica);
                callableStatement.setInt(2, idindicadores);               
                callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
                callableStatement.execute();
                ResultSet rs = (ResultSet) callableStatement.getObject(3); 
                   while (rs.next()) {
                    
                       evaluar.setValor(rs.getInt("VALOR"));
                   }
                
            }
        });

        return evaluar.getValor() ;
    }
}