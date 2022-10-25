/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.gob.minam.common.dao.impl.DaoImpl;
import pe.gob.minam.common.service.excepcion.ServiceException;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.seguridad.dao.IDAOMenu;

/**
 *
 * @author Jorge
 */

@Repository("daoMenu")
public class DAOMenu extends DaoImpl<Menu,Integer> implements IDAOMenu{

    @Override
    public List<Menu> obtenerListaMenuPorRolPadre() throws ServiceException {
         List<Menu> listaOpciones = new ArrayList<>();
        try {
            listaOpciones = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM bd_cverde.menu where id_menu_padre is null")
                    .addEntity(Menu.class)
                    .list();
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaOpciones;
    }

    @Override
    public List<Menu> obtenerListaMenuPorRolHijo(int padre) throws ServiceException {
          List<Menu> listaOpciones = new ArrayList<>();
        try {
            listaOpciones = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM bd_cverde.menu WHERE id_menu_padre=:padre ORDER BY id ")
                    .addEntity(Menu.class)
                    .setParameter("padre", padre)
                    .list();
                    
        } catch (Exception e) {
            
        }
        return listaOpciones;
    }

    @Override
    public List<Menu> obtenerListaPermisos(int rol) throws ServiceException {
          List<Menu> listaOpciones = new ArrayList<>();
        try {
            listaOpciones = sessionFactory.getCurrentSession().createSQLQuery("SELECT m.* FROM bd_cverde.menu m inner join cverde.menu_rol mr on m.id=mr.id_menu where mr.id_rol= :rol and mr.estado_registro='A' order by id_menu_padre,id ")
                    .addEntity(Menu.class)
                    .setParameter("rol", rol)
                    .list();
                    
        } catch (Exception e) {
            
        }
        return listaOpciones;
    }
    
}
