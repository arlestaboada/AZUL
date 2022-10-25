/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.minam.seguridad.controlador.backingBean;


import java.util.List;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.gob.minam.sistema.entidades.Menu;
import pe.gob.minam.sistema.entidades.MenuRol;

/**
 *
 * @author Jorge
 */
@Named("BKMenuRol")
@Scope("session")
public class BKMenuRol {
   
    private MenuRol menurolFormulario;
    private List<MenuRol> listaMenuRol;
    private List<Menu> listaMenu;
    private List<Menu> listaMenuSeleccionados;

    
    public MenuRol getMenurolFormulario() {
        return menurolFormulario;
    }

    public void setMenurolFormulario(MenuRol menurolFormulario) {
        this.menurolFormulario = menurolFormulario;
    }

    public List<MenuRol> getListaMenuRol() {
        return listaMenuRol;
    }

    public void setListaMenuRol(List<MenuRol> listaMenuRol) {
        this.listaMenuRol = listaMenuRol;
    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public List<Menu> getListaMenuSeleccionados() {
        return listaMenuSeleccionados;
    }

    public void setListaMenuSeleccionados(List<Menu> listaMenuSeleccionados) {
        this.listaMenuSeleccionados = listaMenuSeleccionados;
    }
    
    
}
